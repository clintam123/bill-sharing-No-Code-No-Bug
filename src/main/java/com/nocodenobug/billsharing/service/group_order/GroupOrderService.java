package com.nocodenobug.billsharing.service.group_order;

import com.nocodenobug.billsharing.model.entity.GroupLink;
import com.nocodenobug.billsharing.model.request.GroupLinkRequest;

public interface GroupOrderService {
    boolean checkLink(GroupLinkRequest link);

//    GroupLink getGroupLink();
}
