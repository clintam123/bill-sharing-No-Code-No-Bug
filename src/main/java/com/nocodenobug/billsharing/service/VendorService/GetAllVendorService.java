package com.nocodenobug.billsharing.service.VendorService;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface GetAllVendorService {
    Page<VendorDto> getAllVendor(int page, int pageSize);
}
