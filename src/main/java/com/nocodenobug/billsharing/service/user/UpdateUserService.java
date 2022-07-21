package com.nocodenobug.billsharing.service.user;

import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.payload.request.UserChangeInfoRequest;
import org.springframework.stereotype.Service;

@Service
public interface UpdateUserService {
    UserDto updateUser(Long id, UserChangeInfoRequest userDto);
}
