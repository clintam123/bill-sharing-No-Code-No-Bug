package com.nocodenobug.billsharing.service.product;

import com.nocodenobug.billsharing.model.dto.ProductDto;

public interface GetProductByIdService {
    ProductDto getProductById(Long id);
}
