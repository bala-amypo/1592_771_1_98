// package com.example.demo.exception;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.web.context.request.WebRequest;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<?> handleMethodArgumentNotValid(
//             MethodArgumentNotValidException ex) {

//         Map<String, String> errors = new HashMap<>();

//         ex.getBindingResult().getFieldErrors().forEach(error -> {
//             errors.put(error.getField(), error.getDefaultMessage());
//         });

//         return ResponseEntity.status(400)
//                 .body(Map.of("timestamp", LocalDateTime.now(),"errors", errors));
//     }

//     @ExceptionHandler(ResourceNotFoundException.class)
//     public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex, WebRequest req) {
//         return ResponseEntity.status(404)
//                 .body(Map.of("timestamp", LocalDateTime.now(), "message", ex.getMessage()));
//     }

//     @ExceptionHandler(ValidationException.class)
//     public ResponseEntity<?> handleValidation(ValidationException ex) {
//         return ResponseEntity.status(400)
//                 .body(Map.of("timestamp", LocalDateTime.now(), "message", ex.getMessage()));
//     }

//     @ExceptionHandler(IllegalArgumentException.class)
//     public ResponseEntity<?> handleBadRequest(IllegalArgumentException ex) {
//         return ResponseEntity.status(400)
//                 .body(Map.of("timestamp", LocalDateTime.now(), "message", ex.getMessage()));
//     }

// }





// package com.example.demo.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import java.util.HashMap;
// import java.util.Map;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     /* ------------------------------
//      * 404 - Resource Not Found
//      * ------------------------------ */
//     @ExceptionHandler(ResourceNotFoundException.class)
//     public ResponseEntity<Map<String, String>> handleResourceNotFound(
//             ResourceNotFoundException ex) {

//         Map<String, String> body = new HashMap<>();
//         body.put("error", ex.getMessage());

//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
//     }

//     /* ------------------------------
//      * 400 - Validation Errors
//      * ------------------------------ */
//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<Map<String, String>> handleValidation(
//             MethodArgumentNotValidException ex) {

//         Map<String, String> body = new HashMap<>();
//         body.put("error", "Validation failed");

//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
//     }

//     /* ------------------------------
//      * 401 - Authentication Errors
//      * ------------------------------ */
//     @ExceptionHandler(AuthenticationException.class)
//     public ResponseEntity<Map<String, String>> handleAuthentication(
//             AuthenticationException ex) {

//         Map<String, String> body = new HashMap<>();
//         body.put("error", "Authentication failed");

//         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
//     }

//     /* ------------------------------
//      * 403 - Authorization Errors
//      * ------------------------------ */
//     @ExceptionHandler(AccessDeniedException.class)
//     public ResponseEntity<Map<String, String>> handleAccessDenied(
//             AccessDeniedException ex) {

//         Map<String, String> body = new HashMap<>();
//         body.put("error", "Access denied");

//         return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
//     }

//     /* ------------------------------
//      * 500 - Any Other Exception
//      * ------------------------------ */
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<Map<String, String>> handleOther(Exception ex) {

//         Map<String, String> body = new HashMap<>();
//         body.put("error", "Internal server error");

//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
//     }
// }









package com.example.demo.exception;

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

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ------------------------------
     * 404 - Resource Not Found
     * ------------------------------ */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /* ------------------------------
     * 400 - Validation Errors
     * ------------------------------ */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        // Collect all field errors
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        body.put("error", "Validation failed");
        body.put("details", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /* ------------------------------
     * 401 - Authentication Errors
     * ------------------------------ */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthentication(AuthenticationException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Authentication failed: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    /* ------------------------------
     * 403 - Authorization Errors
     * ------------------------------ */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Access denied: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    /* ------------------------------
     * 500 - Any Other Exception
     * ------------------------------ */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleOther(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Internal server error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
