package com.project.code.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonManagedReference("order-customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="store_id")
    @JsonManagedReference("order-store")
    private Store store;

    private Double totalPrice;
    private LocalDateTime date;

    @OneToMany(mappedBy= "orderDetails", fetch = FetchType.LAZY)
    @JsonManagedReference("order-item")
    private List<OrderItem> orderItems;

    public OrderDetails(Customer customer, Store store, Double totalPrice, LocalDateTime date) {
        this.customer = customer;
        this.store = store;
        this.totalPrice = totalPrice;
        this.date = date;
    }
  
}
