package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.ProductGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    Page<ProductGroup> findAllByVendorId(long vendorId, Pageable pageable);
}
