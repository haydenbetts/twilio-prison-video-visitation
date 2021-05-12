package com.urbanairship.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.urbanairship.Predicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalActivityMonitor implements ActivityMonitor {
    private static final long BACKGROUND_DELAY_MS = 200;
    private static GlobalActivityMonitor singleton;
    /* access modifiers changed from: private */
    public final Runnable backgroundRunnable = new Runnable() {
        public void run() {
            boolean unused = GlobalActivityMonitor.this.isForeground = false;
            GlobalActivityMonitor.this.forwardingApplicationListener.onBackground(GlobalActivityMonitor.this.backgroundTime);
        }
    };
    /* access modifiers changed from: private */
    public long backgroundTime;
    private final ForwardingActivityListener forwardingActivityListener = new ForwardingActivityListener() {
        public void onActivityResumed(Activity activity) {
            GlobalActivityMonitor.this.resumedActivities.add(activity);
            super.onActivityResumed(activity);
        }

        public void onActivityPaused(Activity activity) {
            GlobalActivityMonitor.this.resumedActivities.remove(activity);
            super.onActivityPaused(activity);
        }

        public void onActivityStarted(Activity activity) {
            GlobalActivityMonitor.this.handler.removeCallbacks(GlobalActivityMonitor.this.backgroundRunnable);
            GlobalActivityMonitor.access$308(GlobalActivityMonitor.this);
            if (!GlobalActivityMonitor.this.isForeground) {
                boolean unused = GlobalActivityMonitor.this.isForeground = true;
                GlobalActivityMonitor.this.forwardingApplicationListener.onForeground(System.currentTimeMillis());
            }
            super.onActivityStarted(activity);
        }

        public void onActivityStopped(Activity activity) {
            if (GlobalActivityMonitor.this.startedActivities > 0) {
                GlobalActivityMonitor.access$310(GlobalActivityMonitor.this);
            }
            if (GlobalActivityMonitor.this.startedActivities == 0 && GlobalActivityMonitor.this.isForeground) {
                long unused = GlobalActivityMonitor.this.backgroundTime = System.currentTimeMillis() + 200;
                GlobalActivityMonitor.this.handler.postDelayed(GlobalActivityMonitor.this.backgroundRunnable, 200);
            }
            super.onActivityStopped(activity);
        }
    };
    /* access modifiers changed from: private */
    public final ForwardingApplicationListener forwardingApplicationListener = new ForwardingApplicationListener();
    /* access modifiers changed from: private */
    public final Handler handler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean isForeground;
    /* access modifiers changed from: private */
    public List<Activity> resumedActivities = new ArrayList();
    /* access modifiers changed from: private */
    public int startedActivities = 0;

    static /* synthetic */ int access$308(GlobalActivityMonitor globalActivityMonitor) {
        int i = globalActivityMonitor.startedActivities;
        globalActivityMonitor.startedActivities = i + 1;
        return i;
    }

    static /* synthetic */ int access$310(GlobalActivityMonitor globalActivityMonitor) {
        int i = globalActivityMonitor.startedActivities;
        globalActivityMonitor.startedActivities = i - 1;
        return i;
    }

    /* access modifiers changed from: package-private */
    public void registerListener(Context context) {
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(this.forwardingActivityListener);
    }

    /* access modifiers changed from: package-private */
    public void unregisterListener(Context context) {
        ((Application) context.getApplicationContext()).unregisterActivityLifecycleCallbacks(this.forwardingActivityListener);
    }

    public static GlobalActivityMonitor shared(Context context) {
        GlobalActivityMonitor globalActivityMonitor = singleton;
        if (globalActivityMonitor != null) {
            return globalActivityMonitor;
        }
        synchronized (GlobalActivityMonitor.class) {
            if (singleton == null) {
                GlobalActivityMonitor globalActivityMonitor2 = new GlobalActivityMonitor();
                singleton = globalActivityMonitor2;
                globalActivityMonitor2.registerListener(context);
            }
        }
        return singleton;
    }

    public void addActivityListener(ActivityListener activityListener) {
        this.forwardingActivityListener.addListener(activityListener);
    }

    public void removeActivityListener(ActivityListener activityListener) {
        this.forwardingActivityListener.removeListener(activityListener);
    }

    public void addApplicationListener(ApplicationListener applicationListener) {
        this.forwardingApplicationListener.addListener(applicationListener);
    }

    public void removeApplicationListener(ApplicationListener applicationListener) {
        this.forwardingApplicationListener.removeListener(applicationListener);
    }

    public boolean isAppForegrounded() {
        return this.isForeground;
    }

    public List<Activity> getResumedActivities() {
        return Collections.unmodifiableList(this.resumedActivities);
    }

    public List<Activity> getResumedActivities(Predicate<Activity> predicate) {
        ArrayList arrayList = new ArrayList();
        for (Activity next : this.resumedActivities) {
            if (predicate.apply(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
