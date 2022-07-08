package com.nocodenobug.billsharing.service.VendorService.impl;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendoRepository;
import com.nocodenobug.billsharing.service.VendorService.CreateVendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVendorServiceImpl implements CreateVendorService {
    @Autowired
    private VendoRepository vendoRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public VendorDto createVendor(VendorDto vendorDto){
        vendorDto.setId(null);
        Vendor vendor=mapper.map(vendorDto,Vendor.class);
        return mapper.map(vendoRepository.save(vendor),VendorDto.class);
    }

}
