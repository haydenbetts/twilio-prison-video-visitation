package com.urbanairship.app;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForwardingActivityListener implements ActivityListener {
    private final List<ActivityListener> listeners = new ArrayList();

    public void addListener(ActivityListener activityListener) {
        synchronized (this.listeners) {
            this.listeners.add(activityListener);
        }
    }

    public void removeListener(ActivityListener activityListener) {
        synchronized (this.listeners) {
            this.listeners.remove(activityListener);
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ActivityListener) it.next()).onActivityCreated(activity, bundle);
        }
    }

    public void onActivityStarted(Activity activity) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ActivityListener) it.next()).onActivityStarted(activity);
        }
    }

    public void onActivityResumed(Activity activity) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ActivityListener) it.next()).onActivityResumed(activity);
        }
    }

    public void onActivityPaused(Activity activity) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ActivityListener) it.next()).onActivityPaused(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ActivityListener) it.next()).onActivityStopped(activity);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ActivityListener) it.next()).onActivitySaveInstanceState(activity, bundle);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ActivityListener) it.next()).onActivityDestroyed(activity);
        }
    }
}
