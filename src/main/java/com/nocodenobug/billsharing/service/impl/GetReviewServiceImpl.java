package com.nocodenobug.billsharing.service.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.model.entity.ProductReview;
import com.nocodenobug.billsharing.repository.ProductReviewRepository;
import com.nocodenobug.billsharing.service.GetReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetReviewServiceImpl implements GetReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ProductReviewDto> getReviewsOfProduct(int productId, int page, int pageSize) throws ObjectNotFoundException {
        Page<ProductReview> productReviews = productReviewRepository.findAllByProductId(productId, PageRequest.of(page, pageSize));
        if (productReviews.getTotalElements() > 0) {
            return productReviews.map(productReview -> modelMapper.map(productReview, ProductReviewDto.class));
        }
        throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_PRODUCT_REVIEW);
    }
}
