package com.veronicaohashi.studyblog.controllers

import com.veronicaohashi.studyblog.controllers.mappers.AuthorMapper
import com.veronicaohashi.studyblog.controllers.requests.AuthorRequest
import com.veronicaohashi.studyblog.controllers.response.AuthorResponse
import com.veronicaohashi.studyblog.service.AuthorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.util.UUID

@Controller
@RequestMapping("authors")
class AuthorsController(
    private val authorService: AuthorService,
    private val authorMapper: AuthorMapper
) {

  @PostMapping
  fun create(@RequestBody author: AuthorRequest): ResponseEntity<AuthorResponse> = authorService
      .create(authorMapper.toDomain(author))
      .let { ResponseEntity(authorMapper.toResponse(it), HttpStatus.CREATED) }

  @GetMapping("{id}")
  fun getById(@PathVariable id: UUID): ResponseEntity<AuthorResponse> = authorService
      .getById(id)
      .let { ResponseEntity.ok(authorMapper.toResponse(it!!)) }

  @GetMapping
  fun getAll(): ResponseEntity<List<AuthorResponse>?> {
    val authors = authorService.getAll()
    return if (authors.isNullOrEmpty()) {
      ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    } else {
      ResponseEntity.ok(authors.map { authorMapper.toResponse(it) })
    }
  }

  @PutMapping("{id}")
  fun update(
      @PathVariable id: UUID,
      @RequestBody author: AuthorRequest
  ): ResponseEntity<AuthorResponse> = authorService
      .update(id, authorMapper.toDomain(author))
      .let { ResponseEntity.ok(authorMapper.toResponse(it!!)) }

  @DeleteMapping("{id}")
  fun delete(@PathVariable id: UUID): ResponseEntity<Unit> = authorService
      .delete(id)
      .let { ResponseEntity<Unit>(HttpStatus.OK) }
}
