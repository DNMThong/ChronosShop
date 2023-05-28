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
@Table(name = "product_variant", schema = "dbo", catalog = "ChronosShoppingOnline")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_color_id")
    private int productColorId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_sku")
    private String productSku;

    @Column(name = "product_colorName")
    private String productColorName;

    @Column(name = "product_size")
    private String productSize;

    @Column(name = "inventory_quantity")
    private Integer inventoryQuantity;

    @Column(name = "image1")
    private String image1;

    @Column(name = "create_time")
    private Date createTime;
}
