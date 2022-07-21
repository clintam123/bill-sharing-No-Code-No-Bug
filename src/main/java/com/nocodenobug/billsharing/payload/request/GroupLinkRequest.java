package com.nocodenobug.billsharing.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GroupLinkRequest {
    @NotBlank(message = "Link không được để trống")
    @Size(min = 6, max = 6, message = "Link phải đúng 10 kí tự")
    @JsonProperty("link")
    private String link;
}
