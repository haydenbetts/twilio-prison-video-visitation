package com.urbanairship.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.urbanairship.Logger;
import com.urbanairship.job.Job;

public class AndroidJobService extends JobService {
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        JobInfo fromPersistableBundle = JobInfo.fromPersistableBundle(jobParameters.getExtras());
        if (fromPersistableBundle == null) {
            Logger.error("AndroidJobService: Failed to parse jobInfo.", new Object[0]);
            return false;
        }
        Job build = Job.newBuilder(fromPersistableBundle).setCallback(new Job.Callback() {
            public void onFinish(Job job, int i) {
                AndroidJobService androidJobService = AndroidJobService.this;
                JobParameters jobParameters = jobParameters;
                boolean z = true;
                if (i != 1) {
                    z = false;
                }
                androidJobService.jobFinished(jobParameters, z);
            }
        }).build();
        Logger.verbose("AndroidJobService - Running job: %s", fromPersistableBundle);
        Job.EXECUTOR.execute(build);
        return true;
    }
}
