package com.veronicaohashi.studyblog.service

import com.veronicaohashi.studyblog.domain.model.Category
import com.veronicaohashi.studyblog.infrastrucure.repository.CategoryRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

  private val logger = LoggerFactory.getLogger(javaClass)

  fun create(category: Category): Category {
    logger.info("Creating category ${category.name}")
    return categoryRepository.save(category)
  }

  fun getAll(pageable: Pageable): Page<Category> {
    logger.info("Getting all categories")
    return categoryRepository.findAll(pageable)
  }
}
