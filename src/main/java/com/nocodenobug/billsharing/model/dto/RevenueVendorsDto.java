package com.nocodenobug.billsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(
        name = "RevenueVendorsDto",
        classes =
        @ConstructorResult(
                targetClass = RevenueVendorsDto.class,
                columns = {
                        @ColumnResult(name = "vendor_id", type = Integer.class),
                        @ColumnResult(name = "total_vendor", type = BigDecimal.class)
                }
        )
)

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevenueVendorsDto {


    @Id
    private Integer vendor_id;
    private BigDecimal total_vendor;
}
