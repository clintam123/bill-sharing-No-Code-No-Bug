package com.nocodenobug.billsharing.service.order;

import com.nocodenobug.billsharing.model.dto.OrderDto;

public interface UpdateOrderService {
    OrderDto updateOrder(Long id, OrderDto orderDto);

    OrderDto updateTotal(Long id);
}
