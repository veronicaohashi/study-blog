package com.veronicaohashi.studyblog.service

import com.veronicaohashi.studyblog.domain.model.Author
import com.veronicaohashi.studyblog.infrastrucure.repository.AuthorRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AuthorService(private val repository: AuthorRepository) {

  private val logger = LoggerFactory.getLogger(javaClass)

  fun create(author: Author): Author {
    return repository.save(author)
  }
}
