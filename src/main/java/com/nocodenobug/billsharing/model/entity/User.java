package com.nocodenobug.billsharing.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phone;

    private String email;

    private String role;

    @Column(name = "public_image_id")
    private String publicImageId;

    private String provider;


}
