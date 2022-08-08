package com.nocodenobug.billsharing.service.product;

import com.nocodenobug.billsharing.model.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface GetProductsByCategoryTitle {
    Page<?> getProductsByCategoryTitle(String title, int page, int pageSize);
}
