package com.example.orderservice.service;

import com.example.orderservice.domain.dto.OrderDetails;
import com.example.orderservice.domain.dto.OrderModel;
import com.example.orderservice.domain.entities.Orders;
import com.example.orderservice.domain.mappers.OrderMapper;
import com.example.orderservice.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }


    public OrderDetails createOrder(OrderModel orderModel) {
        Orders ordersDb = orderMapper.fromModelToDb(orderModel);
        Orders result = orderRepository.save(ordersDb);
        OrderDetails orderDetails = orderMapper.fromDbToDetails(result);
        return orderDetails;
    }

    public List<OrderDetails> getAllOrders() {
        List<Orders> resultList =  orderRepository.findAll();
        List<OrderDetails> orderDetailsList = orderMapper.fromDbToDetails(resultList);
        return orderDetailsList;
    }
}
