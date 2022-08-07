package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocodenobug.billsharing.constants.RegexConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Getter
@Setter
public class VendorDto {
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String intro;

    @NotBlank
    private String profile;

    @NotBlank
    @Size(max = 45)
    private String address;

    @NotBlank
    @Size(max = 45)
    private String district;

    @NotBlank
    @Size(max = 45)
    private String province;

    @NotBlank
    @Pattern(regexp = RegexConstants.PHONE_REGEX)
    @Length(max = 10)
    private String phone;


    @JsonProperty("opening_time")
    @Column(name = "opening_time")
    private LocalTime openingTime;

    @JsonProperty("closing_time")
    @Column(name = "closing_time")
    private LocalTime closingTime;

    private String logo;

    @JsonProperty("user_id")
    private Long userId;
}
