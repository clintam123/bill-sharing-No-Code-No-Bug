package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductReviewDto {
    @JsonProperty("product_id")
    private Long productId;

    private String title;
    private Integer rating;
    private String content;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("modified_at")
    private LocalDateTime modifiedAt;
}
