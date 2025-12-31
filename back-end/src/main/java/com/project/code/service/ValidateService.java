package com.project.code.service;


import com.project.code.domain.entity.Inventory;
import com.project.code.domain.entity.Product;
import com.project.code.exception.ResourceNotFoundException;
import com.project.code.repository.InventoryRepository;
import com.project.code.repository.OrderDetailsRepository;
import com.project.code.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public boolean validateInventory(Inventory inventory) {
        return inventoryRepository.findByProductIdAndStoreId(
                inventory.getProduct().getId(),
                inventory.getStore().getId()
                )
                .isEmpty();
    }

    public boolean validateProduct(Product product) {
        return productRepository
                .findByNameIgnoreCase(product.getName())
                .isEmpty();
    }

    public boolean ValidateProductId(long id) {
        return productRepository.findById(id).isPresent();
    }

    public boolean ValidateOrderId(long id) {
        return orderDetailsRepository.findById(id).isPresent();
    }

    public Inventory getInventoryId(Inventory inventory) {
        return inventoryRepository.findByProductIdAndStoreId(
                inventory.getProduct().getId(),
                inventory.getStore().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find such inventory"));

    }
}
