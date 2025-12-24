package com.project.code.domain;

public record ApiResponse<T> (
        String status,
        String message,
        T data
) {}
