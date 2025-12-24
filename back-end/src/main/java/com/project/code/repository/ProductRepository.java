package com.project.code.repository;

import org.springframework.data.jpa.repository.Query;
import com.project.code.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product,Long> {
    // ===== BASIC CRUD QUERIES =====
    List<Product> findAll();
    Optional<Product> findById(Long id);

    // ===== DERIVED QUERIES =====
   Optional<Product> findBySku(String sku);
   Optional<Product> findByNameIgnoreCase(String name);
   List<Product> findByCategory(String category);
   List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // ===== SEARCH =====
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByNameContainingIgnoreCaseAndCategory(String name, String category);
    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
    List<Product> findByNameLike(String pname, long storeId);
}
