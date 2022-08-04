package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.payload.response.*;
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
@RequestMapping("/api/v1/order")
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
    public ResponseEntity<?> findAllByOrder(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "page_size") Integer page_size
    ){
        Page<OrderDto> order = getOrderService.findAllByStatus(page,page_size);
        return ResponseEntity.ok(DefaultPagingResponse.success(order));
    }

    @Operation(summary = "Get order", description = "Get order by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findByOrderId(
            @PathVariable("id") Long id
    ){
        OrderDto order = getOrderService.findById(id);
        return ResponseEntity.ok(DefaultResponse.success(order));
    }

    @Operation(summary = "Get vendor id", description = "Get vendor by id")
    @GetMapping("/vendor/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> findByAllVendorId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "page_size") Integer page_size
    ){
        Page<OrderDto> order = getOrderService.findAllByVendorId(id,page,page_size);
        return ResponseEntity.ok(DefaultPagingResponse.success(order));
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> findByALlUserId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "page_size") Integer page_size
    ){
        Page<OrderDto> orderDtos = getOrderService.findAllByUserId(id,page,page_size);
        return ResponseEntity.ok(DefaultPagingResponse.success(orderDtos));
    }

    @Operation(summary = "Delete Order", description = "Delete order with id")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(DefaultResponse.success
                (deleteOrderService.deleteOrder(id)));

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
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> createOrder(@Validated @RequestBody OrderDto orderDto){
        return ResponseEntity.ok(DefaultResponse.success(createOrderService.createOrder(orderDto)));
    }



    @Operation(summary = "Update order", description = "Update order with id")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateOrder(
            @PathVariable("id") Long id,@Validated @RequestBody OrderDto orderDto){

        return ResponseEntity.ok(DefaultResponse.success(updateOrderService.updateOrder(id,orderDto)));

    }


}
