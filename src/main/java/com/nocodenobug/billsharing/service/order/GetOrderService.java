package com.nocodenobug.billsharing.service.order;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.response.SamplePagingResponse;
import org.springframework.data.domain.Page;

public interface GetOrderService {

    Page<OrderDto> findAllByStatus(int page, int pageSize);

    Page<OrderDto> findAllByVendorId(Long id, int page, int page_size);

    Page<OrderDto> findAllByCustomerId(Long id, int page, int page_size);

    OrderDto findById(Long id);
}
