package com.project.code.controller;

import com.project.code.domain.entity.Product;
import com.project.code.service.InventoryService;
import com.project.code.service.ValidateService;
import com.project.code.domain.ApiResponse;
import com.project.code.domain.dto.CombinedRequest;
import com.project.code.domain.entity.Inventory;
import com.project.code.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Inventory Rest API Endpoints", description = "Operations related to inventory management")
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    private final ProductService productService;
    private final ValidateService validateService;

    @Operation(summary = "Search products in inventory", description = "Search products by name in a specific store")
    @GetMapping("/search/{name}/{storeId}")
    public ResponseEntity<ApiResponse<List<Product>>> searchProductInInventory(
            @Parameter(description = "Product name") @PathVariable String name,
            @Parameter(description = "Store ID") @PathVariable Long storeId) {
        List<Product> inventories = productService.findByNameLike(name, storeId);
        ApiResponse<List<Product>> response = new ApiResponse<>("Success", "Products found", inventories);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // @Operation(summary = "Search products in inventory", description = "Search products by name in a specific store")
    // @GetMapping("/search/{name}/{storeId}")
    // public Map<String,Object> searchProduct(@PathVariable String name, @PathVariable long storeId)
    // {
    //     Map<String, Object> map = new HashMap<>();
    //     map.put("products", productService.findByNameLike(name, storeId));
    //     return map;
    // }

    @Operation(summary = "Filter inventory", description = "Filter products by category and name in a store")
    @GetMapping("/filter/{category}/{productName}/{storeId}")
    public ResponseEntity<ApiResponse<List<Product>>> filterInventory(
            @Parameter(description = "Category") @PathVariable("category") String category,
            @Parameter(description = "Product name") @PathVariable("productName") String productName,
            @Parameter(description = "Store ID") @PathVariable Long storeId) {
        List<Product> products = productService.filterProductsInStore(category, productName, storeId);
        ApiResponse<List<Product>> response = new ApiResponse<>("Success", "Filtered products", products);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Validate stock quantity", description = "Check if requested quantity is available")
    @GetMapping("/validate/{quantity}/{storeId}/{productId}")
    public ResponseEntity<ApiResponse<Boolean>> validateQuantity(
            @Parameter(description = "Requested quantity") @PathVariable Integer quantity,
            @Parameter(description = "Store ID") @PathVariable Long storeId,
            @Parameter(description = "Product ID") @PathVariable Long productId) {
        Boolean isValid = inventoryService.validateStock(quantity, storeId, productId);
        ApiResponse<Boolean> response = new ApiResponse<>(
                isValid ? "Success" : "Error",
                isValid ? "Stock available" : "Insufficient stock",
                isValid);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Add to inventory", description = "Add product to store inventory")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> addToInventory(@RequestBody Inventory inventory) {

        Long productId = inventory.getProduct().getId();
        if (!productService.existsById(productId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error", "Product with id " + productId + " does not exist", null));
        }
        
        if (!validateService.validateInventory(inventory)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>("Error", "Data already present in inventory", null));
        }
        try {
            inventoryService.saveInventory(inventory);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error", "Error: " + e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error", "Error: " + e.getMessage(), null));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Success", "Product added to inventory successfully", null));
    }

    @Operation(summary = "Update inventory", description = "Update product inventory details")
    @PutMapping
    public ResponseEntity<ApiResponse<String>> updateInventory(@RequestBody CombinedRequest request) {
        Product product = request.product();
        Inventory inventory = request.inventory();

        if (!productService.existsById(product.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Error", "Id " + product.getId() + " not present in database", null));
        }
        productService.saveProduct(product);

        if (inventory != null) {
            Inventory existing = inventoryService.getInventoryId(inventory);
            if (existing != null) {
                inventory.setId(existing.getId());
                inventoryService.saveInventory(inventory);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("Error", "No data available for this product or store id", null));
            }
        }
        return ResponseEntity
                .ok(new ApiResponse<>("Success", "Successfully updated product with id: " + product.getId(), null));
    }

    @Operation(summary = "Delete from inventory", description = "Remove product from store inventory")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFromInventory(
            @Parameter(description = "Inventory ID to delete") @PathVariable Long id) {
        inventoryService.deleteInventory(id);
        ApiResponse<Void> response = new ApiResponse<>("Success", "Removed from inventory successfully", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}