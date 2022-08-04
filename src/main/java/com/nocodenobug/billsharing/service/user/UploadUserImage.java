package com.nocodenobug.billsharing.service.user;

import org.springframework.web.multipart.MultipartFile;

public interface UploadUserImage {
    String uploadAvatar(Long userId, MultipartFile file);
}
