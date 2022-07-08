package com.nocodenobug.billsharing.service.product_group;

import com.nocodenobug.billsharing.model.dto.ProductGroupDetailDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;

public interface GetProductGroupByIdService {
    ProductGroupDetailDto getProductGroupById(long id);
}
