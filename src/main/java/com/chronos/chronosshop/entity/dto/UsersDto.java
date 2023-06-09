package com.chronos.chronosshop.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsersDto {
    @Email(message = "Email không hợp lệ")
    private String username;

    @NotBlank(message = "Vui lòng nhập password")
    @Min(message = "Mật khẩu phải có ít nhất 6 ký tự",value = 6)
    private String password;
}
