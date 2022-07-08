package com.nocodenobug.billsharing.reponsitory;

import com.nocodenobug.billsharing.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenderReponsitory extends JpaRepository<Vendor,Long> {
}
