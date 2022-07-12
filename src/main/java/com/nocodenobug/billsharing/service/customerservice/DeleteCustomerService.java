package com.nocodenobug.billsharing.service.customerservice;

import org.springframework.stereotype.Service;

@Service
public interface DeleteCustomerService {
    void deleteCustomer(Long id);
}
