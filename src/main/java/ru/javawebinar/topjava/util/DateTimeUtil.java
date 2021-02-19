package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static Date convertToDate(LocalDateTime ldt) {
        return java.sql.Timestamp.valueOf(ldt);
    }

    public static LocalDateTime convertToLocalDate(Date date) {
        return new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static String toString(Date date) {
        LocalDateTime ldt = convertToLocalDate(date);
        return ldt == null ? "" : ldt.format(DATE_FORMATTER);
    }
}

