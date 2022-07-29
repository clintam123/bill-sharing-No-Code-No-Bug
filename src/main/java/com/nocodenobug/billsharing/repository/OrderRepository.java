package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.model.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findByStatusEquals(Integer status, Pageable pageable);

    Page<Order> findAllByVendorIdAndStatusEquals(Long vendorId,Pageable pageable,Integer status);

    Page<Order> findAllByUserIdAndStatusEquals(Long userId, Pageable pageable, Integer status);

}
