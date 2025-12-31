package com.project.code.service;

import com.project.code.domain.entity.Product;
import com.project.code.domain.entity.Store;
import com.project.code.exception.ResourceAlreadyExistsException;
import com.project.code.exception.ResourceNotFoundException;
import com.project.code.repository.InventoryRepository;
import com.project.code.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final InventoryRepository inventoryRepository;

    public Store getStoreById(Long id){
        return storeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
    }

    public boolean storeExists(Long id){
        return storeRepository.existsById(id);
    }

    public Store saveStore(Store store){
        if (storeRepository.existsByNameIgnoreCase(store.getName())) {
            throw new ResourceAlreadyExistsException("Store already exists");
        }
        return storeRepository.save(store);
    }

    public List<Product> getAllProductsInStore(Long storeId){
        if (!storeRepository.existsById(storeId)) {
            throw new ResourceNotFoundException("Store not found");
        }
        return inventoryRepository.findProductsByStoreId(storeId);
    }
}
