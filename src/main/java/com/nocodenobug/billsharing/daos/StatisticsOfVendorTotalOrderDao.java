package com.nocodenobug.billsharing.daos;

import com.nocodenobug.billsharing.model.dto.StatisticsOfVendorTotalOrderDto;
import com.nocodenobug.billsharing.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class StatisticsOfVendorTotalOrderDao {

    private final EntityManager entityManager;


    public StatisticsOfVendorTotalOrderDto statisticsOfVendorTotalOrder(Long vendorId, Long userId , LocalDate start_date, LocalDate end_date) {
        String strQuery =
                " "
                        + " SELECT od.vendor_id as vendor_id,"
                        + "        sum(od.grand_total) AS grand_total "
                        + " FROM team_3.orders od "
                        + " INNER JOIN team_3.vendor vd ON vd.id = od.vendor_id "
                        + " WHERE od.vendor_id = :vendorId AND od.user_id = :userId AND od.updated_at BETWEEN :start_date AND :end_date";

        Query query = entityManager.createNativeQuery(strQuery,"StatisticsOfVendorTotalOrderDto");
        query.setParameter("vendorId",vendorId);
        query.setParameter("userId", userId);
        query.setParameter("start_date",start_date);
        query.setParameter("end_date",end_date);

        StatisticsOfVendorTotalOrderDto  statistics = (StatisticsOfVendorTotalOrderDto) query.getSingleResult();

        return statistics;
    }


}
