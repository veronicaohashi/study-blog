package com.veronicaohashi.studyblog.controllers.handlers

import com.veronicaohashi.studyblog.domain.exception.BusinessException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import kotlin.math.log

@ControllerAdvice
class ExceptionHandler {

  private val logger = LoggerFactory.getLogger(javaClass)

  @ExceptionHandler(BusinessException::class)
  fun onBusinessRuleException(exception: BusinessException): ResponseEntity<ApiError> {
    logger.warn(exception.message, exception)

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(
            ApiError(
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                title = "Business Request Exception",
                details = exception.message ?: "",
                developerMessage = exception.javaClass.name
            )
        )
  }

}