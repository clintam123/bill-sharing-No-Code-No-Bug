package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.model.entity.ProductGroup;
import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.CloudinaryService;
import com.nocodenobug.billsharing.service.category.GetCategoryByIdService;
import com.nocodenobug.billsharing.service.product.CreateProductService;
import com.nocodenobug.billsharing.service.product_group.GetProductGroupByIdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateProductServiceImpl implements CreateProductService {
    private final ProductRepository productRepository;
    private final GetCategoryByIdService categoryService;
    private final GetProductGroupByIdService productGroupService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public CreateProductServiceImpl(ProductRepository productRepository, GetCategoryByIdService getCategoryByIdService, GetProductGroupByIdService getProductGroupByIdService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.productRepository = productRepository;
        this.categoryService = getCategoryByIdService;
        this.productGroupService = getProductGroupByIdService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    public ProductDto createProduct(ProductDto newProduct) {
        CategoryDto category = categoryService.getCategoryById(newProduct.getCategoryId());
        ProductGroupDto productGroupDto = productGroupService.getProductGroupById(newProduct.getProductGroupId());

        newProduct.setImageUrl(cloudinaryService.getUrlImage(FolderConstants.PRODUCT_DEFAULT_IMAGE_PUBLIC_ID));
        Product product = modelMapper.map(newProduct, Product.class);
        product.setProductGroup(modelMapper.map(productGroupDto, ProductGroup.class));
        product.setCategory(modelMapper.map(category, Category.class));
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product productSaved = productRepository.save(product);
        newProduct.setId(productSaved.getId());
        return newProduct;
    }
}
