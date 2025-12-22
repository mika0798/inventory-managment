package com.project.code.dto;

import com.project.code.entity.Inventory;
import com.project.code.entity.Product;
import lombok.Data;

@Data
public class CombinedRequest {
    private Product product;
    private Inventory inventory;
}