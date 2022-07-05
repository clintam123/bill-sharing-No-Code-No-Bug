package com.nocodenobug.billsharing.model.dto;

import lombok.Data;

@Data
public class VendorDto {
    private Integer id;
    private String intro;
    private String profile;
    private String address;
    private String city;
    private String province;
}
