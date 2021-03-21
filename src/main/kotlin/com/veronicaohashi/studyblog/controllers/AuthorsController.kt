package com.veronicaohashi.studyblog.controllers

import com.veronicaohashi.studyblog.controllers.mappers.AuthorMapper
import com.veronicaohashi.studyblog.controllers.requests.AuthorRequest
import com.veronicaohashi.studyblog.controllers.response.AuthorResponse
import com.veronicaohashi.studyblog.service.AuthorService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.util.UUID
import javax.validation.Valid

@Api(tags = ["Authors"])
@RestController
@RequestMapping("authors")
class AuthorsController(
    private val authorService: AuthorService,
    private val authorMapper: AuthorMapper
) {

  @ApiOperation(value = "Create author")
  @PostMapping
  fun create(@Valid @RequestBody author: AuthorRequest): ResponseEntity<AuthorResponse> = authorService
      .create(authorMapper.toDomain(author))
      .let { ResponseEntity(authorMapper.toResponse(it), HttpStatus.CREATED) }

  @ApiOperation(value = "Get author by id")
  @GetMapping("{id}")
  fun getById(@PathVariable id: UUID): ResponseEntity<AuthorResponse> = authorService
      .getById(id)
      .let { ResponseEntity.ok(authorMapper.toResponse(it!!)) }

  @ApiOperation(value = "Get all conciliators without query params")
  @GetMapping
  fun getAll(): ResponseEntity<List<AuthorResponse>?> {
    val authors = authorService.getAll()
    return if (authors.isNullOrEmpty()) {
      ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    } else {
      ResponseEntity.ok(authors.map { authorMapper.toResponse(it) })
    }
  }

  @ApiOperation(value = "Update author")
  @PutMapping("{id}")
  fun update(
      @PathVariable id: UUID,
      @Valid @RequestBody author: AuthorRequest
  ): ResponseEntity<AuthorResponse> = authorService
      .update(id, authorMapper.toDomain(author))
      .let { ResponseEntity.ok(authorMapper.toResponse(it!!)) }

  @ApiOperation(value = "Delete author")
  @DeleteMapping("{id}")
  fun delete(@PathVariable id: UUID): ResponseEntity<Unit> = authorService
      .delete(id)
      .let { ResponseEntity<Unit>(HttpStatus.OK) }
}
