package com.nocodenobug.billsharing.service.category;

import com.nocodenobug.billsharing.model.dto.CategoryDto;

public interface GetCategoryByIdService {
    CategoryDto getCategoryById(long id);
}
