package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.response.SampleResponse;
import com.nocodenobug.billsharing.service.product.CreateProductService;
import com.nocodenobug.billsharing.service.product.DeleteProductService;
import com.nocodenobug.billsharing.service.product.GetProductByIdService;
import com.nocodenobug.billsharing.service.product.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final CreateProductService createService;
    private final GetProductByIdService getService;
    private final UpdateProductService updateService;
    private final DeleteProductService deleteService;

    @Autowired
    public ProductController(CreateProductService createService, GetProductByIdService getService, UpdateProductService updateService, DeleteProductService deleteService) {
        this.createService = createService;
        this.getService = getService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(getService.getProductById(id))
                        .build()
        );
    }
    @PostMapping("")
    public ResponseEntity<SampleResponse> create(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(createService.createProduct(productDto))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleResponse> update(@PathVariable Long id, @RequestBody ProductDto productDto){
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(updateService.updateProduct(id ,productDto))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SampleResponse> delete(@PathVariable Long id){
        deleteService.deleteProduct(id);
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(null)
                        .build()
        );
    }
}
