package com.nocodenobug.billsharing.service.product_group.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import com.nocodenobug.billsharing.model.entity.ProductGroup;
import com.nocodenobug.billsharing.repository.ProductGroupRepository;
import com.nocodenobug.billsharing.service.product_group.UpdateProductGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductGroupServiceImpl implements UpdateProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public UpdateProductGroupServiceImpl(ProductGroupRepository productGroupRepository, ModelMapper modelMapper) {
        this.productGroupRepository = productGroupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductGroupDto updateProductGroup(Long id, ProductGroupDto productGroupDto) {
        if(productGroupRepository.findById(id).isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product with id" + id + " Not Found");
        }
        ProductGroup productGroup = modelMapper.map(productGroupDto, ProductGroup.class);
        return modelMapper.map(productGroupRepository.save(productGroup), ProductGroupDto.class);
    }
}
