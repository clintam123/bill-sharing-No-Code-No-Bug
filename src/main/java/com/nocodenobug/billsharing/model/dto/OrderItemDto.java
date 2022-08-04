package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.model.entity.User;
import lombok.Getter;
import lombok.Setter;

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
    private BigDecimal price;

    @JsonProperty("product_id")
    private Long productId;

//    private User user;

    @JsonProperty("user_id")
    private Long userId;

    private String username;

    @JsonProperty("product_name")
    private String productName;

//    @JsonProperty("order_id")
//    private Long orderId;
    private Order order;
}
