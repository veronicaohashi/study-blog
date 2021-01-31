package com.veronicaohashi.studyblog.controllers

import com.veronicaohashi.studyblog.controllers.mappers.CategoryMapper
import com.veronicaohashi.studyblog.controllers.requests.CategoryRequest
import com.veronicaohashi.studyblog.controllers.response.CategoryResponse
import com.veronicaohashi.studyblog.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping

@Controller
@RequestMapping("categories")
class CategoriesController(
    private val service: CategoryService,
    private val categoryMapper: CategoryMapper
) {

  @PostMapping
  fun create(@RequestBody category: CategoryRequest): ResponseEntity<CategoryResponse> = service
      .create(categoryMapper.toDomain(category))
      .let { ResponseEntity(categoryMapper.toResponse(it), HttpStatus.CREATED) }

  @GetMapping
  fun getAll(): ResponseEntity<List<CategoryResponse>?> {
    val categories = service.getAll()

    return if (categories.isNullOrEmpty()) {
      ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    } else {
      ResponseEntity.ok(categories.map { categoryMapper.toResponse(it) })
    }
  }
}
