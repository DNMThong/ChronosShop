package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "order_detail", schema = "dbo", catalog = "ChronosShoppingOnline")
public class OrderDetail {
    @Id
    @Column(name = "detail_id")
    private String detailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Long total;
}
