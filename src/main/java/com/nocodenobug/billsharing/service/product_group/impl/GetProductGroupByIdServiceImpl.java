package com.nocodenobug.billsharing.service.product_group.impl;

import com.nocodenobug.billsharing.model.dto.ProductGroupDetailDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import com.nocodenobug.billsharing.model.entity.ProductGroup;
import com.nocodenobug.billsharing.repository.ProductGroupRepository;
import com.nocodenobug.billsharing.service.product_group.GetProductGroupByIdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GetProductGroupByIdServiceImpl implements GetProductGroupByIdService {
    private final ProductGroupRepository productGroupRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public GetProductGroupByIdServiceImpl(ProductGroupRepository productGroupRepository, ModelMapper modelMapper) {
        this.productGroupRepository = productGroupRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ProductGroupDetailDto getProductGroupById(long id) {
        ProductGroup productGroup = productGroupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product Group with id: " + id + "not found")
        );
        return modelMapper.map(productGroup, ProductGroupDetailDto.class);
    }
}
