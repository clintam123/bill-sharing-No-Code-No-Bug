package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewDto {

    private String title;
    private Integer rating;
    private String content;

    @JsonProperty("customer_id")
    private Integer customerId;

    @JsonProperty("modified_at")
    private LocalDateTime modifiedAt;
}
