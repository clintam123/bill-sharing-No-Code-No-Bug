package com.nocodenobug.billsharing.service.user;

import com.nocodenobug.billsharing.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UpdateUserService {
    UserDto updateUser(Long id, UserDto userDto);
}
