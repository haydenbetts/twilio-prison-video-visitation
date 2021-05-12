package com.urbanairship.iam;

import com.urbanairship.PendingResult;
import com.urbanairship.automation.Schedule;
import com.urbanairship.automation.ScheduleData;
import com.urbanairship.automation.ScheduleEdits;
import com.urbanairship.automation.actions.Actions;
import java.util.Collection;
import java.util.List;

public interface InAppAutomationScheduler {
    PendingResult<Boolean> cancelSchedule(String str);

    PendingResult<Boolean> cancelScheduleGroup(String str);

    PendingResult<Boolean> editSchedule(String str, ScheduleEdits<? extends ScheduleData> scheduleEdits);

    PendingResult<Schedule<Actions>> getActionSchedule(String str);

    PendingResult<Collection<Schedule<Actions>>> getActionScheduleGroup(String str);

    PendingResult<Collection<Schedule<Actions>>> getActionSchedules();

    PendingResult<Schedule<InAppMessage>> getMessageSchedule(String str);

    PendingResult<Collection<Schedule<InAppMessage>>> getMessageScheduleGroup(String str);

    PendingResult<Collection<Schedule<InAppMessage>>> getMessageSchedules();

    PendingResult<Collection<Schedule<? extends ScheduleData>>> getSchedules();

    PendingResult<Boolean> schedule(Schedule<? extends ScheduleData> schedule);

    PendingResult<Boolean> schedule(List<Schedule<? extends ScheduleData>> list);
}
