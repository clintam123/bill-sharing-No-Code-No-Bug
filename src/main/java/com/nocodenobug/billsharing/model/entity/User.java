package com.nocodenobug.billsharing.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String username, String passwordHash, String email, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
    }

    public User() {

    }
}
