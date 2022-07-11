package com.nocodenobug.billsharing.service.product_group.impl;

import com.nocodenobug.billsharing.repository.ProductGroupRepository;
import com.nocodenobug.billsharing.service.product.GetProductByIdService;
import com.nocodenobug.billsharing.service.product_group.DeleteProductGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductGroupServiceImpl implements DeleteProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ModelMapper modelMapper;
    private final GetProductByIdService getProductByIdService;


    @Autowired
    public DeleteProductGroupServiceImpl(ProductGroupRepository productGroupRepository, ModelMapper modelMapper, GetProductByIdService getProductByIdService) {
        this.productGroupRepository = productGroupRepository;
        this.modelMapper = modelMapper;
        this.getProductByIdService = getProductByIdService;
    }

    @Override
    public void deleteProductGroup(long id) {
        getProductByIdService.getProductById(id);
        productGroupRepository.deleteById(id);
    }
}
