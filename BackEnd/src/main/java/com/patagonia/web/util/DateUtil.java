package com.patagonia.web.util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class DateUtil {

    public static Date getJustDateFrom(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }

    public static Date getCurrentTimeFilterFormat(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        Date date = new Date();
        try {
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Calendar getCurrentTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MILLISECOND, 0);
        System.err.println("CURRENT TIME: " + c.getTime());
        return c;
    }

    public static Instant DurationToTimestamp (String durationStr, Date d) {
        Duration duration = Duration.parse(durationStr);
        return Instant.now().minus(duration);
    }

    public static long DurationToMiliseconds (String durationStr) {
        Duration duration = Duration.parse(durationStr);
        return duration.toMillis();
    }

    public static Map<String, Integer> convertDurationToHoursAndMinutes(String durationString) {
        Duration duration = Duration.parse(durationString);

        int hours = (int) duration.toHours();
        int minutes = (int) duration.minusHours(hours).toMinutes();

        Map<String, Integer> result = new HashMap<>();
        result.put("hours", hours);
        result.put("minutes", minutes);

        return result;
    }

}
