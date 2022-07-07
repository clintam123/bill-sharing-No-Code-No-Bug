package com.nocodenobug.billsharing.service.impl;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.model.entity.ProductReview;
import com.nocodenobug.billsharing.repository.ProductReviewRepository;
import com.nocodenobug.billsharing.service.CreateReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateReviewServiceImpl implements CreateReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductReviewDto createReview(int productId, ProductReviewDto newReview) {
        ProductReview review = modelMapper.map(newReview, ProductReview.class);
        review.setProductId(productId);
        review.setCreatedAt(LocalDateTime.now());
        review.setModifiedAt(LocalDateTime.now());
        productReviewRepository.save(review);
        return newReview;
    }
}
