package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.ProductGroupDetailDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import com.nocodenobug.billsharing.response.Pagination;
import com.nocodenobug.billsharing.response.SamplePagingResponse;
import com.nocodenobug.billsharing.response.SampleResponse;
import com.nocodenobug.billsharing.service.product_group.CreateProductGroupService;
import com.nocodenobug.billsharing.service.product_group.GetAllProductGroupsService;
import com.nocodenobug.billsharing.service.product_group.GetProductGroupByIdService;
import com.nocodenobug.billsharing.service.product_group.UpdateProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-group")
public class ProductGroupController {
    private final UpdateProductGroupService updateService;
    private final CreateProductGroupService createService;
    private final GetProductGroupByIdService getByIdService;
    private final GetAllProductGroupsService getAllProductGroupsService;

    @Autowired
    public ProductGroupController(UpdateProductGroupService updateService, CreateProductGroupService createService, GetProductGroupByIdService getService, GetAllProductGroupsService getAllProductGroupsService) {
        this.updateService = updateService;
        this.createService = createService;
        this.getByIdService = getService;
        this.getAllProductGroupsService = getAllProductGroupsService;
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<?> getProductGroups(@PathVariable long vendorId,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "page_size") int pageSize){
        Page<ProductGroupDetailDto> productGroupDtoPage = getAllProductGroupsService.getAllProductGroups(vendorId, page, pageSize);
        return ResponseEntity.ok(
                SamplePagingResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(productGroupDtoPage.getContent())
                        .pagination(Pagination.builder().page(page).pageSize(pageSize)
                                .totalPage(productGroupDtoPage.getTotalPages())
                                .totalRecord(productGroupDtoPage.getTotalElements()).build())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(getByIdService.getProductGroupById(id))
                        .build()
        );
    }

    @PostMapping("")
    public ResponseEntity<SampleResponse> create(@Validated @RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(createService.createProductGroup(productGroupDto))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleResponse> update(@PathVariable Long id,@Validated @RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(updateService.updateProductGroup(id, productGroupDto))
                        .build()
        );
    }
}
