package org.example.authservice.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val errors: List<String> = ex.bindingResult.fieldErrors.map { error ->
            error.defaultMessage ?: "Invalid value"
        }
        return ResponseEntity(mapOf("message" to "Validation failed", "errors" to errors), HttpStatus.BAD_REQUEST)
    }
}
