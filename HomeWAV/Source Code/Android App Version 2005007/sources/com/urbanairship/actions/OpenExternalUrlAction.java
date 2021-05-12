package com.urbanairship.actions;

import android.content.Intent;
import android.net.Uri;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.util.UriUtils;

public class OpenExternalUrlAction extends Action {
    public static final String DEFAULT_REGISTRY_NAME = "open_external_url_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^u";

    public boolean shouldRunOnMainThread() {
        return true;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        Uri parse = UriUtils.parse(actionArguments.getValue().getString());
        Logger.info("Opening URI: %s", parse);
        Intent intent = new Intent("android.intent.action.VIEW", parse);
        intent.addFlags(268435456);
        UAirship.getApplicationContext().startActivity(intent);
        return ActionResult.newResult(actionArguments.getValue());
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        if ((situation == 0 || situation == 6 || situation == 2 || situation == 3 || situation == 4) && UriUtils.parse(actionArguments.getValue().getString()) != null) {
            return UAirship.shared().getUrlAllowList().isAllowed(actionArguments.getValue().getString(), 2);
        }
        return false;
    }
}
