package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.payload.response.Pagination;
import com.nocodenobug.billsharing.payload.response.SamplePagingResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.service.VendorService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/admin/vendor")
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

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ){
        Page<VendorDto> vendorDtoPage=getAllVendorService.getAllVendor(page,pageSize);
        if (vendorDtoPage==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SamplePagingResponse.builder()
                .success(true)
                .message("get vendor succsess")
                .data(vendorDtoPage.getContent())
                .pagination(Pagination.builder().page(page).pageSize(pageSize)
                        .totalItem(vendorDtoPage.getTotalElements())
                        .totalPage(vendorDtoPage.getTotalPages())
                        .build())
                .build());
    }

    @GetMapping("/get-vendor/{id}")
    public ResponseEntity<?> getAll(
            @PathVariable Long id
    ){
        VendorDto vendorDto=getVendorByIdService.getVendorById(id);
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("get vendor succsess")
                .data(vendorDto)
                .build()
        );
    }
    @PostMapping("/add-vendor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addVendor(@Validated  @RequestBody VendorDto vendorDto){
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("add vendor success")
                .data(createVendorService.createVendor(vendorDto))
                .build()
        );
    }

    @PutMapping("/update-vendor/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateVendor(@PathVariable Long id,@Validated @RequestBody VendorDto vendorDto){
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("update vendor success")
                .data(updateVendorService.updateVendor(id,vendorDto))
                .build()
        );
    }
    @DeleteMapping("/delete-vendor/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteCustomer(
            @PathVariable Long id
    ){

        deleteVendorService.deleteVendor(id);
        return ResponseEntity.ok(SampleResponse.builder()
                 .success(true)
                 .message("delete vendor success")
                 .data("")
                 .build()
                );
    }
    @GetMapping("/search-vendorByPhone/{phone}")
    public ResponseEntity<?> searchVendorByPhone(
            @PathVariable String phone
    ){
        VendorDto vendorDto=searchVendorBySdtService.searchVendorBySdt(phone);
        return  ResponseEntity.ok(SampleResponse.builder()
                        .success(true)
                        .message("search success")
                        .data(vendorDto)
                        .build()
                );
    }
}
