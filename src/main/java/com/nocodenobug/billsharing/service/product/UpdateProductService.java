package com.nocodenobug.billsharing.service.product;

import com.nocodenobug.billsharing.model.dto.ProductDto;

public interface UpdateProductService {
    ProductDto updateProduct(Long id, ProductDto newProduct);
}
