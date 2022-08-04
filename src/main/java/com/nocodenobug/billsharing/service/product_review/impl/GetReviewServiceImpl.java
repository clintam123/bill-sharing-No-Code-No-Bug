package com.nocodenobug.billsharing.service.product_review.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.daos.ProductReviewDao;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.service.product_review.GetReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetReviewServiceImpl implements GetReviewService {
    private final ProductReviewDao productReviewDao;

    @Autowired
    public GetReviewServiceImpl(ProductReviewDao productReviewDao) {
        this.productReviewDao = productReviewDao;
    }

    @Override
    public Page<?> getReviewsOfProduct(Long productId, int page, int pageSize) {
        Page<?> reviews = productReviewDao.getUserWithReviewByProductId(productId, PageRequest.of(page, pageSize));
        if (reviews.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusConstant.NO_MORE_COMMENT);
        }
        return reviews;
    }

}
