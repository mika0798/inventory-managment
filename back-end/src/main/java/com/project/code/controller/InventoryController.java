package com.project.code.controller;

import com.project.code.service.InventoryService;
import com.project.code.domain.ApiResponse;
import com.project.code.domain.entity.Inventory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Inventory Rest API Endpoints",description="Operations related to inventory management")
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @Operation(summary="Search products in inventory",description="Search products by name in a specific store")
    @GetMapping("/search/{name}/{storeId}")
    public ResponseEntity<ApiResponse<List<Inventory>>> searchProductInInventory(
            @Parameter(description="Product name")
            @PathVariable String name,
            @Parameter(description="Store ID")
            @PathVariable Long storeId) {
        List<Inventory> inventories = inventoryService.searchByNameAndStore(name, storeId);
        ApiResponse<List<Inventory>> response = new ApiResponse<>("Success", "Products found", inventories);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary="Filter inventory",description="Filter products by category and name in a store")
    @GetMapping("/filter/{category}/{productName}/{storeId}")
    public ResponseEntity<ApiResponse<List<Inventory>>> filterInventory(
            @Parameter(description="Category")
            @PathVariable("category") String category,
            @Parameter(description="Product name")
            @PathVariable("productName") String productName,
            @Parameter(description="Store ID")
            @PathVariable Long storeId) {
        List<Inventory> inventories = inventoryService.filterByAll(category, productName, storeId);
        ApiResponse<List<Inventory>> response = new ApiResponse<>("Success", "Filtered products", inventories);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary="Validate stock quantity",description="Check if requested quantity is available")
    @GetMapping("/validate/{quantity}/{storeId}/{productId}")
    public ResponseEntity<ApiResponse<Boolean>> validateQuantity(
            @Parameter(description="Requested quantity")
            @PathVariable Integer quantity,
            @Parameter(description="Store ID")
            @PathVariable Long storeId,
            @Parameter(description="Product ID")
            @PathVariable Long productId) {
        Boolean isValid = inventoryService.validateStock(quantity, storeId, productId);
        ApiResponse<Boolean> response = new ApiResponse<>(
                isValid ? "Success" : "Error",
                isValid ? "Stock available" : "Insufficient stock",
                isValid);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary="Add to inventory",description="Add product to store inventory")
    @PostMapping
    public ResponseEntity<ApiResponse<Inventory>> addToInventory(@RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryService.saveInventory(inventory);
        ApiResponse<Inventory> response = new ApiResponse<>("Success", "Product added to inventory", savedInventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary="Update inventory",description="Update product inventory details")
    @PutMapping
    public ResponseEntity<ApiResponse<Inventory>> updateInventory(@RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventory(inventory);
        ApiResponse<Inventory> response = new ApiResponse<>("Success", "Inventory updated successfully", updatedInventory);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary="Delete from inventory",description="Remove product from store inventory")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFromInventory(
            @Parameter(description="Inventory ID to delete")
            @PathVariable Long id) {
        inventoryService.deleteInventory(id);
        ApiResponse<Void> response = new ApiResponse<>("Success", "Removed from inventory successfully", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}