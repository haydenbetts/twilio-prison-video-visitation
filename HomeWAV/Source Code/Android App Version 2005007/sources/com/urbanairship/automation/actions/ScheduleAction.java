package com.urbanairship.automation.actions;

import com.urbanairship.actions.Action;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.actions.ActionValue;
import com.urbanairship.automation.Audience;
import com.urbanairship.automation.InAppAutomation;
import com.urbanairship.automation.Schedule;
import com.urbanairship.automation.ScheduleData;
import com.urbanairship.automation.ScheduleDelay;
import com.urbanairship.automation.Trigger;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.AirshipComponentUtils;
import com.urbanairship.util.DateUtils;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ScheduleAction extends Action {
    private static final String ACTIONS_KEY = "actions";
    private static final String AUDIENCE_KEY = "audience";
    public static final String DEFAULT_REGISTRY_NAME = "schedule_actions";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^sa";
    private static final String DELAY_KEY = "delay";
    private static final String END_KEY = "end";
    private static final String GROUP_KEY = "group";
    private static final String INTERVAL_KEY = "interval";
    private static final String LIMIT_KEY = "limit";
    private static final String PRIORITY_KEY = "priority";
    private static final String START_KEY = "start";
    private static final String TRIGGERS_KEY = "triggers";
    private final Callable<InAppAutomation> actionAutomationCallable;

    public ScheduleAction() {
        this(AirshipComponentUtils.callableForComponent(InAppAutomation.class));
    }

    ScheduleAction(Callable<InAppAutomation> callable) {
        this.actionAutomationCallable = callable;
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        if (situation == 0 || situation == 1 || situation == 3 || situation == 6) {
            return actionArguments.getValue().toJsonValue().isJsonMap();
        }
        return false;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        try {
            InAppAutomation call = this.actionAutomationCallable.call();
            try {
                Schedule<Actions> parseSchedule = parseSchedule(actionArguments.getValue().toJsonValue());
                Boolean bool = call.schedule((Schedule<? extends ScheduleData>) parseSchedule).get();
                return (bool == null || !bool.booleanValue()) ? ActionResult.newEmptyResult() : ActionResult.newResult(ActionValue.wrap(parseSchedule.getId()));
            } catch (JsonException | InterruptedException | ExecutionException e) {
                return ActionResult.newErrorResult(e);
            }
        } catch (Exception e2) {
            return ActionResult.newErrorResult(e2);
        }
    }

    /* access modifiers changed from: package-private */
    public Schedule<Actions> parseSchedule(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        Schedule.Builder<Actions> group = Schedule.newBuilder(new Actions(optMap.opt("actions").optMap())).setLimit(optMap.opt("limit").getInt(1)).setPriority(optMap.opt("priority").getInt(0)).setGroup(optMap.opt(GROUP_KEY).getString());
        if (optMap.containsKey("end")) {
            group.setEnd(DateUtils.parseIso8601(optMap.opt("end").optString(), -1));
        }
        if (optMap.containsKey("start")) {
            group.setStart(DateUtils.parseIso8601(optMap.opt("start").optString(), -1));
        }
        Iterator<JsonValue> it = optMap.opt("triggers").optList().iterator();
        while (it.hasNext()) {
            group.addTrigger(Trigger.fromJson(it.next()));
        }
        if (optMap.containsKey(DELAY_KEY)) {
            group.setDelay(ScheduleDelay.fromJson(optMap.opt(DELAY_KEY)));
        }
        if (optMap.containsKey(INTERVAL_KEY)) {
            group.setInterval(optMap.opt(INTERVAL_KEY).getLong(0), TimeUnit.SECONDS);
        }
        JsonValue jsonValue2 = optMap.opt(AUDIENCE_KEY).optMap().get(AUDIENCE_KEY);
        if (jsonValue2 != null) {
            group.setAudience(Audience.fromJson(jsonValue2));
        }
        try {
            return group.build();
        } catch (IllegalArgumentException e) {
            throw new JsonException("Invalid schedule info", e);
        }
    }
}
