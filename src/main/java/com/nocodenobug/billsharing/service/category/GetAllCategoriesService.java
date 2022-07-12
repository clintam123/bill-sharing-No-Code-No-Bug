package com.nocodenobug.billsharing.service.category;

import com.nocodenobug.billsharing.model.dto.CategoryDto;
import org.springframework.data.domain.Page;

public interface GetAllCategoriesService {
    Page<CategoryDto> getAllCategories(int page, int pageSize);
}
