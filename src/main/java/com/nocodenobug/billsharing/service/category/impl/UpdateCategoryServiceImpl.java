package com.nocodenobug.billsharing.service.category.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.repository.CategoryRepository;
import com.nocodenobug.billsharing.service.category.UpdateCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateCategoryServiceImpl implements UpdateCategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UpdateCategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto newCategory) {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id category NotFound"));
        category.setTitle(newCategory.getTitle());
        category.setCode(newCategory.getCode());
        category.setContent(newCategory.getContent());
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }
}
