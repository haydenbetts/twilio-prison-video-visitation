package com.urbanairship.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    private static final SimpleDateFormat ALT_ISO_DATE_FORMAT;
    private static final SimpleDateFormat ISO_DATE_FORMAT;
    private static final Object lock = new Object();

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        ISO_DATE_FORMAT = simpleDateFormat;
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        ALT_ISO_DATE_FORMAT = simpleDateFormat2;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private DateUtils() {
    }

    public static long parseIso8601(String str) throws ParseException {
        long time;
        if (str != null) {
            try {
                synchronized (lock) {
                    try {
                        time = ISO_DATE_FORMAT.parse(str).getTime();
                    } catch (ParseException unused) {
                        return ALT_ISO_DATE_FORMAT.parse(str).getTime();
                    }
                }
                return time;
            } catch (Exception e) {
                throw new ParseException("Unexpected issue when attempting to parse " + str + " - " + e.getMessage(), -1);
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new ParseException("Unable to parse null timestamp", -1);
        }
    }

    public static long parseIso8601(String str, long j) {
        try {
            return parseIso8601(str);
        } catch (ParseException unused) {
            return j;
        }
    }

    public static String createIso8601TimeStamp(long j) {
        String format;
        synchronized (lock) {
            format = ISO_DATE_FORMAT.format(new Date(j));
        }
        return format;
    }
}
