package com.nocodenobug.billsharing.service.user.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.service.user.DeleteUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserServiceImpl implements DeleteUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void deleteUser(Long id){
        if (userRepository.findById(id).isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id user NotFound");
        }
        userRepository.deleteById(id);
    }
}
