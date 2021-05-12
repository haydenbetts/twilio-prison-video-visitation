package com.urbanairship.push;

import android.content.Context;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.job.JobDispatcher;
import com.urbanairship.job.JobInfo;
import com.urbanairship.push.IncomingPushRunnable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class PushProviderBridge {
    static final String EXTRA_PROVIDER_CLASS = "EXTRA_PROVIDER_CLASS";
    static final String EXTRA_PUSH = "EXTRA_PUSH";

    public static void requestRegistrationUpdate(Context context) {
        Autopilot.automaticTakeOff(context);
        JobDispatcher.shared(context).dispatch(JobInfo.newBuilder().setAction("ACTION_UPDATE_PUSH_REGISTRATION").setId(4).setNetworkAccessRequired(true).setAirshipComponent((Class<? extends AirshipComponent>) PushManager.class).build());
    }

    public static ProcessPushRequest processPush(Class<? extends PushProvider> cls, PushMessage pushMessage) {
        return new ProcessPushRequest(cls, pushMessage);
    }

    public static class ProcessPushRequest {
        private long maxCallbackWaitTime;
        private final Class<? extends PushProvider> provider;
        private final PushMessage pushMessage;

        private ProcessPushRequest(Class<? extends PushProvider> cls, PushMessage pushMessage2) {
            this.provider = cls;
            this.pushMessage = pushMessage2;
        }

        public ProcessPushRequest setMaxCallbackWaitTime(long j) {
            this.maxCallbackWaitTime = j;
            return this;
        }

        public void execute(Context context) {
            execute(context, (Runnable) null);
        }

        public void execute(Context context, Runnable runnable) {
            Future<?> submit = PushManager.PUSH_EXECUTOR.submit(new IncomingPushRunnable.Builder(context).setMessage(this.pushMessage).setProviderClass(this.provider.toString()).build());
            try {
                long j = this.maxCallbackWaitTime;
                if (j > 0) {
                    submit.get(j, TimeUnit.MILLISECONDS);
                } else {
                    submit.get();
                }
            } catch (TimeoutException unused) {
                Logger.error("Application took too long to process push. App may get closed.", new Object[0]);
            } catch (Exception e) {
                Logger.error(e, "Failed to wait for notification", new Object[0]);
            }
            if (runnable != null) {
                runnable.run();
            }
        }

        public void executeSync(Context context) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            execute(context, new Runnable() {
                public void run() {
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                Logger.error(e, "Failed to wait for push.", new Object[0]);
                Thread.currentThread().interrupt();
            }
        }
    }
}
