package com.project.code.security.dto;

public record LoginRequest(
        String username,
        String password
) {
}
