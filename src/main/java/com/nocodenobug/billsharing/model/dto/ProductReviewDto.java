package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ProductReviewDto {
    @JsonProperty("product_id")
    private Integer productId;

    private String title;
    private Integer rating;
    private String content;

    @JsonProperty("customer_id")
    private Integer customerId;
}
