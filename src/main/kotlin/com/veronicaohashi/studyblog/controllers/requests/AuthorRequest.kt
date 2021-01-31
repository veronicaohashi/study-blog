package com.veronicaohashi.studyblog.controllers.requests

import javax.validation.constraints.NotBlank

data class AuthorRequest(
    @NotBlank
    val name: String,
    val about: String? = null
)
