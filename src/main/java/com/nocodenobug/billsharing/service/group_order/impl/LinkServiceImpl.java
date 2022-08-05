package com.nocodenobug.billsharing.service.group_order.impl;

import com.nocodenobug.billsharing.model.entity.GroupLink;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;
import com.nocodenobug.billsharing.service.group_order.LinkService;
import com.nocodenobug.billsharing.service.group_order.OrderService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    OrderService orderService;

    @Override
    public GroupLink getGroupLink(GroupLinkRequest request) {
        String link = RandomString.make(6);
        orderService.addNewOrder(link, request);
        return new GroupLink(link);
    }

    @Override
    public boolean checkLinkExist(GroupLink link) {
        return orderService.findByLink(link.getLink()) != null;
    }
}
