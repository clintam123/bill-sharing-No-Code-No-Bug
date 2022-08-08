package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.daos.OrderVendorDao;
import com.nocodenobug.billsharing.daos.OrderVendorsDao;
import com.nocodenobug.billsharing.daos.RevenueVendorDao;
import com.nocodenobug.billsharing.daos.RevenueVendorsDao;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.model.dto.RevenueVendorDto;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.VendorRepository;
import com.nocodenobug.billsharing.security.UserDetailsImpl;
import com.nocodenobug.billsharing.service.order.StatisticalService;
import com.nocodenobug.billsharing.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    private VendorRepository vendorRepository;

    private final OrderVendorsDao orderVendorsDao;
    private final RevenueVendorsDao revenueVendorsDao;

    private final OrderVendorDao orderVendorDao;
    private final RevenueVendorDao revenueVendorDao;

    @Autowired
    public StatisticalServiceImpl(
            OrderVendorsDao orderVendorsDao,
            RevenueVendorsDao revenueVendorsDao,

            OrderVendorDao orderVendorDao,
            RevenueVendorDao revenueVendorDao
    ) {
        this.orderVendorsDao = orderVendorsDao;
        this.revenueVendorsDao = revenueVendorsDao;

        this.orderVendorDao = orderVendorDao;
        this.revenueVendorDao = revenueVendorDao;
    }

    // phía Admin  tổng doanh thu của vendor
    @Override
    public List<?> orderVendors(String start_date, String end_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(start_date, formatter);
        LocalDate end = LocalDate.parse(end_date, formatter);

        List<?> list = orderVendorsDao.orderVendors(start, end);
        if (!start.isBefore(end)) {
            throw new ObjectNotFoundException(ResponseStatusConstant.DATE_INVALID);
        } else if (list.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
        }
        return list;
    }

    @Override
    public List<?> revenueVendors(String start_date, String end_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(start_date, formatter);
        LocalDate end = LocalDate.parse(end_date, formatter);

        List<?> list = revenueVendorsDao.revenueVendors(start, end);
        if (start.isAfter(end)) {
            throw new ObjectNotFoundException(ResponseStatusConstant.DATE_INVALID);
        } else if (list.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
        }
        return list;
    }

    // phía vendor xem thống kê và tổng doanh thu của mình
    @Override
    public List<?> orderVendor(Long vendor_id, String start_date, String end_date) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(start_date, formatter);
        LocalDate end = LocalDate.parse(end_date, formatter);

        if (start.isAfter(end)) {
            throw new ObjectNotFoundException(ResponseStatusConstant.DATE_INVALID);
        } else {
            List<?> list = orderVendorDao.orderVendor(vendor_id, start, end);

            Optional<Vendor> findById = vendorRepository.findByIdAndUserId(vendor_id, userDetails.getId());
            if (!findById.isPresent()) {
                throw new ObjectNotFoundException(ResponseStatusConstant.FORBIDEN);
            }

            return list;
        }
    }

    @Override
    public RevenueVendorDto revenueVendor(Long vendor_id, String start_date, String end_date) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(start_date, formatter);
        LocalDate end = LocalDate.parse(end_date, formatter);

        if (start.isAfter(end)) {
            throw new ObjectNotFoundException(ResponseStatusConstant.DATE_INVALID);
        } else {
            RevenueVendorDto statistics = revenueVendorDao.revenueVendor(vendor_id, start, end);

            Optional<Vendor> findById = vendorRepository.findByIdAndUserId(vendor_id, userDetails.getId());
            if (!findById.isPresent()) {
                throw new ObjectNotFoundException(ResponseStatusConstant.FORBIDEN);
            }

            return statistics;
        }
    }

}
