package com.nocodenobug.billsharing.service.user;

import com.nocodenobug.billsharing.model.dto.UserDto;

public interface VerificationUserService {
    UserDto getUserByVerification(String verificationCode);
}
