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
public class Payment {
    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_method_name")
    private String paymentMethodName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "subtotal")
    private Long subtotal;

    @Column(name = "subtotal_fee")
    private Long subtotalFee;

    @Column(name = "total")
    private Long total;

    @Column(name = "currency")
    private String currency;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_date")
    private Date paymentDate;
}
