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
public class Coupon {
    @Id
    @Column(name = "coupon_id")
    private String couponId;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "discount")
    private String discount;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "expires_time")
    private Date expiresTime;

    @Column(name = "discount_for")
    private String discountFor;

    @OneToMany(mappedBy = "coupon")
    private List<Orders> orders;
}
