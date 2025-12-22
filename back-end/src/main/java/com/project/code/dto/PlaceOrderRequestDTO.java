package com.project.code.dto;

import lombok.Data;
import java.util.List;

@Data
public class PlaceOrderRequestDTO {
    private Long storeId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String datetime;
    private List<PurchaseProductDTO> purchaseProduct;
    private Double totalPrice;
}
