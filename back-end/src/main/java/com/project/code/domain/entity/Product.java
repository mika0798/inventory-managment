package com.project.code.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="product",uniqueConstraints=
@UniqueConstraint(columnNames="sku"))
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message="Name cannot be null")
    @NotBlank(message="Name cannot be blank")
    private String name;

    @NotNull(message="Field cannot be null")
    private String category;

    @NotNull(message="Sku cannot be null")
    private Double price;

    @NotNull(message="Sku cannot be null")
    private String sku;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference("inventory-product")
    private List<Inventory> inventory;

    public Product(String name, String category, Double price, String sku) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.sku = sku;
    }
}


