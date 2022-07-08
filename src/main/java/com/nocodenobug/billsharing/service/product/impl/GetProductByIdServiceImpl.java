package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.product.GetProductByIdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GetProductByIdServiceImpl implements GetProductByIdService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GetProductByIdServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id: " + id + "not found")
        );
        return modelMapper.map(product, ProductDto.class);
    }
}
