package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.ProductGroupDetailDto;
import com.nocodenobug.billsharing.model.dto.ProductGroupDto;
import com.nocodenobug.billsharing.payload.response.*;
import com.nocodenobug.billsharing.service.product_group.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok(DefaultPagingResponse.success(productGroupDtoPage));
    }

    @Operation(summary = "Lấy nhóm sản phẩm theo Id (có cả sản phẩm)", description = "Lấy nhóm sản phẩm theo Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(DefaultResponse.success(getByIdService.getProductGroupById(id)));
    }



    @Operation(summary = "Tạo nhóm sản phẩm", description = "Tạo nhóm sản phẩm")
    @PostMapping("")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> create(@Validated @RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok(DefaultResponse.success(createService.createProductGroup(productGroupDto)));
    }



    @Operation(summary = "Update nhóm sản phẩm", description = "Update nhóm sản phẩm")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> update(@PathVariable Long id,@Validated @RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok(DefaultResponse.success(updateService.updateProductGroup(id, productGroupDto)));
    }

    @Operation(summary = "Delete nhóm sản phẩm(tất cả sản phẩm trong nhóm)", description = "Update nhóm sản phẩm(tất cả sản phẩm trong nhóm)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        deleteProductGroupService.deleteProductGroup(id);
        return ResponseEntity.ok(DefaultResponse.success("Xóa sản phẩm thành công"));
    }
}
