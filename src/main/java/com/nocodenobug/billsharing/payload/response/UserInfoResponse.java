package com.nocodenobug.billsharing.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserInfoResponse {
    private Long id;
    private String username;
    private String fullName;
    private String phone;
    private String imageUrl;
    private String email;
    private String role;
    private String accessToken;

    public UserInfoResponse(Long id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UserInfoResponse(Long id, String username, String fullName, String email, String role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }
}
