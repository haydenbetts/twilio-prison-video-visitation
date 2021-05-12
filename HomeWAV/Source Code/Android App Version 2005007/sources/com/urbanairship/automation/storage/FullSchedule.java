package com.urbanairship.automation.storage;

import java.util.List;

public class FullSchedule {
    public ScheduleEntity schedule;
    public List<TriggerEntity> triggers;

    public FullSchedule(ScheduleEntity scheduleEntity, List<TriggerEntity> list) {
        this.schedule = scheduleEntity;
        this.triggers = list;
    }

    public String toString() {
        return "FullSchedule{schedule=" + this.schedule + ", triggers=" + this.triggers + '}';
    }
}
