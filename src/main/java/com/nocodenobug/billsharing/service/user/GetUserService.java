package com.nocodenobug.billsharing.service.user;

import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.payload.response.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetUserService {
    Page<UserDto> getAllUser(int page, int pageSize);

    UserDto getById(Long id);

    List<UserInfoResponse> getByRoleAndUsernameOrPhone(String role, String username, String phone);

    User findUserByEmail(String email);
}
