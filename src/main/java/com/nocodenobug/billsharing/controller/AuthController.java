package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.payload.request.ChangePasswordRequest;
import com.nocodenobug.billsharing.payload.request.LoginRequest;
import com.nocodenobug.billsharing.payload.request.SignupRequest;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.payload.response.UserInfoResponse;
import com.nocodenobug.billsharing.service.auth.AuthService;
import com.nocodenobug.billsharing.service.email.SendEmailService;
import com.nocodenobug.billsharing.service.vendor.CreateVendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        description = "Authentication controller",
        name = "Các api về đăng nhập, đăng xuất "
)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private CreateVendorService createVendorService;

    @PostMapping("/login")
    public ResponseEntity<SampleResponse> loginUser(@Validated @RequestBody LoginRequest loginRequest) {
        UserInfoResponse userLoginResponse = authService.login(loginRequest);
        ResponseCookie jwtCookie = authService.generateJwtCookie();
        userLoginResponse.setAccessToken(jwtCookie.getValue());
        SampleResponse response = new SampleResponse(true, "Đăng nhập thành công!", userLoginResponse);

        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<SampleResponse> signUpUser(@Validated @RequestBody SignupRequest signUpRequest) {

        UserInfoResponse userRegisterResponse = authService.signUp(signUpRequest);

        SampleResponse response = new SampleResponse(true, "Đăng ký thành công!", userRegisterResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Tạo vendor", description = "Tạo vendor")
    @PostMapping("/signup/vendor")
    public ResponseEntity<?> addVendor(@Validated @RequestBody VendorDto vendorDto) {
        return ResponseEntity.ok(DefaultResponse.success(createVendorService.createVendor(vendorDto)));
    }

    @PostMapping("/logout")
    public ResponseEntity<SampleResponse> logoutUser() {
        ResponseCookie jwtCookie = authService.cleanJwtCookie();

        SampleResponse response = new SampleResponse(true, "Đăng xuất thành công!", null);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Validated @RequestBody ChangePasswordRequest userChangePassword) {
        authService.changeMyPassword(userChangePassword);
        return ResponseEntity.status(HttpStatus.OK).body(DefaultResponse.success("Đổi mật khẩu thành công!"));
    }


}
