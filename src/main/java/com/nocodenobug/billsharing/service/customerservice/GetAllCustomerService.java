package com.nocodenobug.billsharing.service.customerservice;

import com.nocodenobug.billsharing.model.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface GetAllCustomerService {
    Page<CustomerDto> getAllCustomer(int page, int pageSize);
}
