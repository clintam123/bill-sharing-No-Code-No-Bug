package com.nocodenobug.billsharing.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private float price;
    private int quantity;
    private float discount;
    private int status;

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
}
