package com.nocodenobug.billsharing.service.customerservice.impl;

import com.nocodenobug.billsharing.model.dto.CustomerDto;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.repository.CustomerRepository;
import com.nocodenobug.billsharing.service.customerservice.GetAllCustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllCustomerServiceImpl implements GetAllCustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper ;

    @Override
    public Page<CustomerDto> getAllCustomer(int page, int pageSize){
        Pageable pageable=PageRequest.of(page,pageSize);
        Page<Customer> customerPage=customerRepository.findAll(pageable);
        return customerPage.map(customer -> mapper.map(customer, CustomerDto.class));

    }
}
