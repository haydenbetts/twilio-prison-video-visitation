package com.urbanairship;

import android.content.Context;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonMap;
import java.util.concurrent.Executor;

public abstract class AirshipComponent {
    private static final String ENABLE_KEY_PREFIX = "airshipComponent.enable_";
    private final Context context;
    private final PreferenceDataStore dataStore;
    /* access modifiers changed from: private */
    public final String enableKey;
    private final Executor jobExecutor = AirshipExecutors.newSerialExecutor();

    public int getComponentGroup() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public void onAirshipReady(UAirship uAirship) {
    }

    /* access modifiers changed from: protected */
    public void onComponentEnableChange(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void onDataCollectionEnabledChanged(boolean z) {
    }

    public void onNewConfig(JsonMap jsonMap) {
    }

    public int onPerformJob(UAirship uAirship, JobInfo jobInfo) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
    }

    public AirshipComponent(Context context2, PreferenceDataStore preferenceDataStore) {
        this.context = context2.getApplicationContext();
        this.dataStore = preferenceDataStore;
        this.enableKey = ENABLE_KEY_PREFIX + getClass().getName();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.dataStore.addListener(new PreferenceDataStore.PreferenceChangeListener() {
            public void onPreferenceChange(String str) {
                if (str.equals(AirshipComponent.this.enableKey)) {
                    AirshipComponent airshipComponent = AirshipComponent.this;
                    airshipComponent.onComponentEnableChange(airshipComponent.isComponentEnabled());
                } else if (str.equals(UAirship.DATA_COLLECTION_ENABLED_KEY)) {
                    AirshipComponent airshipComponent2 = AirshipComponent.this;
                    airshipComponent2.onDataCollectionEnabledChanged(airshipComponent2.isDataCollectionEnabled());
                }
            }
        });
    }

    public Executor getJobExecutor(JobInfo jobInfo) {
        return this.jobExecutor;
    }

    public void setComponentEnabled(boolean z) {
        this.dataStore.put(this.enableKey, z);
    }

    public boolean isComponentEnabled() {
        return this.dataStore.getBoolean(this.enableKey, true);
    }

    /* access modifiers changed from: protected */
    public PreferenceDataStore getDataStore() {
        return this.dataStore;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public boolean isDataCollectionEnabled() {
        return this.dataStore.getBoolean(UAirship.DATA_COLLECTION_ENABLED_KEY, true);
    }
}
