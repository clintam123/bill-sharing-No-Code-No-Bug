package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendoRepository extends JpaRepository<Vendor,Long> {
    Vendor findAllByPhone(String phone);
}
