package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Service
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price_old")
    private Integer priceOld;

    @Column(name = "price_new")
    private Integer priceNew;

    @Column(name = "material")
    private String material;

    @Column(name = "description")
    private String description;

    @Column(name = "sold_record")
    private Integer soldRecord;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    private Date updateTime;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<ProductMedia> productMediaList;

    @OneToMany(mappedBy = "product")
    private List<ProductVariant> productVariants;

    public Product( String productName, Integer priceOld, Integer priceNew, String material, String description, Category category, String status) {
        this.productName = productName;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
        this.material = material;
        this.description = description;
        this.category = category;
        this.status = status;
    }
}
