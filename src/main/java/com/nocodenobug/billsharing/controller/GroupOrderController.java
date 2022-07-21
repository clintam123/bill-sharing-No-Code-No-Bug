package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.GroupLink;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.service.group_order.GroupOrderService;
import com.nocodenobug.billsharing.utils.GroupLinkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getGroupLink(){
        groupLink.setLink(GroupLinkUtils.generateRandomString());
        return ResponseEntity.ok(DefaultResponse.success("Group link", groupLink));
    }

    @GetMapping("getall_grouplink")
    public GroupLink getAllGr(){
        return this.groupLink;
    }

    @PostMapping("")
    public ResponseEntity<?> checkLink(@Validated @RequestBody GroupLinkRequest link){
        System.out.println(link);
//        return ResponseEntity.ok(SampleResponse.builder()
//                .success(groupOrderService.checkLink(link))
//                .message("Group link is valid")
//                .data(link)
//                .build()
//        );
        return ResponseEntity.ok(groupOrderService.checkLink(link) ? DefaultResponse.success("Group link is valid", link) : DefaultResponse.error("Group link is invalid")
        );
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