package com.nocodenobug.billsharing.service.category.impl;

import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.repository.CategoryRepository;
import com.nocodenobug.billsharing.security.UserDetailsImpl;
import com.nocodenobug.billsharing.service.CloudinaryService;
import com.nocodenobug.billsharing.service.category.CreateCategoryService;
import com.nocodenobug.billsharing.utils.CurrentUserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateCategoryServiceImpl  implements CreateCategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public CreateCategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public CategoryDto createCategory(CategoryDto newCategory) {
        Category category = modelMapper.map(newCategory, Category.class);
        String imgUrl = cloudinaryService.getUrlImage(FolderConstants.VENDOR_DEFAULT_LOGO_PUBLIC_ID);
        category.setImageUrl(imgUrl);
        UserDetailsImpl currentUser = CurrentUserUtils.getCurrentUserDetails();
        category.setUserId(currentUser.getId());
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }
}
