package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.GroupLink;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.service.group_order.GroupOrderService;
import com.nocodenobug.billsharing.utils.GroupLinkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/group-order")
public class GroupOrderController {

    @Autowired
    private GroupOrderService groupOrderService;

    @Autowired
    private GroupLink groupLink;

    @GetMapping("/get-group-link")
    public SampleResponse getGroupLink(){
        groupLink.setLink(GroupLinkUtils.generateRandomString());
        return SampleResponse.builder()
                .success(true)
                .message("Group Link")
                .data(groupLink)
                .build();
    }

    @PostMapping("")
    public SampleResponse checkLink(@Validated @RequestBody GroupLinkRequest link){
        System.out.println(link);
        return SampleResponse.builder()
                .success(groupOrderService.checkLink(link))
                .message("Group link is valid")
                .data(link)
                .build();
    }

    @MessageMapping("/add-order-item") // request sent to this url -> method is called
    @SendTo("/topic/group-order/{userId}") // method return value sent to this url for all subscribers
    public OrderItemDto sendOrderItem(OrderItemDto orderItemDto) {
        return orderItemDto;
    }

    @MessageMapping("/delete-order-item") // request sent to this url -> method is called
    @SendTo("/topic/group-order/{userId}") // method return value sent to this url for all subscribers
    public OrderItemDto deleteOrderItem(OrderItemDto orderItemDto) {
        return orderItemDto;
    }

    @MessageMapping("/update-order-item") // request sent to this url -> method is called
    @SendTo("/topic/group-order/{userId}") // method return value sent to this url for all subscribers
    public OrderItemDto updateOrderItem(OrderItemDto orderItemDto) {
        return orderItemDto;
    }


}
