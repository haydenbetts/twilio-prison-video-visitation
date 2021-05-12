package com.urbanairship.actions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationManagerCompat;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.modules.location.AirshipLocationClient;
import com.urbanairship.util.Checks;
import com.urbanairship.util.HelperActivity;
import com.urbanairship.util.PermissionsRequester;
import java.util.Arrays;
import java.util.List;

public class EnableFeatureAction extends Action {
    public static final String DEFAULT_REGISTRY_NAME = "enable_feature";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^ef";
    public static final String FEATURE_BACKGROUND_LOCATION = "background_location";
    public static final String FEATURE_LOCATION = "location";
    public static final String FEATURE_USER_NOTIFICATIONS = "user_notifications";
    private final PermissionsRequester permissionsRequester;

    public EnableFeatureAction(PermissionsRequester permissionsRequester2) {
        this.permissionsRequester = permissionsRequester2;
    }

    public EnableFeatureAction() {
        this(new PermissionsRequester() {
            public int[] requestPermissions(Context context, List<String> list) {
                return HelperActivity.requestPermissions(context, (String[]) list.toArray(new String[0]));
            }
        });
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        if (r6.equals("location") == false) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean acceptsArguments(com.urbanairship.actions.ActionArguments r6) {
        /*
            r5 = this;
            int r0 = r6.getSituation()
            r1 = 2
            r2 = 0
            if (r0 == 0) goto L_0x0014
            r3 = 6
            if (r0 == r3) goto L_0x0014
            if (r0 == r1) goto L_0x0014
            r3 = 3
            if (r0 == r3) goto L_0x0014
            r3 = 4
            if (r0 == r3) goto L_0x0014
            return r2
        L_0x0014:
            com.urbanairship.actions.ActionValue r6 = r6.getValue()
            java.lang.String r6 = r6.getString()
            if (r6 != 0) goto L_0x001f
            return r2
        L_0x001f:
            r6.hashCode()
            r0 = -1
            int r3 = r6.hashCode()
            r4 = 1
            switch(r3) {
                case 845239156: goto L_0x0041;
                case 954101670: goto L_0x0036;
                case 1901043637: goto L_0x002d;
                default: goto L_0x002b;
            }
        L_0x002b:
            r1 = -1
            goto L_0x004c
        L_0x002d:
            java.lang.String r3 = "location"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x004c
            goto L_0x002b
        L_0x0036:
            java.lang.String r1 = "background_location"
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x003f
            goto L_0x002b
        L_0x003f:
            r1 = 1
            goto L_0x004c
        L_0x0041:
            java.lang.String r1 = "user_notifications"
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x004b
            goto L_0x002b
        L_0x004b:
            r1 = 0
        L_0x004c:
            switch(r1) {
                case 0: goto L_0x0050;
                case 1: goto L_0x0050;
                case 2: goto L_0x0050;
                default: goto L_0x004f;
            }
        L_0x004f:
            return r2
        L_0x0050:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.actions.EnableFeatureAction.acceptsArguments(com.urbanairship.actions.ActionArguments):boolean");
    }

    public ActionResult perform(ActionArguments actionArguments) {
        String string = actionArguments.getValue().getString();
        Checks.checkNotNull(string, "Missing feature.");
        AirshipLocationClient locationClient = UAirship.shared().getLocationClient();
        string.hashCode();
        char c = 65535;
        switch (string.hashCode()) {
            case 845239156:
                if (string.equals(FEATURE_USER_NOTIFICATIONS)) {
                    c = 0;
                    break;
                }
                break;
            case 954101670:
                if (string.equals(FEATURE_BACKGROUND_LOCATION)) {
                    c = 1;
                    break;
                }
                break;
            case 1901043637:
                if (string.equals("location")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                UAirship.shared().getPushManager().setUserNotificationsEnabled(true);
                if (!NotificationManagerCompat.from(UAirship.getApplicationContext()).areNotificationsEnabled()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            EnableFeatureAction.navigateToNotificationSettings();
                        }
                    });
                }
                return ActionResult.newResult(ActionValue.wrap(true));
            case 1:
                if (locationClient == null) {
                    return ActionResult.newEmptyResult();
                }
                if (!requestLocationPermissions()) {
                    return ActionResult.newResult(ActionValue.wrap(false));
                }
                locationClient.setLocationUpdatesEnabled(true);
                locationClient.setBackgroundLocationAllowed(true);
                return ActionResult.newResult(ActionValue.wrap(true));
            case 2:
                if (locationClient == null) {
                    return ActionResult.newEmptyResult();
                }
                if (!requestLocationPermissions()) {
                    return ActionResult.newResult(ActionValue.wrap(false));
                }
                locationClient.setLocationUpdatesEnabled(true);
                return ActionResult.newResult(ActionValue.wrap(true));
            default:
                return ActionResult.newResult(ActionValue.wrap(false));
        }
    }

    private boolean requestLocationPermissions() {
        for (int i : this.permissionsRequester.requestPermissions(UAirship.getApplicationContext(), Arrays.asList(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}))) {
            if (i == 0) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static void navigateToNotificationSettings() {
        Context applicationContext = UAirship.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                applicationContext.startActivity(new Intent("android.settings.APP_NOTIFICATION_SETTINGS").putExtra("android.provider.extra.APP_PACKAGE", UAirship.getPackageName()).addFlags(268435456));
                return;
            } catch (ActivityNotFoundException e) {
                Logger.debug(e, "Failed to launch notification settings.", new Object[0]);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                applicationContext.startActivity(new Intent("android.settings.APP_NOTIFICATION_SETTINGS").putExtra("app_package", UAirship.getPackageName()).addFlags(268435456).putExtra("app_uid", UAirship.getAppInfo().uid));
                return;
            } catch (ActivityNotFoundException e2) {
                Logger.debug(e2, "Failed to launch notification settings.", new Object[0]);
            }
        }
        Intent addFlags = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").addCategory("android.intent.category.DEFAULT").addFlags(268435456);
        try {
            applicationContext.startActivity(addFlags.setData(Uri.parse("package:" + UAirship.getPackageName())));
        } catch (ActivityNotFoundException e3) {
            Logger.error(e3, "Unable to launch settings activity.", new Object[0]);
        }
    }
}
