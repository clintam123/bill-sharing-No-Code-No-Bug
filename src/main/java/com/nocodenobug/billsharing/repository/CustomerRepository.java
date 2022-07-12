package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.response.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findCustomerByPhone(String phone);
}
