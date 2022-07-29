package com.nocodenobug.billsharing.service.product_review;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;

public interface CreateReviewService {
    ProductReviewDto createReview(Long productId, ProductReviewDto newReview);
}
