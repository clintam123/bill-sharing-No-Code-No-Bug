package com.nocodenobug.billsharing.service.vendor;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CreateVendorService {
    VendorDto createVendor(VendorDto vendorDto, MultipartFile file);
}
