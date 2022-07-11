package com.nocodenobug.billsharing.reponsitory;

import com.nocodenobug.billsharing.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReponsitory extends JpaRepository<Order,Long> {
    Page<Order> findByStatusEquals(Integer status, Pageable pageable);

    Page<Order> findAllByVendorIdAndStatusEquals(Long vendorId,Pageable pageable,Integer status);

    Page<Order> findAllByCustomerIdAndStatusEquals(Long customerId, Pageable pageable, Integer status);

}
