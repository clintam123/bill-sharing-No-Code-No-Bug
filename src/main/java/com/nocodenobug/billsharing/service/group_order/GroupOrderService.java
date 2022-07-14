package com.nocodenobug.billsharing.service.group_order;

import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;

public interface GroupOrderService {
    boolean checkLink(GroupLinkRequest link);

//    GroupLink getGroupLink();
}
