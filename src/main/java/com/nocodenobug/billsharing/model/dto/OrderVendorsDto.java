package com.nocodenobug.billsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@SqlResultSetMapping(
        name = "OrderVendorsDto",
        classes =
        @ConstructorResult(
                targetClass = OrderVendorsDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "shipping", type = Float.class),
                        @ColumnResult(name = "discount", type = Float.class),
                        @ColumnResult(name = "profile", type = String.class),
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "fullname", type = String.class),
                        @ColumnResult(name = "grand_total", type = Float.class),
                        @ColumnResult(name = "created_at", type = LocalDate.class),
                        @ColumnResult(name = "updated_at", type = LocalDate.class),
                }))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderVendorsDto {

    @Id
    private Long id;
    private Float shipping;
    private Float discount;
    private String profile;
    private String username;
    private String fullname;
    private Float grand_total;
    private LocalDate created_at;
    private LocalDate updated_at;

}
