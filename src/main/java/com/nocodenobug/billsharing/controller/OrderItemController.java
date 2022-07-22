package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.service.order_item.CreateOrderItemService;
import com.nocodenobug.billsharing.service.order_item.DeleteOrderItemService;
import com.nocodenobug.billsharing.service.order_item.UpdateOrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(
        description = "Order item resources that provides access to available Order item data",
        name = "Order item Resource")
@RestController
@RequestMapping("/api/v1.0/order-item")
public class OrderItemController {

    @Autowired
    private DeleteOrderItemService deleteOrderItemService;

    @Autowired
    private CreateOrderItemService createOrderItemService;

    @Autowired
    private UpdateOrderItemService updateOrderItemService;

    @Operation(summary = "Delete Order Item", description = "Delete order item with id")
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteOrderItem(@PathVariable("id") Long id){
        deleteOrderItemService.deleteOrderItem(id);
        return ResponseEntity.ok(DefaultResponse.success("Order item deleted successfully"));
    }

    @Operation(summary = "Create order item", description = "Create new order item")
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> createOrderItem(@Validated @RequestBody OrderItemDto orderItemDto){
        return ResponseEntity.ok(DefaultResponse.success( createOrderItemService.createOrderItem(orderItemDto)));
    }


    @Operation(summary = "Update order item", description = "Update order item with id")
    @PutMapping("{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateOrderItem(
            @PathVariable("id")  Long id,
          @Validated @RequestBody OrderItemDto orderItemDto){
        return ResponseEntity.ok(DefaultResponse.success(updateOrderItemService.updateQuantityOrderItem(id,orderItemDto)));
    }


}