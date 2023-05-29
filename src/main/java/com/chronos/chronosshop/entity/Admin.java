package com.chronos.chronosshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Admin {
    @Id
    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name = "position")
    private String position;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    private Date updateTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_login")
    private Date lastLogin;
}
