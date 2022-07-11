package com.nocodenobug.billsharing.reponsitory;

import com.nocodenobug.billsharing.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReponsitory extends JpaRepository<Customer,Long> {
}
