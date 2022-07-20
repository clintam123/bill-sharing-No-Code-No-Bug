package com.nocodenobug.billsharing.service.user.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.service.user.UpdateUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public UserDto updateUser(Long id, UserDto userDto){
        if (userRepository.findById(id).isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id user NotFound");
        }
        User user=mapper.map(userDto,User.class);
        user.setId(id);
        return mapper.map(userRepository.save(user),UserDto.class);
    }
}
