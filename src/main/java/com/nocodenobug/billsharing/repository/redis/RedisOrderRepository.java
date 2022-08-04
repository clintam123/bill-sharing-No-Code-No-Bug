package com.nocodenobug.billsharing.repository.redis;

import com.nocodenobug.billsharing.model.entity.redis.Order;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.Optional;

public interface RedisOrderRepository extends RedisDocumentRepository<Order, String> {
    Order findByLink(String link);
}
