package com.urbanairship.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.urbanairship.UAirship;
import com.urbanairship.google.PlayServicesUtils;
import com.urbanairship.json.JsonMap;
import com.urbanairship.util.Checks;

public class RateAppAction extends Action {
    private static final String AMAZON_URL = "amzn://apps/android?p=";
    public static final String BODY_KEY = "body";
    public static final String DEFAULT_REGISTRY_NAME = "rate_app_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^ra";
    private static final String HTTPS_PLAY_URL = "https://play.google.com/store/apps/details?id=";
    private static final String MARKET_PLAY_URL = "market://details?id=";
    public static final String SHOW_LINK_PROMPT_KEY = "show_link_prompt";
    public static final String SHOW_RATE_APP_INTENT_ACTION = "com.urbanairship.actions.SHOW_RATE_APP_INTENT_ACTION";
    static final String STORE_URI_KEY = "store_uri";
    public static final String TITLE_KEY = "title";

    public boolean shouldRunOnMainThread() {
        return true;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        Uri appStoreUri = getAppStoreUri();
        Checks.checkNotNull(appStoreUri, "Missing store URI");
        if (actionArguments.getValue().toJsonValue().optMap().opt(SHOW_LINK_PROMPT_KEY).getBoolean(false)) {
            startRateAppActivity(appStoreUri, actionArguments);
        } else {
            UAirship.getApplicationContext().startActivity(new Intent("android.intent.action.VIEW", appStoreUri).setFlags(268435456));
        }
        return ActionResult.newEmptyResult();
    }

    private void startRateAppActivity(Uri uri, ActionArguments actionArguments) {
        Context applicationContext = UAirship.getApplicationContext();
        JsonMap optMap = actionArguments.getValue().toJsonValue().optMap();
        Intent putExtra = new Intent(SHOW_RATE_APP_INTENT_ACTION).addFlags(805306368).setPackage(UAirship.getPackageName()).putExtra(STORE_URI_KEY, uri);
        if (optMap.opt("title").isString()) {
            putExtra.putExtra("title", optMap.opt("title").getString());
        }
        if (optMap.opt("body").isString()) {
            putExtra.putExtra("body", optMap.opt("body").getString());
        }
        applicationContext.startActivity(putExtra);
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        if ((situation == 0 || situation == 6 || situation == 2 || situation == 3 || situation == 4) && getAppStoreUri() != null) {
            return true;
        }
        return false;
    }

    private Uri getAppStoreUri() {
        UAirship shared = UAirship.shared();
        if (shared.getAirshipConfigOptions().appStoreUri != null) {
            return shared.getAirshipConfigOptions().appStoreUri;
        }
        String packageName = UAirship.getApplicationContext().getPackageName();
        if (UAirship.shared().getPlatformType() == 1) {
            return Uri.parse(AMAZON_URL + packageName);
        } else if (UAirship.shared().getPlatformType() != 2) {
            return null;
        } else {
            if (PlayServicesUtils.isGooglePlayStoreAvailable(UAirship.getApplicationContext())) {
                return Uri.parse(MARKET_PLAY_URL + packageName);
            }
            return Uri.parse(HTTPS_PLAY_URL + packageName);
        }
    }
}
