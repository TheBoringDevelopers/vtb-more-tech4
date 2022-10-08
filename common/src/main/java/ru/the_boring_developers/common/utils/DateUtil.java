package ru.the_boring_developers.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {
    }

    public static OffsetDateTime now() {
        return OffsetDateTime.now(ZoneId.of("Europe/Moscow"));
    }


    public static OffsetDateTime millisToOffsetDateTime(Long millis) {
        if (millis == null) {
            return null;
        }
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDate) {
        return localDate.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return date != null ? date.format(formatter) : null;
    }
}
