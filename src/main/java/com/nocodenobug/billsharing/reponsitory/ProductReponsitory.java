package com.nocodenobug.billsharing.reponsitory;

import com.nocodenobug.billsharing.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReponsitory extends JpaRepository<Product,Long> {


}
