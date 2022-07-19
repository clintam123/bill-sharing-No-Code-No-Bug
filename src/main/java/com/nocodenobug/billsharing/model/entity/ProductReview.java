package com.nocodenobug.billsharing.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("product_review_id")
    @Column(name = "product_review_id")
    private Integer productReviewId;

    @JsonProperty("product_id")
    @Column(name = "product_id")
    private Integer productId;

    private String title;
    private Integer rating;
    private String content;

    @JsonProperty("customer_id")
    private Integer customerId;

    @JsonProperty("created_at")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty("modified_at")
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
