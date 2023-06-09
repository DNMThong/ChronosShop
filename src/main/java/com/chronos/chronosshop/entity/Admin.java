package com.chronos.chronosshop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Admin implements UserDetails {
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

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Column(name = "isDelete")
    private Boolean isDelete;
}
