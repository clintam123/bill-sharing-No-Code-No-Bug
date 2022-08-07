package com.nocodenobug.billsharing.service.user;

import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface GetUserService {
    Page<UserDto> getAllUser(int page, int pageSize);

    UserDto getById(Long id);

    Object getByUsernameOrPhone(String username, String phone);

    User finUserByEmail(String email);
}
