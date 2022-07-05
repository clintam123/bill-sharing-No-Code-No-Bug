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
    public ProductReviewDto createReview(ProductReviewDto review) {
        ProductReview product = modelMapper.map(review, ProductReview.class);
        product.setCreatedAt(LocalDateTime.now());
        product.setModifiedAt(LocalDateTime.now());
        return modelMapper.map(productReviewRepository.save(product), ProductReviewDto.class);
    }
}
