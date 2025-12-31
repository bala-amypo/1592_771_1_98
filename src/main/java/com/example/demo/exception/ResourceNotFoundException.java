package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    /* Convenience static factories (OPTIONAL, SAFE) */

    public static ResourceNotFoundException user() {
        return new ResourceNotFoundException("User not found");
    }

    public static ResourceNotFoundException course() {
        return new ResourceNotFoundException("Course not found");
    }

    public static ResourceNotFoundException lesson() {
        return new ResourceNotFoundException("Lesson not found");
    }

    public static ResourceNotFoundException progress() {
        return new ResourceNotFoundException("Progress not found");
    }

    public static ResourceNotFoundException recommendation() {
        return new ResourceNotFoundException("Recommendation not found");
    }

    public static ResourceNotFoundException instructor() {
        return new ResourceNotFoundException("Instructor not found");
    }
}
