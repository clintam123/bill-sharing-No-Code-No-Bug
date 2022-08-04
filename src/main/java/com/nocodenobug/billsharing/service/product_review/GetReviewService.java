package com.nocodenobug.billsharing.service.product_review;

import org.springframework.data.domain.Page;

public interface GetReviewService {
        Page<?> getReviewsOfProduct(Long productId, int page, int pageSize);
}
