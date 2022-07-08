package com.nocodenobug.billsharing.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    private String email;
    private int admin;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
