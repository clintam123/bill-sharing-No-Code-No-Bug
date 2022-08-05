package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.service.group_order.OrderService;
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
@RequestMapping("/api/v1/order-item")
public class OrderItemController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeleteOrderItemService deleteOrderItemService;

    @Autowired
    private CreateOrderItemService createOrderItemService;

    @Autowired
    private UpdateOrderItemService updateOrderItemService;

//    @Operation(summary = "Delete Order Item", description = "Delete order item with id")
//    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public ResponseEntity<?> deleteOrderItem(@PathVariable("id") Long id){
//        deleteOrderItemService.deleteOrderItem(id);
//        return ResponseEntity.ok(DefaultResponse.success("Order item deleted successfully"));
//    }
//
//    @Operation(summary = "Create order item", description = "Create new order item")
//    @PostMapping
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public ResponseEntity<?> createOrderItem(@Validated @RequestBody OrderItemDto orderItemDto){
//        return ResponseEntity.ok(DefaultResponse.success( createOrderItemService.createOrderItem(orderItemDto)));
//    }
//
//
//    @Operation(summary = "Update order item", description = "Update order item with id")
//    @PutMapping("{id}")
//    public ResponseEntity<?> updateOrderItem(
//            @PathVariable("id")  Long id,
//          @Validated @RequestBody OrderItemDto orderItemDto){
//        return ResponseEntity.ok(DefaultResponse.success(updateOrderItemService.updateQuantityOrderItem(id,orderItemDto)));
//    }

    @Operation(summary = "Add order item", description = "Add order item store as JSON in redis")
    @PostMapping("/{link}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> add(@Validated @RequestBody OrderItemDto orderItemDto, @PathVariable String link){
        return ResponseEntity.ok(DefaultResponse.success(orderService.addOrderItem(link, orderItemDto)));
    }

    @Operation(summary = "Update order item", description = "Update order item store as JSON in redis")
    @PutMapping("/{link}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> update(@Validated @RequestBody OrderItemDto orderItemDto, @PathVariable String link){
        return ResponseEntity.ok(DefaultResponse.success(orderService.updateOrderItem(link, orderItemDto)));
    }

    @DeleteMapping("/{link}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> delete(@Validated @RequestBody OrderItemDto orderItemDto, @PathVariable String link){
        return ResponseEntity.ok(DefaultResponse.success(orderService.deleteOrderItem(link, orderItemDto)));
    }


}
