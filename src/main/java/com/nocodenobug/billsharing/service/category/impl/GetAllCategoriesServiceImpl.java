package com.nocodenobug.billsharing.service.category.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.repository.CategoryRepository;
import com.nocodenobug.billsharing.service.category.GetAllCategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Categories dont exist",null );
    }
}