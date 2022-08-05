package com.nocodenobug.billsharing.service.category.impl;

import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.repository.CategoryRepository;
import com.nocodenobug.billsharing.service.category.CreateCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryServiceImpl  implements CreateCategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CreateCategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto newCategory) {
        Category category = modelMapper.map(newCategory, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }
}
