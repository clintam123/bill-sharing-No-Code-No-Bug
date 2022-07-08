package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.contants.OrderStatus;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.reponsitory.OrderReponsitory;
import com.nocodenobug.billsharing.service.order.DeleteOrderService;
import com.nocodenobug.billsharing.service.FindByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrderServiceImpl implements DeleteOrderService {

    @Autowired
    private OrderReponsitory orderReponsitory;

    @Autowired
    private FindByIdService findByIdService;


    @Override
    public Order deleteOrder(Long id){
        Order order = findByIdService.checkIdOrder(id);

        order.setStatus(OrderStatus.INACTIVE.getStatus());

        return orderReponsitory.save(order);
    }

}