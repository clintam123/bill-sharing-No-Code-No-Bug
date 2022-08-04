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
    public Page<OrderDto> findAllByStatus(Integer page, Integer pageSize){
        Page<Order> orders = orderRepository.findByStatusEquals(
                OrderStatus.ACTIVE.getStatus(), PageRequest.of(page,pageSize));

        return orders.map(order -> modelMapper.map(order, OrderDto.class));
    }


    @Override
    public Page<OrderDto> findAllByVendorId(Long id, Integer page, Integer page_size){

        Page<Order> orders = orderRepository.findAllByVendorIdAndStatusEquals(
                id,PageRequest.of(page,page_size),OrderStatus.ACTIVE.getStatus());

        return orders.map(order -> modelMapper.map(order,OrderDto.class));
    }

    @Override
    public Page<OrderDto> findAllByUserId(Long id, Integer page, Integer page_size){

        Page<Order> orders = orderRepository.findAllByUserIdAndStatusEquals(
                id,PageRequest.of(page,page_size),OrderStatus.ACTIVE.getStatus());
        return orders.map(order -> modelMapper.map(order,OrderDto.class));
    }


    @Override
    public OrderDto findById(Long id){
        Order order = orderRepository.findById(id).get();

        return modelMapper.map(order,OrderDto.class);
    }

}
