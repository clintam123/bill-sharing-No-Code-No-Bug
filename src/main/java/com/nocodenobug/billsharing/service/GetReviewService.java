package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetReviewService {
        Page<ProductReviewDto> getReviewsOfProduct(int productId, int page, int pageSize);
}
