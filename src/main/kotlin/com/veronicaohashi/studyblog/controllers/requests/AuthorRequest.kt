package com.veronicaohashi.studyblog.controllers.requests

import javax.validation.constraints.NotNull

data class AuthorRequest(
    @field:NotNull(message = "The author name can not be null")
    val name: String,
    val about: String? = null
)
