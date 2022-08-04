package com.nocodenobug.billsharing.service.user.impl;

import com.cloudinary.Cloudinary;
import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.service.user.CreateUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImpl implements CreateUserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final Cloudinary cloudinary;

    @Autowired
    public CreateUserServiceImpl(UserRepository userRepository, ModelMapper mapper, Cloudinary cloudinary) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.cloudinary = cloudinary;
    }

    @Override
    public UserDto createUser(UserDto userDto){
        userDto.setImageUrl(cloudinary.url().generate(FolderConstants.AVATAR_DEFAULT_IMAGE_PUBLIC_ID));
        userRepository.save(mapper.map(userDto, User.class));
        return userDto;
    }
}
