package com.chronos.chronosshop.entity.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PasswordDto {

    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;
    @NotBlank(message = "Vui lòng nhập xác nhận mật khẩu")
    private String confirmPassword;

    @AssertTrue(message = "Mật khẩu xác nhận không đúng")
    public boolean isPasswordConfirmed() {
        return password.equals(confirmPassword);
    }
}
