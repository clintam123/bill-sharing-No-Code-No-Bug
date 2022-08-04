package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.daos.FilterProductDao;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.payload.request.FilterRequest;
import com.nocodenobug.billsharing.service.product.GetProductByFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GetProductByFilterImpl implements GetProductByFilter {
    private final FilterProductDao filterProductDao;

    public GetProductByFilterImpl(FilterProductDao filterProductDao){
        this.filterProductDao = filterProductDao;
    }

    @Override
    public Page<?> getProductByFilter(FilterRequest filters, int page, int pageSize) {
        Page<?> products = filterProductDao.searchProductByFilter(filters, PageRequest.of(page, pageSize));
        if (products.isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm phù hợp");
        }
        return products;
    }
}
