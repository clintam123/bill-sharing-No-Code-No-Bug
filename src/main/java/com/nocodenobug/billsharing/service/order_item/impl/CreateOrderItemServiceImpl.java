package com.nocodenobug.billsharing.service.order_item.impl;

import com.nocodenobug.billsharing.constants.OrderStatus;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.repository.OrderItemRepository;
import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.service.order.UpdateOrderService;
import com.nocodenobug.billsharing.service.order_item.CreateOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreateOrderItemServiceImpl implements CreateOrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UpdateOrderService updateOrderService;

    @Autowired
    private FindByIdService findByIdService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
        orderItemRepository.save(orderItem);
        return modelMapper.map(orderItem, OrderItemDto.class);
    }

    public Product updateQuantityProduct(Long id, Integer quantity) {
        Product product = findByIdService.checkIdProduct(id);
        product.setQuantity(quantity);
        return productRepository.save(product);
    }


}
