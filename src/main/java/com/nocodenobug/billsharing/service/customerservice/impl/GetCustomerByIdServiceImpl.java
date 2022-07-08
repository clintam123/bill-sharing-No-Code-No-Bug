package com.nocodenobug.billsharing.service.customerservice.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.CustomerDto;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.repository.CustomerRepository;
import com.nocodenobug.billsharing.service.customerservice.GetCustomerByIdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCustomerByIdServiceImpl implements GetCustomerByIdService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerDto getById(Long id){
            Optional<Customer> customer=customerRepository.findById(id);
            if (customer.isPresent()){
                return mapper.map(customer.get(), CustomerDto.class);
            }
            throw new NotFoundException(HttpStatus.BAD_REQUEST.value(), "Id customer NotFound",null );




    }
}
