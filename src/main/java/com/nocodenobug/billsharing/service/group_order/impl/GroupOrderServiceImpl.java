package com.nocodenobug.billsharing.service.group_order.impl;

import com.nocodenobug.billsharing.model.entity.GroupLink;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;
import com.nocodenobug.billsharing.service.group_order.GroupOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupOrderServiceImpl implements GroupOrderService {
    @Autowired
    private GroupLink groupLink;

    @Override
    public boolean checkLink(GroupLinkRequest link) {
        return link.getLink().equals(groupLink.getLink());
    }
}
