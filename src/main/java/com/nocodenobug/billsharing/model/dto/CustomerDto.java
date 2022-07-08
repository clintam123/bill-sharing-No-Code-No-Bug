package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocodenobug.billsharing.constants.RegexConstants;
import com.nocodenobug.billsharing.model.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
public class CustomerDto {

    private Long id;

    @JsonProperty("first_name")
    @NotBlank(message = "firstName not blank")
    @Size(min = 0,max = 50,message = "firstName length from 0 to 50")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "lastName not blank")
    @Size(min = 0,max = 50,message = "lastName length from 0 to 50")
    private String lastName;

    @NotBlank(message = "Phone number not blank")
    @Pattern(regexp = RegexConstants.PHONE_REGEX,message = "Phone number validation failed")
    @Length(max = 10)
    private String phone;

    @Email
    private String email;

    private int admin;

    @JsonProperty("user_id")
    private Long userId;
}
