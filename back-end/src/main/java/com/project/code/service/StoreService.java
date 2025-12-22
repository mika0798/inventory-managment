package com.project.code.service;

import com.project.code.entity.Store;
import com.project.code.exception.ResourceAlreadyExistsException;
import com.project.code.exception.StoreNotFoundException;
import com.project.code.repository.ProductRepository;
import com.project.code.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store getStoreById(Long id){
        return storeRepository
                .findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store not found"));
    }

    public boolean storeExists(Long id){
        return storeRepository.existsById(id);
    }

    public Store saveStore(Store store){
        if (storeRepository.existByNameIgnoreCase(store.getName())) {
            throw new ResourceAlreadyExistsException("Store already exists");
        }
        return storeRepository.save(store);
    }

}
