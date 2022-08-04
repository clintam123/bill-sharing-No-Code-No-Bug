package com.nocodenobug.billsharing.service.product;

import org.springframework.web.multipart.MultipartFile;

public interface UploadProductImage {
    String uploadProductImage(Long productId, MultipartFile file);
}
