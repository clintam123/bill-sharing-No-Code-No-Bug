package com.nocodenobug.billsharing.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(
        name = "VendorOrderStatisticsDto",
        classes =
        @ConstructorResult(
                targetClass = VendorOrderStatisticsDto.class,
                columns = {
                        @ColumnResult(name = "vendor_id", type = Long.class),
                        @ColumnResult(name = "shipping", type = Float.class),
                        @ColumnResult(name = "discount",type = Float.class),
                        @ColumnResult(name = "grand_total",type = BigDecimal.class),
                        @ColumnResult(name = "profile",type = String.class)
                }
        )
)

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendorOrderStatisticsDto {

    @Id
    private Long vendor_id;
    private Float shipping;
    private Float discount;
    private BigDecimal grand_total;
    private String profile;


}
