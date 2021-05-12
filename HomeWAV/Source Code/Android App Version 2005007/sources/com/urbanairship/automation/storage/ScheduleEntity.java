package com.urbanairship.automation.storage;

import com.urbanairship.automation.Audience;
import com.urbanairship.automation.TriggerContext;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import java.util.List;

public class ScheduleEntity {
    public int appState;
    public Audience audience;
    public int count;
    public JsonValue data;
    public long editGracePeriod;
    public int executionState;
    public long executionStateChangeDate;
    public String group;
    public int id;
    public long interval;
    public int limit;
    public JsonMap metadata;
    public int priority;
    public String regionId;
    public long scheduleEnd;
    public String scheduleId;
    public long scheduleStart;
    public String scheduleType;
    public List<String> screens;
    public long seconds;
    public TriggerContext triggerContext;

    public String toString() {
        return "ScheduleEntity{id=" + this.id + ", scheduleId='" + this.scheduleId + '\'' + ", group='" + this.group + '\'' + ", metadata=" + this.metadata + ", limit=" + this.limit + ", priority=" + this.priority + ", scheduleStart=" + this.scheduleStart + ", scheduleEnd=" + this.scheduleEnd + ", editGracePeriod=" + this.editGracePeriod + ", interval=" + this.interval + ", scheduleType='" + this.scheduleType + '\'' + ", data=" + this.data + ", count=" + this.count + ", executionState=" + this.executionState + ", executionStateChangeDate=" + this.executionStateChangeDate + ", triggerContext=" + this.triggerContext + ", appState=" + this.appState + ", screens=" + this.screens + ", seconds=" + this.seconds + ", regionId='" + this.regionId + '\'' + ", audience=" + this.audience + '}';
    }
}
