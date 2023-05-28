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
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "ship_id")
    private Integer shipId;

    @Column(name = "coupon_id")
    private String couponId;

    @Column(name = "status")
    private String status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "payment_id")
    private String paymentId;

}
