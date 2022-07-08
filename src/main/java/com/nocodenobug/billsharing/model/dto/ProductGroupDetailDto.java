package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductGroupDetailDto extends ProductGroupDto{
    @JsonProperty("product_list")
    private List<ProductDto> productList;
}
