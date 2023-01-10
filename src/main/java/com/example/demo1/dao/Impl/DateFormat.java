package com.example.demo1.dao.Impl;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class DateFormat {
    public static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}

