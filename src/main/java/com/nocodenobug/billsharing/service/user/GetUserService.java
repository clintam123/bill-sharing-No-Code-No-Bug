package com.nocodenobug.billsharing.service.user;

import com.nocodenobug.billsharing.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface GetUserService {
    Page<UserDto> getAllUser(int page, int pageSize);

    UserDto getById(Long id);

    UserDto findUserBySdt(String sdt);
}
