package com.nocodenobug.billsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "ProductInfoDto",
        classes =
        @ConstructorResult(
                targetClass = ProductInfoDto.class,
                columns = {
                        @ColumnResult(name = "product_id", type = Long.class),
                        @ColumnResult(name = "product_name", type = String.class),
                        @ColumnResult(name = "category", type = String.class),
                        @ColumnResult(name = "vendor", type = String.class),
                        @ColumnResult(name = "vendor_id", type = Long.class),
                        @ColumnResult(name = "prince", type = Float.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "discount", type = Float.class),
                        @ColumnResult(name = "rating", type = Integer.class),
                        @ColumnResult(name = "product_image", type = String.class),
                        @ColumnResult(name = "vendor_logo", type = String.class),

        }))
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDto {
    @Id
    private Long product_id;
    private String product_name;
    private String category;
    private String product_group;
    private String vendor;
    private Long vendor_id;
    private Float prince;
    private Integer quantity;
    private Integer status;
    private String description;
    private Float discount;
    private Integer rating;
    private String product_image;
    private Long vendor_logo;
}
