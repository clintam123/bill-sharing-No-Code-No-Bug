package com.nocodenobug.billsharing.service.product_review;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;

public interface UpdateReviewService {
    ProductReviewDto updateReview(Long reviewId, ProductReviewDto review);
}
