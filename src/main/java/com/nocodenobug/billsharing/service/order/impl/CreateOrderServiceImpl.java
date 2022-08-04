package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.repository.OrderItemRepository;
import com.nocodenobug.billsharing.repository.OrderRepository;
import com.nocodenobug.billsharing.service.order.CreateOrderService;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.service.order_item.GetOrderItemService;
import com.nocodenobug.billsharing.service.order_item.impl.CreateOrderItemServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private FindByIdService findByIdService;

    @Autowired
    private GetOrderItemService getOrderItemService;

    @Autowired
    private CreateOrderItemServiceImpl createOrderItemService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public OrderDto createOrder(OrderDto orderDto){
        Order order = orderRepository.save(modelMapper.map(orderDto,Order.class));

        for(OrderItemDto orderItem : orderDto.getOrderItems()){
            System.out.println(orderItem);
            orderItem.setOrder(order);
            createOrderItemService.createOrderItem(orderItem);
        }



        return modelMapper.map(order, OrderDto.class);

    }




}
