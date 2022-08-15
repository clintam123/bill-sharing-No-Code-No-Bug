package com.nocodenobug.billsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocodenobug.billsharing.constants.RegexConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private LocalDateTime registeredAt;

    @NotBlank(message = "username not blank")
    @Size(min = 0,max = 50,message = "username length from 0 to 50")
    private String username;

    @JsonProperty("password_hash")
    @NotBlank(message = "passwordHash not blank")
    private String passwordHash;

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

    @NotBlank(message = "role not blank")
    private String role;

    @JsonProperty("image_url")
    private String imageUrl;

    private String verificationCode;
    private Integer status;

}
