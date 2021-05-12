package com.urbanairship.analytics;

import android.content.pm.PackageInfo;
import android.os.Build;
import com.urbanairship.UAirship;
import com.urbanairship.json.JsonMap;

class AppForegroundEvent extends Event {
    static final String NOTIFICATION_TYPES_KEY = "notification_types";
    static final String TYPE = "app_foreground";

    public final String getType() {
        return TYPE;
    }

    AppForegroundEvent(long j) {
        super(j);
    }

    public final JsonMap getEventData() {
        PackageInfo packageInfo = UAirship.getPackageInfo();
        return JsonMap.newBuilder().put("connection_type", getConnectionType()).put("connection_subtype", getConnectionSubType()).put("carrier", getCarrier()).put("time_zone", getTimezone()).put("daylight_savings", isDaylightSavingsTime()).put("os_version", Build.VERSION.RELEASE).put("lib_version", UAirship.getVersion()).putOpt("package_version", packageInfo != null ? packageInfo.versionName : null).put("push_id", UAirship.shared().getAnalytics().getConversionSendId()).put(TtmlNode.TAG_METADATA, UAirship.shared().getAnalytics().getConversionMetadata()).put("last_metadata", UAirship.shared().getPushManager().getLastReceivedMetadata()).build();
    }
}
