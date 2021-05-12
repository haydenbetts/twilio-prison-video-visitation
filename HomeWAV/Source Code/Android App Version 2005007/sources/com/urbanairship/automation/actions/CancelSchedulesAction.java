package com.urbanairship.automation.actions;

import com.urbanairship.actions.Action;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.automation.InAppAutomation;
import com.urbanairship.automation.Schedule;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.AirshipComponentUtils;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class CancelSchedulesAction extends Action {
    public static final String ALL = "all";
    public static final String DEFAULT_REGISTRY_NAME = "cancel_scheduled_actions";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^csa";
    public static final String GROUPS = "groups";
    public static final String IDS = "ids";
    private final Callable<InAppAutomation> actionAutomationCallable;

    public CancelSchedulesAction() {
        this(AirshipComponentUtils.callableForComponent(InAppAutomation.class));
    }

    CancelSchedulesAction(Callable<InAppAutomation> callable) {
        this.actionAutomationCallable = callable;
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        if (situation != 0 && situation != 1 && situation != 3 && situation != 6) {
            return false;
        }
        if (actionArguments.getValue().toJsonValue().isString()) {
            return ALL.equalsIgnoreCase(actionArguments.getValue().getString());
        }
        return actionArguments.getValue().toJsonValue().isJsonMap();
    }

    public ActionResult perform(ActionArguments actionArguments) {
        try {
            InAppAutomation call = this.actionAutomationCallable.call();
            JsonValue jsonValue = actionArguments.getValue().toJsonValue();
            if (!jsonValue.isString() || !ALL.equalsIgnoreCase(jsonValue.getString())) {
                JsonValue opt = jsonValue.optMap().opt(GROUPS);
                if (opt.isString()) {
                    call.cancelScheduleGroup(opt.optString());
                } else if (opt.isJsonList()) {
                    Iterator<JsonValue> it = opt.optList().iterator();
                    while (it.hasNext()) {
                        JsonValue next = it.next();
                        if (next.isString()) {
                            call.cancelScheduleGroup(next.optString());
                        }
                    }
                }
                JsonValue opt2 = jsonValue.optMap().opt(IDS);
                if (opt2.isString()) {
                    call.cancelSchedule(opt2.optString());
                } else if (opt2.isJsonList()) {
                    Iterator<JsonValue> it2 = opt2.optList().iterator();
                    while (it2.hasNext()) {
                        JsonValue next2 = it2.next();
                        if (next2.isString()) {
                            call.cancelSchedule(next2.optString());
                        }
                    }
                }
                return ActionResult.newEmptyResult();
            }
            call.cancelSchedules(Schedule.TYPE_ACTION);
            return ActionResult.newEmptyResult();
        } catch (Exception e) {
            return ActionResult.newErrorResult(e);
        }
    }
}
