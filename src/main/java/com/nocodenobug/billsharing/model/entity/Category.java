package com.nocodenobug.billsharing.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String code;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

//    @ManyToOne
//    @JoinColumn(name = "admin_id")
//    private Admin admin;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "image_url")
    private String imageUrl;
}
