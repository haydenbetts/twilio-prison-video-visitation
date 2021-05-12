package com.urbanairship.actions;

import android.content.Context;
import android.content.Intent;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.urbanairship.R;
import com.urbanairship.UAirship;

public class ShareAction extends Action {
    public static final String DEFAULT_REGISTRY_NAME = "share_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^s";

    public boolean shouldRunOnMainThread() {
        return true;
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        if ((situation == 0 || situation == 6 || situation == 2 || situation == 3 || situation == 4) && actionArguments.getValue().getString() != null) {
            return true;
        }
        return false;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        Context applicationContext = UAirship.getApplicationContext();
        applicationContext.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType(ErrorAttachmentLog.CONTENT_TYPE_TEXT_PLAIN).putExtra("android.intent.extra.TEXT", actionArguments.getValue().getString()), applicationContext.getString(R.string.ua_share_dialog_title)).setFlags(268435456));
        return ActionResult.newEmptyResult();
    }
}
