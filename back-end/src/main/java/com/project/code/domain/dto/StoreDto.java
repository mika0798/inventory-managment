package com.project.code.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StoreDto (
    @NotBlank(message="Store name cannot be blank")
    @NotNull(message="Store name cannot be null")
    String name,

    @NotBlank(message="Address cannot be blank")
    @NotNull(message="Address cannot be null")
    String address
) {}
