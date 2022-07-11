package com.nocodenobug.billsharing.reponsitory;

import com.nocodenobug.billsharing.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemReponsitory extends JpaRepository<OrderItem,Long> {

    List<OrderItem>  findAllByOrderId(Long orderId);

}
