package com.nocodenobug.billsharing.daos;

import com.nocodenobug.billsharing.model.dto.RevenueVendorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class RevenueVendorDao {

    private final EntityManager entityManager;


    public RevenueVendorDto revenueVendor(Long vendorId , LocalDate start_date, LocalDate end_date) {
        String strQuery =
                " "
                        + " SELECT od.vendor_id as vendor_id,"
                        + "        sum(od.grand_total) AS grand_total "
                        + " FROM team_3.orders od "
                        + " INNER JOIN team_3.vendor vd ON vd.id = od.vendor_id "
                        + " WHERE od.vendor_id = :vendorId AND od.updated_at BETWEEN :start_date AND :end_date";

        Query query = entityManager.createNativeQuery(strQuery,"RevenueVendorDto");
        query.setParameter("vendorId",vendorId);
        query.setParameter("start_date",start_date);
        query.setParameter("end_date",end_date);

        RevenueVendorDto statistics = (RevenueVendorDto) query.getSingleResult();

        return statistics;
    }


}
