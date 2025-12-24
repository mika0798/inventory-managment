package com.project.code.service;

import com.project.code.domain.entity.Inventory;
import com.project.code.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<Inventory> searchByNameAndStore(String productName, Long storeId) {
        return inventoryRepository.findByProduct_NameContainingIgnoreCaseAndStore_Id(productName, storeId);
    }


    public List<Inventory> filterByAll(String category, String productName, Long storeId) {
        return inventoryRepository.findByProduct_CategoryAndProduct_NameContainingIgnoreCaseAndStore_Id(category, productName, storeId);
    }


    public Boolean validateStock(Integer quantity, Long storeId, Long productId) {
        Inventory inventory = inventoryRepository.findByStore_IdAndProduct_Id(storeId, productId)
                .orElseThrow(() -> new RuntimeException("Product not found in this store"));
        return inventory.getStockLevel() >= quantity;
    }


    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }


    public Inventory updateInventory(Inventory inventory) {
        if (!inventoryRepository.existsById(inventory.getId())) {
            throw new RuntimeException("Inventory record not found with ID: " + inventory.getId());
        }
        return inventoryRepository.save(inventory);
    }


    public void deleteInventory(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new RuntimeException("Inventory record not found with ID: " + id);
        }
        inventoryRepository.deleteById(id);
    }
}