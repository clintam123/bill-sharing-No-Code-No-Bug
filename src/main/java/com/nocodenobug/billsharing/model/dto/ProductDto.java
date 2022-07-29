package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class ProductDto {
    private Long id;
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 1, max = 100, message = "Tên sản phẩm phải nhỏ hơn 100 kí tự")
    private String title;
    private String description;
    private String sku;
    @Positive(message = "Giá sản phẩm phải lớn hơn 0")
    private float price;
    private float discount;
    @Positive(message = "Số lượng sản phẩm phải lớn hơn 0")
    private int quantity;

    private String image_url;
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("product_group_id")
    private Long productGroupId;
}
