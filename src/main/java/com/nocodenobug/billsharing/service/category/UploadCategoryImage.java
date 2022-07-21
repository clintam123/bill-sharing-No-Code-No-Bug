package com.nocodenobug.billsharing.service.category;

import org.springframework.web.multipart.MultipartFile;

public interface UploadCategoryImage {
    String uploadCategoryImage(Long categoryId, MultipartFile file);
}
