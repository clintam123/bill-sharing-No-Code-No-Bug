package com.nocodenobug.billsharing.daos;

import com.nocodenobug.billsharing.model.dto.RevenueVendorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RevenueVendorsDao {

    private final EntityManager entityManager;

    public List<?> revenueVendors(LocalDate start_date, LocalDate end_date) {
        String strQuery =
                " "
                        + "SELECT od.vendor_id as vendor_id,"
                        + " vd.intro as intro,"
                        + " vd.address as address,"
                        + " vd.district as district,"
                        + " vd.province as province,"
                        + " sum(od.grand_total) as revenue"
                        + " FROM team_3.orders od"
                        + " inner join team_3.vendor vd"
                        + " on od.vendor_id = vd.id"
                        + " where od.updated_at BETWEEN :start_date AND :end_date"
                        + " GROUP BY od.vendor_id";

        Query query = entityManager.createNativeQuery(strQuery, RevenueVendorsDto.class);
        query.setParameter("start_date", start_date);
        query.setParameter("end_date", end_date);

        List<?> list = query.getResultList();
        return list;


    }

}
