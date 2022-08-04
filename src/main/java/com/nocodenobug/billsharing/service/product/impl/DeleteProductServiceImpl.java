package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.product.DeleteProductService;
import com.nocodenobug.billsharing.service.product.GetProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductServiceImpl implements DeleteProductService {
    private final ProductRepository productRepository;
    private final GetProductByIdService getProductByIdService;

    @Autowired
    public DeleteProductServiceImpl(ProductRepository productRepository, GetProductByIdService getProductByIdService) {
        this.productRepository = productRepository;
        this.getProductByIdService = getProductByIdService;
    }

    @Override
    public void deleteProduct(long id) {
        getProductByIdService.getProductById(id);
        productRepository.deleteById(id);
    }
}
