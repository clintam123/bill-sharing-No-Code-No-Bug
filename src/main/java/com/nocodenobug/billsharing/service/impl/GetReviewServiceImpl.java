package com.nocodenobug.billsharing.service.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.daos.ProductReviewDao;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.model.dto.CustomerReviewDto;
import com.nocodenobug.billsharing.service.GetReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetReviewServiceImpl implements GetReviewService {

    @Autowired
    private ProductReviewDao productReviewDao;

    @Override
    public Page<CustomerReviewDto> getReviewsOfProduct(int productId, int page, int pageSize) {
        Page reviews = productReviewDao.getUserWithReviewByProductId(productId, PageRequest.of(page, pageSize));
        Page page1 = new PageImpl(reviews.getContent(), PageRequest.of(page, pageSize), reviews.getTotalElements());
        if (reviews.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_PRODUCT_REVIEW);
        }
        return reviews;
    }
}
