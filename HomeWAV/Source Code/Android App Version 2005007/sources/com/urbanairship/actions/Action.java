package com.urbanairship.actions;

import com.urbanairship.Logger;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Action {
    public static final int SITUATION_AUTOMATION = 6;
    public static final int SITUATION_BACKGROUND_NOTIFICATION_ACTION_BUTTON = 5;
    public static final int SITUATION_FOREGROUND_NOTIFICATION_ACTION_BUTTON = 4;
    public static final int SITUATION_MANUAL_INVOCATION = 0;
    public static final int SITUATION_PUSH_OPENED = 2;
    public static final int SITUATION_PUSH_RECEIVED = 1;
    public static final int SITUATION_WEB_VIEW_INVOCATION = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Situation {
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        return true;
    }

    public void onFinish(ActionArguments actionArguments, ActionResult actionResult) {
    }

    public void onStart(ActionArguments actionArguments) {
    }

    public abstract ActionResult perform(ActionArguments actionArguments);

    public boolean shouldRunOnMainThread() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final ActionResult run(ActionArguments actionArguments) {
        try {
            if (!acceptsArguments(actionArguments)) {
                Logger.debug("Action %s is unable to accept arguments: %s", this, actionArguments);
                return ActionResult.newEmptyResultWithStatus(2);
            }
            Logger.info("Running action: %s arguments: %s", this, actionArguments);
            onStart(actionArguments);
            ActionResult perform = perform(actionArguments);
            if (perform == null) {
                perform = ActionResult.newEmptyResult();
            }
            onFinish(actionArguments, perform);
            return perform;
        } catch (Exception e) {
            Logger.error(e, "Failed to run action %s", this);
            return ActionResult.newErrorResult(e);
        }
    }
}
