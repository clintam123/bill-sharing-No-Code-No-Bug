package com.nocodenobug.billsharing.service.vendor;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import org.springframework.stereotype.Service;

@Service
public interface UpdateVendorService {
    VendorDto updateVendor(Long id,VendorDto vendorDto);
}