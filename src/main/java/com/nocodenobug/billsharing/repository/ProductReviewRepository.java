package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {
    @Query("select pr from ProductReview pr where pr.productId = :id")
    Page<ProductReview> findAllByProductId(@Param("id") int productId, Pageable pageable);
}