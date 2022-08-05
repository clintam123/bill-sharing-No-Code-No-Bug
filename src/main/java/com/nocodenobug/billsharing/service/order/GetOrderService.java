package com.nocodenobug.billsharing.service.order;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.Order;
import org.springframework.data.domain.Page;

public interface GetOrderService {

    Page<OrderDto> findAllByStatus(Integer page, Integer pageSize);

    Page<OrderDto> findAllByVendorId(Long id, Integer page, Integer page_size);

    Page<OrderDto> findAllByUserId(Long id, Integer page, Integer page_size);

    OrderDto findById(Long id);
}
