package com.nocodenobug.billsharing.service.order_item;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;

public interface CreateOrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);
}
