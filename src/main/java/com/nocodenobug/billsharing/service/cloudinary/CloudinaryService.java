package com.nocodenobug.billsharing.service.cloudinary;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CloudinaryService {
    String uploadImage(MultipartFile file, String folderName);

    String getUrlImage(String publicId);
}
