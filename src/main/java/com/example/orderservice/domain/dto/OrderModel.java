package com.example.orderservice.domain.dto;

import com.example.orderservice.domain.entities.OrderLineItem;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderModel {
    private String orderNumber;
    private List<OrderLineItem> orderLineItemList;
}
