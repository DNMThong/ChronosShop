package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDateTime;
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
@Table(name = "product_variant")
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

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "productVariant")
    List<Cart> carts;

    @OneToMany(mappedBy = "productVariant")
    List<OrderDetail> orderDetailList;
}
