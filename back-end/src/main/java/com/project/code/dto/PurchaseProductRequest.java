package com.project.code.dto;

import lombok.Data;

@Data
public class PurchaseProductRequest {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private Double total;
}
