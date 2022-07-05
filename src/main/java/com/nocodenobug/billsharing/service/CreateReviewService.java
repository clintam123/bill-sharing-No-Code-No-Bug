package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;

public interface CreateReviewService {
    ProductReviewDto createReview(ProductReviewDto review);
}
