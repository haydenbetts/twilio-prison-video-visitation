package fm.liveswitch;

import java.util.Date;

public class UnixTimestamp {
    private static long _baseTicks = DateExtensions.getTicks(DateExtensions.createDate(1970, 1, 1, 0, 0, 0));

    public static long dateTimeToUnix(Date date) {
        return ticksToUnix(DateExtensions.getTicks(date));
    }

    public static long dateTimeToUnixMillis(Date date) {
        return ticksToUnixMillis(DateExtensions.getTicks(date));
    }

    public static long getUtcNow() {
        return dateTimeToUnix(DateExtensions.getUtcNow());
    }

    public static long getUtcNowMillis() {
        return dateTimeToUnixMillis(DateExtensions.getUtcNow());
    }

    public static long ticksToUnix(long j) {
        return (j - _baseTicks) / ((long) Constants.getTicksPerSecond());
    }

    public static long ticksToUnixMillis(long j) {
        return (j - _baseTicks) / ((long) Constants.getTicksPerMillisecond());
    }

    public static Date unixMillisToDateTime(long j) {
        return DateExtensions.createDate(unixMillisToTicks(j));
    }

    public static long unixMillisToTicks(long j) {
        return _baseTicks + (j * ((long) Constants.getTicksPerMillisecond()));
    }

    public static Date unixToDateTime(long j) {
        return DateExtensions.createDate(unixToTicks(j));
    }

    public static long unixToTicks(long j) {
        return _baseTicks + (j * ((long) Constants.getTicksPerSecond()));
    }
}
