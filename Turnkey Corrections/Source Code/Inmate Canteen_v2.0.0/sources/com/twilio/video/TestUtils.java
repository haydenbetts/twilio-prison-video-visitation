package com.twilio.video;

import android.content.Context;
import android.provider.Settings;

public class TestUtils {
    public static final long FIVE_SECONDS = 5000;
    public static final long FOUR_SECONDS = 4000;
    public static final long ICE_TIMEOUT = 30000;
    public static final long INVALID_REGION_TIMEOUT = 60;
    public static final long ONE_SECOND = 1000;
    public static final long SIP_TIMEOUT = 125;
    public static final long SMALL_WAIT = 5000;
    public static final long STATE_TRANSITION_TIMEOUT = 15;
    public static final long THREE_SECONDS = 3000;
    public static final long TWO_SECONDS = 2000;

    public static boolean isFTL(Context context) {
        return "true".equals(Settings.System.getString(context.getContentResolver(), "firebase.test.lab"));
    }

    public static void blockingWait(long j) throws InterruptedException {
        Thread.sleep(j);
    }
}
