package com.urbanairship.actions;

import android.widget.Toast;
import com.urbanairship.UAirship;

public class ToastAction extends Action {
    public static final String DEFAULT_REGISTRY_NAME = "toast_action";
    public static final String LENGTH_KEY = "length";
    public static final String TEXT_KEY = "text";

    public boolean shouldRunOnMainThread() {
        return true;
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        if (situation != 0 && situation != 2 && situation != 3 && situation != 4 && situation != 5 && situation != 6) {
            return false;
        }
        if (actionArguments.getValue().getMap() != null) {
            return actionArguments.getValue().getMap().opt("text").isString();
        }
        if (actionArguments.getValue().getString() != null) {
            return true;
        }
        return false;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        String str;
        int i;
        if (actionArguments.getValue().getMap() != null) {
            i = actionArguments.getValue().getMap().opt(LENGTH_KEY).getInt(0);
            str = actionArguments.getValue().getMap().opt("text").getString();
        } else {
            str = actionArguments.getValue().getString();
            i = 0;
        }
        if (i == 1) {
            Toast.makeText(UAirship.getApplicationContext(), str, 1).show();
        } else {
            Toast.makeText(UAirship.getApplicationContext(), str, 0).show();
        }
        return ActionResult.newResult(actionArguments.getValue());
    }
}
