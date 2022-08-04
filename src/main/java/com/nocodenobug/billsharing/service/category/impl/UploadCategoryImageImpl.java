package com.nocodenobug.billsharing.service.category.impl;

import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.repository.CategoryRepository;
import com.nocodenobug.billsharing.service.CloudinaryService;
import com.nocodenobug.billsharing.service.category.UploadCategoryImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class UploadCategoryImageImpl implements UploadCategoryImage {
    private final CloudinaryService cloudinaryService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public UploadCategoryImageImpl(CloudinaryService cloudinaryService, CategoryRepository categoryRepository) {
        this.cloudinaryService = cloudinaryService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String uploadCategoryImage(Long categoryId, MultipartFile file) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "category not found")
        );
        String imageUrl = cloudinaryService.uploadImage(file, FolderConstants.CATEGORY_IMAGE_FOLDER);
        category.setImageUrl(imageUrl);
        category.setModifiedAt(LocalDateTime.now());
        categoryRepository.save(category);
        return imageUrl;
    }
}
