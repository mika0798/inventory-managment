package com.project.code.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonBackReference("order-item")
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonManagedReference
    private Product product;

    @NotNull(message="Quantity cannot be null")
    private Integer quantity;

    @NotNull(message="Price cannot be null")
    private Double price;

    public OrderItem(OrderDetails orderDetails, Product product, Integer quantity, Double price) {
        this.orderDetails = orderDetails;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

}

