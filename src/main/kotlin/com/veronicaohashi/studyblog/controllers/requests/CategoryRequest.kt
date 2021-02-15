package com.veronicaohashi.studyblog.controllers.requests

import javax.validation.constraints.NotEmpty

data class CategoryRequest(
    @field:NotEmpty(message = "The category name can not be empty")
    val name: String
)
