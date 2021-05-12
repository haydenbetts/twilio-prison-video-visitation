package com.urbanairship.automation;

import com.urbanairship.automation.Schedule;
import com.urbanairship.automation.ScheduleDelay;
import com.urbanairship.automation.actions.Actions;
import com.urbanairship.automation.deferred.Deferred;
import com.urbanairship.automation.storage.FullSchedule;
import com.urbanairship.automation.storage.ScheduleEntity;
import com.urbanairship.automation.storage.TriggerEntity;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

class ScheduleConverters {
    ScheduleConverters() {
    }

    static List<FullSchedule> convertSchedules(Collection<Schedule<? extends ScheduleData>> collection) {
        ArrayList arrayList = new ArrayList();
        for (Schedule<? extends ScheduleData> convert : collection) {
            arrayList.add(convert((Schedule<?>) convert));
        }
        return arrayList;
    }

    static <T extends ScheduleData> Schedule<T> convert(FullSchedule fullSchedule) throws JsonException, IllegalArgumentException, ClassCastException {
        Schedule.Builder audience = createScheduleBuilder(fullSchedule.schedule.data, fullSchedule.schedule.scheduleType).setId(fullSchedule.schedule.scheduleId).setMetadata(fullSchedule.schedule.metadata).setGroup(fullSchedule.schedule.group).setEnd(fullSchedule.schedule.scheduleEnd).setStart(fullSchedule.schedule.scheduleStart).setLimit(fullSchedule.schedule.limit).setPriority(fullSchedule.schedule.priority).setInterval(fullSchedule.schedule.interval, TimeUnit.MILLISECONDS).setEditGracePeriod(fullSchedule.schedule.editGracePeriod, TimeUnit.MILLISECONDS).setAudience(fullSchedule.schedule.audience);
        ScheduleDelay.Builder seconds = ScheduleDelay.newBuilder().setAppState(fullSchedule.schedule.appState).setRegionId(fullSchedule.schedule.regionId).setScreens(fullSchedule.schedule.screens).setSeconds(fullSchedule.schedule.seconds);
        for (TriggerEntity next : fullSchedule.triggers) {
            if (next.isCancellation) {
                seconds.addCancellationTrigger(convert(next));
            } else {
                audience.addTrigger(convert(next));
            }
        }
        return audience.setDelay(seconds.build()).build();
    }

    static Trigger convert(TriggerEntity triggerEntity) {
        return new Trigger(triggerEntity.triggerType, triggerEntity.goal, triggerEntity.jsonPredicate);
    }

    static FullSchedule convert(Schedule<?> schedule) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        ArrayList arrayList = new ArrayList();
        scheduleEntity.scheduleId = schedule.getId();
        scheduleEntity.group = schedule.getGroup();
        scheduleEntity.metadata = schedule.getMetadata();
        scheduleEntity.scheduleEnd = schedule.getEnd();
        scheduleEntity.scheduleStart = schedule.getStart();
        scheduleEntity.limit = schedule.getLimit();
        scheduleEntity.priority = schedule.getPriority();
        scheduleEntity.interval = schedule.getInterval();
        scheduleEntity.editGracePeriod = schedule.getEditGracePeriod();
        scheduleEntity.audience = schedule.getAudience();
        scheduleEntity.scheduleType = schedule.getType();
        scheduleEntity.data = schedule.getDataAsJson();
        for (Trigger convert : schedule.getTriggers()) {
            arrayList.add(convert(convert, false, schedule.getId()));
        }
        ScheduleDelay delay = schedule.getDelay();
        if (delay != null) {
            scheduleEntity.screens = delay.getScreens();
            scheduleEntity.regionId = delay.getRegionId();
            scheduleEntity.appState = delay.getAppState();
            scheduleEntity.seconds = delay.getSeconds();
            for (Trigger convert2 : delay.getCancellationTriggers()) {
                arrayList.add(convert(convert2, true, schedule.getId()));
            }
        }
        return new FullSchedule(scheduleEntity, arrayList);
    }

    private static TriggerEntity convert(Trigger trigger, boolean z, String str) {
        TriggerEntity triggerEntity = new TriggerEntity();
        triggerEntity.goal = trigger.getGoal();
        triggerEntity.isCancellation = z;
        triggerEntity.triggerType = trigger.getType();
        triggerEntity.jsonPredicate = trigger.getPredicate();
        triggerEntity.parentScheduleId = str;
        return triggerEntity;
    }

    private static <T extends ScheduleData> Schedule.Builder<T> createScheduleBuilder(JsonValue jsonValue, String str) throws JsonException {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1161803523:
                if (str.equals(Schedule.TYPE_ACTION)) {
                    c = 0;
                    break;
                }
                break;
            case -379237425:
                if (str.equals("in_app_message")) {
                    c = 1;
                    break;
                }
                break;
            case 647890911:
                if (str.equals(Schedule.TYPE_DEFERRED)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return Schedule.newBuilder(new Actions(jsonValue.optMap()));
            case 1:
                return Schedule.newBuilder(InAppMessage.fromJson(jsonValue));
            case 2:
                return Schedule.newBuilder(Deferred.fromJson(jsonValue));
            default:
                throw new IllegalArgumentException("Invalid type: " + str);
        }
    }
}
