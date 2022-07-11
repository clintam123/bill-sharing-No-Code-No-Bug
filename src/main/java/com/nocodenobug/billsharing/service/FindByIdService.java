package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.model.entity.*;

public interface FindByIdService {
    Order checkIdOrder(Long id);

    Product checkIdProduct(Long id);

    OrderItem checkIdOrderItem(Long id);

    Vendor checkIdVendor(Long id);

    Customer checkIdCustomer(Long id);
}
