package com.veronicaohashi.studyblog.controllers

import com.veronicaohashi.studyblog.controllers.mappers.AuthorMapper
import com.veronicaohashi.studyblog.controllers.requests.AuthorRequest
import com.veronicaohashi.studyblog.domain.model.Author
import com.veronicaohashi.studyblog.service.AuthorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
@RequestMapping("authors")
class AuthorsController(
    private val authorService: AuthorService,
    private val authorMapper: AuthorMapper
) {

 @PostMapping
 fun create(@RequestBody author: AuthorRequest): ResponseEntity<Author> = authorService
     .create(authorMapper.toDomain(author))
     .let { ResponseEntity(it, HttpStatus.CREATED) }
}
