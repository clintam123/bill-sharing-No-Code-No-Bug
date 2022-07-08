package com.nocodenobug.billsharing.service.order_item.impl;

import com.nocodenobug.billsharing.exception.ExceptionObject;
import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.reponsitory.OrderItemReponsitory;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.service.order_item.UpdateOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UpdateOrderItemServiceImpl implements UpdateOrderItemService {

    @Autowired
    OrderItemReponsitory orderItemReponsitory;

    @Autowired
    private FindByIdService findByIdService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto){
        OrderItem orderItem = modelMapper.map(orderItemDto,OrderItem.class);

        OrderItem findByIdOrder = findByIdService.checkIdOrderItem(id);

        Product product = findByIdService.checkIdProduct(orderItemDto.getProductId());
        Order order = findByIdService.checkIdOrder(orderItemDto.getOrder().getId());

        orderItem.setCreatedAt(findByIdOrder.getCreatedAt());
        orderItem.setUpdatedAt(LocalDateTime.now());
        orderItem.setTotal(findByIdOrder.getTotal());

        return modelMapper.map(orderItemReponsitory.save(orderItem),OrderItemDto.class);
    }

    @Override
    public OrderItemDto updateQuantityOrderItem(Long id, OrderItemDto orderItemDto){
        OrderItem orderItemId = findByIdService.checkIdOrderItem(id);
        if(orderItemId == null){
            throw ExceptionObject.builder().message("OrderItemId khong ton tai").build();
        }

        OrderItem orderItemOld = orderItemId;
        orderItemId = modelMapper.map(orderItemDto,OrderItem.class);

        orderItemId.setId(id);
        orderItemId.setQuantity(orderItemDto.getQuantity());
        orderItemId.setUpdatedAt(LocalDateTime.now());
        orderItemId.setCreatedAt(orderItemOld.getCreatedAt());
        orderItemId.setProductId(orderItemOld.getProductId());
        orderItemId.setOrder(orderItemOld.getOrder());
        orderItemId.setContent(orderItemOld.getContent());

        Product productOld = findByIdService.checkIdProduct(orderItemId.getProductId());

        int quantity = orderItemDto.getQuantity();
        double price = Double.parseDouble(productOld.getPrice() + "");
        BigDecimal total = BigDecimal.valueOf(price * quantity);
        orderItemId.setTotal(total);

        return modelMapper.map(orderItemReponsitory.save(orderItemId),OrderItemDto.class);
    }


}
