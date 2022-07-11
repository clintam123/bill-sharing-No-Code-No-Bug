package com.nocodenobug.billsharing.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocodenobug.billsharing.constants.RegexConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalTime;

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

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;


    @JsonProperty("opening_time")
    @Column(name = "opening_time")
    private LocalTime openingTime;


    @JsonProperty("closing_time")
    @Column(name = "closing_time")
    private LocalTime closingTime;

    private String phone;

    @JsonProperty("user_id")
    @JoinColumn(name = "user_id")
    private Long userId;
}
