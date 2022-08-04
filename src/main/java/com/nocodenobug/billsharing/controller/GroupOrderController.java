package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.GroupLink;
import com.nocodenobug.billsharing.model.entity.redis.OrderRedis;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.service.group_order.LinkService;
import com.nocodenobug.billsharing.service.group_order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/group-order")
public class GroupOrderController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/get-group-link")
    public ResponseEntity<?> getGroupLink(@Validated @RequestBody GroupLinkRequest request){
        return ResponseEntity.ok(DefaultResponse.success(linkService.getGroupLink(request)));
    }

    @PostMapping("/check-link")
    public ResponseEntity<?> checkLink(@Validated @RequestBody GroupLink link){
        System.out.println(link);
        return ResponseEntity.ok(linkService.checkLinkExist(link) ? DefaultResponse.success("Group link is valid", link) : DefaultResponse.error("Group link is invalid")
        );
    }

    @SubscribeMapping("/group-order/{link}")
    public OrderRedis sendOrder(@DestinationVariable String link) {
        System.out.println("subscribe mapping");
        return orderService.findByLink(link);
    }


    @MessageMapping("/add-order-item/{link}") // request sent to this url -> method is called
    @SendTo("/topic/group-order/{link}") // method return value sent to this url for all subscribers
    public OrderRedis addOrderItem(OrderItemDto orderItemDto, @DestinationVariable String link) {
        System.out.println("orderItemDto: " + orderItemDto);
        return orderService.addOrderItem(link, orderItemDto);
    }

    @MessageMapping("/update-order-item/{link}") // request sent to this url -> method is called
    @SendTo("/topic/group-order/{link}") // method return value sent to this url for all subscribers
    public OrderRedis updateOrderItem(OrderItemDto orderItemDto, @DestinationVariable String link) {
        return orderService.updateOrderItem(link, orderItemDto);
    }

    @MessageMapping("/delete-order-item/{link}") // request sent to this url -> method is called
    @SendTo("/topic/group-order/{link}") // method return value sent to this url for all subscribers
    public OrderRedis deleteOrderItem(OrderItemDto orderItemDto, @DestinationVariable String link) {
        return orderService.deleteOrderItem(link, orderItemDto);
    }

    @PostMapping("/order/{link}")
    public ResponseEntity<?> saveOrder(@PathVariable String link){
        orderService.saveOrder(link);
        return ResponseEntity.ok(DefaultResponse.success("Success"));
    }

}