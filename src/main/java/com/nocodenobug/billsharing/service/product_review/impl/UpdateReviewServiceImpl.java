package com.nocodenobug.billsharing.service.product_review.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.model.entity.ProductReview;
import com.nocodenobug.billsharing.repository.ProductReviewRepository;
import com.nocodenobug.billsharing.service.product_review.UpdateReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UpdateReviewServiceImpl implements UpdateReviewService {
    private final ProductReviewRepository productReviewRepository;

    @Autowired
    public UpdateReviewServiceImpl(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    @Override
    public ProductReviewDto updateReview(Long reviewId, ProductReviewDto review) {
        Optional<ProductReview> productReview = productReviewRepository.findById(reviewId);
        if (productReview.isPresent()) {
            ProductReview product = productReview.get();
            product.setContent(review.getContent());
            product.setTitle(review.getTitle());
            product.setRating(review.getRating());
            review.setModifiedAt(LocalDateTime.now());
            product.setModifiedAt(review.getModifiedAt());
            productReviewRepository.save(product);
            return review;
        }
        throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_REVIEW);
    }
}
