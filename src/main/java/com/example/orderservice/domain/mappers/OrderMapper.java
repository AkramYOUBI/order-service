package com.example.orderservice.domain.mappers;

import com.example.orderservice.domain.dto.OrderDetails;
import com.example.orderservice.domain.dto.OrderModel;
import com.example.orderservice.domain.entities.Orders;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Orders fromModelToDb(OrderModel orderModel);

    OrderDetails fromDbToDetails(Orders result);
    List<OrderDetails> fromDbToDetails(List<Orders> result);
}
