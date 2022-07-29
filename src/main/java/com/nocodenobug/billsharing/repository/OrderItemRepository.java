package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem>  findAllByOrderId(Long orderId);



}
