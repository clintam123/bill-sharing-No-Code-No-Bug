package com.nocodenobug.billsharing.service.product_group;

import com.nocodenobug.billsharing.model.dto.ProductGroupDetailDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import org.springframework.data.domain.Page;

public interface GetAllProductGroupsService {
    Page<ProductGroupDetailDto> getAllProductGroups(long vendorId, int page, int pageSize);
}
