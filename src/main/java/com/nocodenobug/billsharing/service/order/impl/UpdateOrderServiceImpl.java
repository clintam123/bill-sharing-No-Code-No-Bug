package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.contants.OrderStatus;
import com.nocodenobug.billsharing.exception.ExceptionObject;
import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.reponsitory.OrderReponsitory;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.service.order.UpdateOrderService;
import com.nocodenobug.billsharing.service.order_item.GetOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UpdateOrderServiceImpl implements UpdateOrderService {

    @Autowired
    private OrderReponsitory orderReponsitory;

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
            throw ExceptionObject.builder().message("Order không tồn tại").build();
        }

        Order orderOld = orderId;
        orderId = modelMapper.map(orderDto,Order.class);

        orderId.setId(id);
        orderId.setCreatedAt(orderOld.getCreatedAt());
        orderId.setUpdatedAt(LocalDateTime.now());
        orderId.setStatus(orderOld.getStatus());
        orderId.setTotal(BigDecimal.valueOf(getOrderItemService.getAmount(orderOld.getId())));

        double total = Double.parseDouble(orderOld.getTotal()+"");
        orderId.setGrandTotal(BigDecimal.valueOf(total + orderOld.getShipping() - orderDto.getDiscount()));

        return modelMapper.map(orderReponsitory.save(orderId),OrderDto.class);
    }

    @Override
    public OrderDto updateTotal(Long id){

        Order orderId = findByIdService.checkIdOrder(id);
        if(orderId.getStatus() == OrderStatus.INACTIVE.getStatus()){
            throw ExceptionObject.builder().message("Order không tồn tại").build();
        }

        orderId.setTotal(BigDecimal.valueOf(getOrderItemService.getAmount(id)));

        double total = Double.parseDouble(orderId.getTotal()+"");
        orderId.setGrandTotal(BigDecimal.valueOf(total + orderId.getShipping() - orderId.getDiscount()));

        return modelMapper.map(orderReponsitory.save(orderId),OrderDto.class);
    }

}
