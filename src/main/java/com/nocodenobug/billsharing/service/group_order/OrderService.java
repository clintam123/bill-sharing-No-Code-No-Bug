package com.nocodenobug.billsharing.service.group_order;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.redis.OrderRedis;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;

public interface OrderService {
    OrderRedis addNewOrder(String link, GroupLinkRequest request);
    void deleteOrder(Long id);
    OrderRedis findByLink(String link);
    OrderRedis addOrderItem(String link, OrderItemDto orderItemDto);
    OrderRedis updateOrderItem(String link, OrderItemDto orderItemDto);
    OrderRedis deleteOrderItem(String link, OrderItemDto orderItemDto);

    void saveOrder(String link);
}
