package com.nocodenobug.billsharing.service.category.impl;

import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDetailDto;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.model.entity.ProductGroup;
import com.nocodenobug.billsharing.repository.CategoryRepository;
import com.nocodenobug.billsharing.service.category.GetAllCategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GetAllCategoriesServiceImpl implements GetAllCategoriesService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GetAllCategoriesServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<CategoryDto> getAllCategories(int page, int pageSize) {
        Page<Category> categories = categoryRepository.findAll(PageRequest.of(page, pageSize));
        if(categories.getTotalElements() > 0){
            return categories.map(category -> modelMapper.map(category, CategoryDto.class));
        }
        throw new EntityNotFoundException("Category not exist");
    }
}
