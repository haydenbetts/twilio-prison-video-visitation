package com.urbanairship.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.ResultCallback;
import com.urbanairship.UAirship;

public class NotificationProxyActivity extends Activity {
    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Autopilot.automaticTakeOff((Context) this);
        if (UAirship.isTakingOff() || UAirship.isFlying()) {
            Intent intent = getIntent();
            if (intent == null || intent.getAction() == null) {
                finish();
                return;
            }
            Logger.verbose("NotificationProxyActivity - Received intent: %s", intent.getAction());
            new NotificationIntentProcessor(this, intent).process().addResultCallback(new ResultCallback<Boolean>() {
                public void onResult(Boolean bool) {
                    Logger.verbose("NotificationProxyActivity - Finished processing notification intent with result %s.", bool);
                }
            });
            finish();
            return;
        }
        Logger.error("NotificationProxyActivity - unable to receive intent, takeOff not called.", new Object[0]);
        finish();
    }
}
