package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.daos.StatisticsOfOrderOfAllVendorDao;
import com.nocodenobug.billsharing.daos.StatisticsOfTotalOrderOfAllVendorDao;
import com.nocodenobug.billsharing.daos.StatisticsOfVendorTotalOrderDao;
import com.nocodenobug.billsharing.daos.VendorOrderStatisticsDao;
import com.nocodenobug.billsharing.exceptions.ObjectNotFoundException;
import com.nocodenobug.billsharing.model.dto.StatisticsOfVendorTotalOrderDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.repository.OrderRepository;
import com.nocodenobug.billsharing.service.order.StatisticalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

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
            )
    {
        this.statisticsOfOrderOfAllVendorDao = statisticsOfOrderOfAllVendorDao;
        this.statisticsOfTotalOrderOfAllVendorDao = statisticsOfTotalOrderOfAllVendorDao;

        this.vendorOrderStatisticsDao = vendorOrderStatisticsDao;
        this.statisticsOfVendorTotalOrderDao = statisticsOfVendorTotalOrderDao;
    }


    // phía admin xem thống kê tổng tất cả các vendor
    @Override
    public List<?> statisticsOrderVendor(String start_date,String end_date){
        List<?> list = statisticsOfOrderOfAllVendorDao.statisticsOrderVendor(start_date,end_date);
        if(list.isEmpty()){
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
        }
        return list;
    }

    @Override
    public List<?> totalOrderVendor(String start_date, String end_date){
        List<?> list = statisticsOfTotalOrderOfAllVendorDao.statisticalTotalOrderVendor(start_date,end_date);
        if(list.isEmpty()){
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
        }
        return list;
    }


    // phía vendor xem thống kê và tổng doanh thu của mình

    @Override
    public List<?> vendorOrderStatistics(Long vendor_id, String start_date, String end_date){
        List<?> list = vendorOrderStatisticsDao.vendorOrderStatistics(vendor_id,start_date,end_date);
        if(list.isEmpty()){
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
        }
        return list;
    }


    @Override
    public StatisticsOfVendorTotalOrderDto statisticsOfVendorTotalOrder(Long vendor_id, String start_date, String end_date){
        StatisticsOfVendorTotalOrderDto statistics = statisticsOfVendorTotalOrderDao.statisticsOfVendorTotalOrder(vendor_id,start_date,end_date);
        if(statistics == null){
            throw new ObjectNotFoundException(ResponseStatusConstant.NOT_FOUND_SATISTICS);
        }
        return statistics;
    }

}
