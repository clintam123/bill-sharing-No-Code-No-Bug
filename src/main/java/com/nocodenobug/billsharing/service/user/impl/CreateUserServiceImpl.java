package com.nocodenobug.billsharing.service.user.impl;

import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.service.user.CreateUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImpl implements CreateUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto){
        userDto.setId(null);
        User user=mapper.map(userDto,User.class);
        return mapper.map(userRepository.save(user),UserDto.class);
    }

}
