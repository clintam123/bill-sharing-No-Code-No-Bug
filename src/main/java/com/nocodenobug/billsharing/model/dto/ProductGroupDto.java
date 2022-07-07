package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductGroupDto {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("vendor_id")
    private Long vendorId;
}
