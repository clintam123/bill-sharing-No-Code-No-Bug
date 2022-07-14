package com.nocodenobug.billsharing.service.auth;

import com.nocodenobug.billsharing.payload.request.ChangePasswordRequest;
import com.nocodenobug.billsharing.payload.request.LoginRequest;
import com.nocodenobug.billsharing.payload.request.SignupRequest;
import com.nocodenobug.billsharing.payload.response.UserInfoResponse;
import org.springframework.http.ResponseCookie;

public interface AuthService {

    UserInfoResponse login(LoginRequest loginRequest);

    UserInfoResponse signUp(SignupRequest signupRequest);

    void changeMyPassword(ChangePasswordRequest userChangePassword);

    ResponseCookie generateJwtCookie();

    ResponseCookie cleanJwtCookie();

}