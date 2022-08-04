package com.nocodenobug.billsharing.service.order_item.impl;

import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.repository.OrderItemRepository;
import com.nocodenobug.billsharing.service.order_item.DeleteOrderItemService;
import com.nocodenobug.billsharing.service.FindByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrderItemServiceImpl implements DeleteOrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private FindByIdService findByIdService;

    @Override
    public void deleteOrderItem(Long id){
        OrderItem orderItem =findByIdService.checkIdOrderItem(id);

        orderItemRepository.deleteById(orderItem.getId());
    }

}
