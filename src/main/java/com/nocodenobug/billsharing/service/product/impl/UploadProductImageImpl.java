package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import com.nocodenobug.billsharing.model.entity.Category;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.model.entity.ProductGroup;
import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.CloudinaryService;
import com.nocodenobug.billsharing.service.category.GetCategoryByIdService;
import com.nocodenobug.billsharing.service.product.GetProductByIdService;
import com.nocodenobug.billsharing.service.product.UploadProductImage;
import com.nocodenobug.billsharing.service.product_group.GetProductGroupByIdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class UploadProductImageImpl implements UploadProductImage {
    private final CloudinaryService cloudinaryService;
    private final ProductRepository productRepository;
    private final GetProductByIdService getProductByIdService;
    private final ModelMapper modelMapper;
    private final GetCategoryByIdService categoryService;
    private final GetProductGroupByIdService productGroupService;

    @Autowired
    public UploadProductImageImpl(CloudinaryService cloudinaryService, ProductRepository productRepository,
                                  GetProductByIdService getProductByIdService, ModelMapper modelMapper,
                                  GetCategoryByIdService categoryService, GetProductGroupByIdService productGroupService) {
        this.cloudinaryService = cloudinaryService;
        this.productRepository = productRepository;
        this.getProductByIdService = getProductByIdService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.productGroupService = productGroupService;
    }

    @Override
    public String uploadProductImage(Long productId, MultipartFile file) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "product not found")
        );
        String imageUrl = cloudinaryService.uploadImage(file, FolderConstants.PRODUCT_IMAGE_FOLDER);
        product.setImage_url(imageUrl);
        productRepository.save(product);
        return imageUrl;
    }
}
