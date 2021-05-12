package com.urbanairship.automation;

import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ScheduleDelay implements Parcelable, JsonSerializable {
    public static final int APP_STATE_ANY = 1;
    private static final String APP_STATE_ANY_NAME = "any";
    public static final int APP_STATE_BACKGROUND = 3;
    private static final String APP_STATE_BACKGROUND_NAME = "background";
    public static final int APP_STATE_FOREGROUND = 2;
    private static final String APP_STATE_FOREGROUND_NAME = "foreground";
    private static final String APP_STATE_KEY = "app_state";
    private static final String CANCELLATION_TRIGGERS_KEY = "cancellation_triggers";
    public static final Parcelable.Creator<ScheduleDelay> CREATOR = new Parcelable.Creator<ScheduleDelay>() {
        public ScheduleDelay createFromParcel(Parcel parcel) {
            return new ScheduleDelay(parcel);
        }

        public ScheduleDelay[] newArray(int i) {
            return new ScheduleDelay[i];
        }
    };
    private static final String REGION_ID_KEY = "region_id";
    private static final String SCREEN_KEY = "screen";
    private static final String SECONDS_KEY = "seconds";
    private final int appState;
    private final List<Trigger> cancellationTriggers;
    private final String regionId;
    private final List<String> screens;
    private final long seconds;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AppState {
    }

    public int describeContents() {
        return 0;
    }

    ScheduleDelay(Builder builder) {
        this.seconds = builder.seconds;
        this.screens = builder.screens == null ? Collections.emptyList() : new ArrayList<>(builder.screens);
        this.appState = builder.appState;
        this.regionId = builder.regionId;
        this.cancellationTriggers = builder.cancellationTriggers;
    }

    protected ScheduleDelay(Parcel parcel) {
        this.seconds = parcel.readLong();
        ArrayList arrayList = new ArrayList();
        this.screens = arrayList;
        parcel.readList(arrayList, String.class.getClassLoader());
        int readInt = parcel.readInt();
        int i = 3;
        if (readInt == 1) {
            i = 1;
        } else if (readInt == 2) {
            i = 2;
        } else if (readInt != 3) {
            throw new IllegalStateException("Invalid app state from parcel.");
        }
        this.appState = i;
        this.regionId = parcel.readString();
        this.cancellationTriggers = parcel.createTypedArrayList(Trigger.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.seconds);
        parcel.writeList(this.screens);
        parcel.writeInt(this.appState);
        parcel.writeString(this.regionId);
        parcel.writeTypedList(this.cancellationTriggers);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long getSeconds() {
        return this.seconds;
    }

    public List<String> getScreens() {
        return this.screens;
    }

    public int getAppState() {
        return this.appState;
    }

    public String getRegionId() {
        return this.regionId;
    }

    public List<Trigger> getCancellationTriggers() {
        return this.cancellationTriggers;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScheduleDelay scheduleDelay = (ScheduleDelay) obj;
        if (this.seconds != scheduleDelay.seconds || this.appState != scheduleDelay.appState) {
            return false;
        }
        List<String> list = this.screens;
        if (list == null ? scheduleDelay.screens != null : !list.equals(scheduleDelay.screens)) {
            return false;
        }
        String str = this.regionId;
        if (str == null ? scheduleDelay.regionId == null : str.equals(scheduleDelay.regionId)) {
            return this.cancellationTriggers.equals(scheduleDelay.cancellationTriggers);
        }
        return false;
    }

    public int hashCode() {
        long j = this.seconds;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        List<String> list = this.screens;
        int i2 = 0;
        int hashCode = (((i + (list != null ? list.hashCode() : 0)) * 31) + this.appState) * 31;
        String str = this.regionId;
        if (str != null) {
            i2 = str.hashCode();
        }
        return ((hashCode + i2) * 31) + this.cancellationTriggers.hashCode();
    }

    public static ScheduleDelay fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        Builder seconds2 = newBuilder().setSeconds(optMap.opt(SECONDS_KEY).getLong(0));
        String lowerCase = optMap.opt(APP_STATE_KEY).getString(APP_STATE_ANY_NAME).toLowerCase(Locale.ROOT);
        lowerCase.hashCode();
        int i = 2;
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case -1332194002:
                if (lowerCase.equals(APP_STATE_BACKGROUND_NAME)) {
                    c = 0;
                    break;
                }
                break;
            case 96748:
                if (lowerCase.equals(APP_STATE_ANY_NAME)) {
                    c = 1;
                    break;
                }
                break;
            case 1984457027:
                if (lowerCase.equals(APP_STATE_FOREGROUND_NAME)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                i = 3;
                break;
            case 1:
                i = 1;
                break;
            case 2:
                break;
            default:
                throw new JsonException("Invalid app state: " + lowerCase);
        }
        seconds2.setAppState(i);
        if (optMap.containsKey("screen")) {
            JsonValue opt = optMap.opt("screen");
            if (opt.isString()) {
                seconds2.setScreen(opt.optString());
            } else {
                seconds2.setScreens(opt.optList());
            }
        }
        if (optMap.containsKey("region_id")) {
            seconds2.setRegionId(optMap.opt("region_id").optString());
        }
        Iterator<JsonValue> it = optMap.opt(CANCELLATION_TRIGGERS_KEY).optList().iterator();
        while (it.hasNext()) {
            seconds2.addCancellationTrigger(Trigger.fromJson(it.next()));
        }
        try {
            return seconds2.build();
        } catch (IllegalArgumentException e) {
            throw new JsonException("Invalid schedule delay info", e);
        }
    }

    public JsonValue toJsonValue() {
        int appState2 = getAppState();
        return JsonMap.newBuilder().put(SECONDS_KEY, getSeconds()).put(APP_STATE_KEY, appState2 != 1 ? appState2 != 2 ? appState2 != 3 ? null : APP_STATE_BACKGROUND_NAME : APP_STATE_FOREGROUND_NAME : APP_STATE_ANY_NAME).put("screen", (JsonSerializable) JsonValue.wrapOpt(getScreens())).put("region_id", getRegionId()).put(CANCELLATION_TRIGGERS_KEY, (JsonSerializable) JsonValue.wrapOpt(getCancellationTriggers())).build().toJsonValue();
    }

    public String toString() {
        return "ScheduleDelay{seconds=" + this.seconds + ", screens=" + this.screens + ", appState=" + this.appState + ", regionId='" + this.regionId + '\'' + ", cancellationTriggers=" + this.cancellationTriggers + '}';
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int appState = 1;
        /* access modifiers changed from: private */
        public final List<Trigger> cancellationTriggers = new ArrayList();
        /* access modifiers changed from: private */
        public String regionId = null;
        /* access modifiers changed from: private */
        public List<String> screens;
        /* access modifiers changed from: private */
        public long seconds;

        public Builder setSeconds(long j) {
            this.seconds = j;
            return this;
        }

        public Builder setScreen(String str) {
            this.screens = Collections.singletonList(str);
            return this;
        }

        public Builder setScreens(List<String> list) {
            this.screens = list;
            return this;
        }

        public Builder setScreens(JsonList jsonList) {
            this.screens = new ArrayList();
            Iterator<JsonValue> it = jsonList.iterator();
            while (it.hasNext()) {
                JsonValue next = it.next();
                if (next.getString() != null) {
                    this.screens.add(next.getString());
                }
            }
            return this;
        }

        public Builder setAppState(int i) {
            this.appState = i;
            return this;
        }

        public Builder setRegionId(String str) {
            this.regionId = str;
            return this;
        }

        public Builder addCancellationTrigger(Trigger trigger) {
            this.cancellationTriggers.add(trigger);
            return this;
        }

        public ScheduleDelay build() {
            if (((long) this.cancellationTriggers.size()) <= 10) {
                return new ScheduleDelay(this);
            }
            throw new IllegalArgumentException("No more than 10 cancellation triggers allowed.");
        }
    }
}
