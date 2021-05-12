package com.urbanairship.automation;

import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;

public class TriggerContext implements JsonSerializable {
    private static final String EVENT_KEY = "event";
    private static final String TRIGGER_KEY = "trigger";
    private JsonValue event;
    private Trigger trigger;

    public TriggerContext(Trigger trigger2, JsonValue jsonValue) {
        this.trigger = trigger2;
        this.event = jsonValue;
    }

    public JsonValue getEvent() {
        return this.event;
    }

    public Trigger getTrigger() {
        return this.trigger;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put(TRIGGER_KEY, (JsonSerializable) this.trigger).put("event", (JsonSerializable) this.event).build().toJsonValue();
    }

    public static TriggerContext fromJson(JsonValue jsonValue) throws JsonException {
        return new TriggerContext(Trigger.fromJson(jsonValue.optMap().opt(TRIGGER_KEY)), jsonValue.optMap().opt("event"));
    }

    public String toString() {
        return "TriggerContext{trigger=" + this.trigger + ", event=" + this.event + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TriggerContext triggerContext = (TriggerContext) obj;
        if (!this.trigger.equals(triggerContext.trigger)) {
            return false;
        }
        return this.event.equals(triggerContext.event);
    }

    public int hashCode() {
        return (this.trigger.hashCode() * 31) + this.event.hashCode();
    }
}
