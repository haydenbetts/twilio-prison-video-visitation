package com.urbanairship.analytics.location;

import android.location.Location;
import com.urbanairship.analytics.Event;
import com.urbanairship.json.JsonMap;
import com.urbanairship.util.UAStringUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class LocationEvent extends Event {
    static final String FOREGROUND_KEY = "foreground";
    static final String H_ACCURACY_KEY = "h_accuracy";
    static final String LATITUDE_KEY = "lat";
    static final String LONGITUDE_KEY = "long";
    static final String PROVIDER_KEY = "provider";
    static final String REQUESTED_ACCURACY_KEY = "requested_accuracy";
    static final String TYPE = "location";
    static final String UPDATE_DISTANCE_KEY = "update_dist";
    public static final int UPDATE_TYPE_CONTINUOUS = 0;
    static final String UPDATE_TYPE_KEY = "update_type";
    public static final int UPDATE_TYPE_SINGLE = 1;
    static final String V_ACCURACY_KEY = "v_accuracy";
    private final String accuracy;
    private final String foreground;
    private final String latitude;
    private final String longitude;
    private final String provider;
    private final String requestedAccuracy;
    private final String updateDistance;
    private final int updateType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface UpdateType {
    }

    public int getPriority() {
        return 0;
    }

    public String getType() {
        return "location";
    }

    public LocationEvent(Location location, int i, int i2, int i3, boolean z) {
        this.latitude = String.format(Locale.US, "%.6f", new Object[]{Double.valueOf(location.getLatitude())});
        this.longitude = String.format(Locale.US, "%.6f", new Object[]{Double.valueOf(location.getLongitude())});
        this.provider = UAStringUtil.isEmpty(location.getProvider()) ? "UNKNOWN" : location.getProvider();
        this.accuracy = String.valueOf(location.getAccuracy());
        String str = "NONE";
        this.requestedAccuracy = i2 >= 0 ? String.valueOf(i2) : str;
        this.updateDistance = i3 >= 0 ? String.valueOf(i3) : str;
        this.foreground = z ? "true" : "false";
        this.updateType = i;
    }

    public final JsonMap getEventData() {
        return JsonMap.newBuilder().put(LATITUDE_KEY, this.latitude).put("long", this.longitude).put(REQUESTED_ACCURACY_KEY, this.requestedAccuracy).put(UPDATE_TYPE_KEY, this.updateType == 0 ? "CONTINUOUS" : "SINGLE").put(PROVIDER_KEY, this.provider).put(H_ACCURACY_KEY, this.accuracy).put(V_ACCURACY_KEY, "NONE").put(FOREGROUND_KEY, this.foreground).put(UPDATE_DISTANCE_KEY, this.updateDistance).build();
    }
}
