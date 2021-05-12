package fm.liveswitch;

import java.util.Date;

public class Build {
    public static final String VersionConstant = "1.10.0.33260";

    public static String getVersion() {
        return VersionConstant;
    }

    public static Date getDate() {
        if (getYear() == 0 && getMonth() == 0 && getDay() == 0) {
            return DateExtensions.createDate(1970, 1, 1, 0, 0, 0);
        }
        return DateExtensions.createDate(getYear(), getMonth(), getDay(), 0, 0, 0);
    }

    public static int getDay() {
        return ParseAssistant.parseIntegerValue(StringExtensions.substring("2020-09-12", 8, 2));
    }

    public static int getMajorVersion() {
        return ParseAssistant.parseIntegerValue(StringExtensions.split(VersionConstant, new char[]{'.'})[0]);
    }

    public static int getMinorVersion() {
        return ParseAssistant.parseIntegerValue(StringExtensions.split(VersionConstant, new char[]{'.'})[1]);
    }

    public static int getMonth() {
        return ParseAssistant.parseIntegerValue(StringExtensions.substring("2020-09-12", 5, 2));
    }

    public static int getPatchVersion() {
        return ParseAssistant.parseIntegerValue(StringExtensions.split(VersionConstant, new char[]{'.'})[2]);
    }

    public static int getRevisionVersion() {
        return ParseAssistant.parseIntegerValue(StringExtensions.split(VersionConstant, new char[]{'.'})[3]);
    }

    public static int getYear() {
        return ParseAssistant.parseIntegerValue(StringExtensions.substring("2020-09-12", 0, 4));
    }
}
