package com.urbanairship.analytics;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.stripe.android.view.ShippingInfoWidget;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public abstract class Event {
    static final String CARRIER_KEY = "carrier";
    static final String CONNECTION_SUBTYPE_KEY = "connection_subtype";
    static final String CONNECTION_TYPE_KEY = "connection_type";
    static final String DATA_KEY = "data";
    static final String DAYLIGHT_SAVINGS_KEY = "daylight_savings";
    static final String EVENT_ID_KEY = "event_id";
    public static final int HIGH_PRIORITY = 2;
    static final String LAST_METADATA_KEY = "last_metadata";
    static final String LIB_VERSION_KEY = "lib_version";
    public static final int LOW_PRIORITY = 0;
    static final String METADATA_KEY = "metadata";
    public static final int NORMAL_PRIORITY = 1;
    static final String OS_VERSION_KEY = "os_version";
    static final String PACKAGE_VERSION_KEY = "package_version";
    static final String PUSH_ID_KEY = "push_id";
    static final String SESSION_ID_KEY = "session_id";
    static final String TIME_KEY = "time";
    static final String TIME_ZONE_KEY = "time_zone";
    static final String TYPE_KEY = "type";
    private final String eventId;
    private final String time;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
    }

    public abstract JsonMap getEventData();

    public int getPriority() {
        return 1;
    }

    public abstract String getType();

    public boolean isValid() {
        return true;
    }

    public Event(long j) {
        this.eventId = UUID.randomUUID().toString();
        this.time = millisecondsToSecondsString(j);
    }

    public Event() {
        this(System.currentTimeMillis());
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getTime() {
        return this.time;
    }

    public String createEventPayload(String str) {
        JsonMap.Builder newBuilder = JsonMap.newBuilder();
        newBuilder.put("type", getType()).put("event_id", this.eventId).put("time", this.time).put("data", (JsonSerializable) JsonMap.newBuilder().putAll(getEventData()).put("session_id", str).build());
        return newBuilder.build().toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000e, code lost:
        r0 = r0.getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getConnectionType() {
        /*
            r2 = this;
            android.content.Context r0 = com.urbanairship.UAirship.getApplicationContext()
            java.lang.String r1 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            if (r0 == 0) goto L_0x0019
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            if (r0 == 0) goto L_0x0019
            int r0 = r0.getType()
            goto L_0x001a
        L_0x0019:
            r0 = -1
        L_0x001a:
            if (r0 == 0) goto L_0x002d
            r1 = 1
            if (r0 == r1) goto L_0x0029
            r1 = 6
            if (r0 == r1) goto L_0x0025
            java.lang.String r0 = "none"
            goto L_0x002f
        L_0x0025:
            java.lang.String r0 = "wimax"
            goto L_0x002f
        L_0x0029:
            java.lang.String r0 = "wifi"
            goto L_0x002f
        L_0x002d:
            java.lang.String r0 = "cell"
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.analytics.Event.getConnectionType():java.lang.String");
    }

    public String getConnectionSubType() {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) UAirship.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return "";
            }
            return activeNetworkInfo.getSubtypeName();
        } catch (ClassCastException e) {
            Logger.error("Connection subtype lookup failed", e);
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public String getCarrier() {
        TelephonyManager telephonyManager = (TelephonyManager) UAirship.getApplicationContext().getSystemService(ShippingInfoWidget.PHONE_FIELD);
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    /* access modifiers changed from: protected */
    public long getTimezone() {
        return (long) (Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis()) / 1000);
    }

    /* access modifiers changed from: protected */
    public boolean isDaylightSavingsTime() {
        return Calendar.getInstance().getTimeZone().inDaylightTime(new Date());
    }

    public static String millisecondsToSecondsString(long j) {
        return String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(((double) j) / 1000.0d)});
    }
}
