package com.nocodenobug.billsharing.model.entity.redis;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Document
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
public class OrderItemRedis {
    @Indexed
    @NonNull
    private Integer quantity;

    @Searchable
    private String content;

    @Indexed @NonNull
    private BigDecimal total;

    @Indexed @NonNull
    private BigDecimal price;

    @Indexed @NonNull
    private Long productId;

    @Indexed @NonNull
    private Long userId;

    @Indexed @NonNull
    private String username;

    @Indexed @NonNull
    private String productName;
}
