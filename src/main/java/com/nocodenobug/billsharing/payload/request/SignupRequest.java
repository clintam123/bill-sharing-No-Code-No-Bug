package com.nocodenobug.billsharing.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20, message = "3 <= độ dài username <= 20")
    private String username;

    @NotBlank
    @Size(min = 6, max = 40, message = "6 <= độ dài password <= 40")
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String role;
}
