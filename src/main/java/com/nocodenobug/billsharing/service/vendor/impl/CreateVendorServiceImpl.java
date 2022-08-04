package com.nocodenobug.billsharing.service.vendor.impl;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendorRepository;
import com.nocodenobug.billsharing.service.vendor.CreateVendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVendorServiceImpl implements CreateVendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public VendorDto createVendor(VendorDto vendorDto){
        vendorDto.setId(null);
        Vendor vendor=mapper.map(vendorDto,Vendor.class);
        return mapper.map(vendorRepository.save(vendor),VendorDto.class);
    }

}
