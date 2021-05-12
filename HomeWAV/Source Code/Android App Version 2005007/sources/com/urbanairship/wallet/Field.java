package com.urbanairship.wallet;

import android.text.TextUtils;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;

public class Field implements JsonSerializable {
    private static final String LABEL_KEY = "label";
    private static final String VALUE_KEY = "value";
    private final String label;
    private final String name;
    private final Object value;

    Field(Builder builder) {
        this.name = builder.name;
        this.label = builder.label;
        this.value = builder.value;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    public String toString() {
        return toJsonValue().toString();
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt("label", this.label).putOpt("value", this.value).build().toJsonValue();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String label;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public Object value;

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder setValue(String str) {
            this.value = str;
            return this;
        }

        public Builder setValue(int i) {
            this.value = Integer.valueOf(i);
            return this;
        }

        public Field build() {
            if (!TextUtils.isEmpty(this.name) && (this.value != null || !TextUtils.isEmpty(this.label))) {
                return new Field(this);
            }
            throw new IllegalStateException("The field must have a name and either a value or label.");
        }
    }
}
