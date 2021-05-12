package com.urbanairship.locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;

public class LocaleChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
            if (UAirship.isTakingOff() || UAirship.isFlying()) {
                Autopilot.automaticTakeOff(context);
                UAirship.shared().getLocaleManager().onDeviceLocaleChanged();
                return;
            }
            Logger.error("LocaleChangedReceiver - unable to receive intent, takeOff not called.", new Object[0]);
        }
    }
}
