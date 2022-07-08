package com.nocodenobug.billsharing.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "intro")
    private String intro;
    @Column(name = "profile")
    private String profile;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "province")
    private String province;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
