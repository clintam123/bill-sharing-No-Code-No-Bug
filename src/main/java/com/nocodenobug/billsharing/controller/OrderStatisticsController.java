package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.service.order.StatisticalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        description = "Order Statistics resources that provides access to available Order Statistics  data",
        name = "Order Statistics Resource")
@RestController
@RequestMapping("/api/v1/statistics")
public class OrderStatisticsController {

    @Autowired
    private StatisticalService statisticalService;

    // phía admin xem thống kê tổng tất cả các vendor

    @Operation(summary = "Statistics Of Order Of All Vendor", description = "Statistics Of Order Of All Vendor resources")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/order-all-vendor")
    public ResponseEntity<?> statisticsOfOrderOfAllVendor(
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date
    ){
        return ResponseEntity.ok(DefaultResponse.success(
                statisticalService.statisticsOrderVendor(start_date,end_date)));
    }


    @Operation(summary = "Statistics Of Total Order Of All Vendor", description = "Statistics Of Total Order Of All Vendor resources")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/total-order-all-vendor")
    public ResponseEntity<?> statisticsOfTotalOrderOfAllVendor(
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date
    ){
        return ResponseEntity.ok(DefaultResponse.success(
                statisticalService.totalOrderVendor(start_date,end_date)));
    }

    // phía vendor xem thống kê và tổng doanh thu của mình
    @Operation(summary = "Vendor Order Statistics", description = "Vendor Order Statistics resources")
    @PreAuthorize("hasRole('VENDOR')")
    @GetMapping("/order-vendor/{id}")
    public ResponseEntity<?> vendorOrderStatistics(
            @PathVariable("id") Long vendor_id,
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date
    ){
        return ResponseEntity.ok((DefaultResponse.success(
                statisticalService.vendorOrderStatistics(vendor_id,start_date,end_date)
        )));
    }

    @Operation(summary = "Statistics Of Vendor Total Order", description = "Statistics Of Vendor Total Order resources")
    @PreAuthorize("hasRole('VENDOR')")
    @GetMapping("/total-order-vendor/{id}")
    public ResponseEntity<?> statisticsOfVendorTotalOrder(
            @PathVariable("id") Long vendor_id,
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date
    ){
        return ResponseEntity.ok((DefaultResponse.success(
                statisticalService.statisticsOfVendorTotalOrder(vendor_id,start_date,end_date)
        )));
    }







}
