package com.example.orderservice.domain.repository;

import com.example.orderservice.domain.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Orders, Long> {
}
