package com.urbanairship.automation.storage;

import com.urbanairship.json.JsonPredicate;

public class TriggerEntity {
    public double goal;
    int id;
    public boolean isCancellation;
    public JsonPredicate jsonPredicate;
    public String parentScheduleId;
    public double progress;
    public int triggerType;

    public String toString() {
        return "TriggerEntity{id=" + this.id + ", triggerType=" + this.triggerType + ", goal=" + this.goal + ", jsonPredicate=" + this.jsonPredicate + ", isCancellation=" + this.isCancellation + ", progress=" + this.progress + ", parentScheduleId='" + this.parentScheduleId + '\'' + '}';
    }
}
