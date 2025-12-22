package com.project.code.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.code.repository.InventoryRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message="Name cannot be null")
    @NotBlank(message="Name cannot be blank")
    private String name;

    @NotNull(message="Address cannot be null")
    @NotBlank(message="Address cannot be blank")
    private String address;

    @OneToMany(mappedBy="store", fetch = FetchType.LAZY)
    @JsonManagedReference("store-inventory")
    private List<Inventory> inventory;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

