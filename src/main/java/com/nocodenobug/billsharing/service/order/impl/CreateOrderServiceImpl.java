package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.*;
import com.nocodenobug.billsharing.constants.OrderStatus;
import com.nocodenobug.billsharing.repository.OrderItemRepository;
import com.nocodenobug.billsharing.repository.OrderRepository;
import com.nocodenobug.billsharing.service.order.CreateOrderService;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.service.order_item.GetOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
    private ModelMapper modelMapper;


    @Override
    public OrderDto createOrder(OrderDto orderDto){
        Order order = modelMapper.map(orderDto,Order.class);


        List<OrderItem> orderItems = orderItemRepository.findAll();

        order.setId(orderDto.getId());
        order.setStatus(OrderStatus.CHUA_THANH_TOAN.getStatus());
        order.setGrandTotal(BigDecimal.valueOf(0));

        Vendor findById = findByIdService.checkIdVendor(orderDto.getVendorId());
        User findByIdUser = findByIdService.checkIdUser(orderDto.getUserId());

        return modelMapper.map(orderRepository.save(order),OrderDto.class);
    }




}
