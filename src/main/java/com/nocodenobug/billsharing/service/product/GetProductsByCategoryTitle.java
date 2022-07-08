package com.nocodenobug.billsharing.service.product;

import com.nocodenobug.billsharing.model.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface GetProductsByCategoryTitle {
    Page<ProductDto> getProductsByCategoryTitle(String title, int page, int pageSize);
}
