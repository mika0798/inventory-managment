package com.project.code.repository;


import com.project.code.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product,Long> {
    // ===== BASIC CRUD QUERIES =====
    List<Product> findAll();
    Optional<Product> findById(Long id);

    // ===== BASIC DERIVED QUERIES =====
   Optional<Product> findBySku(String sku);
   Optional<Product> findByNameIgnoreCase(String name);
   List<Product> findByCategory(String category);
   List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // ===== SEARCH =====
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByNameContainingIgnoreCaseAndCategory(String name, String category
    );
}
