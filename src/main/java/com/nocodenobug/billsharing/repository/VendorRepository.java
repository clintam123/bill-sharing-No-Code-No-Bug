package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {
    Vendor findAllByPhone(String phone);

    Optional<Vendor> findByIdAndUserId(Long id,Long userId);
    Vendor findByUserId(Long userId);
}
