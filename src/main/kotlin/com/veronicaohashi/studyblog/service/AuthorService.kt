package com.veronicaohashi.studyblog.service

import com.veronicaohashi.studyblog.domain.exception.AuthorAlreadyExistsException
import com.veronicaohashi.studyblog.domain.exception.AuthorNotFoundException
import com.veronicaohashi.studyblog.domain.model.Author
import com.veronicaohashi.studyblog.infrastrucure.repository.AuthorRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthorService(private val repository: AuthorRepository) {

  private val logger = LoggerFactory.getLogger(javaClass)

  fun create(author: Author): Author = repository.findByName(author.name)
      ?.let { throw AuthorAlreadyExistsException(it) }
      ?: repository.save(author).also { logger.info("Creating author $author") }

  fun getById(id: UUID): Author? = repository.findById(id)
      .also { logger.info("Getting author $id") }
      .orElseThrow { throw AuthorNotFoundException(id) }

  fun update(id: UUID, author: Author): Author? = getById(id)
      ?.let {
        logger.info("Updating author $it")
        val updatedAuthor = it.copy(
            name = author.name,
            about = author.about
        )
        repository.save(updatedAuthor)
      }

  fun delete(id: UUID) = getById(id)
      ?.let {
        logger.info("Deleting author $it")
        repository.delete(it)
      }

  fun getAll(): List<Author>? = repository.findAll()
}
