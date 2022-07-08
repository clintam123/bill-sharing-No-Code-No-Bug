package com.nocodenobug.billsharing.service.customerservice.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.CustomerDto;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.repository.CustomerRepository;
import com.nocodenobug.billsharing.service.customerservice.UpdateCustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerServiceImpl implements UpdateCustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto){
        if (customerRepository.findById(id).isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id customer NotFound");
        }
        Customer customer=mapper.map(customerDto,Customer.class);
        customer.setId(id);
        return mapper.map(customerRepository.save(customer),CustomerDto.class);
    }
}
