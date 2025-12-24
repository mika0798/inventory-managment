package com.project.code.dto;

import com.project.code.entity.Inventory;
import com.project.code.entity.Product;

public record CombinedRequest (
    Product product,
    Inventory inventory
){}