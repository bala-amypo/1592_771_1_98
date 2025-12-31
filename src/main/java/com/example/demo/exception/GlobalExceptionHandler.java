

package com.example.demo.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ----------------------------------------
     * 400 – Invalid Request Body (@Valid DTO)
     * ---------------------------------------- */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", 400,
                "error", "Invalid input",
                "details", errors
        ));
    }

    /* ----------------------------------------
     * 400 – Entity validation (@Entity rules)
     * ---------------------------------------- */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(
            ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> v : violations) {
            errors.put(v.getPropertyPath().toString(), v.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", 400,
                "error", "Validation failed",
                "details", errors
        ));
    }

    /* ----------------------------------------
     * 404 – Custom Not Found
     * ---------------------------------------- */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "status", 404,
                "error", ex.getMessage()
        ));
    }

    /* ----------------------------------------
     * 401 – Authentication failure
     * ---------------------------------------- */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuth(
            AuthenticationException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "status", 401,
                "error", "Authentication failed",
                "message", ex.getMessage()
        ));
    }

    /* ----------------------------------------
     * 403 – Authorization failure
     * ---------------------------------------- */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(
            AccessDeniedException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                "status", 403,
                "error", "Access denied"
        ));
    }

    /* ----------------------------------------
     * 400 – Business logic errors
     * (IMPORTANT: keeps RuntimeException behavior)
     * ---------------------------------------- */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(
            RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", 400,
                "error", ex.getMessage()
        ));
    }

    /* ----------------------------------------
     * 500 – Fallback
     * ---------------------------------------- */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOther(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", 500,
                "error", "Internal server error"
        ));
    }
}
