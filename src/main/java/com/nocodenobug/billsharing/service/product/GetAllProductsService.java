package com.nocodenobug.billsharing.service.product;

import com.nocodenobug.billsharing.model.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface GetAllProductsService {
    Page<ProductDto> getAllProducts(long vendorId, int page, int pageSize);
}
