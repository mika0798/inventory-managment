package com.project.code.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message="Name cannot be null")
    @NotBlank(message="Name cannot be blank")
    private String name;

    @NotNull(message="Email cannot be null")
    @Email(message="Invalid email format")
    private String email;

    @NotNull(message="Phone number cannot be null")
    @NotBlank(message="Phone number cannot be blank")
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonBackReference("order-customer")
    private List<OrderDetails> orders;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}

