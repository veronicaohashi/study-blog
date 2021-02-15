package com.veronicaohashi.studyblog.controllers.handlers

import com.veronicaohashi.studyblog.domain.exception.BusinessException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

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

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun onValidationException(exception: MethodArgumentNotValidException): ResponseEntity<ApiError> {
    logger.warn(exception.message, exception)

    val details: MutableList<ApiError.Details> = mutableListOf()

    exception.bindingResult.fieldErrors.forEach {
      val fieldName = it.field
      val fieldErrorMessage = it.defaultMessage

      details.add(ApiError.Details(target = fieldName, message = fieldErrorMessage))
    }

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(
            ApiError(
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                title = "Business Request Exception, invalid fields",
                details = exception.message ?: "",
                developerMessage = exception.javaClass.name,
                fieldDetails = details
            )
        )
  }

  @ExceptionHandler(Exception::class)
  fun onInternalException(exception: Exception): ResponseEntity<ApiError> {
    logger.warn(exception.message, exception)

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ApiError(
                timestamp = LocalDateTime.now(),
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                title = "Internal Server Error",
                details = exception.message ?: "",
                developerMessage = exception.javaClass.name
            )
        )
  }
}
