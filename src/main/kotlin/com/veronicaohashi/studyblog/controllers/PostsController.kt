package com.veronicaohashi.studyblog.controllers

import com.veronicaohashi.studyblog.controllers.mappers.PostMapper
import com.veronicaohashi.studyblog.controllers.requests.PostRequest
import com.veronicaohashi.studyblog.controllers.response.PostResponse
import com.veronicaohashi.studyblog.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

@Controller
@RequestMapping("posts")
class PostsController(
    private val postService: PostService,
    private val postMapper: PostMapper
) {

  @PostMapping
  fun create(@Valid @RequestBody post: PostRequest): ResponseEntity<PostResponse> = postService
      .create(post)
      .let { ResponseEntity(postMapper.toResponse(it), HttpStatus.CREATED) }

  @GetMapping
  fun getAll(
      @RequestParam(value = "title", required = false, defaultValue = "") title: String?,
      pageable: Pageable
  ): ResponseEntity<Page<PostResponse>> = ResponseEntity
      .ok(postService.getAll(title, pageable).map { postMapper.toResponse(it) })
}
