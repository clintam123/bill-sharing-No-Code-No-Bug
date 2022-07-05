package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import org.springframework.http.ResponseEntity;

public interface UpdateReviewService {
    ProductReviewDto updateReview(int reviewId, ProductReviewDto review);
}
