package com.project.code.domain.dto;

import com.project.code.domain.entity.Inventory;
import com.project.code.domain.entity.Product;

public record CombinedRequest (
    Product product,
    Inventory inventory
){}