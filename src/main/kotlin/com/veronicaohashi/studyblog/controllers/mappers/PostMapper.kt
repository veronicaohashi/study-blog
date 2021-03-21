package com.veronicaohashi.studyblog.controllers.mappers

import com.veronicaohashi.studyblog.controllers.response.PostResponse
import com.veronicaohashi.studyblog.domain.model.Post
import org.springframework.stereotype.Component

@Component
class PostMapper {

  fun toResponse(post: Post) = PostResponse(
      id = post.id,
      title = post.title,
      subtitle = post.subtitle,
      content = post.content,
      writtenAt = post.writtenAt!!,
      author = post.author,
      categories = post.categories
  )
}
