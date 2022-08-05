package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.repository.redis.RedisOrderRepository;
import com.nocodenobug.billsharing.service.group_order.impl.OrderServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/redis")
public class RedisTestController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ModelMapper modelMapper;

}
