package com.example.orderservice.domain.dto;

import com.example.orderservice.domain.entities.OrderLineItem;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetails {
    private Long id;
    private String orderNumber;
    private List<OrderLineItem> orderLineItemList;
}
