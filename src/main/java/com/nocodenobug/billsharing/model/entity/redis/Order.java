package com.nocodenobug.billsharing.model.entity.redis;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Data
@Document
@RequiredArgsConstructor(staticName = "of")
public class Order {
    @Id
    @Indexed
    private Long id;

    @Indexed @NonNull
    private Integer status;

    @Indexed @NonNull
    private Float shipping;

    @Indexed @NonNull
    private Float discount;

    @Indexed @NonNull
    private BigDecimal grandTotal;

    @Indexed @NonNull
    private String link;

    @Indexed @NonNull
    private Long vendorId;

    @Indexed @NonNull
    private Long userId;

    @NonNull
    private List<OrderItem> orderItems;
}
