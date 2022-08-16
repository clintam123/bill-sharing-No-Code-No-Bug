package com.nocodenobug.billsharing.service.vendor.impl;


import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendorRepository;
import com.nocodenobug.billsharing.service.vendor.UpdateVendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateVendorServiceImpl implements UpdateVendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public VendorDto updateVendor(Long id, VendorDto vendorDto) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id vendor NotFound")
        );
        String logo = vendor.getLogo();
        vendor = mapper.map(vendorDto, Vendor.class);
        vendor.setId(id);
        vendor.setLogo(logo);
        System.out.println("----" + vendor);
        return mapper.map(vendorRepository.save(vendor), VendorDto.class);
    }
}
