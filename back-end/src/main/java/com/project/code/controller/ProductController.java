package com.project.code.controller;
import com.project.code.service.ProductService;
import com.project.code.domain.ApiResponse;
import com.project.code.domain.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Product Rest API Endpoints",description="Operations related to products")
@RestController("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary="Get all products",description="Retrieve all products from database")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ApiResponse<List<Product>> response = new ApiResponse<>("Success", "Products retrieved", products);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary="Get product by ID",description="Retrieve a product by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(
            @Parameter(description="Product ID")
            @PathVariable Long id) {
        Product product = productService.getProductById(id);
        ApiResponse<Product> response = new ApiResponse<>("Success", "Product retrieved", product);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary="Search products by name",description="Search products by product name")
    @GetMapping("/searchProduct/{name}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByName(
            @Parameter(description="Product name to search")
            @PathVariable String name) {
        List<Product> products = productService.searchByName(name);
        ApiResponse<List<Product>> response = new ApiResponse<>("Success", "Products found", products);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary="Filter products",description="Filter products by category and name")
    @GetMapping("/filter/{category}/{productName}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByCategoryAndName(
            @Parameter(description="Category ID")
            @PathVariable("category") Long categoryId,
            @Parameter(description="Product name")
            @PathVariable("productName") String productName) {
        List<Product> products = productService.filterByCategoryAndName(categoryId, productName);
        ApiResponse<List<Product>> response = new ApiResponse<>("Success", "Filtered products", products);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary="Add new product",description="Create a new product in database")
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        ApiResponse<Product> response = new ApiResponse<>("Success", "Product created successfully", savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary="Update product",description="Update existing product details")
    @PutMapping
    public ResponseEntity<ApiResponse<Product>> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        ApiResponse<Product> response = new ApiResponse<>("Success", "Product updated successfully", updatedProduct);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary="Delete product",description="Delete a product by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(
            @Parameter(description="Product ID to delete")
            @PathVariable Long id) {
        productService.deleteProduct(id);
        ApiResponse<Void> response = new ApiResponse<>("Success", "Product deleted successfully", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





}
