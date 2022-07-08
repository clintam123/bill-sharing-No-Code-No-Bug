package com.nocodenobug.billsharing.service.order;

import com.fasterxml.jackson.databind.JsonNode;
import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.Order;

public interface CreateOrderService {
//    Order create(JsonNode orderData);

    OrderDto createOrder(OrderDto orderDto);
}
