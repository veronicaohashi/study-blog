package com.veronicaohashi.studyblog.controllers

import com.veronicaohashi.studyblog.controllers.mappers.CategoryMapper
import com.veronicaohashi.studyblog.controllers.requests.CategoryRequest
import com.veronicaohashi.studyblog.controllers.response.CategoryResponse
import com.veronicaohashi.studyblog.service.CategoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("categories")
class CategoriesController(
    private val service: CategoryService,
    private val categoryMapper: CategoryMapper
) {

  @PostMapping
  fun create(@Valid @RequestBody category: CategoryRequest): ResponseEntity<CategoryResponse> = service
      .create(categoryMapper.toDomain(category))
      .let { ResponseEntity(categoryMapper.toResponse(it), HttpStatus.CREATED) }

  @GetMapping
  fun getAll(pageable: Pageable): ResponseEntity<Page<CategoryResponse>> = ResponseEntity
      .ok(service.getAll(pageable).map { categoryMapper.toResponse(it) })
}
