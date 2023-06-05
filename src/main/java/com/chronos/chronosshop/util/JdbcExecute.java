package com.chronos.chronosshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.chronos.chronosshop.util.DateUtil.formatLocalDateTime;

@Service
public class JdbcExecute {
    @Autowired
    private JdbcTemplate template;

    public void saveAdmin(String name, String email, String image, String password) {
        String sql = "EXECUTE TaoAdmin " +
                "@name = N'" + name + "', " +
                "@email = '" + email + "', " +
                "@image = '" + image + "', " +
                "@position = N'Administrator', " +
                "@password = N'" + password + "', " +
                "@status = N'Active', " +
                "@create_time = '" + formatLocalDateTime(LocalDateTime.now()) + "', " +
                "@update_time = '" + formatLocalDateTime(LocalDateTime.now()) + "', " +
                "@last_login = null;";
        template.execute(sql);
    }

    public void saveUser(String username, String email, String phone, String password) {
        String sql = "EXECUTE TaoUser " +
                "@username = N'" + username + "', " +
                "@email = '" + email + "', " +
                "@phoneNo = '" + phone + "', " +
                "@gender = null, " +
                "@birthday = null, " +
                "@image = null, " +
                "@password = '" + password + "', " +
                "@created_date = '" + formatLocalDateTime(LocalDateTime.now()) + "', " +
                "@updated_date = '" + formatLocalDateTime(LocalDateTime.now()) + "', " +
                "@last_login = null, " +
                "@status = N'Hoạt động';";
        template.execute(sql);
    }
}
