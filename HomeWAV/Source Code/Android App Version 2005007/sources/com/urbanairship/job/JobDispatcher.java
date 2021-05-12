package com.urbanairship.job;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import com.urbanairship.Logger;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.GlobalActivityMonitor;

public class JobDispatcher {
    private static final int DEFAULT_JOB_ID_START = 3000000;
    private static final String JOB_ID_START_KEY = "com.urbanairship.job.JOB_ID_START";
    private static JobDispatcher instance;
    private final ActivityMonitor activityMonitor;
    private final Context context;
    private boolean isUsingFallbackScheduler;
    private Integer jobIdStart;
    private Scheduler scheduler;
    private final SchedulerFactory schedulerFactory;

    interface SchedulerFactory {
        Scheduler createFallbackScheduler(Context context);

        Scheduler createScheduler(Context context);
    }

    public static JobDispatcher shared(Context context2) {
        if (instance == null) {
            synchronized (JobDispatcher.class) {
                if (instance == null) {
                    instance = new JobDispatcher(context2);
                }
            }
        }
        return instance;
    }

    private JobDispatcher(Context context2) {
        this(context2, new DefaultSchedulerFactory(), GlobalActivityMonitor.shared(context2));
    }

    JobDispatcher(Context context2, SchedulerFactory schedulerFactory2, ActivityMonitor activityMonitor2) {
        this.isUsingFallbackScheduler = false;
        this.context = context2.getApplicationContext();
        this.schedulerFactory = schedulerFactory2;
        this.activityMonitor = activityMonitor2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0030 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatch(com.urbanairship.job.JobInfo r4) {
        /*
            r3 = this;
            boolean r0 = r3.requiresScheduling(r4)     // Catch:{ SchedulerException -> 0x0042 }
            if (r0 == 0) goto L_0x0018
            com.urbanairship.job.Scheduler r0 = r3.getScheduler()     // Catch:{ SchedulerException -> 0x0042 }
            android.content.Context r1 = r3.context     // Catch:{ SchedulerException -> 0x0042 }
            int r2 = r4.getId()     // Catch:{ SchedulerException -> 0x0042 }
            int r2 = r3.getScheduleId(r2)     // Catch:{ SchedulerException -> 0x0042 }
            r0.schedule(r1, r4, r2)     // Catch:{ SchedulerException -> 0x0042 }
            return
        L_0x0018:
            com.urbanairship.job.Scheduler r0 = r3.getScheduler()     // Catch:{ IllegalStateException | SecurityException -> 0x0030 }
            android.content.Context r1 = r3.context     // Catch:{ IllegalStateException | SecurityException -> 0x0030 }
            int r2 = r4.getId()     // Catch:{ IllegalStateException | SecurityException -> 0x0030 }
            r0.cancel(r1, r2)     // Catch:{ IllegalStateException | SecurityException -> 0x0030 }
            android.content.Context r0 = r3.context     // Catch:{ IllegalStateException | SecurityException -> 0x0030 }
            r1 = 0
            android.content.Intent r1 = com.urbanairship.job.AirshipService.createIntent(r0, r4, r1)     // Catch:{ IllegalStateException | SecurityException -> 0x0030 }
            r0.startService(r1)     // Catch:{ IllegalStateException | SecurityException -> 0x0030 }
            goto L_0x0054
        L_0x0030:
            com.urbanairship.job.Scheduler r0 = r3.getScheduler()     // Catch:{ SchedulerException -> 0x0042 }
            android.content.Context r1 = r3.context     // Catch:{ SchedulerException -> 0x0042 }
            int r2 = r4.getId()     // Catch:{ SchedulerException -> 0x0042 }
            int r2 = r3.getScheduleId(r2)     // Catch:{ SchedulerException -> 0x0042 }
            r0.schedule(r1, r4, r2)     // Catch:{ SchedulerException -> 0x0042 }
            goto L_0x0054
        L_0x0042:
            r0 = move-exception
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Scheduler failed to schedule jobInfo"
            com.urbanairship.Logger.error(r0, r2, r1)
            boolean r0 = r3.useFallbackScheduler()
            if (r0 == 0) goto L_0x0054
            r3.dispatch(r4)
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.job.JobDispatcher.dispatch(com.urbanairship.job.JobInfo):void");
    }

    /* access modifiers changed from: package-private */
    public void reschedule(JobInfo jobInfo, Bundle bundle) {
        try {
            getScheduler().reschedule(this.context, jobInfo, getScheduleId(jobInfo.getId()), bundle);
        } catch (SchedulerException e) {
            Logger.error(e, "Scheduler failed to schedule jobInfo", new Object[0]);
            if (useFallbackScheduler()) {
                reschedule(jobInfo, bundle);
            }
        }
    }

    public void cancel(int i) {
        try {
            getScheduler().cancel(this.context, getScheduleId(i));
        } catch (SchedulerException e) {
            Logger.error(e, "Scheduler failed to cancel job with id: %s", Integer.valueOf(i));
            if (useFallbackScheduler()) {
                cancel(i);
            }
        }
    }

    private Scheduler getScheduler() {
        if (this.scheduler == null) {
            this.scheduler = this.schedulerFactory.createScheduler(this.context);
        }
        return this.scheduler;
    }

    private boolean requiresScheduling(JobInfo jobInfo) {
        NetworkInfo activeNetworkInfo;
        if (!this.activityMonitor.isAppForegrounded() || jobInfo.getInitialDelay() > 0) {
            return true;
        }
        if (!jobInfo.isNetworkAccessRequired()) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private boolean useFallbackScheduler() {
        if (this.isUsingFallbackScheduler) {
            return false;
        }
        this.scheduler = this.schedulerFactory.createFallbackScheduler(this.context);
        this.isUsingFallbackScheduler = true;
        return true;
    }

    private synchronized int getScheduleId(int i) {
        if (this.jobIdStart == null) {
            ApplicationInfo applicationInfo = null;
            try {
                applicationInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            } catch (Exception e) {
                Logger.error(e, "Failed to get application info.", new Object[0]);
            }
            if (applicationInfo == null || applicationInfo.metaData == null) {
                this.jobIdStart = Integer.valueOf(DEFAULT_JOB_ID_START);
            } else {
                this.jobIdStart = Integer.valueOf(applicationInfo.metaData.getInt(JOB_ID_START_KEY, DEFAULT_JOB_ID_START));
            }
        }
        return i + this.jobIdStart.intValue();
    }

    private static class DefaultSchedulerFactory implements SchedulerFactory {
        private DefaultSchedulerFactory() {
        }

        public Scheduler createScheduler(Context context) {
            if (Build.VERSION.SDK_INT >= 22) {
                return new AndroidJobScheduler();
            }
            return new AlarmScheduler();
        }

        public Scheduler createFallbackScheduler(Context context) {
            if (Build.VERSION.SDK_INT >= 22) {
                return new AndroidJobScheduler();
            }
            return new AlarmScheduler();
        }
    }
}
