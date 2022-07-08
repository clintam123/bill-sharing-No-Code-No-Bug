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

    @Column(name = "username")
    private String username;
    @Column(name = "passwordHash")
    private String passwordHash;
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
}
