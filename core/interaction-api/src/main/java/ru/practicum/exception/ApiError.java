package ru.practicum.exception;

import java.time.LocalDateTime;

public record ApiError(String status, String reason, String message, LocalDateTime timestamp) {
}

/* NEW
public record ApiError(String path, String httpMethod, int statusCode, String error, String message) {
}*/