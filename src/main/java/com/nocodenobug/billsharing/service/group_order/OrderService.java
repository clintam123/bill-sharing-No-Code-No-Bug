package com.nocodenobug.billsharing.service.group_order;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.redis.Order;
import com.nocodenobug.billsharing.model.entity.redis.OrderItem;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;

import java.util.Optional;

public interface OrderService {
    Order addNewOrder(String link, GroupLinkRequest request);
    void deleteOrder(Long id);
    Order findByLink(String link);
    Order addOrderItem(String link, OrderItemDto orderItemDto);
    Order updateOrderItem(String link, OrderItemDto orderItemDto);
    Order deleteOrderItem(String link, OrderItemDto orderItemDto);
}
