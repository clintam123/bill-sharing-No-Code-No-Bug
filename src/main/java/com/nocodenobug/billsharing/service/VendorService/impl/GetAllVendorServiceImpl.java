package com.nocodenobug.billsharing.service.VendorService.impl;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendorRepository;
import com.nocodenobug.billsharing.service.VendorService.GetAllVendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllVendorServiceImpl implements GetAllVendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<VendorDto> getAllVendor(int page, int pageSize){
        Pageable pageable= PageRequest.of(page,pageSize);
        Page<Vendor> vendorPage= vendorRepository.findAll(pageable);
        return vendorPage.map(vendor -> mapper.map(vendor,VendorDto.class));
    }

}
