package com.urbanairship.actions;

import android.content.Intent;
import android.net.Uri;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.UriUtils;

public class DeepLinkAction extends Action {
    public static final String DEFAULT_REGISTRY_NAME = "deep_link_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^d";

    public boolean shouldRunOnMainThread() {
        return true;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        Uri parse = UriUtils.parse(actionArguments.getValue().getString());
        if (parse == null) {
            return ActionResult.newEmptyResult();
        }
        Logger.info("Deep linking: %s", parse);
        DeepLinkListener deepLinkListener = UAirship.shared().getDeepLinkListener();
        if (deepLinkListener == null || !deepLinkListener.onDeepLink(parse.toString())) {
            Intent intent = new Intent("android.intent.action.VIEW", parse).addFlags(268435456).setPackage(UAirship.getPackageName());
            PushMessage pushMessage = (PushMessage) actionArguments.getMetadata().getParcelable(ActionArguments.PUSH_MESSAGE_METADATA);
            if (pushMessage != null) {
                intent.putExtra(PushManager.EXTRA_PUSH_MESSAGE_BUNDLE, pushMessage.getPushBundle());
            }
            UAirship.getApplicationContext().startActivity(intent);
            return ActionResult.newResult(actionArguments.getValue());
        }
        Logger.info("Calling through to deep link listener with uri string: %s", parse.toString());
        return ActionResult.newResult(actionArguments.getValue());
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        if ((situation == 0 || situation == 6 || situation == 2 || situation == 3 || situation == 4) && UriUtils.parse(actionArguments.getValue().getString()) != null) {
            return true;
        }
        return false;
    }
}
