package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.product.GetProductsByCategoryTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GetProductsByCategoryTitleImpl implements GetProductsByCategoryTitle {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public GetProductsByCategoryTitleImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<ProductDto> getProductsByCategoryTitle(String title, int page, int pageSize) {
        Page<Product> products = productRepository.getProductByCategoryTitle(title, PageRequest.of(page, pageSize));
        if (products.getTotalElements() > 0) {
            return products.map((product -> modelMapper.map(product, ProductDto.class)));
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product with category: " + title + " NotFound", null);
    }
}
