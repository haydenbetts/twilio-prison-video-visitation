package com.urbanairship.job;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import com.urbanairship.Logger;
import com.urbanairship.util.ManifestUtils;

class AndroidJobScheduler implements Scheduler {
    private static final long DEFAULT_DELAY_MS = 10000;
    private static final long INITIAL_RETRY_MS = 30000;
    private JobScheduler scheduler;

    AndroidJobScheduler() {
    }

    public void cancel(Context context, int i) throws SchedulerException {
        JobScheduler scheduler2 = getScheduler(context);
        if (scheduler2 != null) {
            scheduler2.cancel(i);
        }
    }

    public void reschedule(Context context, JobInfo jobInfo, int i, Bundle bundle) throws SchedulerException {
        scheduleJob(context, jobInfo, i, 30000);
    }

    public void schedule(Context context, JobInfo jobInfo, int i) throws SchedulerException {
        if (jobInfo.isNetworkAccessRequired() || jobInfo.getInitialDelay() > 0) {
            scheduleJob(context, jobInfo, i, jobInfo.getInitialDelay());
            return;
        }
        scheduleJob(context, jobInfo, i, 10000);
    }

    private void scheduleJob(Context context, JobInfo jobInfo, int i, long j) throws SchedulerException {
        JobScheduler scheduler2 = getScheduler(context);
        if (scheduler2 != null) {
            try {
                if (scheduler2.schedule(createAndroidJobInfo(context, jobInfo, i, j)) != 0) {
                    Logger.verbose("AndroidJobScheduler: Scheduling jobInfo: %s scheduleId: %s", jobInfo, Integer.valueOf(i));
                    return;
                }
                throw new SchedulerException("Android JobScheduler failed to schedule job.");
            } catch (Exception e) {
                throw new SchedulerException("Android JobScheduler failed to schedule job: ", e);
            }
        }
    }

    private JobInfo createAndroidJobInfo(Context context, JobInfo jobInfo, int i, long j) {
        JobInfo.Builder extras = new JobInfo.Builder(i, new ComponentName(context, AndroidJobService.class)).setExtras(jobInfo.toPersistableBundle());
        if (j > 0) {
            extras.setMinimumLatency(j);
        }
        if (jobInfo.isPersistent() && ManifestUtils.isPermissionGranted("android.permission.RECEIVE_BOOT_COMPLETED")) {
            extras.setPersisted(true);
        }
        if (jobInfo.isNetworkAccessRequired()) {
            extras.setRequiredNetworkType(1);
        }
        return extras.build();
    }

    private JobScheduler getScheduler(Context context) {
        if (this.scheduler == null) {
            this.scheduler = (JobScheduler) context.getSystemService("jobscheduler");
        }
        return this.scheduler;
    }
}
