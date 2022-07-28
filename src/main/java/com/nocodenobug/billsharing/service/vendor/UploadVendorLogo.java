package com.nocodenobug.billsharing.service.vendor;

import org.springframework.web.multipart.MultipartFile;

public interface UploadVendorLogo {
    String uploadLogo(Long vendorId, MultipartFile file);
}
