package com.nocodenobug.billsharing.service.product;

import com.nocodenobug.billsharing.model.dto.ProductDto;

public interface CreateProductService {
    ProductDto createProduct(ProductDto newProduct);
}
