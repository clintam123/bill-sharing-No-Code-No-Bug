package com.nocodenobug.billsharing.service.group_order.impl;

import com.nocodenobug.billsharing.constants.OrderStatus;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.model.entity.redis.OrderRedis;
import com.nocodenobug.billsharing.model.entity.redis.OrderItemRedis;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;
import com.nocodenobug.billsharing.repository.OrderRepository;
import com.nocodenobug.billsharing.repository.redis.RedisOrderRepository;
import com.nocodenobug.billsharing.service.group_order.OrderService;
import com.nocodenobug.billsharing.service.order.CreateOrderService;
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
    private OrderRepository mySQLOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderRedis addNewOrder(String link, GroupLinkRequest request) {
        OrderRedis newOrderRedis = OrderRedis.of(OrderStatus.CHUA_THANH_TOAN.getStatus(), 1000f, 0f, BigDecimal.valueOf(1000)
                , link, request.getVendorId(), request.getUserId(), new ArrayList<>());
        return redisOrderRepository.save(newOrderRedis);
    }

    @Override
    public void deleteOrder(Long id) {
        redisOrderRepository.deleteById(String.valueOf(id));
    }

    @Override
    public OrderRedis findByLink(String link) {
        OrderRedis orderRedis = redisOrderRepository.findByLink(link);
        System.out.println(orderRedis);
        if (orderRedis.getId() == null) {
            throw new NotFoundException(404, "link not found");
        }
        return orderRedis;
    }

    @Override
    public OrderRedis addOrderItem(String link, OrderItemDto orderItemDto) {
        OrderRedis orderRedis = findByLink(link);
        List<OrderItemRedis> orderItemRedis = orderRedis.getOrderItemRedis();
        boolean exists = false;
        for (int i = 0; i < orderItemRedis.size(); i++) {
            if (orderItemRedis.get(i).getUserId().equals(orderItemDto.getUserId()) && orderItemRedis.get(i).getProductId().equals(orderItemDto.getProductId())) {
                exists = true;
                OrderItemRedis item = orderItemRedis.get(i);
                item.setQuantity(item.getQuantity() + orderItemDto.getQuantity());
                item.setTotal(item.getTotal().add(orderItemDto.getTotal()));
                break;
            }
        }
        if (!exists) {
            orderItemRedis.add(modelMapper.map(orderItemDto, OrderItemRedis.class));
        }
        orderRedis.setGrandTotal(orderRedis.getGrandTotal().add(orderItemDto.getTotal()));
        return redisOrderRepository.save(orderRedis);
    }

    @Override
    public OrderRedis updateOrderItem(String link, OrderItemDto orderItemDto) {
        System.out.println("orderItemDto: " + orderItemDto);
        OrderRedis orderRedis = findByLink(link);
        List<OrderItemRedis> orderItemRedis = orderRedis.getOrderItemRedis();
        for (int i = 0; i < orderItemRedis.size(); i++) {
            if (orderItemRedis.get(i).getUserId().equals(orderItemDto.getUserId()) && orderItemRedis.get(i).getProductId().equals(orderItemDto.getProductId())) {
                if (orderItemDto.getQuantity() == 0) {
                    orderRedis.setGrandTotal(orderRedis.getGrandTotal().subtract(orderItemRedis.get(i).getTotal()));
                    orderItemRedis.remove(i);

                } else {
                    orderRedis.setGrandTotal(orderRedis.getGrandTotal().subtract(orderItemRedis.get(i).getTotal()).add(orderItemDto.getTotal()));
                    orderItemRedis.set(i, modelMapper.map(orderItemDto, OrderItemRedis.class));
                }
            }
        }
        System.out.println("order: " + orderRedis);
        return redisOrderRepository.save(orderRedis);
    }

    @Override
    public OrderRedis deleteOrderItem(String link, OrderItemDto orderItemDto) {
        OrderRedis orderRedis = findByLink(link);
        List<OrderItemRedis> orderItemRedis = orderRedis.getOrderItemRedis();
        for (int i = 0; i < orderItemRedis.size(); i++) {
            if (orderItemRedis.get(i).getUserId().equals(orderItemDto.getUserId()) && orderItemRedis.get(i).getProductId().equals(orderItemDto.getProductId())) {
                orderItemRedis.remove(i);
            }
        }
        orderRedis.setGrandTotal(orderRedis.getGrandTotal().subtract(orderItemDto.getTotal()));
        return redisOrderRepository.save(orderRedis);
    }

    @Override
    public void saveOrder(String link) {
        OrderRedis orderRedis = findByLink(link);
        Order order = modelMapper.map(orderRedis, Order.class);
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRedis orderItemRedis : orderRedis.getOrderItemRedis()) {
            OrderItem orderItem = modelMapper.map(orderItemRedis, OrderItem.class);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        mySQLOrderRepository.save(order);
        redisOrderRepository.deleteById(String.valueOf(orderRedis.getId()));
    }

}
