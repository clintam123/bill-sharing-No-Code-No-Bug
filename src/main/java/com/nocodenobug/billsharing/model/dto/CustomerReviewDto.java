package com.nocodenobug.billsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@SqlResultSetMapping(
        name = "CustomerReviewDto",
        classes =
        @ConstructorResult(
                targetClass = CustomerReviewDto.class,
                columns = {
                        @ColumnResult(name = "customer_name", type = String.class),
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "rating", type = Integer.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "modified_at", type = LocalDateTime.class),
                }))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReviewDto {
    @Id
    private String customer_name;
    private String title;
    private Integer rating;
    private String content;
    private LocalDateTime modified_at;
}