package com.nocodenobug.billsharing.service.VendorService.impl;


import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendoRepository;
import com.nocodenobug.billsharing.service.VendorService.UpdateVendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateVendorServiceImpl implements UpdateVendorService {
    @Autowired
    private VendoRepository vendoRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public VendorDto updateVendor(Long id,VendorDto vendorDto){
        if (vendoRepository.findById(id).isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id vendor NotFound");

        }
        Vendor vendor=mapper.map(vendorDto,Vendor.class);
        vendor.setId(id);
        return mapper.map(vendoRepository.save(vendor),VendorDto.class);
    }
}
