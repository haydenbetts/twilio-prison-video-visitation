package com.urbanairship.job;

import android.content.Context;
import android.os.Bundle;

interface Scheduler {
    void cancel(Context context, int i) throws SchedulerException;

    void reschedule(Context context, JobInfo jobInfo, int i, Bundle bundle) throws SchedulerException;

    void schedule(Context context, JobInfo jobInfo, int i) throws SchedulerException;
}
