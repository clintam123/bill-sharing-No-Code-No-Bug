package com.nocodenobug.billsharing.service;

import org.springframework.data.domain.Page;

public interface GetReviewService {
        Page getReviewsOfProduct(int productId, int page, int pageSize);
}
