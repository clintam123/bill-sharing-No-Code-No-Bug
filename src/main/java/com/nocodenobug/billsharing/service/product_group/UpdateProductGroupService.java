package com.nocodenobug.billsharing.service.product_group;

import com.nocodenobug.billsharing.model.dto.ProductGroupDto;

public interface UpdateProductGroupService {
    ProductGroupDto updateProductGroup(Long id, ProductGroupDto productGroupDto);
}
