package com.nocodenobug.billsharing.service.customerservice.impl;

import com.nocodenobug.billsharing.model.dto.CustomerDto;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.repository.CustomerRepository;
import com.nocodenobug.billsharing.service.customerservice.DeleteCustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerServiceImpl implements DeleteCustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GetCustomerByIdServiceImpl getCustomerByIdService;

    @Override
    public void deleteCustomer(Long id){
        CustomerDto dto= getCustomerByIdService.getById(id);
        customerRepository.deleteById(id);
    }
}
