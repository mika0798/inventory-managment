package com.project.code.service;

import com.project.code.domain.entity.Inventory;
import com.project.code.domain.entity.Product;
import com.project.code.repository.InventoryRepository;
import com.project.code.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    public List<Product> filterProductsInStore(
            String category,
            String productName,
            Long storeId
    ) {
        List<Product> products;

        if ("null".equalsIgnoreCase(category)) {
            products = inventoryRepository.searchProductsInStore(storeId, productName);
        } else if ("null".equalsIgnoreCase(productName)) {
            products = inventoryRepository.findProductsByStoreIdAndCategory(storeId, category);
        } else {
            products = inventoryRepository.searchProductsInStoreByNameAndCategory(storeId, productName, category);
        }
        return products;
    }

    public List<Product> filterAllProducts(String category, String productName) {
        List<Product> products;

        if ("null".equalsIgnoreCase(category)) {
            products = productRepository.findByNameContainingIgnoreCase(productName);
        } else if ("null".equalsIgnoreCase(productName)) {
            products = productRepository.findByCategory(category);
        } else {
            products = productRepository
                    .findByNameContainingIgnoreCaseAndCategory(productName, category);
        }
        return products;
    }
    public List<Product> findByNameLike(String name, Long storeId) {
        return productRepository.findByNameLike(name, storeId);
    }

    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> filterByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filterByCategoryAndName(String category, String productName) {
        return productRepository
                .findByNameContainingIgnoreCaseAndCategory(productName, category);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public Product updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product not found with ID: " + product.getId());
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }
}