package com.urbanairship.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LocationRequestOptions implements JsonSerializable, Parcelable {
    public static final Parcelable.Creator<LocationRequestOptions> CREATOR = new Parcelable.Creator<LocationRequestOptions>() {
        public LocationRequestOptions createFromParcel(Parcel parcel) {
            return new LocationRequestOptions(parcel);
        }

        public LocationRequestOptions[] newArray(int i) {
            return new LocationRequestOptions[i];
        }
    };
    public static final int DEFAULT_REQUEST_PRIORITY = 2;
    public static final float DEFAULT_UPDATE_INTERVAL_METERS = 800.0f;
    public static final long DEFAULT_UPDATE_INTERVAL_MILLISECONDS = 300000;
    public static final String MIN_DISTANCE_KEY = "minDistance";
    public static final String MIN_TIME_KEY = "minTime";
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 2;
    public static final int PRIORITY_HIGH_ACCURACY = 1;
    public static final String PRIORITY_KEY = "priority";
    public static final int PRIORITY_LOW_POWER = 3;
    public static final int PRIORITY_NO_POWER = 4;
    private final float minDistance;
    private final long minTime;
    private final int priority;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
    }

    public int describeContents() {
        return 0;
    }

    private LocationRequestOptions(Builder builder) {
        this(builder.priority, builder.minTime, builder.minDistance);
    }

    private LocationRequestOptions(Parcel parcel) {
        this(parcel.readInt(), parcel.readLong(), parcel.readFloat());
    }

    private LocationRequestOptions(int i, long j, float f) {
        this.priority = i;
        this.minTime = j;
        this.minDistance = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.priority);
        parcel.writeLong(this.minTime);
        parcel.writeFloat(this.minDistance);
    }

    public static LocationRequestOptions createDefaultOptions() {
        return new LocationRequestOptions(2, 300000, 800.0f);
    }

    public int getPriority() {
        return this.priority;
    }

    public long getMinTime() {
        return this.minTime;
    }

    public float getMinDistance() {
        return this.minDistance;
    }

    public String toString() {
        return "LocationRequestOptions: Priority " + this.priority + " minTime " + this.minTime + " minDistance " + this.minDistance;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationRequestOptions)) {
            return false;
        }
        LocationRequestOptions locationRequestOptions = (LocationRequestOptions) obj;
        if (locationRequestOptions.priority == this.priority && locationRequestOptions.minTime == this.minTime && locationRequestOptions.minDistance == this.minDistance) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static void verifyMinTime(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("minTime must be greater or equal to 0");
        }
    }

    /* access modifiers changed from: private */
    public static void verifyMinDistance(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("minDistance must be greater or equal to 0");
        }
    }

    /* access modifiers changed from: private */
    public static void verifyPriority(int i) {
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            throw new IllegalArgumentException("Priority can only be either PRIORITY_HIGH_ACCURACY, PRIORITY_BALANCED_POWER_ACCURACY, PRIORITY_LOW_POWER, or PRIORITY_NO_POWER");
        }
    }

    public JsonValue toJsonValue() {
        HashMap hashMap = new HashMap();
        hashMap.put("priority", Integer.valueOf(getPriority()));
        hashMap.put(MIN_DISTANCE_KEY, Float.valueOf(getMinDistance()));
        hashMap.put(MIN_TIME_KEY, Long.valueOf(getMinTime()));
        try {
            return JsonValue.wrap((Object) hashMap);
        } catch (JsonException e) {
            Logger.error(e, "LocationRequestOptions - Unable to serialize to JSON.", new Object[0]);
            return JsonValue.NULL;
        }
    }

    public static LocationRequestOptions fromJson(JsonValue jsonValue) throws JsonException {
        float f;
        JsonMap map = jsonValue.getMap();
        if (map != null) {
            Number number = map.opt(MIN_DISTANCE_KEY).getNumber();
            if (number == null) {
                f = 800.0f;
            } else {
                f = number.floatValue();
            }
            long j = map.opt(MIN_TIME_KEY).getLong(300000);
            int i = map.opt("priority").getInt(2);
            try {
                verifyPriority(i);
                verifyMinDistance(f);
                verifyMinTime(j);
                return new LocationRequestOptions(i, j, f);
            } catch (IllegalArgumentException e) {
                throw new JsonException("Invalid value.", e);
            }
        } else {
            throw new JsonException("Invalid location request options: " + jsonValue);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public float minDistance = 800.0f;
        /* access modifiers changed from: private */
        public long minTime = 300000;
        /* access modifiers changed from: private */
        public int priority = 2;

        public Builder setMinTime(long j, TimeUnit timeUnit) {
            LocationRequestOptions.verifyMinTime(timeUnit.toMillis(j));
            this.minTime = timeUnit.toMillis(j);
            return this;
        }

        public Builder setMinDistance(float f) {
            LocationRequestOptions.verifyMinDistance(f);
            this.minDistance = f;
            return this;
        }

        public Builder setPriority(int i) {
            LocationRequestOptions.verifyPriority(i);
            this.priority = i;
            return this;
        }

        public LocationRequestOptions build() {
            return new LocationRequestOptions(this);
        }
    }
}
