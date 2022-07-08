package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.product.UpdateProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UpdateProductServiceImpl implements UpdateProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UpdateProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ProductDto updateProduct(Long id, ProductDto newProduct) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product with id" + id + " Not Found", null);
        }
        Product product = modelMapper.map(newProduct, Product.class);
        return modelMapper.map(productRepository.save(product), ProductDto.class);
    }
}
