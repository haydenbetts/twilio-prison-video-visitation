package com.urbanairship.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.util.UAStringUtil;

public class InstallReceiver extends BroadcastReceiver {
    private static final String ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER";
    private static final String EXTRA_INSTALL_REFERRER = "referrer";

    public void onReceive(Context context, Intent intent) {
        Autopilot.automaticTakeOff(context);
        if (!UAirship.isTakingOff() && !UAirship.isFlying()) {
            Logger.error("InstallReceiver - unable to track install referrer, takeOff not called.", new Object[0]);
        } else if (intent != null) {
            String stringExtra = intent.getStringExtra(EXTRA_INSTALL_REFERRER);
            if (UAStringUtil.isEmpty(stringExtra) || !ACTION_INSTALL_REFERRER.equals(intent.getAction())) {
                Logger.debug("InstallReceiver - missing referrer or invalid action.", new Object[0]);
                return;
            }
            UAirship.shared().getAnalytics().addEvent(new InstallAttributionEvent(stringExtra));
        }
    }
}
