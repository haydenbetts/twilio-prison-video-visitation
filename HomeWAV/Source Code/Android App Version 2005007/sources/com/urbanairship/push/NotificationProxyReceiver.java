package com.urbanairship.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.PendingResult;
import com.urbanairship.UAirship;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NotificationProxyReceiver extends BroadcastReceiver {
    private static final long ACTION_TIMEOUT_SECONDS = 9;

    public void onReceive(Context context, Intent intent) {
        Autopilot.automaticTakeOff(context);
        if (!UAirship.isTakingOff() && !UAirship.isFlying()) {
            Logger.error("NotificationProxyReceiver - unable to receive intent, takeOff not called.", new Object[0]);
        } else if (intent != null && intent.getAction() != null) {
            Logger.verbose("NotificationProxyReceiver - Received intent: %s", intent.getAction());
            final BroadcastReceiver.PendingResult goAsync = goAsync();
            final PendingResult<Boolean> process = new NotificationIntentProcessor(context, intent).process();
            AirshipExecutors.THREAD_POOL_EXECUTOR.execute(new Runnable() {
                public void run() {
                    try {
                        Logger.verbose("NotificationProxyReceiver - Finished processing notification intent with result %s.", (Boolean) process.get(NotificationProxyReceiver.ACTION_TIMEOUT_SECONDS, TimeUnit.SECONDS));
                    } catch (InterruptedException | ExecutionException e) {
                        Logger.error(e, "NotificationProxyReceiver - Exception when processing notification intent.", new Object[0]);
                        Thread.currentThread().interrupt();
                    } catch (TimeoutException unused) {
                        Logger.error("NotificationProxyReceiver - Application took too long to process notification intent.", new Object[0]);
                    }
                    goAsync.finish();
                }
            });
        }
    }
}
