package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.constants.ESort;
import com.nocodenobug.billsharing.daos.FilterProductDao;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.payload.request.FilterRequest;
import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.product.GetProductsByCategoryTitle;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GetProductsByCategoryTitleImpl implements GetProductsByCategoryTitle {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final FilterProductDao filterProductDao;


    @Autowired
    public GetProductsByCategoryTitleImpl(ProductRepository productRepository, ModelMapper modelMapper, FilterProductDao filterProductDao) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.filterProductDao = filterProductDao;
    }


    @Override
    public Page<?> getProductsByCategoryTitle(String title, int page, int pageSize) {
        Page<?> products = filterProductDao.searchProductByFilter(new FilterRequest(null, title, null, ESort.RANDOM.getValue()), PageRequest.of(page, pageSize));
        if (products.getTotalElements() > 0) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product with category: " + title + " NotFound");
        }
        return products;
    }
}
