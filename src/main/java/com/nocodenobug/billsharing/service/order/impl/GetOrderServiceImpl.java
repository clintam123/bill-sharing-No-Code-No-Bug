package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.constants.OrderStatus;
import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.repository.OrderRepository;
import com.nocodenobug.billsharing.service.order.GetOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetOrderServiceImpl implements GetOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<OrderDto> findAllByStatus(int page, int pageSize){
        Page<Order> order = orderRepository.findByStatusEquals(
                OrderStatus.ACTIVE.getStatus(), PageRequest.of(page,pageSize));

        return order.map(order1 -> modelMapper.map(order1, OrderDto.class));
    }

    @Override
    public Page<OrderDto> findAllByVendorId(Long id, int page, int page_size){
        Page<Order> orders = orderRepository.findAllByVendorIdAndStatusEquals(id,PageRequest.of(page,page_size),OrderStatus.ACTIVE.getStatus());

        return orders.map(order -> modelMapper.map(order,OrderDto.class));
    }

    @Override
    public Page<OrderDto> findAllByCustomerId(Long id, int page, int page_size){
        Page<Order> orders = orderRepository.findAllByCustomerIdAndStatusEquals(id,PageRequest.of(page,page_size),OrderStatus.ACTIVE.getStatus());

        return orders.map(order -> modelMapper.map(order, OrderDto.class));
    }

    @Override
    public OrderDto findById(Long id){
        Order order = orderRepository.findById(id).get();

        return modelMapper.map(order,OrderDto.class);
    }

}
