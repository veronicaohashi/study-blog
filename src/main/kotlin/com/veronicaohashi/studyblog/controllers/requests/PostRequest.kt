package com.veronicaohashi.studyblog.controllers.requests

import java.util.UUID
import javax.validation.constraints.NotBlank

data class PostRequest(
    @NotBlank
    val title: String,
    @NotBlank
    val subtitle: String,
    @NotBlank
    val content: String,
    @NotBlank
    val authorId: UUID,
    @NotBlank
    val categoryIds: List<UUID>
)
