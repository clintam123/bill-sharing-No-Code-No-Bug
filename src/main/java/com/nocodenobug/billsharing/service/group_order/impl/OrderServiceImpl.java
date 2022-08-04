package com.nocodenobug.billsharing.service.group_order.impl;

import com.nocodenobug.billsharing.constants.OrderStatus;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.redis.Order;
import com.nocodenobug.billsharing.model.entity.redis.OrderItem;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;
import com.nocodenobug.billsharing.repository.redis.RedisOrderRepository;
import com.nocodenobug.billsharing.service.group_order.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RedisOrderRepository redisOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Order addNewOrder(String link, GroupLinkRequest request) {
        Order newOrder = Order.of(OrderStatus.CHUA_THANH_TOAN.getStatus(), 1000f, 0f, BigDecimal.ZERO
                , link, request.getVendorId(), request.getUserId(), new ArrayList<>());
        return redisOrderRepository.save(newOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        redisOrderRepository.deleteById(String.valueOf(id));
    }

    @Override
    public Order findByLink(String link) {
        Order order = redisOrderRepository.findByLink(link);
//        System.out.println(order);
        if (order.getId() == null) {
            throw new NotFoundException(404, "link not found");
        }
        return order;
    }

    @Override
    public Order addOrderItem(String link, OrderItemDto orderItemDto) {
        Order order = findByLink(link);
        order.getOrderItems().add(modelMapper.map(orderItemDto, OrderItem.class));
        order.setGrandTotal(order.getGrandTotal().add(orderItemDto.getTotal()));
        return redisOrderRepository.save(order);
    }

    @Override
    public Order updateOrderItem(String link, OrderItemDto orderItemDto) {
        System.out.println("orderItemDto: " + orderItemDto);
        Order order = findByLink(link);
        List<OrderItem> orderItems = order.getOrderItems();
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getUserId().equals(orderItemDto.getUserId()) && orderItems.get(i).getProductId().equals(orderItemDto.getProductId())) {
                if (orderItemDto.getQuantity() == 0) {
                    order.setGrandTotal(order.getGrandTotal().subtract(orderItems.get(i).getTotal()));
                    orderItems.remove(i);

                } else {
                    order.setGrandTotal(order.getGrandTotal().subtract(orderItems.get(i).getTotal()).add(orderItemDto.getTotal()));
                    orderItems.set(i, modelMapper.map(orderItemDto, OrderItem.class));
                }
            }
        }
        System.out.println("order: " + order);
        return redisOrderRepository.save(order);
    }

    @Override
    public Order deleteOrderItem(String link, OrderItemDto orderItemDto) {
        Order order = findByLink(link);
        List<OrderItem> orderItems = order.getOrderItems();
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getUserId().equals(orderItemDto.getUserId()) && orderItems.get(i).getProductId().equals(orderItemDto.getProductId())) {
                orderItems.remove(i);
            }
        }
        order.setGrandTotal(order.getGrandTotal().subtract(orderItemDto.getTotal()));
        return redisOrderRepository.save(order);
    }

}
