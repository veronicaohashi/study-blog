package com.veronicaohashi.studyblog.controllers.handlers

import java.time.LocalDateTime

data class ApiError(
    val title: String,
    val status: Int,
    val details: String,
    val developerMessage: String,
    val timestamp: LocalDateTime,
    val fieldDetails: List<Details>? = emptyList()
) {
  data class Details(val target: String, val message: String? = "")
}
