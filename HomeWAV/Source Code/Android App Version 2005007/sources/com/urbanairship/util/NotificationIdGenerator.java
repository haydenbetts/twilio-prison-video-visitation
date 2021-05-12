package com.urbanairship.util;

import android.content.SharedPreferences;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;

public class NotificationIdGenerator {
    private static final int MAX_RANGE = 50;
    private static final String NEXT_ID_KEY = "count";
    private static final String SHARED_PREFERENCES_FILE = "com.urbanairship.notificationidgenerator";
    private static int range = 40;
    private static int start = 1000;

    private static SharedPreferences getPreferences() {
        return UAirship.getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }

    private static void putInt(String str, int i) {
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putInt(str, i);
        edit.apply();
    }

    private static int getInt(String str, int i) {
        return getPreferences().getInt(str, i);
    }

    public static void setStart(int i) {
        putInt(NEXT_ID_KEY, i);
        start = i;
    }

    public static void setRange(int i) {
        if (i > 50) {
            Logger.error("The maximum number of notifications allowed is %s. Limiting alert id range to conform.", 50);
            i = 50;
        }
        putInt(NEXT_ID_KEY, start);
        range = i;
    }

    public static int getStart() {
        return start;
    }

    public static int getRange() {
        return range;
    }

    public static int nextID() {
        int i = getInt(NEXT_ID_KEY, start) + 1;
        if (i < start + range) {
            Logger.verbose("NotificationIdGenerator - Incrementing notification ID count", new Object[0]);
            putInt(NEXT_ID_KEY, i);
        } else {
            Logger.verbose("NotificationIdGenerator - Resetting notification ID count", new Object[0]);
            putInt(NEXT_ID_KEY, start);
        }
        Logger.verbose("NotificationIdGenerator - Notification ID: %s", Integer.valueOf(i));
        return i;
    }
}
