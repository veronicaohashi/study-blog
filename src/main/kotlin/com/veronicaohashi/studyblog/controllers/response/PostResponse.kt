package com.veronicaohashi.studyblog.controllers.response

import com.veronicaohashi.studyblog.domain.model.Author
import com.veronicaohashi.studyblog.domain.model.Category
import java.time.LocalDateTime
import java.util.UUID

data class PostResponse(
    val id: UUID,
    val title: String,
    val subtitle: String,
    val content: String,
    val written_at: LocalDateTime,
    val author: Author,
    val categories: List<Category>
)
