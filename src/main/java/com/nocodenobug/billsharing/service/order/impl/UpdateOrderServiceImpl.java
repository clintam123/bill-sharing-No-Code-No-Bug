package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.constants.OrderStatus;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.repository.OrderRepository;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.service.order.UpdateOrderService;
import com.nocodenobug.billsharing.service.order_item.GetOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UpdateOrderServiceImpl implements UpdateOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FindByIdService findByIdService;

    @Autowired
    private GetOrderItemService getOrderItemService;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto){

        Order orderId = findByIdService.checkIdOrder(id);
        if(orderId.getStatus() == OrderStatus.INACTIVE.getStatus()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order khong ton tai");
        }

        Order orderOld = orderId;
        orderId = modelMapper.map(orderDto,Order.class);

        orderId.setId(id);
        orderId.setCreatedAt(orderOld.getCreatedAt());
        orderId.setUpdatedAt(LocalDateTime.now());
        orderId.setStatus(orderOld.getStatus());
        orderId.setGrandTotal(BigDecimal.valueOf(orderOld.getShipping() - orderDto.getDiscount()));

        return modelMapper.map(orderRepository.save(orderId),OrderDto.class);
    }

    @Override
    public OrderDto updateTotal(Long id){

        Order orderId = findByIdService.checkIdOrder(id);
        if(orderId.getStatus() == OrderStatus.INACTIVE.getStatus()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order khong ton tai");
        }

        orderId.setGrandTotal(BigDecimal.valueOf(orderId.getShipping() - orderId.getDiscount()));

        return modelMapper.map(orderRepository.save(orderId),OrderDto.class);
    }

}
