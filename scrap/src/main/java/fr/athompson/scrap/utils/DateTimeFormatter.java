package fr.athompson.scrap.utils;

import java.time.LocalDateTime;

public class DateTimeFormatter {

    public final static java.time.format.DateTimeFormatter JJ_MM_AAAA_SLASH_HH_MM = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static LocalDateTime toLocalDateTime(String date, String heureMinutes, java.time.format.DateTimeFormatter format) {
        String stringToDate = date + ' ' + heureMinutes;
        return LocalDateTime.parse(stringToDate, format);
    }

}
