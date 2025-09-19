package io.github.umbrellaleaf5.tasks_organizer.handler

import io.github.umbrellaleaf5.tasks_organizer.exception.BadRequestException
import io.github.umbrellaleaf5.tasks_organizer.exception.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

  private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

  @ExceptionHandler(NotFoundException::class)
  fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ErrorResponse> {
    logger.warn("Not found: {}", ex.message)
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(ErrorResponse("Not Found", ex.message ?: "Resource not found"))
  }

  @ExceptionHandler(BadRequestException::class)
  fun handleBadRequestException(ex: BadRequestException): ResponseEntity<ErrorResponse> {
    logger.warn("Bad request: {}", ex.message)
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(ErrorResponse("Bad Request", ex.message ?: "Invalid request"))
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
    val errors = ex.bindingResult.fieldErrors.joinToString {
      "${it.field}: ${it.defaultMessage}"
    }
    logger.warn("Validation error: {}", errors)
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(ErrorResponse("Validation Error", errors))
  }

  @ExceptionHandler(DataAccessException::class)
  fun handleDataAccessException(ex: DataAccessException): ResponseEntity<ErrorResponse> {
    logger.error("Database error: {}", ex.message, ex)
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(ErrorResponse("Database Error", "An error occurred while accessing the database"))
  }

  @ExceptionHandler(Exception::class)
  fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
    logger.error("Unexpected error: {}", ex.message, ex)
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(ErrorResponse("Internal Server Error", "An unexpected error occurred"))
  }
}

data class ErrorResponse(
  val error: String,
  val message: String
)