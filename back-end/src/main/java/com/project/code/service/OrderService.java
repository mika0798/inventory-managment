package com.project.code.service;

import com.project.code.dto.PlaceOrderRequest;
import com.project.code.dto.PurchaseProductRequest;
import com.project.code.entity.*;
import com.project.code.exception.InventoryNotFoundException;
import com.project.code.exception.ProductNotFoundException;
import com.project.code.exception.StoreNotFoundException;
import com.project.code.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private ProductRepository productRepository;
    private InventoryRepository inventoryRepository;
    private CustomerRepository customerRepository;
    private StoreRepository storeRepository;
    private OrderItemRepository orderItemRepository;
    private OrderDetailsRepository orderDetailsRepository;

    public Long saveOrder(PlaceOrderRequest placeOrderRequest) {
        //Retrieve or create customer
        Customer customer = customerRepository
                .findByEmail(placeOrderRequest.getCustomerEmail())
                .orElseGet(() -> {
                    Customer c = new Customer();
                    c.setName(placeOrderRequest.getCustomerName());
                    c.setEmail(placeOrderRequest.getCustomerEmail());
                    c.setPhone(placeOrderRequest.getCustomerPhone());
                    return customerRepository.save(c);
                });

        // Retrieve the store
        Store store = storeRepository
                .findById(placeOrderRequest.getStoreId())
                .orElseThrow(() -> new StoreNotFoundException("Store not found"));

        // Create OrderDetails
        OrderDetails order = new OrderDetails();
        order.setStore(store);
        order.setCustomer(customer);
        order.setTotalPrice(placeOrderRequest.getTotalPrice());
        order.setDate(java.time.LocalDateTime.now());

        order = orderDetailsRepository.save(order);

        // Create and save purchased items
        List<PurchaseProductRequest> purchasedProductList = placeOrderRequest.getPurchaseProduct();
        for (PurchaseProductRequest productRequest : purchasedProductList) {
            OrderItem purchasedItem = new OrderItem();

            // Update inventory
            Inventory inventory = inventoryRepository
                    .findByProductIdAndStoreId(
                    productRequest.getId(),
                    store.getId()
                    ).orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

            inventory.setStockLevel(inventory.getStockLevel() - productRequest.getQuantity());

            // Save purchased items
            purchasedItem.setOrderDetails(order);
            Product theProduct = productRepository
                    .findById(productRequest.getId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
            purchasedItem.setProduct(theProduct);
            purchasedItem.setQuantity(productRequest.getQuantity());
            purchasedItem.setPrice(productRequest.getPrice()*productRequest.getQuantity());
            orderItemRepository.save(purchasedItem);
        }

        return order.getId();
    }
}
