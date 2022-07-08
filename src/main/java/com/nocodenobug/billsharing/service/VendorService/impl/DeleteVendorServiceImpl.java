package com.nocodenobug.billsharing.service.VendorService.impl;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendoRepository;

import com.nocodenobug.billsharing.service.VendorService.DeleteVendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteVendorServiceImpl implements DeleteVendorService {
    @Autowired
    private VendoRepository vendoRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GetVendorByIdServiceImpl getVendorByIdService;
    @Override
    public void deleteVendor(Long id){
        VendorDto vendorDto= getVendorByIdService.getVendorById(id);
        vendoRepository.deleteById(id);

    }
}
