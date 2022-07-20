package com.nocodenobug.billsharing.service.cloudinary.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nocodenobug.billsharing.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile file, String folder) {
        try {
            if (Objects.requireNonNull(file.getContentType()).contains("image")) {
                Map<?,?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                        "folder", folder
                ));
                return uploadResult.get("public_id").toString();
            } else {
                throw new IllegalArgumentException("File is not an image");
            }
        } catch (IOException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @Override
    public String getUrlImage(String publicId) {
        return cloudinary.url().imageTag(publicId);
    }
}