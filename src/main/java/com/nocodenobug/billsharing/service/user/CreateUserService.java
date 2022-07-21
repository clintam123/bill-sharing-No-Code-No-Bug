package com.nocodenobug.billsharing.service.user;

import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface CreateUserService {
    UserDto createUser(UserDto userDto);
}
