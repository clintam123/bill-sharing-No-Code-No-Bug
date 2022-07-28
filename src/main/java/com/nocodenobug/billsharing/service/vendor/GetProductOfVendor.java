package com.nocodenobug.billsharing.service.vendor;

import org.springframework.data.domain.Page;


public interface GetProductOfVendor {
    Page<?> listProductOfVendor(String vendorName, int page, int pageSize);
}
