package com.nocodenobug.billsharing.service.group_order;

import com.nocodenobug.billsharing.model.entity.GroupLink;
import com.nocodenobug.billsharing.payload.request.GroupLinkRequest;

public interface LinkService {
    GroupLink getGroupLink(GroupLinkRequest request);

    boolean checkLinkExist(GroupLink link);

}
