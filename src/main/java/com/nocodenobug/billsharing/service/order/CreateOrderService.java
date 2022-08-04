package com.nocodenobug.billsharing.service.order;

import com.nocodenobug.billsharing.model.dto.OrderDto;

public interface CreateOrderService {
//    Order create(JsonNode orderData);

    OrderDto createOrder(OrderDto orderDto);
}
