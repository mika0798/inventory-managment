package com.project.code.dto;

import lombok.Data;
import java.util.List;

@Data
public class PlaceOrderRequest {
    private Long storeId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String datetime;
    private List<PurchaseProductRequest> purchaseProduct;
    private Double totalPrice;
}
