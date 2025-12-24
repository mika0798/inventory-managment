package com.project.code.dto;

public record PurchaseProductDto (
        Long id,
        String name,
        Double price,
        Integer quantity,
        Double total
) {}


