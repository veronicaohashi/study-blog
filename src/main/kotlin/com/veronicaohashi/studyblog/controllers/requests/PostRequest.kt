package com.veronicaohashi.studyblog.controllers.requests

import java.util.UUID
import javax.validation.constraints.NotEmpty

data class PostRequest(
    @field:NotEmpty(message = "The post title can not be empty")
    val title: String,
    @field:NotEmpty(message = "The post subtitle can not be empty")
    val subtitle: String,
    @field:NotEmpty(message = "The post content can not be empty")
    val content: String,
    @field:NotEmpty(message = "The post should be associated with an author")
    val authorId: UUID,
    @field:NotEmpty(message = "The post should be associated with one or more categories")
    val categoryIds: List<UUID>
)
