package com.nocodenobug.billsharing.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {
    private Long id;
    @NotBlank(message = "Tên thẻ loại không được để trống")
    @Size(min = 1, max = 100, message = "Tên thẻ loại phải nhỏ hơn 100 kí tự")
    private String title;
    private String content;
    private String code;
}
