package com.nocodenobug.billsharing.service.customerservice;

import com.nocodenobug.billsharing.model.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface CreateCustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
}
