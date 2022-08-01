package com.nocodenobug.billsharing.service.category.impl;

import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.repository.CategoryRepository;
import com.nocodenobug.billsharing.service.CloudinaryService;
import com.nocodenobug.billsharing.service.category.CreateCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateCategoryServiceImpl  implements CreateCategoryService {
    @Autowired
    private CloudinaryService cloudinaryService;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CreateCategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto newCategory, MultipartFile file) {
        Category category = modelMapper.map(newCategory, Category.class);
        String imgUrl = cloudinaryService.uploadImage(file, FolderConstants.CATEGORY_IMAGE_FOLDER);
        category.setImageUrl(imgUrl);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }
}
