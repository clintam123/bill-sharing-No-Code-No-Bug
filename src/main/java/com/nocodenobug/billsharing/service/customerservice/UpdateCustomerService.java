package com.nocodenobug.billsharing.service.customerservice;

import com.nocodenobug.billsharing.model.dto.CustomerDto;

public interface UpdateCustomerService {


    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
}
