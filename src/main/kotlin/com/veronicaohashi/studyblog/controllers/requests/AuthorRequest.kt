package com.veronicaohashi.studyblog.controllers.requests

data class AuthorRequest(
    val name: String,
    val about: String? = null
)
