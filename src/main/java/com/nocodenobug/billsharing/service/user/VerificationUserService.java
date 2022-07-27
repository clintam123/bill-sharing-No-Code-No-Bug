package com.nocodenobug.billsharing.service.user;

import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;

public interface VerificationUserService {
    UserDto getUserByVerification(String verificationCode);
}
