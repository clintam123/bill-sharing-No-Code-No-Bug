package com.nocodenobug.billsharing.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GroupLinkRequest {
    @NotBlank(message = "Link không được để trống")
    @Size(min = 10, max = 10, message = "Link phải đúng 10 kí tự")
    private String link;
}
