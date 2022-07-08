package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;

public interface UpdateReviewService {
    ProductReviewDto updateReview(int reviewId, ProductReviewDto review);
}
