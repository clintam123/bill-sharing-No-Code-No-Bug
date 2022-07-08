package com.nocodenobug.billsharing.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocodenobug.billsharing.constants.RegexConstants;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity

@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;

    private String phone;

    private String email;

    private int admin;

    @JsonProperty("user_id")
    @Column(name = "user_id")
    private Long userId;
}
