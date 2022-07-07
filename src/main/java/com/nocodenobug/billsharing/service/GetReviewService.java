package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.model.dto.CustomerReviewDto;
import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface GetReviewService {
        Page<CustomerReviewDto> getReviewsOfProduct(int productId, int page, int pageSize);
}
