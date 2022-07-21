package com.nocodenobug.billsharing.service.user.impl;

import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.service.CloudinaryService;
import com.nocodenobug.billsharing.service.user.GetUserService;
import com.nocodenobug.billsharing.service.user.UploadUserImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadUserImageImpl implements UploadUserImage {
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;
    private final GetUserService getUserService;
    private final ModelMapper modelMapper;

    @Autowired
    public UploadUserImageImpl(CloudinaryService cloudinaryService, UserRepository userRepository, GetUserService getUserService, ModelMapper modelMapper) {
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.getUserService = getUserService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String uploadAvatar(Long userId, MultipartFile file) {
        UserDto userDto = getUserService.getById(userId);
        String urlImage = cloudinaryService.uploadImage(file, FolderConstants.AVATAR_FOLDER);
        userDto.setImageUrl(urlImage);
        userRepository.save(modelMapper.map(userDto, User.class));
        return urlImage;
    }
}
