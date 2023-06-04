package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderDetail {
    @Id
    @Column(name = "detail_id")
    private String detailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_color_id")
    private ProductVariant productVariant;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "quantity")
    private Integer quantity;
}
