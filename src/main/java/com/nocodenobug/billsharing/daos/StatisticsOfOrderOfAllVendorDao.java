package com.nocodenobug.billsharing.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsOfOrderOfAllVendorDao {
    private final EntityManager entityManager;

    public List<?> statisticsOrderVendor(String start_date, String end_date) {
        String strQuery =
                " "
                        + " SELECT od.id AS id, "
                        + "        vd.profile AS profile, "
                        + "        us.username AS username, "
                        + "        concat(us.last_name, ' ', us.first_name) AS fullname, "
                        + "        od.grand_total AS grand_total "
                        + " FROM team_3.orders od "
                        + " INNER JOIN team_3.vendor vd ON vd.id = od.vendor_id "
                        + " INNER JOIN team_3.user us ON us.id = vd.user_id"
                        + " WHERE od.updated_at BETWEEN :start_date AND :end_date";
//                        + " GROUP BY od.vendor_id ";

        Query query = entityManager.createNativeQuery(strQuery,"StatisticsOfOrderOfAllVendorDto");
        query.setParameter("start_date", start_date);
        query.setParameter("end_date", end_date);

        List<?> list = query.getResultList();

        return list;
    }
}
