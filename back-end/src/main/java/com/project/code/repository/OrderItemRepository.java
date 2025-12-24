package com.project.code.repository;

import com.project.code.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    void deleteByProductId(Long productId);
}


