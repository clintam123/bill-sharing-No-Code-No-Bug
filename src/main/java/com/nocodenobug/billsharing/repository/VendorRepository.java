package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {
    Vendor findAllByPhone(String phone);






}
