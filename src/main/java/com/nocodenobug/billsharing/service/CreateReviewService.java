package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.model.entity.ProductReview;

public interface CreateReviewService {
    ProductReviewDto createReview(int productId, ProductReviewDto newReview);
}
