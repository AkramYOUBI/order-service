package com.example.orderservice.domain.repository;

import com.example.orderservice.domain.entities.OrderLineItem;
import com.example.orderservice.domain.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
}
