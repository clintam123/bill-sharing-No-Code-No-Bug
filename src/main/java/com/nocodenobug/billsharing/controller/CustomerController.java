package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.CustomerDto;
import com.nocodenobug.billsharing.payload.response.Pagination;
import com.nocodenobug.billsharing.payload.response.SamplePagingResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.service.customerservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ){
        Page<CustomerDto> customerDtoPage=getAllCustomerService.getAllCustomer(page,pageSize);

        if (customerDtoPage==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SamplePagingResponse.builder()
                .success(true)
                .message("get customer succsess")
                .data(customerDtoPage.getContent())
                .pagination(Pagination.builder().page(page).pageSize(pageSize)
                        .totalItem(customerDtoPage.getTotalElements())
                        .totalPage(customerDtoPage.getTotalPages())
                        .build())
                .build());

    }

    @GetMapping("/get-customer/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAll(
            @PathVariable Long id
    ){
        CustomerDto customerDto=getCustomerByIdService.getById(id);
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("get customer succsess")
                .data(customerDto)
                .build()
        );
    }

    @PostMapping("/add-customer")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addCustomer(@Validated @RequestBody CustomerDto customerDto){
       return ResponseEntity.ok(SampleResponse.builder()
               .success(true)
               .message("add customer success")
               .data(createCustomerService.createCustomer(customerDto))
               .build()
       );
    }
    @PutMapping("/update-customer/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id,@Validated @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("update customer success")
                .data(updateCustomerService.updateCustomer(id,customerDto))
                .build()
        );
    }
    @DeleteMapping("/delete-customer/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping("/search-customerByPhone/{phone}")
    public ResponseEntity<?> searchCustomerByPhone(
            @PathVariable String phone
    ){
        CustomerDto customerDto=searchCustomerBySdtService.findCustomerBySdt(phone);
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Search success")
                .data(customerDto)
                .build()
        );
    }


}
