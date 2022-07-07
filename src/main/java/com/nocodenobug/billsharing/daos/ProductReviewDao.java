package com.nocodenobug.billsharing.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductReviewDao {

    private final EntityManager entityManager;

    //Lấy ra tên người dùng và bình luận của họ, có paginng và sorting
    public Page<?> getUserWithReviewByProductId(int productId, Pageable pageable) {
        String strQuery =
                " "
                        + "SELECT  r.title AS title, "
                        + "        r.rating AS rating, "
                        + "        r.content AS content, "
                        + "        r.modified_at AS modified_at, "
                        + "        concat(c.first_name,' ' ,c.last_name) AS username "
                        + " FROM product_review r "
                        + " JOIN customer c ON c.id = r.customer_id "
                        + " WHERE r.product_id = :productId "
                        + " ORDER BY r.modified_at DESC ";

        Query query = entityManager.createNativeQuery(strQuery, "CustomerReviewDto");

        query.setParameter("productId", productId);
        query.setParameter("productId", productId);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List list = query.getResultList();

        int total = getTotalReviewByProductId(productId);

        return new PageImpl<>(list, pageable, total);
    }

    //tính tổng sổ bình luận của sản phẩm
    public int getTotalReviewByProductId(int productId) {
        String strQuery =
                " "
                        + "SELECT  count(*) "
                        + " FROM product_review r "
                        + " WHERE r.product_id = :productId ";

        Query query = entityManager.createNativeQuery(strQuery);
        query.setParameter("productId", productId);
        return ((Number) query.getSingleResult()).intValue();
    }
}
