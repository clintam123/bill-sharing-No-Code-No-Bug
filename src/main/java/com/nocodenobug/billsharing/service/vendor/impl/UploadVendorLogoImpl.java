package com.nocodenobug.billsharing.service.vendor.impl;

import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendorRepository;
import com.nocodenobug.billsharing.service.CloudinaryService;
import com.nocodenobug.billsharing.service.vendor.UploadVendorLogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadVendorLogoImpl implements UploadVendorLogo {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public String uploadLogo(Long vendorId, MultipartFile file) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Vendor not found"));
        String logo = cloudinaryService.uploadImage(file, FolderConstants.VENDOR_LOGO_FOLDER);
        vendor.setLogo(logo);
        vendorRepository.save(vendor);
        return logo;
    }
}
