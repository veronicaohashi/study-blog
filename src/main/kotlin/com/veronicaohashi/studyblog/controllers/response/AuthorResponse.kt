package com.veronicaohashi.studyblog.controllers.response

import java.util.UUID

data class AuthorResponse(
    val id: UUID,
    val name: String,
    val about: String?
)
