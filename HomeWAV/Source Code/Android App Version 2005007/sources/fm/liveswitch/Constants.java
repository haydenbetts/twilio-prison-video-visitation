package fm.liveswitch;

public class Constants {
    private static int __hoursPerDay = 24;
    private static int __millisecondsPerDay = (getMillisecondsPerHour() * getHoursPerDay());
    private static int __millisecondsPerHour = (getMillisecondsPerMinute() * getMinutesPerHour());
    private static int __millisecondsPerMinute = (getMillisecondsPerSecond() * getSecondsPerMinute());
    private static int __millisecondsPerSecond = 1000;
    private static int __minutesPerDay = (getMinutesPerHour() * getHoursPerDay());
    private static int __minutesPerHour = 60;
    private static int __nanosecondsPerTick = 100;
    private static int __secondsPerDay = (getSecondsPerHour() * getHoursPerDay());
    private static int __secondsPerHour = (getSecondsPerMinute() * getMinutesPerHour());
    private static int __secondsPerMinute = 60;
    private static long __ticksPerDay = (getTicksPerHour() * ((long) getHoursPerDay()));
    private static long __ticksPerHour = ((long) (getTicksPerMinute() * getMinutesPerHour()));
    private static int __ticksPerMillisecond = 10000;
    private static int __ticksPerMinute = (getTicksPerSecond() * getSecondsPerMinute());
    private static int __ticksPerSecond = (getTicksPerMillisecond() * getMillisecondsPerSecond());

    public static int getHoursPerDay() {
        return __hoursPerDay;
    }

    public static int getMillisecondsPerDay() {
        return __millisecondsPerDay;
    }

    public static int getMillisecondsPerHour() {
        return __millisecondsPerHour;
    }

    public static int getMillisecondsPerMinute() {
        return __millisecondsPerMinute;
    }

    public static int getMillisecondsPerSecond() {
        return __millisecondsPerSecond;
    }

    public static int getMinutesPerDay() {
        return __minutesPerDay;
    }

    public static int getMinutesPerHour() {
        return __minutesPerHour;
    }

    public static int getNanosecondsPerTick() {
        return __nanosecondsPerTick;
    }

    public static int getSecondsPerDay() {
        return __secondsPerDay;
    }

    public static int getSecondsPerHour() {
        return __secondsPerHour;
    }

    public static int getSecondsPerMinute() {
        return __secondsPerMinute;
    }

    public static long getTicksPerDay() {
        return __ticksPerDay;
    }

    public static long getTicksPerHour() {
        return __ticksPerHour;
    }

    public static int getTicksPerMillisecond() {
        return __ticksPerMillisecond;
    }

    public static int getTicksPerMinute() {
        return __ticksPerMinute;
    }

    public static int getTicksPerSecond() {
        return __ticksPerSecond;
    }
}
