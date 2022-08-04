package com.nocodenobug.billsharing.service.user.impl;

import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.service.user.VerificationUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationUserServiceImpl implements VerificationUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getUserByVerification(String verificationCode){
        return modelMapper.map(userRepository.findUserByVerificationCode(verificationCode),UserDto.class);
    }
}
