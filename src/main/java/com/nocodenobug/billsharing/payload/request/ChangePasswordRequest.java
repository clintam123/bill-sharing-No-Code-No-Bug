package com.nocodenobug.billsharing.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ChangePasswordRequest {
    @NotBlank(message = "password hiện tại không được để trống!")
    private String currentPassword;

    @NotBlank(message = "password mới không được để trống!")
    @Size(min = 6, max = 40, message = "password mới phải ít nhất 8 ký tự!")
    private String newPassword;

    @NotBlank(message = "xác nhận lại password mới!")
    private String newPasswordConfirm;
}
