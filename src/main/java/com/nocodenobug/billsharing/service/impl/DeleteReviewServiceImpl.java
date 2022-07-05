package com.nocodenobug.billsharing.service.impl;

import com.nocodenobug.billsharing.model.entity.ProductReview;
import com.nocodenobug.billsharing.repository.ProductReviewRepository;
import com.nocodenobug.billsharing.service.DeleteReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteReviewServiceImpl implements DeleteReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Override
    public Boolean deleteReview(int reviewId) {
        productReviewRepository.deleteById(reviewId);
        Optional<ProductReview> product = productReviewRepository.findById(reviewId);
        return product.isEmpty();
    }
}
