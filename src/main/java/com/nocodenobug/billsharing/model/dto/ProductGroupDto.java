package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductGroupDto {
    private Long id;
    @NotBlank(message = "Tên nhóm sản phẩm không được để trống")
    @Size(min = 1, max = 100, message = "Tên nhóm sản phẩm phải nhỏ hơn 100 kí tự")
    private String name;
    private String description;
    @JsonProperty("vendor_id")
    private Long vendorId;
}
