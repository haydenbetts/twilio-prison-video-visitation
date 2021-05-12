package com.urbanairship.automation;

import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class Trigger implements Parcelable, JsonSerializable {
    public static final int ACTIVE_SESSION = 9;
    private static final String ACTIVE_SESSION_NAME = "active_session";
    private static final String APP_INIT_NAME = "app_init";
    private static final String BACKGROUND_NAME = "background";
    public static final Parcelable.Creator<Trigger> CREATOR = new Parcelable.Creator<Trigger>() {
        public Trigger createFromParcel(Parcel parcel) {
            return new Trigger(parcel);
        }

        public Trigger[] newArray(int i) {
            return new Trigger[i];
        }
    };
    public static final int CUSTOM_EVENT_COUNT = 5;
    private static final String CUSTOM_EVENT_COUNT_NAME = "custom_event_count";
    public static final int CUSTOM_EVENT_VALUE = 6;
    private static final String CUSTOM_EVENT_VALUE_NAME = "custom_event_value";
    private static final String FOREGROUND_NAME = "foreground";
    private static final String GOAL_KEY = "goal";
    public static final int LIFE_CYCLE_APP_INIT = 8;
    public static final int LIFE_CYCLE_BACKGROUND = 2;
    public static final int LIFE_CYCLE_FOREGROUND = 1;
    private static final String PREDICATE_KEY = "predicate";
    public static final int REGION_ENTER = 3;
    private static final String REGION_ENTER_NAME = "region_enter";
    public static final int REGION_EXIT = 4;
    private static final String REGION_EXIT_NAME = "region_exit";
    private static final String SCREEN_NAME = "screen";
    public static final int SCREEN_VIEW = 7;
    private static final String TYPE_KEY = "type";
    public static final int VERSION = 10;
    private static final String VERSION_NAME = "version";
    private final double goal;
    private final JsonPredicate predicate;
    private final int type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface TriggerType {
    }

    public int describeContents() {
        return 0;
    }

    public Trigger(int i, double d, JsonPredicate jsonPredicate) {
        this.type = i;
        this.goal = d;
        this.predicate = jsonPredicate;
    }

    public Trigger(Parcel parcel) {
        int i;
        JsonPredicate jsonPredicate;
        switch (parcel.readInt()) {
            case 1:
                i = 1;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 3;
                break;
            case 4:
                i = 4;
                break;
            case 5:
                i = 5;
                break;
            case 6:
                i = 6;
                break;
            case 7:
                i = 7;
                break;
            case 8:
                i = 8;
                break;
            case 9:
                i = 9;
                break;
            case 10:
                i = 10;
                break;
            default:
                throw new IllegalStateException("Invalid trigger type from parcel.");
        }
        double readDouble = parcel.readDouble();
        JsonValue jsonValue = (JsonValue) parcel.readParcelable(JsonValue.class.getClassLoader());
        if (jsonValue != null) {
            try {
                jsonPredicate = JsonPredicate.parse(jsonValue);
            } catch (JsonException e) {
                throw new IllegalStateException("Invalid trigger predicate from parcel.", e);
            }
        } else {
            jsonPredicate = null;
        }
        this.type = i;
        this.goal = readDouble;
        this.predicate = jsonPredicate;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeDouble(this.goal);
        JsonPredicate jsonPredicate = this.predicate;
        parcel.writeParcelable(jsonPredicate == null ? null : jsonPredicate.toJsonValue(), i);
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put("type", convertType(this.type)).put(GOAL_KEY, this.goal).put(PREDICATE_KEY, (JsonSerializable) this.predicate).build().toJsonValue();
    }

    public int getType() {
        return this.type;
    }

    public double getGoal() {
        return this.goal;
    }

    public JsonPredicate getPredicate() {
        return this.predicate;
    }

    public static Trigger fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        JsonPredicate parse = optMap.containsKey(PREDICATE_KEY) ? JsonPredicate.parse(optMap.opt(PREDICATE_KEY)) : null;
        double d = optMap.opt(GOAL_KEY).getDouble(-1.0d);
        if (d > 0.0d) {
            String lowerCase = optMap.opt("type").optString().toLowerCase(Locale.ROOT);
            try {
                return new Trigger(convertType(lowerCase), d, parse);
            } catch (IllegalArgumentException unused) {
                throw new JsonException("Invalid trigger type: " + lowerCase);
            }
        } else {
            throw new JsonException("Trigger goal must be defined and greater than 0.");
        }
    }

    private static int convertType(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1566014583:
                if (str.equals(REGION_EXIT_NAME)) {
                    c = 0;
                    break;
                }
                break;
            case -1332194002:
                if (str.equals(BACKGROUND_NAME)) {
                    c = 1;
                    break;
                }
                break;
            case -1302099507:
                if (str.equals(REGION_ENTER_NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -907689876:
                if (str.equals("screen")) {
                    c = 3;
                    break;
                }
                break;
            case 351608024:
                if (str.equals("version")) {
                    c = 4;
                    break;
                }
                break;
            case 1167511662:
                if (str.equals(APP_INIT_NAME)) {
                    c = 5;
                    break;
                }
                break;
            case 1607242588:
                if (str.equals(CUSTOM_EVENT_COUNT_NAME)) {
                    c = 6;
                    break;
                }
                break;
            case 1624363966:
                if (str.equals(CUSTOM_EVENT_VALUE_NAME)) {
                    c = 7;
                    break;
                }
                break;
            case 1984457027:
                if (str.equals(FOREGROUND_NAME)) {
                    c = 8;
                    break;
                }
                break;
            case 2075869789:
                if (str.equals(ACTIVE_SESSION_NAME)) {
                    c = 9;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 4;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 7;
            case 4:
                return 10;
            case 5:
                return 8;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 1;
            case 9:
                return 9;
            default:
                throw new IllegalArgumentException("Invalid trigger type: " + str);
        }
    }

    public String getTriggerName() {
        return convertType(this.type);
    }

    private static String convertType(int i) {
        switch (i) {
            case 1:
                return FOREGROUND_NAME;
            case 2:
                return BACKGROUND_NAME;
            case 3:
                return REGION_ENTER_NAME;
            case 4:
                return REGION_EXIT_NAME;
            case 5:
                return CUSTOM_EVENT_COUNT_NAME;
            case 6:
                return CUSTOM_EVENT_VALUE_NAME;
            case 7:
                return "screen";
            case 8:
                return APP_INIT_NAME;
            case 9:
                return ACTIVE_SESSION_NAME;
            case 10:
                return "version";
            default:
                throw new IllegalArgumentException("Invalid trigger type: " + i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Trigger trigger = (Trigger) obj;
        if (this.type != trigger.type || Double.compare(trigger.goal, this.goal) != 0) {
            return false;
        }
        JsonPredicate jsonPredicate = this.predicate;
        JsonPredicate jsonPredicate2 = trigger.predicate;
        if (jsonPredicate != null) {
            return jsonPredicate.equals(jsonPredicate2);
        }
        if (jsonPredicate2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.type;
        long doubleToLongBits = Double.doubleToLongBits(this.goal);
        int i2 = ((i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        JsonPredicate jsonPredicate = this.predicate;
        return i2 + (jsonPredicate != null ? jsonPredicate.hashCode() : 0);
    }

    public String toString() {
        return "Trigger{type=" + convertType(this.type) + ", goal=" + this.goal + ", predicate=" + this.predicate + '}';
    }
}
