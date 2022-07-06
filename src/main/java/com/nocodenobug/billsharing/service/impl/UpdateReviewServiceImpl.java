package com.nocodenobug.billsharing.service.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.model.entity.ProductReview;
import com.nocodenobug.billsharing.repository.ProductReviewRepository;
import com.nocodenobug.billsharing.service.UpdateReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateReviewServiceImpl implements UpdateReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductReviewDto updateReview(int reviewId, ProductReviewDto review) {
            Optional<ProductReview> productReview = productReviewRepository.findById(reviewId);
            if (productReview.isPresent()) {
                ProductReview product= productReview.get();
                product.setContent(review.getContent().isEmpty() ? review.getContent() : product.getContent());
                product.setTitle(review.getTitle().isEmpty() ? review.getTitle() : product.getTitle());
                product.setRating(review.getRating() == null ? product.getRating() : review.getRating());
                return modelMapper.map(productReviewRepository.save(product), ProductReviewDto.class);
            }
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_REVIEW);
    }
}
