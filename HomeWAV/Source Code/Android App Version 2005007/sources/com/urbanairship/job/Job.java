package com.urbanairship.job;

import com.urbanairship.AirshipComponent;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.util.UAStringUtil;
import java.util.concurrent.Executor;

class Job implements Runnable {
    private static final long AIRSHIP_WAIT_TIME_MS = 5000;
    static final Executor EXECUTOR = AirshipExecutors.newSerialExecutor();
    /* access modifiers changed from: private */
    public final Callback callback;
    /* access modifiers changed from: private */
    public final JobInfo jobInfo;

    public interface Callback {
        void onFinish(Job job, int i);
    }

    private Job(Builder builder) {
        this.jobInfo = builder.jobInfo;
        this.callback = builder.callback;
    }

    public void run() {
        final UAirship waitForTakeOff = UAirship.waitForTakeOff(5000);
        if (waitForTakeOff == null) {
            Logger.error("JobDispatcher - UAirship not ready. Rescheduling job: %s", this.jobInfo);
            Callback callback2 = this.callback;
            if (callback2 != null) {
                callback2.onFinish(this, 1);
                return;
            }
            return;
        }
        final AirshipComponent findAirshipComponent = findAirshipComponent(waitForTakeOff, this.jobInfo.getAirshipComponentName());
        if (findAirshipComponent == null) {
            Logger.error("JobDispatcher - Unavailable to find airship components for jobInfo: %s", this.jobInfo);
            Callback callback3 = this.callback;
            if (callback3 != null) {
                callback3.onFinish(this, 0);
            }
        } else if (!findAirshipComponent.isComponentEnabled()) {
            Logger.debug("JobDispatcher - Component disabled. Dropping jobInfo: %s", this.jobInfo);
            Callback callback4 = this.callback;
            if (callback4 != null) {
                callback4.onFinish(this, 0);
            }
        } else {
            findAirshipComponent.getJobExecutor(this.jobInfo).execute(new Runnable() {
                public void run() {
                    int onPerformJob = findAirshipComponent.onPerformJob(waitForTakeOff, Job.this.jobInfo);
                    Logger.verbose("Job - Finished: %s with result: %s", Job.this.jobInfo, Integer.valueOf(onPerformJob));
                    if (Job.this.callback != null) {
                        Job.this.callback.onFinish(Job.this, onPerformJob);
                    }
                }
            });
        }
    }

    private AirshipComponent findAirshipComponent(UAirship uAirship, String str) {
        if (UAStringUtil.isEmpty(str)) {
            return null;
        }
        for (AirshipComponent next : uAirship.getComponents()) {
            if (next.getClass().getName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public static Builder newBuilder(JobInfo jobInfo2) {
        return new Builder(jobInfo2);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Callback callback;
        /* access modifiers changed from: private */
        public final JobInfo jobInfo;

        Builder(JobInfo jobInfo2) {
            this.jobInfo = jobInfo2;
        }

        /* access modifiers changed from: package-private */
        public Builder setCallback(Callback callback2) {
            this.callback = callback2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Job build() {
            return new Job(this);
        }
    }
}
