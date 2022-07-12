package com.nocodenobug.billsharing.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer status;
    private Float shipping;
    private BigDecimal total;
    private Float discount;
    private BigDecimal grandTotal;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonProperty("vendor_id")
    @Column(name = "vendor_id")
    private Long vendorId;

    @JsonProperty("customer_id")
    @Column(name = "customer_id")
    private Long customerId;

//    @ManyToOne
//    @JoinColumn(name = "vendor_id")
//    private Vendor vendor;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
