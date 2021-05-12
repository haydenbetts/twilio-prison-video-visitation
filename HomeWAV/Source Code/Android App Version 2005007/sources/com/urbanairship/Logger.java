package com.urbanairship;

import com.urbanairship.util.UAStringUtil;
import java.util.Locale;

public class Logger {
    static String DEFAULT_TAG = "UALib";
    private static LoggingCore logger = new LoggingCore(6, "UALib");

    private Logger() {
    }

    public static void setTag(String str) {
        logger.setTag(str);
    }

    public static void setLogLevel(int i) {
        logger.setLogLevel(i);
    }

    public static int getLogLevel() {
        return logger.getLogLevel();
    }

    public static void disableDefaultLogger() {
        logger.setDefaultLoggerEnabled(false);
    }

    public static void addListener(LoggerListener loggerListener) {
        logger.addListener(loggerListener);
    }

    public static void removeListener(LoggerListener loggerListener) {
        logger.removeListener(loggerListener);
    }

    public static void warn(String str, Object... objArr) {
        logger.log(5, (Throwable) null, str, objArr);
    }

    public static void warn(Throwable th, String str, Object... objArr) {
        logger.log(5, th, str, objArr);
    }

    public static void warn(Throwable th) {
        logger.log(5, th, (String) null, (Object[]) null);
    }

    public static void verbose(Throwable th) {
        logger.log(2, th, (String) null, (Object[]) null);
    }

    public static void verbose(String str, Object... objArr) {
        logger.log(2, (Throwable) null, str, objArr);
    }

    public static void debug(String str, Object... objArr) {
        logger.log(3, (Throwable) null, str, objArr);
    }

    public static void debug(Throwable th, String str, Object... objArr) {
        logger.log(3, th, str, objArr);
    }

    public static void info(String str, Object... objArr) {
        logger.log(4, (Throwable) null, str, objArr);
    }

    public static void info(Throwable th, String str, Object... objArr) {
        logger.log(4, th, str, objArr);
    }

    public static void error(String str, Object... objArr) {
        logger.log(6, (Throwable) null, str, objArr);
    }

    public static void error(Throwable th) {
        logger.log(6, th, (String) null, (Object[]) null);
    }

    public static void error(Throwable th, String str, Object... objArr) {
        logger.log(6, th, str, objArr);
    }

    static int parseLogLevel(String str, int i) throws IllegalArgumentException {
        if (UAStringUtil.isEmpty(str)) {
            return i;
        }
        String upperCase = str.toUpperCase(Locale.ROOT);
        upperCase.hashCode();
        char c = 65535;
        switch (upperCase.hashCode()) {
            case 2251950:
                if (upperCase.equals("INFO")) {
                    c = 0;
                    break;
                }
                break;
            case 2402104:
                if (upperCase.equals("NONE")) {
                    c = 1;
                    break;
                }
                break;
            case 2656902:
                if (upperCase.equals("WARN")) {
                    c = 2;
                    break;
                }
                break;
            case 64921139:
                if (upperCase.equals("DEBUG")) {
                    c = 3;
                    break;
                }
                break;
            case 66247144:
                if (upperCase.equals("ERROR")) {
                    c = 4;
                    break;
                }
                break;
            case 1069090146:
                if (upperCase.equals("VERBOSE")) {
                    c = 5;
                    break;
                }
                break;
            case 1940088646:
                if (upperCase.equals("ASSERT")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 4;
            case 1:
            case 6:
                return 7;
            case 2:
                return 5;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 2;
            default:
                try {
                    int parseInt = Integer.parseInt(str);
                    if (parseInt <= 7 && parseInt >= 2) {
                        return parseInt;
                    }
                    warn("%s is not a valid log level. Falling back to %s.", Integer.valueOf(parseInt), Integer.valueOf(i));
                    return i;
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException("Invalid log level: " + str);
                }
        }
    }
}
