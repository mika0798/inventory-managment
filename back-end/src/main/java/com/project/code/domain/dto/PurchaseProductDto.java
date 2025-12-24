package com.project.code.domain.dto;

public record PurchaseProductDto (
        Long id,
        String name,
        Double price,
        Integer quantity,
        Double total
) {}


