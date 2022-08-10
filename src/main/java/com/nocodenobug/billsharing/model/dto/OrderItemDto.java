package com.nocodenobug.billsharing.model.dto;

import com.nocodenobug.billsharing.model.entity.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderItemDto {

    private Long id;

    @Min(value = 1, message = "Số lượng phải lớn hơn không")
    private Integer quantity;

    @NotBlank(message = "Content Không được để trống")
    private String content;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal price;

    private Long productId;

    private Long userId;

    private String username;

    private String productName;

    private String imageUrl;

    private Order order;
}
