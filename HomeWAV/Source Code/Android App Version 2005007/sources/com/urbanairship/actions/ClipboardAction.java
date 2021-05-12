package com.urbanairship.actions;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Handler;
import android.os.Looper;
import com.urbanairship.UAirship;

public class ClipboardAction extends Action {
    public static final String DEFAULT_REGISTRY_NAME = "clipboard_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^c";
    public static final String LABEL_KEY = "label";
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
        final String str;
        final String str2;
        if (actionArguments.getValue().getMap() != null) {
            str2 = actionArguments.getValue().getMap().opt("text").getString();
            str = actionArguments.getValue().getMap().opt("label").getString();
        } else {
            str2 = actionArguments.getValue().getString();
            str = null;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                ((ClipboardManager) UAirship.getApplicationContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(str, str2));
            }
        });
        return ActionResult.newResult(actionArguments.getValue());
    }
}
