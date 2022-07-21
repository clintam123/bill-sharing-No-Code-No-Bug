package com.nocodenobug.billsharing.service.product_review.impl;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.model.entity.ProductReview;
import com.nocodenobug.billsharing.repository.ProductReviewRepository;
import com.nocodenobug.billsharing.service.product_review.CreateReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateReviewServiceImpl implements CreateReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CreateReviewServiceImpl(ProductReviewRepository productReviewRepository, ModelMapper modelMapper) {
        this.productReviewRepository = productReviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductReviewDto createReview(Long productId, ProductReviewDto newReview) {
        newReview.setModifiedAt(LocalDateTime.now());
        ProductReview review = modelMapper.map(newReview, ProductReview.class);
        review.setProductId(productId);
        review.setCreatedAt(LocalDateTime.now());
        review.setModifiedAt(newReview.getModifiedAt());
        productReviewRepository.save(review);
        return newReview;
    }
}
