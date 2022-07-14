package com.nocodenobug.billsharing.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "registered_at")
    @CreationTimestamp
    private LocalDateTime registeredAt;
//    @Column(name = "last_login")
//    private LocalDateTime lastLogin;
    @Column(name = "email")
    private String email;

    public User(String username, String passwordHash, String email, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {

    }
}
