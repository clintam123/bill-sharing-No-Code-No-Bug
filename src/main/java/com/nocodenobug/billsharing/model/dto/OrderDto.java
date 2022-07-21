package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.model.entity.Vendor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private Integer status;

    @Min(value = 1,message = "Tiền shipping phải lớn hơn không")
    private Float shipping;


    @Min(value = 1,message = "Tiền discount phải lớn hơn không")
    private Float discount;
    private BigDecimal grandTotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonProperty("vendor_id")
    private Long vendorId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("order_items")
    private List<OrderItem> orderItems;
}
