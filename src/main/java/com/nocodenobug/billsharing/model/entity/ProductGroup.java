package com.nocodenobug.billsharing.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_group")
@Data
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
//    @ManyToOne
//    @JoinColumn(name = "vendor_id")
//    private Vendor vendor;
    @Column(name = "vendor_id")
    private Long vendorId;
    @OneToMany(mappedBy = "productGroup")
    private List<Product> productList;
}
