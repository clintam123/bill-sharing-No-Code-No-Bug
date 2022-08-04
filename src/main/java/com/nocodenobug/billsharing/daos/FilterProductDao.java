package com.nocodenobug.billsharing.daos;

import com.nocodenobug.billsharing.constants.ESort;
import com.nocodenobug.billsharing.payload.request.FilterRequest;
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
public class FilterProductDao {

    private final EntityManager entityManager;

    public Page<?> searchProductByFilter(FilterRequest filters, Pageable pageable){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM product_rating pr ");
        sb.append(" WHERE pr.status = 1 ");

        if (filters.getProductName() != null){
            sb.append(" AND pr.product_name like :product_name ");
        }
        if (filters.getCategoryTitle() != null){
            sb.append(" AND pr.category like :category ");
        }
        if (filters.getVendorName() != null){
            sb.append(" AND pr.vendor like :vendor_name ");
        }
        if (filters.getSort() == ESort.DISCOUNT.getValue()){
            sb.append(" ORDER BY pr.discount DESC ");
        }
        if (filters.getSort() == ESort.RATING.getValue()){
            sb.append(" ORDER BY pr.rating DESC ");
        }

        Query query = entityManager.createNativeQuery(String.valueOf(sb), "ProductInfoDto");

        if (filters.getProductName() != null){
            query.setParameter("product_name", "%"+filters.getProductName()+"%");
        }

        if (filters.getVendorName() != null){
            query.setParameter("vendor_name", "%"+filters.getVendorName()+"%");
        }
        if (filters.getCategoryTitle() != null){
            query.setParameter("category", "%"+filters.getCategoryTitle()+"%");
        }
        System.out.println(query.toString());

        int total = 0;
        int index;
        if (pageable != null) {
            total = getTotalProductByFilter(filters);
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

    public int getTotalProductByFilter(FilterRequest filters){
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT count(*)");
        sb.append(" FROM product_rating pr ");
        sb.append(" WHERE status = 1 ");

        if (filters.getProductName() != null){
            sb.append(" AND pr.product_name like :product_name ");
        }
        if (filters.getCategoryTitle() != null){
            sb.append(" AND pr.category like :category ");
        }
        if (filters.getVendorName() != null){
            sb.append(" AND pr.vendor like :vendor_name ");
        }

        Query query = entityManager.createNativeQuery(String.valueOf(sb));

        if (filters.getVendorName() != null){
            query.setParameter("vendor_name", "%"+filters.getVendorName()+"%");
        }
        if (filters.getCategoryTitle() != null){
            query.setParameter("category", "%"+filters.getCategoryTitle()+"%");
        }

        if (filters.getProductName() != null){
            query.setParameter("product_name", "%"+filters.getProductName()+"%");
        }

        return ((Number)query.getSingleResult()).intValue();
    }
}
