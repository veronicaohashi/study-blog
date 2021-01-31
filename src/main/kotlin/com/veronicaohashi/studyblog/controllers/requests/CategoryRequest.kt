package com.veronicaohashi.studyblog.controllers.requests

import javax.validation.constraints.NotBlank

data class CategoryRequest(
    @NotBlank
    val name: String
)
