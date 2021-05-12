package com.urbanairship.app;

import android.app.Activity;
import android.os.Bundle;
import com.urbanairship.Predicate;

public class FilteredActivityListener implements ActivityListener {
    private final Predicate<Activity> filter;
    private final ActivityListener listener;

    public FilteredActivityListener(ActivityListener activityListener, Predicate<Activity> predicate) {
        this.listener = activityListener;
        this.filter = predicate;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.filter.apply(activity)) {
            this.listener.onActivityCreated(activity, bundle);
        }
    }

    public void onActivityStarted(Activity activity) {
        if (this.filter.apply(activity)) {
            this.listener.onActivityStarted(activity);
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.filter.apply(activity)) {
            this.listener.onActivityResumed(activity);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.filter.apply(activity)) {
            this.listener.onActivityPaused(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.filter.apply(activity)) {
            this.listener.onActivityStopped(activity);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (this.filter.apply(activity)) {
            this.listener.onActivitySaveInstanceState(activity, bundle);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.filter.apply(activity)) {
            this.listener.onActivityDestroyed(activity);
        }
    }
}
