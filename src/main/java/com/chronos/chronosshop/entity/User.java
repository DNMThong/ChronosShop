package com.chronos.chronosshop.entity;

import jakarta.persistence.*;
import lombok.*;

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
public class User {
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
    private Date birthday;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_date")
    private Date updatedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_login")
    private Date lastLogin;

    @Temporal(TemporalType.DATE)
    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    List<AddressShipping> addressShippingList;

    @OneToMany(mappedBy = "user")
    List<Payment> payments;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

}
