package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReviewDto {

    private String title;
    private Integer rating;
    private String content;

    @JsonProperty("customer_id")
    private Integer customerId;
}
