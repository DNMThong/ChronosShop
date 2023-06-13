package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;

import java.sql.Date;
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
@NamedStoredProcedureQuery(
        name = "TaoOrderVaPayment",
        procedureName = "TaoOrderVaPayment",
        parameters = {
                @StoredProcedureParameter(name = "user_id", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "ship_id", mode = ParameterMode.IN, type = int.class),
                @StoredProcedureParameter(name = "coupon_id", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "status", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "create_time", mode = ParameterMode.IN, type = LocalDateTime.class),
                @StoredProcedureParameter(name = "update_time", mode = ParameterMode.IN, type = LocalDateTime.class),
                @StoredProcedureParameter(name = "payment_method_name", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "subtotal", mode = ParameterMode.IN, type = Long.class),
                @StoredProcedureParameter(name = "subtotal_fee", mode = ParameterMode.IN, type = Long.class),
                @StoredProcedureParameter(name = "total", mode = ParameterMode.IN, type = Long.class),
                @StoredProcedureParameter(name = "currency", mode = ParameterMode.IN, type = String.class),

        },
        resultSetMappings = "OrdersMapping"
)
@SqlResultSetMapping(
        name = "OrdersMapping",
        entities = @EntityResult(entityClass = Orders.class)
)
public class Orders {
    @Id
    @Column(name = "order_id")
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private AddressShipping addressShipping;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(name = "status")
    private String status;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @Column(name = "deleted")
    private Boolean deleted;

}
