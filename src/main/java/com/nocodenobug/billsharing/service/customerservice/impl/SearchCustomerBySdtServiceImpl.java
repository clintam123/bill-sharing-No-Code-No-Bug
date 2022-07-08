package com.nocodenobug.billsharing.service.customerservice.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.CustomerDto;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.repository.CustomerRepository;
import com.nocodenobug.billsharing.service.customerservice.SearchCustomerBySdtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchCustomerBySdtServiceImpl implements SearchCustomerBySdtService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerDto findCustomerBySdt(String sdt){
     Optional<Customer> customer=customerRepository.findCustomerByPhone(sdt);
     if (customer.isPresent()){
         return mapper.map(customer.get(),CustomerDto.class);
     }else {
         throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Phone number customer NotFound");
     }
    }
}
