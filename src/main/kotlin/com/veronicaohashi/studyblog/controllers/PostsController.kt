package com.veronicaohashi.studyblog.controllers

import com.veronicaohashi.studyblog.controllers.mappers.PostMapper
import com.veronicaohashi.studyblog.controllers.requests.PostRequest
import com.veronicaohashi.studyblog.controllers.response.PostResponse
import com.veronicaohashi.studyblog.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
  fun getAll(): ResponseEntity<List<PostResponse>?> {
    val posts = postService.getAll()

    return if (posts.isNullOrEmpty()) {
      ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    } else {
      ResponseEntity.ok(posts.map { postMapper.toResponse(it) })
    }
  }
}
