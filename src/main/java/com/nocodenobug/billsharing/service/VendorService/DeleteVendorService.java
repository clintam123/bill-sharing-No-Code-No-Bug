package com.nocodenobug.billsharing.service.VendorService;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import org.springframework.stereotype.Service;

@Service
public interface DeleteVendorService {
    void deleteVendor(Long id);
}
