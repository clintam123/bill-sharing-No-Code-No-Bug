package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.service.order.StatisticalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Tag(
        description = "Tài nguyên Thống kê Đơn hàng cung cấp quyền truy cập vào dữ liệu Thống kê Đơn hàng có sẵn",
        name = "Tài Nguyên Thống Kê Đơn Hàng")
@RestController
@RequestMapping("/api/v1/statistics")
public class    OrderStatisticsController {

    @Autowired
    private StatisticalService statisticalService;

    // phía admin xem thống kê tổng tất cả các vendor

    @Operation(summary = "Thống kê đơn đặt hàng của tất cả nhà cung cấp", description = "Tài nguyên thống kê đơn hàng Thống kê đơn đặt hàng của tất cả nhà cung cấp")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/order-vendors")
    public ResponseEntity<?> statisticsOfOrderOfAllVendor(
            @RequestParam(value = "start_date") String start_date,
            @RequestParam(value = "end_date") String end_date) {
        return ResponseEntity.ok(DefaultResponse.success(
                statisticalService.statisticsOrderVendor(start_date, end_date)));
    }


    @Operation(summary = "Thống kê tổng đơn đặt hàng của tất cả nhà cung cấp", description = "Thống kê tổng số đơn đặt hàng của tất cả nhà cung cấp")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/revenue-vendors")
    public ResponseEntity<?> statisticsOfTotalOrderOfAllVendor(
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date
    ) {
        return ResponseEntity.ok(DefaultResponse.success(
                statisticalService.totalOrderVendor(start_date, end_date)));
    }

    // phía vendor xem thống kê và tổng doanh thu của mình
    @Operation(summary = "Thống kê đơn đặt hàng của nhà cung cấp theo vendor", description = "Tài nguyên thống kê đơn hàng của nhà cung cấp")
    @PreAuthorize("hasRole('VENDOR')")
    @GetMapping("/order-vendor/{id}")
    public ResponseEntity<?> vendorOrderStatistics(
            @PathVariable("id") Long vendor_id,
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date
    ) {
        return ResponseEntity.ok((DefaultResponse.success(
                statisticalService.vendorOrderStatistics(vendor_id, start_date, end_date))));
    }

    @Operation(summary = "Thống kê tổng đơn hàng của nhà cung cấp theo vendor", description = "Tài nguyên thống kê tổng đơn hàng của nhà cung cấp")
    @PreAuthorize("hasRole('VENDOR')")
    @GetMapping("/revenue-vendors/{id}")
    public ResponseEntity<?> statisticsOfVendorTotalOrder(
            @PathVariable("id") Long vendor_id,
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date
    ) {
        return ResponseEntity.ok((DefaultResponse.success(
                statisticalService.statisticsOfVendorTotalOrder(vendor_id, start_date, end_date))));
    }


}
