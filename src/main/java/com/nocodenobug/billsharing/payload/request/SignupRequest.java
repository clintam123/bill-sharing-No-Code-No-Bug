package com.nocodenobug.billsharing.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Size(min = 5, max = 40, message = "5 <= độ dài password <= 40")
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @JsonProperty("last_name")
    private String lastName;

//    @NotBlank
//    @JsonProperty("image_url")
//    private String imageUrl;

    private String provider = "local";

    @NotBlank
    private String phone;

    @NotBlank
    private String role;
}
