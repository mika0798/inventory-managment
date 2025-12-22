package com.project.code.controller;

import com.project.code.dto.ApiResponse;
import com.project.code.dto.PlaceOrderRequest;
import com.project.code.entity.Store;
import com.project.code.repository.StoreRepository;
import com.project.code.service.OrderService;
import com.project.code.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name="Stores Rest API Endpoints",description="Operations related to stores")
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    private final OrderService orderService;

    @Autowired
    public StoreController(OrderService orderService, StoreService storeService) {
        this.storeService = storeService;
        this.orderService = orderService;
    }

    @Operation(summary="Validate if store exists",description="Validate store by Id")
    @GetMapping("validate/{storeId}")
    public ResponseEntity<ApiResponse<Boolean>> validateStore(
            @Parameter(description="Id of store")
            @PathVariable Long storeId) {

        Boolean result = storeService.storeExists(storeId);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>("Success"
                ,result ? "Store exists" : "Store not found"
                ,result);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary="Add new store",description="Create new store in the database")
    @PostMapping
    public ResponseEntity<ApiResponse<Store>> addStore(@RequestBody Store store) {
        Store savedStore = storeService.saveStore(store);
        ApiResponse<Store> response = new ApiResponse<>(
                "Store",
                savedStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary="Add an order",description="Place new order")
    @PostMapping("/placeOrder")
    public ResponseEntity<ApiResponse<PlaceOrderRequest>> placeOrder(@RequestBody PlaceOrderRequest orderRequest) {
        orderService.saveOrder(orderRequest);
        ApiResponse<PlaceOrderRequest> response = new ApiResponse<>(
                "Success",
                "Order has been placed",
                orderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
