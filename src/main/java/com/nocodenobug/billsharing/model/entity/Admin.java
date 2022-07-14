package com.nocodenobug.billsharing.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;

    private String phone;

    private String email;

    @JsonProperty("user_id")
    @Column(name = "user_id")
    private Long userId;

}
