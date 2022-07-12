package com.nocodenobug.billsharing.service.customerservice;

import com.nocodenobug.billsharing.model.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface GetCustomerByIdService {
    CustomerDto getById(Long id);
}
