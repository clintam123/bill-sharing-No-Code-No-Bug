package com.nocodenobug.billsharing.service.category;

import com.nocodenobug.billsharing.model.dto.CategoryDto;

public interface UpdateCategoryService {
    CategoryDto updateCategory(Long id, CategoryDto newCategory);
}
