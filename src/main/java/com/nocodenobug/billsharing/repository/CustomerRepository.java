package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findCustomerByPhone(String phone);
}
