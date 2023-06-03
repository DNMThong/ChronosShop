package com.chronos.chronosshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "address_shipping", schema = "dbo", catalog = "ChronosShoppingOnline")
public class AddressShipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_id")
    private int shipId;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @Column(name = "recipient_address")
    private String recipientAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "addressShipping")
    List<Orders> orders;


}
