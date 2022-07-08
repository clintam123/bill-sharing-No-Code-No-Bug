package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.ProductDto;
import com.nocodenobug.billsharing.response.Pagination;
import com.nocodenobug.billsharing.response.SamplePagingResponse;
import com.nocodenobug.billsharing.response.SampleResponse;
import com.nocodenobug.billsharing.service.product.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        description = "Product controller",
        name = "Các api về sản phẩm (dành cho vendor)"
)
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final CreateProductService createService;
    private final GetProductByIdService getService;
    private final UpdateProductService updateService;
    private final DeleteProductService deleteService;
    private final GetProductsByCategoryTitle getProductsByCategoryTitle;

    @Autowired
    public ProductController(CreateProductService createService, GetProductByIdService getService, UpdateProductService updateService, DeleteProductService deleteService, GetProductsByCategoryTitle getProductsByCategoryTitle) {
        this.createService = createService;
        this.getService = getService;
        this.updateService = updateService;
        this.deleteService = deleteService;
        this.getProductsByCategoryTitle = getProductsByCategoryTitle;
    }

    @Operation(summary = "Lấy tất cả sản phẩm theo tên thể loại", description =
            "page: trang hiện tại (bắt đầu từ 0), page_size: số record trong trang hiện tại," +
                    "category_title: tên thể loại ")

    @GetMapping("/category")
    public ResponseEntity<SamplePagingResponse> findByCategoryTitle(@RequestParam(value = "category_title") String categoryTitle,
                                                              @RequestParam(value = "page") int page,
                                                              @RequestParam(value = "page_size") int pageSize){
        Page<ProductDto> productPage = getProductsByCategoryTitle.getProductsByCategoryTitle(categoryTitle, page, pageSize);
        return ResponseEntity.ok(
                SamplePagingResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(productPage.getContent())
                        .pagination(Pagination.builder().page(page).pageSize(pageSize)
                                .totalPage(productPage.getTotalPages())
                                .totalRecord(productPage.getTotalElements()).build())
                        .build()
        );

    }

    @Operation(summary = "Lấy sản phẩm theo Id", description = "Lấy sản phẩm theo Id")
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

    @Operation(summary = "Tạo sản phẩm", description = "Tạo sản phẩm")
    @PostMapping("")
    public ResponseEntity<SampleResponse> create(@Validated @RequestBody ProductDto productDto){
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(createService.createProduct(productDto))
                        .build()
        );
    }

    @Operation(summary = "Update sản phẩm", description = "Update sản phẩm")
    @PutMapping("/{id}")
    public ResponseEntity<SampleResponse> update(@PathVariable Long id,
                                                 @Validated @RequestBody ProductDto productDto){
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(updateService.updateProduct(id ,productDto))
                        .build()
        );
    }

    @Operation(summary = "Xóa sản phẩm", description = "Xóa sản phẩm")
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
