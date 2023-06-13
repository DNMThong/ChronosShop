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
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @Column(name = "detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_color_id")
    private ProductVariant productVariant;

    @Column(name = "product_price")
    private Integer productPrice;

    public OrderDetail(Orders order, ProductVariant productVariant, Integer productPrice, Integer quantity) {
        this.order = order;
        this.productVariant = productVariant;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    @Column(name = "quantity")
    private Integer quantity;
}
