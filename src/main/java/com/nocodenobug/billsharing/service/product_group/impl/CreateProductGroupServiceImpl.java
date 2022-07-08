package com.nocodenobug.billsharing.service.product_group.impl;

import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import com.nocodenobug.billsharing.model.entity.ProductGroup;
import com.nocodenobug.billsharing.repository.ProductGroupRepository;
import com.nocodenobug.billsharing.service.product_group.CreateProductGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductGroupServiceImpl implements CreateProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public CreateProductGroupServiceImpl(ProductGroupRepository productGroupRepository, ModelMapper modelMapper) {
        this.productGroupRepository = productGroupRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProductGroupDto createProductGroup(ProductGroupDto productGroupDto) {
        ProductGroup productGroup = modelMapper.map(productGroupDto, ProductGroup.class);
        return modelMapper.map(productGroupRepository.save(productGroup), ProductGroupDto.class);
    }
}
