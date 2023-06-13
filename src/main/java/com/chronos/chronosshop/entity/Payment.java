package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

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
    private LocalDateTime paymentDate;

    public Payment(String paymentId, String paymentMethodName, Users user, Long subtotal, Long subtotalFee, Long total, String currency, String status, LocalDateTime paymentDate, Boolean deleted) {
        this.paymentId = paymentId;
        this.paymentMethodName = paymentMethodName;
        this.user = user;
        this.subtotal = subtotal;
        this.subtotalFee = subtotalFee;
        this.total = total;
        this.currency = currency;
        this.status = status;
        this.paymentDate = paymentDate;
        this.deleted = deleted;
    }

    @OneToOne(mappedBy = "payment")
    private Orders orders;

    @Column(name = "deleted")
    private Boolean deleted;

}
