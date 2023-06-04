package com.chronos.chronosshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class JdbcExecuteQuery {
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
                "@create_time = '" + formatDateTime(LocalDateTime.now()) + "', " +
                "@update_time = '" + formatDateTime(LocalDateTime.now()) + "', " +
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
                "@created_date = '" + formatDateTime(LocalDateTime.now()) + "', " +
                "@updated_date = '" + formatDateTime(LocalDateTime.now()) + "', " +
                "@last_login = null, " +
                "@status = N'Hoạt động';";
        template.execute(sql);
    }


    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static Timestamp createTimestamp(String datetime, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        java.util.Date parsedDate = dateFormat.parse(datetime);
        return new Timestamp(parsedDate.getTime());
    }
}
