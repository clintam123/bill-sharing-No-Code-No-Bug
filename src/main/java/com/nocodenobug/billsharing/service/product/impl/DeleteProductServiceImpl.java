package com.nocodenobug.billsharing.service.product.impl;

import com.nocodenobug.billsharing.repository.ProductRepository;
import com.nocodenobug.billsharing.service.product.DeleteProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductServiceImpl implements DeleteProductService {
    private final ProductRepository productRepository;

    @Autowired
    public DeleteProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
