package com.nocodenobug.billsharing.service.product;

import com.nocodenobug.billsharing.model.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

public interface CreateProductService {
    ProductDto createProduct(ProductDto newProduct, MultipartFile file);
}
