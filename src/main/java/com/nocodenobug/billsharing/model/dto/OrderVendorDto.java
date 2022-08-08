package com.nocodenobug.billsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "VendorOrderStatisticsDto",
        classes =
        @ConstructorResult(
                targetClass = OrderVendorDto.class,
                columns = {
                        @ColumnResult(name = "vendor_id", type = Long.class),
                        @ColumnResult(name = "shipping", type = Float.class),
                        @ColumnResult(name = "discount", type = Float.class),
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "fullname", type = String.class),
                        @ColumnResult(name = "grand_total", type = BigDecimal.class),
                        @ColumnResult(name = "created_at", type = LocalDate.class),
                        @ColumnResult(name = "updated_at", type = LocalDate.class),
                        @ColumnResult(name = "profile", type = String.class)
                }
        )
)

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderVendorDto {

    @Id
    private Long vendor_id;
    private Float shipping;
    private Float discount;
    private String username;
    private String fullname;
    private BigDecimal grand_total;
    private LocalDate created_at;
    private LocalDate updated_at;
    private String profile;


}
