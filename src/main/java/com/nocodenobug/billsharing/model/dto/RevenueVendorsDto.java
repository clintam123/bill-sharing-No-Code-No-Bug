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
                        @ColumnResult(name = "intro", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "district", type = String.class),
                        @ColumnResult(name = "province", type = String.class),
                        @ColumnResult(name = "revenue", type = BigDecimal.class)
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
    private String intro;
    private String address;
    private String district;
    private String province;
    private BigDecimal revenue;
}
