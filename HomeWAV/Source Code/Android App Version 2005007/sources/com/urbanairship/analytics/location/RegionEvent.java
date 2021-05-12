package com.urbanairship.analytics.location;

import com.urbanairship.Logger;
import com.urbanairship.analytics.Event;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import com.urbanairship.util.UAStringUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class RegionEvent extends Event implements JsonSerializable {
    private static final String BOUNDARY_EVENT = "action";
    public static final int BOUNDARY_EVENT_ENTER = 1;
    public static final int BOUNDARY_EVENT_EXIT = 2;
    private static final String CIRCULAR_REGION = "circular_region";
    private static final String CIRCULAR_REGION_RADIUS = "radius";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    public static final int MAX_CHARACTER_LENGTH = 255;
    public static final double MAX_LATITUDE = 90.0d;
    public static final double MAX_LONGITUDE = 180.0d;
    public static final double MIN_LATITUDE = -90.0d;
    public static final double MIN_LONGITUDE = -180.0d;
    private static final String PROXIMITY_REGION = "proximity";
    private static final String PROXIMITY_REGION_ID = "proximity_id";
    private static final String PROXIMITY_REGION_MAJOR = "major";
    private static final String PROXIMITY_REGION_MINOR = "minor";
    private static final String PROXIMITY_REGION_RSSI = "rssi";
    public static final String REGION_ID = "region_id";
    private static final String SOURCE = "source";
    public static final String TYPE = "region_event";
    private final int boundaryEvent;
    private CircularRegion circularRegion;
    private ProximityRegion proximityRegion;
    private final String regionId;
    private final String source;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Boundary {
    }

    public int getPriority() {
        return 2;
    }

    public final String getType() {
        return TYPE;
    }

    private RegionEvent(Builder builder) {
        this.regionId = builder.regionId;
        this.source = builder.source;
        this.boundaryEvent = builder.boundaryEvent;
        this.circularRegion = builder.circularRegion;
        this.proximityRegion = builder.proximityRegion;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getBoundaryEvent() {
        return this.boundaryEvent;
    }

    public final JsonMap getEventData() {
        JsonMap.Builder put = JsonMap.newBuilder().put(REGION_ID, this.regionId).put("source", this.source).put(BOUNDARY_EVENT, this.boundaryEvent == 1 ? "enter" : "exit");
        ProximityRegion proximityRegion2 = this.proximityRegion;
        if (proximityRegion2 != null && proximityRegion2.isValid()) {
            JsonMap.Builder putOpt = JsonMap.newBuilder().put(PROXIMITY_REGION_ID, this.proximityRegion.getProximityId()).put(PROXIMITY_REGION_MAJOR, this.proximityRegion.getMajor()).put(PROXIMITY_REGION_MINOR, this.proximityRegion.getMinor()).putOpt(PROXIMITY_REGION_RSSI, this.proximityRegion.getRssi());
            if (this.proximityRegion.getLatitude() != null) {
                putOpt.put(LATITUDE, Double.toString(this.proximityRegion.getLatitude().doubleValue()));
            }
            if (this.proximityRegion.getLongitude() != null) {
                putOpt.put(LONGITUDE, Double.toString(this.proximityRegion.getLongitude().doubleValue()));
            }
            put.put(PROXIMITY_REGION, (JsonSerializable) putOpt.build());
        }
        CircularRegion circularRegion2 = this.circularRegion;
        if (circularRegion2 != null && circularRegion2.isValid()) {
            put.put(CIRCULAR_REGION, (JsonSerializable) JsonMap.newBuilder().put(CIRCULAR_REGION_RADIUS, String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.circularRegion.getRadius())})).put(LATITUDE, String.format(Locale.US, "%.7f", new Object[]{Double.valueOf(this.circularRegion.getLatitude())})).put(LONGITUDE, String.format(Locale.US, "%.7f", new Object[]{Double.valueOf(this.circularRegion.getLongitude())})).build());
        }
        return put.build();
    }

    public JsonValue toJsonValue() {
        return getEventData().toJsonValue();
    }

    static boolean regionEventCharacterCountIsValid(String str) {
        return str.length() <= 255 && str.length() > 0;
    }

    static boolean regionEventLatitudeIsValid(Double d) {
        return d.doubleValue() <= 90.0d && d.doubleValue() >= -90.0d;
    }

    static boolean regionEventLongitudeIsValid(Double d) {
        return d.doubleValue() <= 180.0d && d.doubleValue() >= -180.0d;
    }

    public boolean isValid() {
        String str = this.regionId;
        if (str == null || this.source == null) {
            Logger.error("The region ID and source must not be null.", new Object[0]);
            return false;
        } else if (!regionEventCharacterCountIsValid(str)) {
            Logger.error("The region ID must not be greater than %s or less than %s characters in length.", 255, 1);
            return false;
        } else if (!regionEventCharacterCountIsValid(this.source)) {
            Logger.error("The source must not be greater than %s or less than %s characters in length.", 255, 1);
            return false;
        } else {
            int i = this.boundaryEvent;
            if (i >= 1 && i <= 2) {
                return true;
            }
            Logger.error("The boundary event must either be an entrance (%s) or an exit (%s).", 1, 2);
            return false;
        }
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int boundaryEvent;
        /* access modifiers changed from: private */
        public CircularRegion circularRegion;
        /* access modifiers changed from: private */
        public ProximityRegion proximityRegion;
        /* access modifiers changed from: private */
        public String regionId;
        /* access modifiers changed from: private */
        public String source;

        private Builder() {
        }

        public Builder setRegionId(String str) {
            this.regionId = str;
            return this;
        }

        public Builder setSource(String str) {
            this.source = str;
            return this;
        }

        public Builder setBoundaryEvent(int i) {
            this.boundaryEvent = i;
            return this;
        }

        public Builder setCircularRegion(CircularRegion circularRegion2) {
            this.circularRegion = circularRegion2;
            return this;
        }

        public Builder setProximityRegion(ProximityRegion proximityRegion2) {
            this.proximityRegion = proximityRegion2;
            return this;
        }

        public RegionEvent build() {
            Checks.checkNotNull(this.regionId, "Region identifier must not be null");
            Checks.checkNotNull(this.source, "Region event source must not be null");
            Checks.checkArgument(!UAStringUtil.isEmpty(this.regionId), "Region identifier must be greater than 0 characters.");
            boolean z = false;
            Checks.checkArgument(this.regionId.length() <= 255, "Region identifier exceeds max identifier length: 255");
            Checks.checkArgument(!UAStringUtil.isEmpty(this.source), "Source must be greater than 0 characters.");
            if (this.source.length() <= 255) {
                z = true;
            }
            Checks.checkArgument(z, "Source exceeds max source length: 255");
            int i = this.boundaryEvent;
            if (i >= 1 && i <= 2) {
                return new RegionEvent(this);
            }
            throw new IllegalArgumentException("The boundary event must either be an entrance (1) or an exit (2).");
        }
    }
}
