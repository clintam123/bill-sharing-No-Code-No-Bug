package com.nocodenobug.billsharing.service.order_item;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;

public interface UpdateOrderItemService{
    OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto);

    OrderItemDto updateQuantityOrderItem(Long id, OrderItemDto orderItemDto);
}
