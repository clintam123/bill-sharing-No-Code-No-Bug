package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p.*" +
            "FROM product p " +
            "JOIN category c ON p.category_id = c.id " +
            "WHERE c.title = :title",
    nativeQuery = true)
    Page<Product> getProductByCategoryTitle(@Param("title") String title, Pageable pageable);
}
