package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_color_id")
    private int productColorId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "product_sku")
    private Image image;

    @Column(name = "product_color_name")
    private String productColorName;

    @Column(name = "product_size")
    private String productSize;

    @Column(name = "inventory_quantity")
    private Integer inventoryQuantity;

    @Column(name = "image1")
    private String image1;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;
}
