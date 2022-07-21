package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.CustomerDto;
import com.nocodenobug.billsharing.payload.response.*;
import com.nocodenobug.billsharing.service.customerservice.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(
        description = "Customer controller",
        name = "Các api về Customer (dành cho admin)"
)
@RestController
@RequestMapping("/api/v1.0/admin/customer")
public class CustomerController {
    @Autowired
    private CreateCustomerService createCustomerService;
    @Autowired
    private UpdateCustomerService updateCustomerService;
    @Autowired
    private DeleteCustomerService deleteCustomerService;
    @Autowired
    private GetAllCustomerService getAllCustomerService;
    @Autowired
    private GetCustomerByIdService getCustomerByIdService;
    @Autowired
    private SearchCustomerBySdtService searchCustomerBySdtService;
    @Operation(summary = "Lấy tất cả Customer", description =
            "page: trang hiện tại (bắt đầu từ 0), page_size: số record trong trang hiện tại,"
    )
    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ){
        Page<CustomerDto> customerDtoPage=getAllCustomerService.getAllCustomer(page,pageSize);

        if (customerDtoPage==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(DefaultPagingResponse.success(customerDtoPage));

    }
    @Operation(summary = "Lấy customer theo Id", description = "Lấy customer theo Id")
    @GetMapping("/get-customer/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    public ResponseEntity<?> getAll(
            @PathVariable Long id
    ){
        CustomerDto customerDto=getCustomerByIdService.getById(id);
        return ResponseEntity.ok(DefaultResponse.success(customerDto));
    }
    @Operation(summary = "Tạo customer", description = "Tạo customer")
    @PostMapping("/add-customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> addCustomer(@Validated @RequestBody CustomerDto customerDto){
       return ResponseEntity.ok(DefaultResponse.success(createCustomerService.createCustomer(customerDto)));
    }
    @Operation(summary = "Update customer", description = "Update customer")
    @PutMapping("/update-customer/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id,@Validated @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(DefaultResponse.success(updateCustomerService.updateCustomer(id,customerDto)));
    }
    @Operation(summary = "Xóa customer", description = "Xóa customer")
    @DeleteMapping("/delete-customer/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteCustomer(
            @PathVariable Long id
    ){

        deleteCustomerService.deleteCustomer(id);
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("delete customer success")
                .data("")
                .build()
        );
    }
    @Operation(summary = "Tìm kiếm customer theo SDT", description = "Tìm kiếm customer theo SDT")
    @GetMapping("/search-customerByPhone/{phone}")
    public ResponseEntity<?> searchCustomerByPhone(
            @PathVariable String phone
    ){
        CustomerDto customerDto=searchCustomerBySdtService.findCustomerBySdt(phone);
        return ResponseEntity.ok(DefaultResponse.success(customerDto));
    }


}
