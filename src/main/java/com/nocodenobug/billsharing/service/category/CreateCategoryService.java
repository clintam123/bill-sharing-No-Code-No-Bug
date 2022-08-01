package com.nocodenobug.billsharing.service.category;

import com.nocodenobug.billsharing.model.dto.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

public interface CreateCategoryService {
    CategoryDto createCategory(CategoryDto category, MultipartFile file);
}
