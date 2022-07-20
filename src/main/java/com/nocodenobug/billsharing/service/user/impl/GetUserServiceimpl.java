package com.nocodenobug.billsharing.service.user.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.service.user.GetUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserServiceimpl implements GetUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<UserDto> getAllUser(int page, int pageSize){
        Pageable pageable= PageRequest.of(page,pageSize);
        Page<User> customerPage=userRepository.findAll(pageable);
        return customerPage.map(user -> mapper.map(user, UserDto.class));

    }
    @Override
    public UserDto getById(Long id){
        Optional<User> user=userRepository.findById(id);
        if (user.isPresent()){
            return mapper.map(user.get(), UserDto.class);
        }
        throw new NotFoundException(HttpStatus.BAD_REQUEST.value(), "Id user NotFound");
    }
    @Override
    public UserDto findUserBySdt(String sdt){
        Optional<User> user=userRepository.findUserByPhone(sdt);
        if (user.isPresent()){
            return mapper.map(user.get(),UserDto.class);
        }else {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Phone number user NotFound");
        }
    }

}