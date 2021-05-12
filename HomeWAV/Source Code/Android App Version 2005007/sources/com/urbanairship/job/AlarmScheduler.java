package com.urbanairship.job;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.urbanairship.Logger;

class AlarmScheduler implements Scheduler {
    private static final long DEFAULT_MAX_BACK_OFF_TIME_MS = 5120000;
    private static final long DEFAULT_STARTING_BACK_OFF_TIME_MS = 10000;
    static final String EXTRA_BACKOFF_DELAY = "EXTRA_BACKOFF_DELAY";

    AlarmScheduler() {
    }

    public void cancel(Context context, int i) {
        PendingIntent service = PendingIntent.getService(context, i, AirshipService.createIntent(context, (JobInfo) null, (Bundle) null), 536870912);
        if (service != null) {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(service);
            service.cancel();
        }
    }

    public void schedule(Context context, JobInfo jobInfo, int i) throws SchedulerException {
        long initialDelay = jobInfo.getInitialDelay();
        if (initialDelay <= 0) {
            initialDelay = 10000;
        }
        scheduleIntent(context, jobInfo, i, initialDelay);
    }

    public void reschedule(Context context, JobInfo jobInfo, int i, Bundle bundle) throws SchedulerException {
        long j;
        long j2 = bundle != null ? bundle.getLong(EXTRA_BACKOFF_DELAY, 0) : 0;
        if (j2 <= 0) {
            j = 10000;
        } else {
            j = Math.min(j2 * 2, DEFAULT_MAX_BACK_OFF_TIME_MS);
        }
        scheduleIntent(context, jobInfo, i, j);
    }

    private void scheduleIntent(Context context, JobInfo jobInfo, int i, long j) throws SchedulerException {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_BACKOFF_DELAY, j);
        Intent createIntent = AirshipService.createIntent(context, jobInfo, bundle);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent service = PendingIntent.getService(context, i, createIntent, 134217728);
        try {
            Logger.verbose("AlarmScheduler - Scheduling jobInfo: %s with delay: %s", jobInfo, Long.valueOf(j));
            alarmManager.set(3, SystemClock.elapsedRealtime() + j, service);
        } catch (RuntimeException e) {
            Logger.error(e, "AlarmScheduler - Failed to schedule intent %s", createIntent.getAction());
            throw new SchedulerException("AlarmScheduler - Failed to schedule intent " + createIntent.getAction(), e);
        }
    }
}
