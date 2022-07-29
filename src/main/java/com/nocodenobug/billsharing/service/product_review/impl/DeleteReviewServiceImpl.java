package com.nocodenobug.billsharing.service.product_review.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.model.entity.ProductReview;
import com.nocodenobug.billsharing.repository.ProductReviewRepository;
import com.nocodenobug.billsharing.service.product_review.DeleteReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteReviewServiceImpl implements DeleteReviewService {
    private final ProductReviewRepository productReviewRepository;

    @Autowired
    public DeleteReviewServiceImpl(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    @Override
    public Boolean deleteReview(Long reviewId) {
        productReviewRepository.deleteById(reviewId);
        Optional<ProductReview> product = productReviewRepository.findById(reviewId);
        product.map(productReview -> new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_REVIEW));
        return true;
    }
}
