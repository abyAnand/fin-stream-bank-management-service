package com.finStream.bankmanagementservice.advice;

import com.finStream.bankmanagementservice.dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Catches and handles all exceptions that are not explicitly handled by other exception handlers.
     * Logs the exception details and returns a standardized error response.
     *
     * @param ex The exception that was thrown.
     * @param request Provides context about the web request during which the exception occurred.
     * @return A ResponseEntity containing details about the error, the HTTP status, and a user-friendly error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("An error occurred: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                LocalDate.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An internal error occurred",
                request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
