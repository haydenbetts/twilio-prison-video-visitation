package com.urbanairship.job;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.urbanairship.Logger;
import com.urbanairship.job.Job;
import com.urbanairship.util.AirshipHandlerThread;

public class AirshipService extends Service {
    public static final String ACTION_RUN_JOB = "RUN_JOB";
    static final String EXTRA_JOB_INFO_BUNDLE = "EXTRA_JOB_INFO_BUNDLE";
    static final String EXTRA_RESCHEDULE_EXTRAS = "EXTRA_RESCHEDULE_EXTRAS";
    private static final int MSG_INTENT_JOB_FINISHED = 2;
    private static final int MSG_INTENT_RECEIVED = 1;
    /* access modifiers changed from: private */
    public IncomingHandler handler;
    private int lastStartId = 0;
    private Looper looper;
    private int runningJobs;

    public IBinder onBind(Intent intent) {
        return null;
    }

    private final class IncomingHandler extends Handler {
        IncomingHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                AirshipService.this.onHandleIntent((Intent) message.obj, message.arg1);
            } else if (i == 2) {
                AirshipService.this.onJobFinished((Intent) message.obj, message.arg1);
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        AirshipHandlerThread airshipHandlerThread = new AirshipHandlerThread("Airship Service");
        airshipHandlerThread.start();
        this.looper = airshipHandlerThread.getLooper();
        this.handler = new IncomingHandler(this.looper);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Message obtainMessage = this.handler.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.arg1 = i2;
        obtainMessage.obj = intent;
        Logger.verbose("AirshipService - Received intent: %s", intent);
        this.handler.sendMessage(obtainMessage);
        return 2;
    }

    public void onDestroy() {
        this.looper.quit();
    }

    /* access modifiers changed from: private */
    public void onHandleIntent(final Intent intent, int i) {
        this.lastStartId = i;
        final Message obtainMessage = this.handler.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.arg1 = i;
        obtainMessage.obj = intent;
        if (intent == null || !ACTION_RUN_JOB.equals(intent.getAction()) || intent.getBundleExtra(EXTRA_JOB_INFO_BUNDLE) == null) {
            this.handler.sendMessage(obtainMessage);
            return;
        }
        final JobInfo fromBundle = JobInfo.fromBundle(intent.getBundleExtra(EXTRA_JOB_INFO_BUNDLE));
        if (fromBundle == null) {
            this.handler.sendMessage(obtainMessage);
            return;
        }
        this.runningJobs++;
        Job build = Job.newBuilder(fromBundle).setCallback(new Job.Callback() {
            public void onFinish(Job job, int i) {
                AirshipService.this.handler.sendMessage(obtainMessage);
                if (i == 1) {
                    JobDispatcher.shared(AirshipService.this.getApplicationContext()).reschedule(fromBundle, intent.getBundleExtra(AirshipService.EXTRA_RESCHEDULE_EXTRAS));
                }
            }
        }).build();
        Logger.verbose("AirshipService - Running job: %s", fromBundle);
        Job.EXECUTOR.execute(build);
    }

    /* access modifiers changed from: private */
    public void onJobFinished(Intent intent, int i) {
        Logger.verbose("AirshipService - Component finished job with startId: %s", Integer.valueOf(i));
        int i2 = this.runningJobs - 1;
        this.runningJobs = i2;
        if (i2 <= 0) {
            this.runningJobs = 0;
            Logger.verbose("AirshipService - All jobs finished, stopping with last startId: %s", Integer.valueOf(this.lastStartId));
            stopSelf(this.lastStartId);
        }
    }

    public static Intent createIntent(Context context, JobInfo jobInfo, Bundle bundle) {
        Intent action = new Intent(context, AirshipService.class).setAction(ACTION_RUN_JOB);
        if (jobInfo != null) {
            action.putExtra(EXTRA_JOB_INFO_BUNDLE, jobInfo.toBundle());
        }
        if (bundle != null) {
            action.putExtra(EXTRA_RESCHEDULE_EXTRAS, bundle);
        }
        return action;
    }
}
