package com.chronos.chronosshop.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsersDto {
    @NotBlank(message = "Vui lòng nhập họ vào tên")
    String fullname;

    @NotBlank(message = "Vui lòng nhập số điện thoại")
    String phone;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Vui lòng nhập email")
    private String email;

    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    @NotBlank(message = "Vui lòng nhập mật khẩu")
    private String password;
}
