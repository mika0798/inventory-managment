package com.project.code.controller;

import com.project.code.domain.ApiResponse;
import com.project.code.domain.dto.PlaceOrderRequest;
import com.project.code.domain.dto.StoreDto;
import com.project.code.domain.entity.Product;
import com.project.code.domain.entity.Store;

import com.project.code.service.OrderService;
import com.project.code.service.StoreService;
import com.project.code.service.ValidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Stores Rest API Endpoints",description="Operations related to stores")
@RestController
@RequestMapping("/stores")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final OrderService orderService;
    private final ValidateService validateService;

    @Operation(summary="Validate if store exists",description="Validate store by Id")
    @GetMapping("validate/{storeId}")
    public ResponseEntity<ApiResponse<Boolean>> validateStore(
            @Parameter(description="Id of store")
            @PathVariable Long storeId) {

        Boolean result = storeService.storeExists(storeId);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(
                result ? "Success" : "Error"
                ,result ? "Store exists" : "Store not found"
                ,result);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary="Get products for a store", description="Retrieve all products available in a store")
    @GetMapping("/{storeId}/products")
    public ResponseEntity<ApiResponse<List<Product>>> getProducts(@PathVariable Long storeId) {
        List<Product> products = storeService.getAllProductsInStore(storeId);
        ApiResponse<List<Product>> response = new ApiResponse<>(
                "Success",
                "Products retrieved successfully",
                products);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary="Add new store",description="Create new store in the database")
    @PostMapping
    public ResponseEntity<ApiResponse<Store>> addStore(@Valid @RequestBody StoreDto newStore) {
        Store createStore = new Store(newStore.name(), newStore.address());
        Store savedStore = storeService.saveStore(createStore);
        Boolean result = storeService.storeExists(savedStore.getId());
        ApiResponse<Store> response = new ApiResponse<>(
                result ? "Success" : "Error"
                ,result ? "Store created successfully!" : "Could not create new store",
                savedStore);
        return ResponseEntity
                .status(result ? HttpStatus.CREATED : HttpStatus.NO_CONTENT)
                .body(response);
    }

    @Operation(summary="Add an order",description="Place new order")
    @PostMapping("/placeOrder")
    public ResponseEntity<ApiResponse<Long>> placeOrder(@RequestBody PlaceOrderRequest orderRequest) {
        Long orderId = orderService.saveOrder(orderRequest);
        boolean result = validateService.ValidateOrderId(orderId);
        ApiResponse<Long> response = new ApiResponse<>(
                result ? "Success" : "Error"
                ,result ? "Order has been placed" : "Could not place order",
                result ? orderId : null);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
