package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.model.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderItemDto {

    private Long id;

    @Min(value = 1,message = "Số lượng phải lớn hơn không")
    private Integer quantity;

    @NotBlank(message = "Content Không được để trống")
    private String content;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonProperty("product_id")
    private Long productId;

    private Customer customer;

//    @JsonProperty("order_id")
//    private Long orderId;
    private Order order;
}
