package com.nocodenobug.billsharing.service.category.impl;

import com.nocodenobug.billsharing.repository.CategoryRepository;
import com.nocodenobug.billsharing.service.category.DeleteCategoryService;
import com.nocodenobug.billsharing.service.category.GetCategoryByIdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryServiceImpl implements DeleteCategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final GetCategoryByIdService getCategoryByIdService;

    @Autowired
    public DeleteCategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, GetCategoryByIdService getCategoryByIdService) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.getCategoryByIdService = getCategoryByIdService;
    }

    @Override
    public void deleteCategory(long id) {
        getCategoryByIdService.getCategoryById(id);
        categoryRepository.deleteById(id);
    }
}
