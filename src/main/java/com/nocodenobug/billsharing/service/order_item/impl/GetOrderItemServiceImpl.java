package com.nocodenobug.billsharing.service.order_item.impl;

import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.repository.OrderItemRepository;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.service.order_item.GetOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrderItemServiceImpl implements GetOrderItemService {


    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private FindByIdService findByIdService;


    @Override
    public double getAmount(Long id){
        double sum =0;
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(id);
        for (OrderItem list: orderItemList) {
            Product findById = findByIdService.checkIdProduct(list.getProductId());
            double price = Double.parseDouble(findById.getPrice()+"");
            int quantity = list.getQuantity();

            sum += price * quantity;
        }
        return sum;

    }



}
