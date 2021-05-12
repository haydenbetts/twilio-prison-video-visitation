package com.urbanairship.push;

import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.util.Calendar;
import java.util.Date;

class QuietTimeInterval implements JsonSerializable {
    private static final String END_HOUR_KEY = "end_hour";
    private static final String END_MIN_KEY = "end_min";
    private static final int NOT_SET_VAL = -1;
    private static final String START_HOUR_KEY = "start_hour";
    private static final String START_MIN_KEY = "start_min";
    private final int endHour;
    private final int endMin;
    private final int startHour;
    private final int startMin;

    private QuietTimeInterval(Builder builder) {
        this.startHour = builder.startHour;
        this.startMin = builder.startMin;
        this.endHour = builder.endHour;
        this.endMin = builder.endMin;
    }

    /* access modifiers changed from: package-private */
    public boolean isInQuietTime(Calendar calendar) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, this.startHour);
        instance.set(12, this.startMin);
        instance.set(13, 0);
        instance.set(14, 0);
        Calendar instance2 = Calendar.getInstance();
        instance2.set(11, this.endHour);
        instance2.set(12, this.endMin);
        instance2.set(13, 0);
        instance2.set(14, 0);
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        if (calendar2.compareTo(instance) == 0 || calendar2.compareTo(instance2) == 0) {
            return true;
        }
        if (instance2.compareTo(instance) == 0) {
            return false;
        }
        if (instance2.after(instance)) {
            if (!calendar2.after(instance) || !calendar2.before(instance2)) {
                return false;
            }
            return true;
        } else if (calendar2.before(instance2) || calendar2.after(instance)) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public Date[] getQuietTimeIntervalDateArray() {
        if (this.startHour == -1 || this.startMin == -1 || this.endHour == -1 || this.endMin == -1) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.set(11, this.startHour);
        instance.set(12, this.startMin);
        instance.set(13, 0);
        instance.set(14, 0);
        Calendar instance2 = Calendar.getInstance();
        instance2.set(11, this.endHour);
        instance2.set(12, this.endMin);
        instance2.set(13, 0);
        instance2.set(14, 0);
        return new Date[]{instance.getTime(), instance2.getTime()};
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put(START_HOUR_KEY, this.startHour).put(START_MIN_KEY, this.startMin).put(END_HOUR_KEY, this.endHour).put(END_MIN_KEY, this.endMin).build().toJsonValue();
    }

    public static QuietTimeInterval fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        if (!optMap.isEmpty()) {
            return new Builder().setStartHour(optMap.opt(START_HOUR_KEY).getInt(-1)).setStartMin(optMap.opt(START_MIN_KEY).getInt(-1)).setEndHour(optMap.opt(END_HOUR_KEY).getInt(-1)).setEndMin(optMap.opt(END_MIN_KEY).getInt(-1)).build();
        }
        throw new JsonException("Invalid quiet time interval: " + jsonValue);
    }

    public String toString() {
        return "QuietTimeInterval{startHour=" + this.startHour + ", startMin=" + this.startMin + ", endHour=" + this.endHour + ", endMin=" + this.endMin + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QuietTimeInterval quietTimeInterval = (QuietTimeInterval) obj;
        if (this.startHour == quietTimeInterval.startHour && this.startMin == quietTimeInterval.startMin && this.endHour == quietTimeInterval.endHour && this.endMin == quietTimeInterval.endMin) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.startHour * 31) + this.startMin) * 31) + this.endHour) * 31) + this.endMin;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int endHour = -1;
        /* access modifiers changed from: private */
        public int endMin = -1;
        /* access modifiers changed from: private */
        public int startHour = -1;
        /* access modifiers changed from: private */
        public int startMin = -1;

        public Builder setQuietTimeInterval(Date date, Date date2) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            this.startHour = instance.get(11);
            this.startMin = instance.get(12);
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(date2);
            this.endHour = instance2.get(11);
            this.endMin = instance2.get(12);
            return this;
        }

        public Builder setStartHour(int i) {
            this.startHour = i;
            return this;
        }

        public Builder setStartMin(int i) {
            this.startMin = i;
            return this;
        }

        public Builder setEndHour(int i) {
            this.endHour = i;
            return this;
        }

        public Builder setEndMin(int i) {
            this.endMin = i;
            return this;
        }

        public QuietTimeInterval build() {
            return new QuietTimeInterval(this);
        }
    }
}
