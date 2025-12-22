package com.project.code.service;


import com.project.code.entity.Inventory;
import com.project.code.entity.Product;
import com.project.code.repository.InventoryRepository;
import com.project.code.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClass {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ServiceClass(InventoryRepository inventoryRepository,ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository=productRepository;
    }

    public boolean validateInventory(Inventory inventory) {
        return true;
    }

    public boolean validateProduct(Product product) {
        return true;
    }

    public boolean ValidateProductId(long id) {
        return true;
    }

    public Inventory getInventoryId(Inventory inventory) {
        Inventory result = inventoryRepository.findByProductIdAndStoreId(inventory.getProduct().getId(),inventory.getStore().getId());
        return result;
    }
}
