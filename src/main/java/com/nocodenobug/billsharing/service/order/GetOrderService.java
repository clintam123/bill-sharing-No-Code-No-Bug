package com.nocodenobug.billsharing.service.order;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import org.springframework.data.domain.Page;

public interface GetOrderService {

    Page<OrderDto> findAllByStatus(int page, int pageSize);

    Page<OrderDto> findAllByVendorId(Long id, int page, int page_size);

    Page<OrderDto> findAllByUserId(Long id, int page, int page_size);

    OrderDto findById(Long id);
}
