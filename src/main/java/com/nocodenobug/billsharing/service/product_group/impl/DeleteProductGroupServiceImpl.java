package com.nocodenobug.billsharing.service.product_group.impl;

import com.nocodenobug.billsharing.repository.ProductGroupRepository;
import com.nocodenobug.billsharing.service.product_group.DeleteProductGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductGroupServiceImpl implements DeleteProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public DeleteProductGroupServiceImpl(ProductGroupRepository productGroupRepository, ModelMapper modelMapper) {
        this.productGroupRepository = productGroupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteProductGroup(long id) {
        productGroupRepository.deleteById(id);
    }
}
