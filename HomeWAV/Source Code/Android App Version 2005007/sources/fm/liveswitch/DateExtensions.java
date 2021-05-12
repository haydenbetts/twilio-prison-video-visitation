package fm.liveswitch;

import androidx.exifinterface.media.ExifInterface;
import androidx.work.WorkRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateExtensions {
    private static long origin = -62135596800000L;

    public static Date createDate(long j) {
        return new Date((j / WorkRequest.MIN_BACKOFF_MILLIS) + origin);
    }

    public static Date createDate(int i, int i2, int i3, int i4, int i5, int i6) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2 - 1, i3, i4, i5, i6);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return gregorianCalendar.getTime();
    }

    public static Date createDate(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return addMilliseconds(createDate(i, i2, i3, i4, i5, i6), (double) i7);
    }

    public static Date getNow() {
        return new Date();
    }

    public static Date getUtcNow() {
        return new Date();
    }

    public static long getTicks(Date date) {
        return (long) (((double) (date.getTime() - origin)) * 10000.0d);
    }

    public static Date toUniversalTime(Date date) {
        return new Date(date.getTime());
    }

    public static String toString(Date date, String str, IFormatProvider iFormatProvider) {
        return new SimpleDateFormat(str.replace(ExifInterface.GPS_DIRECTION_TRUE, "'T'").replace("f", ExifInterface.LATITUDE_SOUTH)).format(date).substring(0, 22).replace("'T'", ExifInterface.GPS_DIRECTION_TRUE);
    }

    public static int getYear(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return gregorianCalendar.get(1);
    }

    public static int getMonth(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return gregorianCalendar.get(2) + 1;
    }

    public static int getDay(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return gregorianCalendar.get(5);
    }

    public static int getHour(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return gregorianCalendar.get(11);
    }

    public static int getMinute(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return gregorianCalendar.get(12);
    }

    public static int getSecond(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return gregorianCalendar.get(13);
    }

    public static int getMillisecond(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return gregorianCalendar.get(14);
    }

    public static Date addHours(Date date, double d) {
        return createDate(getTicks(date) + ((long) (d * ((double) Constants.getTicksPerHour()))));
    }

    public static Date addMinutes(Date date, double d) {
        return createDate(getTicks(date) + ((long) (d * ((double) Constants.getTicksPerMinute()))));
    }

    public static Date addSeconds(Date date, double d) {
        return createDate(getTicks(date) + ((long) (d * ((double) Constants.getTicksPerSecond()))));
    }

    public static Date addMilliseconds(Date date, double d) {
        return createDate(getTicks(date) + ((long) (d * ((double) Constants.getTicksPerMillisecond()))));
    }
}
