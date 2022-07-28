package com.nocodenobug.billsharing.service.vendor.impl;

import com.nocodenobug.billsharing.constants.ESort;
import com.nocodenobug.billsharing.daos.FilterProductDao;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.payload.request.FilterRequest;
import com.nocodenobug.billsharing.service.vendor.GetProductOfVendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GetProductOfVendorImpl implements GetProductOfVendor {
    @Autowired
    private FilterProductDao filterProductDao;

    @Override
    public Page<?> listProductOfVendor(String vendorName, int page, int pageSize) {
        Page<?> listProduct = filterProductDao.searchProductByFilter(new FilterRequest(vendorName, null, null, ESort.RANDOM.getValue()), PageRequest.of(page, pageSize));
        if (listProduct.isEmpty()) throw new NotFoundException(HttpStatus.NO_CONTENT.value(), "Không còn sản phẩm để hiển thị");
        return listProduct;
    }
}
