package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final LocalDateTime MIN_DATE = LocalDateTime.of(1, 1, 1, 0, 0);
    private static final LocalDateTime MAX_DATE = LocalDateTime.of(3000, 1, 1, 0, 0);


    public static boolean isBetweenHalfOpen(Comparable lt, Comparable startTime, Comparable endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime getStartInclusive(LocalDate localDate) {
        return localDate != null ? localDate.atStartOfDay() : MIN_DATE;
    }

    public static LocalDateTime getEndExclusive(LocalDate localDate) {
        return localDate != null ? localDate.plus(1, ChronoUnit.DAYS).atStartOfDay() : MAX_DATE;
    }
}

