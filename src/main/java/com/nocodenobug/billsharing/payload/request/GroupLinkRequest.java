package com.nocodenobug.billsharing.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class GroupLinkRequest {
    @NotBlank
    private Long userId;

    @NotBlank
    private Long vendorId;

    private Float shipping;

}
