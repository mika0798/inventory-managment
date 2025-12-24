package com.project.code.dto;

import java.util.List;


public record PlaceOrderRequest (
    Long storeId,
    String customerName,
    String customerEmail,
    String customerPhone,
    String datetime,
    List<PurchaseProductDto> purchaseProduct,
    Double totalPrice
    ){}
