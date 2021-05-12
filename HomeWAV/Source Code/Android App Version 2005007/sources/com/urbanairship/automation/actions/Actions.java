package com.urbanairship.automation.actions;

import com.urbanairship.automation.ScheduleData;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.util.HashMap;
import java.util.Map;

public class Actions implements ScheduleData {
    private final JsonMap actions;

    public Actions(JsonMap jsonMap) {
        this.actions = jsonMap;
    }

    public JsonValue toJsonValue() {
        return this.actions.toJsonValue();
    }

    public JsonMap getActionsMap() {
        return this.actions;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.actions.equals(((Actions) obj).actions);
    }

    public int hashCode() {
        return this.actions.hashCode();
    }

    public static class Builder {
        private Map<String, JsonSerializable> actions;

        private Builder() {
            this.actions = new HashMap();
        }

        public Builder addAction(String str, JsonSerializable jsonSerializable) {
            this.actions.put(str, jsonSerializable);
            return this;
        }

        public Builder addAction(String str, String str2) {
            this.actions.put(str, JsonValue.wrap(str2));
            return this;
        }

        public Builder addAction(String str, long j) {
            this.actions.put(str, JsonValue.wrap(j));
            return this;
        }

        public Builder addAction(String str, double d) {
            this.actions.put(str, JsonValue.wrap(d));
            return this;
        }

        public Builder addAction(String str, boolean z) {
            this.actions.put(str, JsonValue.wrap(z));
            return this;
        }

        public Actions build() {
            return new Actions(JsonValue.wrapOpt(this.actions).optMap());
        }
    }
}
