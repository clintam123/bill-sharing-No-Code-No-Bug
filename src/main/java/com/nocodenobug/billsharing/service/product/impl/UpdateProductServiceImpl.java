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

@Service
public class UpdateProductServiceImpl implements UpdateProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final GetCategoryByIdService categoryService;
    private final GetProductGroupByIdService productGroupService;

    @Autowired
    public UpdateProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, GetCategoryByIdService categoryService, GetProductGroupByIdService productGroupService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.productGroupService = productGroupService;
    }
    @Override
    public ProductDto updateProduct(Long id, ProductDto newProduct) {
//        if(productRepository.findById(id).isEmpty()){
//            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product with id" + id + " Not Found");
//        }
//        CategoryDto category = categoryService.getCategoryById(newProduct.getCategoryId());
//        ProductGroupDto productGroupDto = productGroupService.getProductGroupById(newProduct.getProductGroupId());
//
//        Product product = modelMapper.map(newProduct, Product.class);
//        product.setId(id);
//        product.setProductGroup(modelMapper.map(productGroupDto, ProductGroup.class));
//        product.setCategory(modelMapper.map(category, Category.class));
//
//        newProduct.setId(productRepository.save(product).getId());
//        return newProduct;

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "product not found")
        );
        BeanUtils.copyProperties(newProduct, product);
        product.setId(id);
        productRepository.save(product);
        newProduct.setId(id);
        return newProduct;
    }
}
