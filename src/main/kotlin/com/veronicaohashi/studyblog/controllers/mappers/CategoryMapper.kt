package com.veronicaohashi.studyblog.controllers.mappers

import com.veronicaohashi.studyblog.controllers.requests.CategoryRequest
import com.veronicaohashi.studyblog.controllers.response.CategoryResponse
import com.veronicaohashi.studyblog.domain.model.Category
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoryMapper {

  fun toDomain(categoryRequest: CategoryRequest) = Category(
      id = UUID.randomUUID(),
      name = categoryRequest.name
  )

  fun toResponse(category: Category) = CategoryResponse(
      id = UUID.randomUUID(),
      name = category.name
  )
}