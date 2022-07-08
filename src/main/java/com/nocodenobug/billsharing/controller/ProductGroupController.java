package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.ProductGroupDetailDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import com.nocodenobug.billsharing.response.Pagination;
import com.nocodenobug.billsharing.response.SamplePagingResponse;
import com.nocodenobug.billsharing.response.SampleResponse;
import com.nocodenobug.billsharing.service.product_group.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        description = "Product group controller",
        name = "Các api về nhóm sản phẩm (dành cho vendor)"
)
@RestController
@RequestMapping("/api/v1/product-group")
public class ProductGroupController {
    private final UpdateProductGroupService updateService;
    private final CreateProductGroupService createService;
    private final GetProductGroupByIdService getByIdService;
    private final GetAllProductGroupsService getAllProductGroupsService;
    private final DeleteProductGroupService deleteProductGroupService;

    @Autowired
    public ProductGroupController(UpdateProductGroupService updateService, CreateProductGroupService createService, GetProductGroupByIdService getService, GetAllProductGroupsService getAllProductGroupsService, DeleteProductGroupService deleteProductGroupService) {
        this.updateService = updateService;
        this.createService = createService;
        this.getByIdService = getService;
        this.getAllProductGroupsService = getAllProductGroupsService;
        this.deleteProductGroupService = deleteProductGroupService;
    }

    @Operation(summary = "Lấy tất cả nhóm sản phẩm theo id của vendor(cửa hàng)", description =
            "page: trang hiện tại (bắt đầu từ 0), page_size: số record trong trang hiện tại," +
                    "vendor_id: id của cửa hàng ")

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
                                .totalItem(productGroupDtoPage.getTotalElements()).build())
                        .build()
        );
    }

    @Operation(summary = "Lấy nhóm sản phẩm theo Id", description = "Lấy nhóm sản phẩm theo Id")
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

    @Operation(summary = "Tạo nhóm sản phẩm", description = "Tạo nhóm sản phẩm")
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

    @Operation(summary = "Update nhóm sản phẩm", description = "Update nhóm sản phẩm")
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

    @Operation(summary = "Delete nhóm sản phẩm(tất cả sản phẩm trong nhóm)", description = "Update nhóm sản phẩm(tất cả sản phẩm trong nhóm)")
    @DeleteMapping("/{id}")
    public ResponseEntity<SampleResponse> delete(@PathVariable Long id) {
        deleteProductGroupService.deleteProductGroup(id);
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data("")
                        .build()
        );
    }
}