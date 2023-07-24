package com.ba.quepro.token.system.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtils {

    public static Date getDate(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String getDateStringFromDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return formatter.format(date);
    }

    public static Date convertStringToDate(String dateStr) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        LocalDateTime nextTime = format.parse(dateStr).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Instant instant = nextTime.toInstant(ZoneOffset.UTC);
        return Date.from(instant);
    }

    public static String getTimeStringFromDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }
}
