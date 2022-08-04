package com.nocodenobug.billsharing.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public Page<?> getUserWithReviewByProductId(Long productId, Pageable pageable) {
        String strQuery =
                " "
                        + "SELECT  r.title AS title, "
                        + "        r.rating AS rating, "
                        + "        r.content AS content, "
                        + "        r.modified_at AS modified_at, "
                        + "        concat(u.first_name,' ' ,u.last_name) AS customer_name "
                        + " FROM product_review r "
                        + " JOIN user u ON u.id = r.user_id "
                        + " WHERE r.product_id = :productId "
                        + " ORDER BY r.modified_at DESC ";

        Query query = entityManager.createNativeQuery(strQuery, "CustomerReviewDto");
        query.setParameter("productId", productId);

        int total = 0;
        int index;
        if (pageable != null) {
            total = getTotalReviewByProductId(productId);
            index = pageable.getPageNumber() * pageable.getPageSize();
            query.setFirstResult(index);
            query.setMaxResults(pageable.getPageSize());
            if (index >= total) {
                return Page.empty();
            }
        } else {
            query.setMaxResults(50);
        }
        List<?> list = query.getResultList();

        return pageable == null ? new PageImpl<>(list) : new PageImpl<>(list, pageable, total);
    }

    //tính tổng sổ bình luận của sản phẩm
    public int getTotalReviewByProductId(Long productId) {
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
