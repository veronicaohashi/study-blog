package com.veronicaohashi.studyblog.controllers.mappers

import com.veronicaohashi.studyblog.controllers.requests.AuthorRequest
import com.veronicaohashi.studyblog.controllers.response.AuthorResponse
import com.veronicaohashi.studyblog.domain.model.Author
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class AuthorMapper {

  fun toDomain(authorRequest: AuthorRequest) = Author(
      id = UUID.randomUUID(),
      name = authorRequest.name,
      about = authorRequest.about
  )

  fun toResponse(author: Author) = AuthorResponse(
      id = author.id,
      name = author.name,
      about = author.about
  )
}
