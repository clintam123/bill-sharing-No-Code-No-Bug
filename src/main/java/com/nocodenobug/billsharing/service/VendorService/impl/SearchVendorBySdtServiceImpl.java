package com.nocodenobug.billsharing.service.VendorService.impl;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendoRepository;
import com.nocodenobug.billsharing.service.VendorService.SearchVendorBySdtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchVendorBySdtServiceImpl implements SearchVendorBySdtService {
    @Autowired
    private VendoRepository vendoRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public VendorDto searchVendorBySdt(String sdt){
        Vendor vendor=vendoRepository.findAllByPhone(sdt);
        return mapper.map(vendor,VendorDto.class);
    }
}
