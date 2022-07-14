package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.payload.response.Pagination;
import com.nocodenobug.billsharing.payload.response.SamplePagingResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.service.order.CreateOrderService;
import com.nocodenobug.billsharing.service.order.DeleteOrderService;
import com.nocodenobug.billsharing.service.order.GetOrderService;
import com.nocodenobug.billsharing.service.order.UpdateOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        description = "Order resources that provides access to available Order data",
        name = "Order Resource")
@RestController
@RequestMapping("/api/v1.0/order")
public class OrderController {

    @Autowired
    private CreateOrderService createOrderService;

    @Autowired
    private GetOrderService getOrderService;

    @Autowired
    private DeleteOrderService deleteOrderService;

    @Autowired
    private UpdateOrderService updateOrderService;

    @Operation(summary = "Get all order", description = "Get all")
    @GetMapping()
    public ResponseEntity<SamplePagingResponse> findAllByOrder(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int page_size
    ){
        Page<OrderDto> order = getOrderService.findAllByStatus(page,page_size);
        return ResponseEntity.ok(SamplePagingResponse.builder().message("GetSuccess").success(true)
                .data(order.getContent()).pagination(Pagination.builder().page(page).pageSize(page_size)
                        .totalPage(order.getTotalPages()).totalItem(order.getTotalElements()).build()).build());
    }

    @Operation(summary = "Get order", description = "Get order by id")
    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse> findByOrderId(
            @PathVariable("id") Long id
    ){
        OrderDto order = getOrderService.findById(id);
        return ResponseEntity.ok(SampleResponse.builder().message("GetSuccess")
                .success(true).data(order).build());
    }

    @Operation(summary = "Get vendor id", description = "Get vendor by id")
    @GetMapping("/vendor/{id}")
    @PreAuthorize("hasAuthority('VENDOR')")
    public ResponseEntity<SamplePagingResponse> findByAllVendorId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int page_size
    ){
        Page<OrderDto> order = getOrderService.findAllByVendorId(id,page,page_size);
        return ResponseEntity.ok(SamplePagingResponse.builder().message("GetSuccess").success(true).data(order.getContent())
                .pagination(Pagination.builder().page(page).pageSize(page_size).totalPage(order.getTotalPages())
                        .totalItem(order.getTotalElements()).build()).build());
    }

    @Operation(summary = "Get customer id", description = "Get customer by id")
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    private ResponseEntity<SamplePagingResponse> findAllByCustomerId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int page_size
    ){
        Page<OrderDto> orderDtos = getOrderService.findAllByCustomerId(id,page,page_size);
        return ResponseEntity.ok(SamplePagingResponse.builder().success(true).message("Get CustomerId success")
                .data(orderDtos.getContent()).pagination(Pagination.builder().page(page).pageSize(page_size)
                        .totalPage(orderDtos.getTotalPages()).totalItem(orderDtos.getTotalElements()).build()).build());
    }

    @Operation(summary = "Delete Order", description = "Delete order with id")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<SampleResponse> deleteOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(SampleResponse.builder().success(true).message("Delete success")
                .data(deleteOrderService.deleteOrder(id)).build());
    }

    @Operation(summary = "Create order", description = "Create new order")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "${api.response-codes.badRequest.desc}",
                            content = {@Content(examples = {@ExampleObject(value = "")})}),
                    @ApiResponse(
                            responseCode = "404",
                            description = "${api.response-codes.notFound.desc}",
                            content = {@Content(examples = {@ExampleObject(value = "")})})
            })
    @PostMapping()
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<SampleResponse> createOrder(@Validated @RequestBody OrderDto orderDto){
        return ResponseEntity.ok(SampleResponse.builder().
                success(true).message("Create success").data(createOrderService.createOrder(orderDto)).build());
    }


    @Operation(summary = "Update order", description = "Update order with id")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<SampleResponse> updateOrder(
            @PathVariable("id") Long id,@Validated @RequestBody OrderDto orderDto){

        return ResponseEntity.ok(SampleResponse.builder().success(true).message("Update success")
                .data(updateOrderService.updateOrder(id,orderDto)).build());
    }



}
