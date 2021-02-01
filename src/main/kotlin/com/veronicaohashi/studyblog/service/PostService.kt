package com.veronicaohashi.studyblog.service

import com.veronicaohashi.studyblog.controllers.requests.PostRequest
import com.veronicaohashi.studyblog.domain.exception.AuthorNotFoundException
import com.veronicaohashi.studyblog.domain.exception.CategoryNotFoundException
import com.veronicaohashi.studyblog.domain.model.Post
import com.veronicaohashi.studyblog.infrastrucure.repository.AuthorRepository
import com.veronicaohashi.studyblog.infrastrucure.repository.CategoryRepository
import com.veronicaohashi.studyblog.infrastrucure.repository.PostRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PostService(
    private val postRepository: PostRepository,
    private val authorRepository: AuthorRepository,
    private val categoryRepository: CategoryRepository
) {

  private val logger = LoggerFactory.getLogger(javaClass)

  fun create(post: PostRequest): Post {
    val author = authorRepository.findById(post.authorId)
        .orElseThrow { throw AuthorNotFoundException(post.authorId) }

    val categories = categoryRepository.findAllById(post.categoryIds)

    if (categories.isNullOrEmpty()) {
      throw CategoryNotFoundException(post.categoryIds)
    }

    val postToCreate = Post(
        id = UUID.randomUUID(),
        title = post.title,
        subtitle = post.subtitle,
        content = post.content,
        author = author,
        categories = categories
      )

    return postRepository.save(postToCreate)
  }

  fun getAll(): List<Post>? = postRepository.findAll()
}
