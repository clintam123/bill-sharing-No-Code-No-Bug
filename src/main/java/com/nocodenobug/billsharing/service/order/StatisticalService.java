package com.nocodenobug.billsharing.service.order;

import com.nocodenobug.billsharing.model.dto.RevenueVendorDto;

import java.util.List;

public interface StatisticalService {

    List<?> orderVendors(String start_date, String end_date);

    List<?> revenueVendors(String start_date, String end_date);

    List<?> orderVendor(Long vendor_id, String start_date, String end_date);

    RevenueVendorDto revenueVendor(Long vendor_id, String start_date, String end_date);
}
