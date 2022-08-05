package com.nocodenobug.billsharing.repository.redis;

import com.nocodenobug.billsharing.model.entity.redis.OrderRedis;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface RedisOrderRepository extends RedisDocumentRepository<OrderRedis, String> {
    OrderRedis findByLink(String link);
}
