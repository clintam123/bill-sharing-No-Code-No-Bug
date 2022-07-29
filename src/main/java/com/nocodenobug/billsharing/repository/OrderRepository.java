package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.model.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findByStatusEquals(Integer status, Pageable pageable);

    Page<Order> findAllByVendorIdAndStatusEquals(Long vendorId,Pageable pageable,Integer status);

    Page<Order> findAllByUserIdAndStatusEquals(Long userId, Pageable pageable, Integer status);

    // thống kê doanh thu vendor từ ngày đầu đến ngày cuối (tính theo grandTotal của order)
    @Query("select od.id,vd.profile,vd.district from Order od inner join Vendor vd on od.vendorId = vd.id where od.updatedAt = ?1 group by od.vendorId")
    List<Order> statistics(LocalDateTime createdAt);

}
