package com.nocodenobug.billsharing.service.product;

import com.nocodenobug.billsharing.payload.request.FilterRequest;
import org.springframework.data.domain.Page;

public interface GetProductByFilter {
    Page<?> getProductByFilter(FilterRequest filters, int page, int pageSize);
}
