package com.example.orderservice.service;

import com.example.orderservice.domain.dto.InventoryResponse;
import com.example.orderservice.domain.dto.OrderDetails;
import com.example.orderservice.domain.dto.OrderModel;
import com.example.orderservice.domain.entities.Orders;
import com.example.orderservice.domain.mappers.OrderMapper;
import com.example.orderservice.domain.repository.OrderRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient.Builder webClientBuilder;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.webClientBuilder = webClientBuilder;
    }


    public OrderDetails createOrder(OrderModel orderModel) throws IllegalAccessException {
        List<String> itemSkuCode = orderModel.getOrderLineItemList().stream().map(x -> x.getSkuCode()).collect(Collectors.toList());

        // Todo: check the out quantity product and update the names for better understanding

        //Verify if this item is in the database of inventoryService
        InventoryResponse[] resultFromInventoryService = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", itemSkuCode).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductInStock = Arrays.stream(resultFromInventoryService).allMatch(InventoryResponse::getIsInStock);

        OrderDetails orderDetails = new OrderDetails();
        if(allProductInStock){
            Orders ordersDb = orderMapper.fromModelToDb(orderModel);
            Orders result = orderRepository.save(ordersDb);
            orderDetails = orderMapper.fromDbToDetails(result);
        }else{
            throw new IllegalAccessException("Product is not in the stock");
        }
        log.info("Order placed successfully");
        return orderDetails;
    }

    public List<OrderDetails> getAllOrders() {
        List<Orders> resultList =  orderRepository.findAll();
        List<OrderDetails> orderDetailsList = orderMapper.fromDbToDetails(resultList);
        return orderDetailsList;
    }
}
