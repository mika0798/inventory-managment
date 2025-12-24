package com.project.code.repository;

import com.project.code.domain.entity.Inventory;
import com.project.code.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    // ===== CORE =====
    List<Inventory> findByStoreId(Long storeId);
    Optional<Inventory> findByProductIdAndStoreId(Long productId, Long storeId);
    void deleteByProductId(Long productId);

    // ===== STORE to PRODUCTS =====
    @Query(
        """
        SELECT i.product FROM Inventory i WHERE i.store.id = :storeId
        """)
    List<Product> findProductsByStoreId(Long storeId);

    @Query("""
        SELECT i.product FROM Inventory i WHERE i.store.id = :storeId
        AND i.product.category = :category
        """)
    List<Product> findProductsByStoreIdAndCategory(
            Long storeId,
            String category
    );

    @Query("""
        SELECT i.product FROM Inventory i WHERE i.store.id = :storeId
        AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :name, '%'))
        """)
    List<Product> searchProductsInStore(
            Long storeId,
            String name
    );

    @Query("""
        SELECT i.product FROM Inventory i WHERE i.store.id = :storeId
        AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :name, '%'))
        AND i.product.category = :category
    """)
    List<Product> searchProductsInStoreByNameAndCategory(
            Long storeId,
            String name,
            String category
    );

    List<Inventory> findByProduct_NameContainingIgnoreCaseAndStore_Id(String productName, Long storeId);
    List<Inventory> findByProduct_CategoryAndProduct_NameContainingIgnoreCaseAndStore_Id(String category, String productName, Long storeId);
    Optional<Inventory> findByStore_IdAndProduct_Id(Long storeId, Long productId);
}
