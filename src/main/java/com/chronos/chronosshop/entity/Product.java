package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "status")
    private String status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
