package com.nocodenobug.billsharing.service.user;

import org.springframework.stereotype.Service;

@Service
public interface DeleteUserService {
    void deleteUser(Long id);
}
