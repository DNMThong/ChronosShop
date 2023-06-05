package com.chronos.chronosshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Users {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "users")
    private Cart cart;

    @OneToMany(mappedBy = "users")
    List<AddressShipping> addressShippingList;

    @OneToMany(mappedBy = "users")
    List<Payment> payments;

    @OneToMany(mappedBy = "users")
    List<Orders> orders;

}
