package com.nocodenobug.billsharing.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file, String folder) {
        try {
            if (Objects.requireNonNull(file.getContentType()).contains("image")) {
                Map<?,?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                        "folder", folder
                ));
                return this.getUrlImage(uploadResult.get("public_id").toString());
            }else {
                throw new IllegalArgumentException("File is not an image");
            }
        } catch (IOException | IllegalArgumentException e) {
            return null;
        }
    }

    public String getUrlImage(String publicId) {
        return cloudinary.url().generate(publicId);
    }
}