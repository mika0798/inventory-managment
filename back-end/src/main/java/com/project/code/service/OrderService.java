package com.project.code.service;

import com.project.code.domain.dto.PlaceOrderRequest;
import com.project.code.domain.dto.PurchaseProductDto;
import com.project.code.domain.entity.*;
import com.project.code.exception.ResourceNotFoundException;
import com.project.code.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public Long saveOrder(PlaceOrderRequest placeOrderRequest) {
        //Retrieve or create customer
        Customer customer = customerRepository
                .findByEmail(placeOrderRequest.customerEmail())
                .orElseGet(() -> {
                    Customer c = new Customer();
                    c.setName(placeOrderRequest.customerName());
                    c.setEmail(placeOrderRequest.customerEmail());
                    c.setPhone(placeOrderRequest.customerPhone());
                    return customerRepository.save(c);
                });

        // Retrieve the store
        Store store = storeRepository
                .findById(placeOrderRequest.storeId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        // Create OrderDetails
        OrderDetails order = new OrderDetails();
        order.setStore(store);
        order.setCustomer(customer);
        order.setTotalPrice(placeOrderRequest.totalPrice());
        order.setDate(java.time.LocalDateTime.now());

        order = orderDetailsRepository.save(order);

        // Create and save purchased items
        List<PurchaseProductDto> purchasedProductList = placeOrderRequest.purchaseProduct();
        for (PurchaseProductDto productRequest : purchasedProductList) {
            OrderItem purchasedItem = new OrderItem();

            // Update inventory
            Inventory inventory = inventoryRepository
                    .findByProductIdAndStoreId(
                    productRequest.id(),
                    store.getId()
                    ).orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

            inventory.setStockLevel(inventory.getStockLevel() - productRequest.quantity());

            // Save purchased items
            purchasedItem.setOrderDetails(order);
            Product theProduct = productRepository
                    .findById(productRequest.id())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            purchasedItem.setProduct(theProduct);
            purchasedItem.setQuantity(productRequest.quantity());
            purchasedItem.setPrice(productRequest.price()*productRequest.quantity());
            orderItemRepository.save(purchasedItem);
        }

        return order.getId();
    }
}
