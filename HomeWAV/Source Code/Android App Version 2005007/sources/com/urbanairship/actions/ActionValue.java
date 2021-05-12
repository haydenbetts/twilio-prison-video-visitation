package com.urbanairship.actions;

import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;

public class ActionValue implements JsonSerializable, Parcelable {
    public static final Parcelable.Creator<ActionValue> CREATOR = new Parcelable.Creator<ActionValue>() {
        public ActionValue createFromParcel(Parcel parcel) {
            return new ActionValue((JsonValue) parcel.readParcelable(JsonValue.class.getClassLoader()));
        }

        public ActionValue[] newArray(int i) {
            return new ActionValue[i];
        }
    };
    private final JsonValue jsonValue;

    public int describeContents() {
        return 0;
    }

    public ActionValue(JsonValue jsonValue2) {
        this.jsonValue = jsonValue2 == null ? JsonValue.NULL : jsonValue2;
    }

    public static ActionValue wrap(String str) {
        return new ActionValue(JsonValue.wrap(str));
    }

    public static ActionValue wrap(int i) {
        return new ActionValue(JsonValue.wrap(i));
    }

    public static ActionValue wrap(long j) {
        return new ActionValue(JsonValue.wrap(j));
    }

    public static ActionValue wrap(char c) {
        return new ActionValue(JsonValue.wrap(c));
    }

    public static ActionValue wrap(boolean z) {
        return new ActionValue(JsonValue.wrap(z));
    }

    public static ActionValue wrap(JsonSerializable jsonSerializable) {
        return new ActionValue(JsonValue.wrap(jsonSerializable));
    }

    public static ActionValue wrap(Object obj) throws ActionValueException {
        try {
            return new ActionValue(JsonValue.wrap(obj));
        } catch (JsonException e) {
            throw new ActionValueException("Invalid ActionValue object: " + obj, e);
        }
    }

    public ActionValue() {
        this.jsonValue = JsonValue.NULL;
    }

    public String getString() {
        return this.jsonValue.getString();
    }

    public String getString(String str) {
        return this.jsonValue.getString(str);
    }

    public int getInt(int i) {
        return this.jsonValue.getInt(i);
    }

    public double getDouble(double d) {
        return this.jsonValue.getDouble(d);
    }

    public long getLong(long j) {
        return this.jsonValue.getLong(j);
    }

    public boolean getBoolean(boolean z) {
        return this.jsonValue.getBoolean(z);
    }

    public JsonList getList() {
        return this.jsonValue.getList();
    }

    public JsonMap getMap() {
        return this.jsonValue.getMap();
    }

    public boolean isNull() {
        return this.jsonValue.isNull();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ActionValue) {
            return this.jsonValue.equals(((ActionValue) obj).jsonValue);
        }
        return false;
    }

    public int hashCode() {
        return this.jsonValue.hashCode();
    }

    public String toString() {
        return this.jsonValue.toString();
    }

    public JsonValue toJsonValue() {
        return this.jsonValue;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.jsonValue, i);
    }
}
