package com.nocodenobug.billsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(
        name = "RevenueVendorDto",
        classes =
        @ConstructorResult(
                targetClass = RevenueVendorDto.class,
                columns = {
                        @ColumnResult(name = "vendor_id", type = Long.class),
                        @ColumnResult(name = "grand_total", type = BigDecimal.class)
                }
        )
)

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RevenueVendorDto {

        @Id
        private Long vendor_id;
        private BigDecimal grand_total;

}