package com.project.code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record ApiResponse<T> (
        String status,
        String message,
        T data
) {}
