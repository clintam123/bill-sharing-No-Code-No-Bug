package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String sku;
    private float price;
    private float discount;
    private int quantity;

    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("product_group_id")
    private Long productGroupId;
}
