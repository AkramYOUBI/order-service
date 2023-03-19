package com.example.orderservice.web;

import com.example.orderservice.domain.dto.OrderDetails;
import com.example.orderservice.domain.dto.OrderModel;
import com.example.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    private OrderDetails createOrder(@RequestBody OrderModel orderModel) throws IllegalAccessException {
        return orderService.createOrder(orderModel);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    private List<OrderDetails> getAllOrders(){
        return orderService.getAllOrders();
    }
}
