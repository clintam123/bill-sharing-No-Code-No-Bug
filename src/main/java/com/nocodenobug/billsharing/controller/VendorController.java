package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.payload.response.*;
import com.nocodenobug.billsharing.service.VendorService.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        description = "vendor controller",
        name = "Các api về vendor (dành cho admin)"
)
@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {
    @Autowired
    private CreateVendorService createVendorService;
    @Autowired
    private DeleteVendorService deleteVendorService;
    @Autowired
    private GetAllVendorService getAllVendorService;
    @Autowired
    private GetVendorByIdService getVendorByIdService;
    @Autowired
    private SearchVendorBySdtService searchVendorBySdtService;
    @Autowired
    private UpdateVendorService updateVendorService;
    @Operation(summary = "Lấy tất cả vendor", description =
            "page: trang hiện tại (bắt đầu từ 0), page_size: số record trong trang hiện tại,"
                    )
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ){
        Page<VendorDto> vendorDtoPage=getAllVendorService.getAllVendor(page,pageSize);
        if (vendorDtoPage==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(DefaultPagingResponse.success(vendorDtoPage));
    }

    @Operation(summary = "Lấy vendor theo Id", description = "Lấy vendor theo Id")
    @GetMapping("/get-vendor/{id}")
    public ResponseEntity<?> getAll(
            @PathVariable Long id
    ){
        VendorDto vendorDto=getVendorByIdService.getVendorById(id);
        return ResponseEntity.ok(DefaultResponse.success(vendorDto));
    }
    @Operation(summary = "Tạo vendor", description = "Tạo vendor")
    @PostMapping("/add-vendor")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> addVendor(@Validated  @RequestBody VendorDto vendorDto){
        return ResponseEntity.ok(DefaultResponse.success(createVendorService.createVendor(vendorDto)));
    }

    @Operation(summary = "Update vendor", description = "Update vendor")
    @PutMapping("/update-vendor/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> updateVendor(@PathVariable Long id,@Validated @RequestBody VendorDto vendorDto){
        return ResponseEntity.ok(DefaultResponse.success(updateVendorService.updateVendor(id,vendorDto)));
    }
//    @Operation(summary = "Xóa vendor", description = "Xóa vendor")
//    @DeleteMapping("/delete-vendor/{id}")
//    @PreAuthorize("hasRole('VENDOR')")
//    public ResponseEntity<?> deleteCustomer(
//            @PathVariable Long id
//    ){
//
//        deleteVendorService.deleteVendor(id);
//        return ResponseEntity.ok(SampleResponse.builder()
//                 .success(true)
//                 .message("delete vendor success")
//                 .data("")
//                 .build()
//                );
//    }
    @Operation(summary = "Tìm kiếm vendor theo SDT", description = "Tìm kiếm vendor theo SDT")
    @GetMapping("/search-vendorByPhone/{phone}")
    public ResponseEntity<?> searchVendorByPhone(
            @PathVariable String phone
    ){
        VendorDto vendorDto=searchVendorBySdtService.searchVendorBySdt(phone);
        return  ResponseEntity.ok(DefaultResponse.success(vendorDto));
    }
}
