package com.nocodenobug.billsharing.service.order;

import com.nocodenobug.billsharing.model.dto.StatisticsOfVendorTotalOrderDto;
import com.nocodenobug.billsharing.model.entity.Order;

import java.util.List;

public interface StatisticalService {

    List<?> statisticsOrderVendor(String start_date,String end_date);

    List<?> totalOrderVendor(String start_date, String end_date);

    List<?> vendorOrderStatistics(Long vendor_id, String start_date, String end_date);

    StatisticsOfVendorTotalOrderDto statisticsOfVendorTotalOrder(Long vendor_id, String start_date, String end_date);
}
