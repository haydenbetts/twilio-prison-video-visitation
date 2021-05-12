package com.urbanairship.app;

import android.app.Activity;
import com.urbanairship.Predicate;
import java.util.List;

public interface ActivityMonitor {
    void addActivityListener(ActivityListener activityListener);

    void addApplicationListener(ApplicationListener applicationListener);

    List<Activity> getResumedActivities();

    List<Activity> getResumedActivities(Predicate<Activity> predicate);

    boolean isAppForegrounded();

    void removeActivityListener(ActivityListener activityListener);

    void removeApplicationListener(ApplicationListener applicationListener);
}
