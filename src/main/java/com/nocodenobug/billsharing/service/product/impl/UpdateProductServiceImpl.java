package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.category.GetCategoryByIdService;
import com.nocodenobug.billsharing.service.product.UpdateProductService;
import com.nocodenobug.billsharing.service.product_group.GetProductGroupByIdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class UpdateProductServiceImpl implements UpdateProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final GetCategoryByIdService categoryService;
    private final GetProductGroupByIdService productGroupService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    public UpdateProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, GetCategoryByIdService categoryService, GetProductGroupByIdService productGroupService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.productGroupService = productGroupService;
    }
    @Override
    public ProductDto updateProduct(Long id, ProductDto newProduct) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "product not found")
        );
        mapper.map(newProduct, product);
        System.out.println(product);
        System.out.println(newProduct);
        productRepository.save(product);
        return mapper.map(product, ProductDto.class);
    }
}
