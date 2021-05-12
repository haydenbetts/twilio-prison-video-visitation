package com.urbanairship;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface AirshipComponentGroups {
    public static final int ACTION_AUTOMATION = 4;
    public static final int ANALYTICS = 1;
    public static final int CHANNEL = 7;
    public static final int IN_APP = 3;
    public static final int LOCATION = 6;
    public static final int MESSAGE_CENTER = 2;
    public static final int NAMED_USER = 5;
    public static final int NONE = -1;
    public static final int PUSH = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Group {
    }
}
