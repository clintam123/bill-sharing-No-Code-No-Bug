package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.daos.StatisticsOfOrderOfAllVendorDao;
import com.nocodenobug.billsharing.daos.StatisticsOfTotalOrderOfAllVendorDao;
import com.nocodenobug.billsharing.daos.StatisticsOfVendorTotalOrderDao;
import com.nocodenobug.billsharing.daos.VendorOrderStatisticsDao;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.model.dto.StatisticsOfOrderOfAllVendorDto;
import com.nocodenobug.billsharing.model.dto.StatisticsOfVendorTotalOrderDto;
import com.nocodenobug.billsharing.model.dto.VendorOrderStatisticsDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.repository.OrderRepository;
import com.nocodenobug.billsharing.security.UserDetailsImpl;
import com.nocodenobug.billsharing.service.order.StatisticalService;
import com.nocodenobug.billsharing.utils.CurrentUserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private HttpServletRequest request;

    private final StatisticsOfOrderOfAllVendorDao statisticsOfOrderOfAllVendorDao;

    private final StatisticsOfTotalOrderOfAllVendorDao statisticsOfTotalOrderOfAllVendorDao;

    private final VendorOrderStatisticsDao vendorOrderStatisticsDao;
    private final StatisticsOfVendorTotalOrderDao statisticsOfVendorTotalOrderDao;

    @Autowired
    public StatisticalServiceImpl
            (
                    StatisticsOfOrderOfAllVendorDao statisticsOfOrderOfAllVendorDao,
                    StatisticsOfTotalOrderOfAllVendorDao statisticsOfTotalOrderOfAllVendorDao,

                    VendorOrderStatisticsDao vendorOrderStatisticsDao,
                    StatisticsOfVendorTotalOrderDao statisticsOfVendorTotalOrderDao
            ) {
        this.statisticsOfOrderOfAllVendorDao = statisticsOfOrderOfAllVendorDao;
        this.statisticsOfTotalOrderOfAllVendorDao = statisticsOfTotalOrderOfAllVendorDao;

        this.vendorOrderStatisticsDao = vendorOrderStatisticsDao;
        this.statisticsOfVendorTotalOrderDao = statisticsOfVendorTotalOrderDao;
    }


    @Override
    public List<?> statisticsOrderVendor(String start_date, String end_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(start_date, formatter);
        LocalDate end = LocalDate.parse(end_date, formatter);

        List<?> list = statisticsOfOrderOfAllVendorDao.statisticsOrderVendor(start, end);
        if (!start.isBefore(end)) {
            throw new ObjectNotFoundException(ResponseStatusConstant.DATE_INVALID);
        } else if (list.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
        }
        return list;
    }

    @Override
    public List<?> totalOrderVendor(String start_date, String end_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(start_date, formatter);
        LocalDate end = LocalDate.parse(end_date, formatter);

        List<?> list = statisticsOfTotalOrderOfAllVendorDao.statisticalTotalOrderVendor(start, end);
        if (start.isAfter(end)) {
            throw new ObjectNotFoundException(ResponseStatusConstant.DATE_INVALID);
        } else if (list.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
        }
        return list;
    }


    // phía vendor xem thống kê và tổng doanh thu của mình
    @Override
    public List<?> vendorOrderStatistics(Long vendor_id, String start_date, String end_date) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        System.out.println(userDetails.getId() + "id");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(start_date, formatter);
        LocalDate end = LocalDate.parse(end_date, formatter);

        if (start.isAfter(end)) {
            throw new ObjectNotFoundException(ResponseStatusConstant.DATE_INVALID);
        } else {
            List<?> list = vendorOrderStatisticsDao.vendorOrderStatistics(vendor_id, userDetails.getId(), start, end);
            Optional<Order> order = orderRepository.findByVendorIdAndUserId(vendor_id, userDetails.getId());
            if (!order.isPresent()) {
                throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_VENDORID);
            }

            if (list.isEmpty()) {
                throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
            }
            return list;
        }
    }

    @Override
    public StatisticsOfVendorTotalOrderDto statisticsOfVendorTotalOrder(Long vendor_id, String start_date, String end_date) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(start_date, formatter);
        LocalDate end = LocalDate.parse(end_date, formatter);

        if (start.isAfter(end)) {
            throw new ObjectNotFoundException(ResponseStatusConstant.DATE_INVALID);
        } else {
            StatisticsOfVendorTotalOrderDto statistics = statisticsOfVendorTotalOrderDao.statisticsOfVendorTotalOrder(vendor_id, userDetails.getId(), start, end);
            Optional<Order> order = orderRepository.findByVendorIdAndUserId(vendor_id, userDetails.getId());
            if (!order.isPresent()) {
                throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_VENDORID);
            }

            if (statistics.getVendor_id() == null) {
                throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
            } else if (statistics.getVendor_id() != vendor_id) {
                throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_VENDORID);
            } else {
                return statistics;

            }
        }
    }

}
