package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        boolean isBefore = endTime == null || lt.compareTo(endTime) < 0;
        boolean isAtOrAfter = startTime == null || lt.compareTo(startTime) >= 0;
        return isAtOrAfter && isBefore;
    }

    public static String prepareTimeForJsp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        return DATE_TIME_FORMATTER.format(localDateTime);
    }
}
