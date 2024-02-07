package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return isAtOrAfter(lt, startTime) && isBefore(lt, endTime);
    }

    public static boolean isAtOrAfter(LocalTime lt, LocalTime time) {
        return lt.compareTo(time) >= 0;
    }

    public static boolean isBefore(LocalTime lt, LocalTime time) {
        return lt.compareTo(time) < 0;
    }

    public static String prepareTimeForJsp(LocalDateTime localDateTime) {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }
}
