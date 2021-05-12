package com.urbanairship;

import android.content.Context;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.ApplicationListener;
import com.urbanairship.app.SimpleApplicationListener;

public class ApplicationMetrics extends AirshipComponent {
    private static final String LAST_APP_VERSION_KEY = "com.urbanairship.application.metrics.APP_VERSION";
    private static final String LAST_OPEN_KEY = "com.urbanairship.application.metrics.LAST_OPEN";
    private final ActivityMonitor activityMonitor;
    private boolean appVersionUpdated = false;
    private final ApplicationListener listener;
    private final PreferenceDataStore preferenceDataStore;

    ApplicationMetrics(Context context, final PreferenceDataStore preferenceDataStore2, ActivityMonitor activityMonitor2) {
        super(context, preferenceDataStore2);
        this.preferenceDataStore = preferenceDataStore2;
        this.listener = new SimpleApplicationListener() {
            public void onForeground(long j) {
                preferenceDataStore2.put(ApplicationMetrics.LAST_OPEN_KEY, j);
            }
        };
        this.activityMonitor = activityMonitor2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        checkAppVersion();
        this.activityMonitor.addApplicationListener(this.listener);
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        this.activityMonitor.removeApplicationListener(this.listener);
    }

    public long getLastOpenTimeMillis() {
        return this.preferenceDataStore.getLong(LAST_OPEN_KEY, -1);
    }

    public boolean getAppVersionUpdated() {
        return this.appVersionUpdated;
    }

    public long getCurrentAppVersion() {
        return UAirship.getAppVersion();
    }

    private long getLastAppVersion() {
        return this.preferenceDataStore.getLong(LAST_APP_VERSION_KEY, -1);
    }

    private void checkAppVersion() {
        long appVersion = UAirship.getAppVersion();
        long lastAppVersion = getLastAppVersion();
        if (lastAppVersion > -1 && appVersion > lastAppVersion) {
            this.appVersionUpdated = true;
        }
        this.preferenceDataStore.put(LAST_APP_VERSION_KEY, appVersion);
    }
}
