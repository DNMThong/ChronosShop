package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "discount_for")
    private String discountFor;

    @Column(name = "pid_or_order")
    private String pidOrOrder;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "expires_time")
    private LocalDateTime expiresTime;

    @OneToMany(mappedBy = "coupon")
    private List<Orders> orders;

    public Coupon(String couponId, String couponName, String discount, String discountFor, String pidOrOrder, LocalDateTime createTime, LocalDateTime expiresTime) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.discount = discount;
        this.discountFor = discountFor;
        this.pidOrOrder = pidOrOrder;
        this.createTime = createTime;
        this.expiresTime = expiresTime;
    }

    public String getDiscountForByAdmin() {
        if (discountFor.equals("Hoá đơn")) {
            return "Hoá đơn trên";
        } else if (discountFor.equals("Sản phẩm")) {
            return "Mã sản phẩm";
        } else return discountFor;
    }


}
