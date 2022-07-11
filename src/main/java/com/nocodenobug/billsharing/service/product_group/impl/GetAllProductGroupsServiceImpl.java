package com.nocodenobug.billsharing.service.product_group.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.ProductGroupDetailDto;
import com.nocodenobug.billsharing.model.entity.ProductGroup;
import com.nocodenobug.billsharing.repository.ProductGroupRepository;
import com.nocodenobug.billsharing.service.product_group.GetAllProductGroupsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductGroupsServiceImpl implements GetAllProductGroupsService {
    private final ProductGroupRepository productGroupRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public GetAllProductGroupsServiceImpl(ProductGroupRepository productGroupRepository, ModelMapper modelMapper) {
        this.productGroupRepository = productGroupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ProductGroupDetailDto> getAllProductGroups(long vendorId, int page, int pageSize) {
        Page<ProductGroup> productGroups = productGroupRepository.findAllByVendorId(vendorId, PageRequest.of(page, pageSize));
        if(productGroups.getTotalElements() > 0){
            return productGroups.map(productGroup -> modelMapper.map(productGroup, ProductGroupDetailDto.class));
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product group" + " Not Found");
    }
}
