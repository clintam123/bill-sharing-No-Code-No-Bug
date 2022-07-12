package com.nocodenobug.billsharing.model.entity;

import com.nocodenobug.billsharing.constants.ProductStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String description;
    private String sku;
    private BigDecimal price;
    private int quantity;
    private float discount;
    private ProductStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    @Column(name = "category_id")
//    private Long categoryId;

//    @Column(name = "product_group_id")
//    private Long productGroupId;
    @ManyToOne
    @JoinColumn(name = "product_group_id")
    private ProductGroup productGroup;

    @PrePersist
    public void prePersist() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date =  LocalDateTime.now().format(formatter);
        this.sku = this.category.getCode() + ".sku." + date;
        this.status = ProductStatus.valueOf("ACTIVE");
    }
}
