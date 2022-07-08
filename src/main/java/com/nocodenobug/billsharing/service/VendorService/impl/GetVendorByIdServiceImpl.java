package com.nocodenobug.billsharing.service.VendorService.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendoRepository;
import com.nocodenobug.billsharing.service.VendorService.GetVendorByIdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetVendorByIdServiceImpl implements GetVendorByIdService {
    @Autowired
    private VendoRepository vendoRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public VendorDto getVendorById(Long id){
        Optional<Vendor> vendor=vendoRepository.findById(id);
        if (vendor.isPresent()){
            return mapper.map(vendor.get(),VendorDto.class);
        }

        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id vendor NotFound",null );
    }
}
