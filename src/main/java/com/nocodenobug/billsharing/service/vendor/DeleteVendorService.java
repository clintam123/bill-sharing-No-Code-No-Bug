package com.nocodenobug.billsharing.service.vendor;

import org.springframework.stereotype.Service;

@Service
public interface DeleteVendorService {
    void deleteVendor(Long id);
}
