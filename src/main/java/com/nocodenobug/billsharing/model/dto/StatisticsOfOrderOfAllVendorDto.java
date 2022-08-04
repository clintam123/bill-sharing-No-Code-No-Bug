package com.nocodenobug.billsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@SqlResultSetMapping(
        name = "StatisticsOfOrderOfAllVendorDto",
        classes =
        @ConstructorResult(
                targetClass = StatisticsOfOrderOfAllVendorDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "profile", type = String.class),
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "fullname", type = String.class),
                        @ColumnResult(name = "grand_total", type = Float.class),
                }))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsOfOrderOfAllVendorDto {

    @Id
    private Integer id;
    private String profile;
    private String username;
    private String fullname;
    private Float grand_total;

}
