package com.urbanairship.iam;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import com.urbanairship.Logger;
import com.urbanairship.Predicate;
import com.urbanairship.app.ActivityListener;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.ApplicationListener;
import com.urbanairship.app.FilteredActivityListener;
import com.urbanairship.app.ForwardingActivityListener;
import com.urbanairship.app.GlobalActivityMonitor;
import com.urbanairship.util.ManifestUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InAppActivityMonitor implements ActivityMonitor {
    public static final String EXCLUDE_FROM_AUTO_SHOW = "com.urbanairship.push.iam.EXCLUDE_FROM_AUTO_SHOW";
    private static InAppActivityMonitor shared;
    /* access modifiers changed from: private */
    public final Predicate<Activity> activityPredicate;
    /* access modifiers changed from: private */
    public final Set<Class> allowedActivities = new HashSet();
    private final FilteredActivityListener filteredActivityListener;
    private final ForwardingActivityListener forwardingActivityListener;
    private final ActivityMonitor globalActivityMonitor;
    /* access modifiers changed from: private */
    public final Set<Class> ignoredActivities = new HashSet();

    private InAppActivityMonitor(ActivityMonitor activityMonitor) {
        AnonymousClass1 r0 = new Predicate<Activity>() {
            public boolean apply(Activity activity) {
                if (InAppActivityMonitor.this.allowedActivities.contains(activity.getClass())) {
                    return true;
                }
                if (InAppActivityMonitor.this.ignoredActivities.contains(activity.getClass())) {
                    return false;
                }
                if (InAppActivityMonitor.this.shouldIgnoreActivity(activity)) {
                    InAppActivityMonitor.this.ignoredActivities.add(activity.getClass());
                    return false;
                }
                InAppActivityMonitor.this.allowedActivities.add(activity.getClass());
                return true;
            }
        };
        this.activityPredicate = r0;
        this.globalActivityMonitor = activityMonitor;
        ForwardingActivityListener forwardingActivityListener2 = new ForwardingActivityListener();
        this.forwardingActivityListener = forwardingActivityListener2;
        this.filteredActivityListener = new FilteredActivityListener(forwardingActivityListener2, r0);
    }

    public static InAppActivityMonitor shared(Context context) {
        if (shared == null) {
            synchronized (InAppActivityMonitor.class) {
                if (shared == null) {
                    InAppActivityMonitor inAppActivityMonitor = new InAppActivityMonitor(GlobalActivityMonitor.shared(context));
                    shared = inAppActivityMonitor;
                    inAppActivityMonitor.init();
                }
            }
        }
        return shared;
    }

    private void init() {
        this.globalActivityMonitor.addActivityListener(this.filteredActivityListener);
    }

    public void addActivityListener(ActivityListener activityListener) {
        this.forwardingActivityListener.addListener(activityListener);
    }

    public void removeActivityListener(ActivityListener activityListener) {
        this.forwardingActivityListener.removeListener(activityListener);
    }

    public void addApplicationListener(ApplicationListener applicationListener) {
        this.globalActivityMonitor.addApplicationListener(applicationListener);
    }

    public void removeApplicationListener(ApplicationListener applicationListener) {
        this.globalActivityMonitor.removeApplicationListener(applicationListener);
    }

    public boolean isAppForegrounded() {
        return this.globalActivityMonitor.isAppForegrounded();
    }

    public List<Activity> getResumedActivities() {
        return this.globalActivityMonitor.getResumedActivities(this.activityPredicate);
    }

    public List<Activity> getResumedActivities(final Predicate<Activity> predicate) {
        return this.globalActivityMonitor.getResumedActivities(new Predicate<Activity>() {
            public boolean apply(Activity activity) {
                return InAppActivityMonitor.this.activityPredicate.apply(activity) && predicate.apply(activity);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean shouldIgnoreActivity(Activity activity) {
        ActivityInfo activityInfo = ManifestUtils.getActivityInfo(activity.getClass());
        if (activityInfo == null || activityInfo.metaData == null || !activityInfo.metaData.getBoolean(EXCLUDE_FROM_AUTO_SHOW, false)) {
            return false;
        }
        Logger.verbose("InAppActivityMonitor - Activity contains metadata to exclude it from auto showing an in-app message", new Object[0]);
        return true;
    }
}
