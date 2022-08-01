package com.nocodenobug.billsharing.service.vendor.impl;

import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendorRepository;
import com.nocodenobug.billsharing.security.UserDetailsImpl;
import com.nocodenobug.billsharing.service.CloudinaryService;
import com.nocodenobug.billsharing.service.vendor.CreateVendorService;
import com.nocodenobug.billsharing.utils.CurrentUserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateVendorServiceImpl implements CreateVendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public VendorDto createVendor(VendorDto vendorDto, MultipartFile file){
        Vendor vendor=mapper.map(vendorDto,Vendor.class);
        vendor.setLogo(cloudinaryService.uploadImage(file, FolderConstants.VENDOR_LOGO_FOLDER));
        UserDetailsImpl currentUser = CurrentUserUtils.getCurrentUserDetails();
        vendor.setUserId(currentUser.getId());
        return mapper.map(vendorRepository.save(vendor),VendorDto.class);
    }

}
