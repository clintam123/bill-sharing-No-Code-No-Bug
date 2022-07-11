package com.nocodenobug.billsharing.service.customerservice.impl;

import com.nocodenobug.billsharing.model.dto.CustomerDto;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.repository.CustomerRepository;
import com.nocodenobug.billsharing.service.customerservice.CreateCustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerServiceImpl implements CreateCustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto){
        customerDto.setId(null);
        Customer customer=mapper.map(customerDto,Customer.class);
        return mapper.map(customerRepository.save(customer),CustomerDto.class);
    }
}
