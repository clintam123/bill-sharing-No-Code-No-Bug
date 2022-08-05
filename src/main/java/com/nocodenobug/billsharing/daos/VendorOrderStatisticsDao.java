package com.nocodenobug.billsharing.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VendorOrderStatisticsDao {

    private final EntityManager entityManager;

    public List<?> vendorOrderStatistics(Long vendorId,Long userId, LocalDate start_date, LocalDate end_date) {
        String strQuery =
                " "
                        + " SELECT od.vendor_id as vendor_id, "
                        + "        od.shipping as shipping, "
                        + "        od.discount as discount, "
                        + "        od.grand_total as grand_total, "
                        + "        vd.profile as profile "
                        + " FROM team_3.orders od "
                        + " INNER JOIN team_3.vendor vd ON vd.id = od.vendor_id "
                        + " WHERE od.vendor_id = :vendorId AND od.user_id = :userId AND od.updated_at BETWEEN :start_date AND :end_date";

        Query query = entityManager.createNativeQuery(strQuery,"VendorOrderStatisticsDto");
        query.setParameter("vendorId", vendorId);
        query.setParameter("userId",userId);
        query.setParameter("start_date",start_date);
        query.setParameter("end_date",end_date);

        List<?> list = query.getResultList();
        return list;
    }


}
