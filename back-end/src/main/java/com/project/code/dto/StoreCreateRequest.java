package com.project.code.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreCreateRequest {
    @NotBlank(message="Store name cannot be blank")
    @NotNull(message="Store name cannot be null")
    private String name;

    @NotBlank(message="Address cannot be blank")
    @NotNull(message="Address cannot be null")
    private String address;
}
