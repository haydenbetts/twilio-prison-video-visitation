package com.urbanairship.automation.storage;

import java.util.Collection;
import java.util.List;

public abstract class AutomationDao {
    public abstract void delete(ScheduleEntity scheduleEntity);

    /* access modifiers changed from: package-private */
    public abstract void deleteSchedulesByType(int i);

    public abstract List<FullSchedule> getActiveExpiredSchedules();

    public abstract List<TriggerEntity> getActiveTriggers(int i);

    public abstract List<TriggerEntity> getActiveTriggers(int i, String str);

    public abstract FullSchedule getSchedule(String str);

    public abstract FullSchedule getSchedule(String str, String str2);

    public abstract int getScheduleCount();

    public abstract List<FullSchedule> getSchedules();

    public abstract List<FullSchedule> getSchedules(Collection<String> collection);

    public abstract List<FullSchedule> getSchedules(Collection<String> collection, String str);

    public abstract List<FullSchedule> getSchedulesByType(String str);

    public abstract List<FullSchedule> getSchedulesWithGroup(String str);

    public abstract List<FullSchedule> getSchedulesWithGroup(String str, String str2);

    public abstract List<FullSchedule> getSchedulesWithStates(int... iArr);

    public abstract void insert(ScheduleEntity scheduleEntity, List<TriggerEntity> list);

    public abstract void update(ScheduleEntity scheduleEntity, List<TriggerEntity> list);

    public abstract void updateTriggers(List<TriggerEntity> list);

    public void insert(Collection<FullSchedule> collection) {
        for (FullSchedule next : collection) {
            if (next != null) {
                insert(next);
            }
        }
    }

    public void insert(FullSchedule fullSchedule) {
        insert(fullSchedule.schedule, fullSchedule.triggers);
    }

    public void updateSchedules(Collection<FullSchedule> collection) {
        for (FullSchedule next : collection) {
            if (next != null) {
                update(next);
            }
        }
    }

    public void update(FullSchedule fullSchedule) {
        update(fullSchedule.schedule, fullSchedule.triggers);
    }

    public void delete(FullSchedule fullSchedule) {
        delete(fullSchedule.schedule);
    }

    public void deleteSchedules(Collection<FullSchedule> collection) {
        for (FullSchedule next : collection) {
            if (next != null) {
                delete(next);
            }
        }
    }
}
