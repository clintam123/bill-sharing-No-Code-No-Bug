package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.GroupLink;
import com.nocodenobug.billsharing.model.request.GroupLinkRequest;
import com.nocodenobug.billsharing.response.SampleResponse;
import com.nocodenobug.billsharing.service.group_order.GroupOrderService;
import com.nocodenobug.billsharing.utils.GroupLinkUtils;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/group-order")
public class GroupOrderController {

    @Autowired
    private GroupOrderService groupOrderService;

    @Autowired
    private GroupLink groupLink;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/get-group-link")
    public GroupLink getGroupLink(){
        groupLink.setLink(GroupLinkUtils.generateRandomString());
        return groupLink;
    }

    @GetMapping("getall_grouplink")
    public GroupLink getAllGr(){
        return groupLink;
    }

    @PostMapping("")
    public SampleResponse checkLink(@Validated @RequestBody GroupLinkRequest link){
        return SampleResponse.builder()
                .success(groupOrderService.checkLink(link))
                .message("Group link is valid")
                .data(link)
                .build();
    }

    @MessageMapping("/add-order-item") // request sent to this url -> method is called
    @SendTo("/topic/group-order/{userId}") // method return value sent to this url for all subscribers
    public OrderItemDto sendOrderItem(OrderItemDto orderItemDto) {
        System.out.println("add-order-item");
        return orderItemDto;
    }

    @MessageMapping("/delete-order-item/bill_sharing/{pubsub}") // request sent to this url -> method is called
//    @SendTo("/topic/public") // method return value sent to this url for all subscribers
    public void deleteOrderItem(@Payload Long id,@DestinationVariable String pubsub) {
        OrderItemDto orderItemDto1=new OrderItemDto();
        orderItemDto1.setContent("aloalo");
        orderItemDto1.setId(Long.parseLong("1"));
//        System.out.println(pubsub);
        simpMessagingTemplate.convertAndSend("/topic/public/bill_sharing/" + pubsub, orderItemDto1);
    }

    @MessageMapping("/update-order-item") // request sent to this url -> method is called
    @SendTo("/topic/group-order/{userId}") // method return value sent to this url for all subscribers
    public OrderItemDto updateOrderItem(OrderItemDto orderItemDto) {

        return orderItemDto;

    }


}