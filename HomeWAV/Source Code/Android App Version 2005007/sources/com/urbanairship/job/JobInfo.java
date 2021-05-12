package com.urbanairship.job;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

public class JobInfo {
    public static final int ANALYTICS_EVENT_UPLOAD = 0;
    public static final int ANALYTICS_UPDATE_ADVERTISING_ID = 1;
    public static final int CHANNEL_UPDATE = 5;
    public static final int CHANNEL_UPDATE_ATTRIBUTES = 11;
    public static final int CHANNEL_UPDATE_PUSH_TOKEN = 4;
    public static final int CHANNEL_UPDATE_TAG_GROUPS = 6;
    private static final String EXTRA_AIRSHIP_COMPONENT = "EXTRA_AIRSHIP_COMPONENT";
    private static final String EXTRA_INITIAL_DELAY = "EXTRA_INITIAL_DELAY";
    private static final String EXTRA_IS_NETWORK_ACCESS_REQUIRED = "EXTRA_IS_NETWORK_ACCESS_REQUIRED";
    private static final String EXTRA_JOB_ACTION = "EXTRA_JOB_ACTION";
    private static final String EXTRA_JOB_EXTRAS = "EXTRA_JOB_EXTRAS";
    private static final String EXTRA_JOB_ID = "EXTRA_JOB_ID";
    private static final String EXTRA_PERSISTENT = "EXTRA_PERSISTENT";
    private static final int GENERATED_ID_OFFSET = 49;
    private static final int GENERATED_RANGE = 50;
    public static final int JOB_FINISHED = 0;
    public static final int JOB_RETRY = 1;
    public static final int NAMED_USER_UPDATE_ID = 2;
    public static final int NAMED_USER_UPDATE_TAG_GROUPS = 3;
    private static final String NEXT_GENERATED_ID_KEY = "next_generated_id";
    public static final int REMOTE_DATA_REFRESH = 10;
    public static final int RICH_PUSH_SYNC_MESSAGE_STATE = 9;
    public static final int RICH_PUSH_UPDATE_MESSAGES = 8;
    public static final int RICH_PUSH_UPDATE_USER = 7;
    private static final String SHARED_PREFERENCES_FILE = "com.urbanairship.job.ids";
    /* access modifiers changed from: private */
    public static final Object preferenceLock = new Object();
    static SharedPreferences sharedPreferences;
    private final String action;
    private final String airshipComponentName;
    private final JsonMap extras;
    private final int id;
    private final long initialDelay;
    private final boolean isNetworkAccessRequired;
    private final boolean persistent;

    @Retention(RetentionPolicy.SOURCE)
    public @interface JobId {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface JobResult {
    }

    private JobInfo(Builder builder) {
        this.action = builder.action == null ? "" : builder.action;
        this.airshipComponentName = builder.airshipComponentName;
        this.extras = builder.extras != null ? builder.extras : JsonMap.EMPTY_MAP;
        this.isNetworkAccessRequired = builder.isNetworkAccessRequired;
        this.initialDelay = builder.initialDelay;
        this.persistent = builder.persistent;
        this.id = builder.jobId;
    }

    static void resetGeneratedIds(Context context) {
        synchronized (preferenceLock) {
            if (sharedPreferences == null) {
                sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
            }
            sharedPreferences.edit().remove(NEXT_GENERATED_ID_KEY).apply();
        }
    }

    public String getAction() {
        return this.action;
    }

    public int getId() {
        return this.id;
    }

    public boolean isNetworkAccessRequired() {
        return this.isNetworkAccessRequired;
    }

    public long getInitialDelay() {
        return this.initialDelay;
    }

    public JsonMap getExtras() {
        return this.extras;
    }

    public String getAirshipComponentName() {
        return this.airshipComponentName;
    }

    public boolean isPersistent() {
        return this.persistent;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_AIRSHIP_COMPONENT, this.airshipComponentName);
        bundle.putString(EXTRA_JOB_ACTION, this.action);
        bundle.putInt(EXTRA_JOB_ID, this.id);
        bundle.putString(EXTRA_JOB_EXTRAS, this.extras.toString());
        bundle.putBoolean(EXTRA_IS_NETWORK_ACCESS_REQUIRED, this.isNetworkAccessRequired);
        bundle.putLong(EXTRA_INITIAL_DELAY, this.initialDelay);
        bundle.putBoolean(EXTRA_PERSISTENT, this.persistent);
        return bundle;
    }

    public PersistableBundle toPersistableBundle() {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(EXTRA_AIRSHIP_COMPONENT, this.airshipComponentName);
        persistableBundle.putString(EXTRA_JOB_ACTION, this.action);
        persistableBundle.putInt(EXTRA_JOB_ID, this.id);
        persistableBundle.putString(EXTRA_JOB_EXTRAS, this.extras.toString());
        persistableBundle.putBoolean(EXTRA_IS_NETWORK_ACCESS_REQUIRED, this.isNetworkAccessRequired);
        persistableBundle.putLong(EXTRA_INITIAL_DELAY, this.initialDelay);
        persistableBundle.putBoolean(EXTRA_PERSISTENT, this.persistent);
        return persistableBundle;
    }

    public static JobInfo fromBundle(Bundle bundle) {
        if (bundle == null) {
            return new Builder().build();
        }
        try {
            Builder persistent2 = new Builder().setAction(bundle.getString(EXTRA_JOB_ACTION)).setInitialDelay(bundle.getLong(EXTRA_INITIAL_DELAY, 0), TimeUnit.MILLISECONDS).setExtras(JsonValue.parseString(bundle.getString(EXTRA_JOB_EXTRAS)).optMap()).setAirshipComponent(bundle.getString(EXTRA_AIRSHIP_COMPONENT)).setNetworkAccessRequired(bundle.getBoolean(EXTRA_IS_NETWORK_ACCESS_REQUIRED)).setPersistent(bundle.getBoolean(EXTRA_PERSISTENT));
            persistent2.setId(bundle.getInt(EXTRA_JOB_ID, 0));
            return persistent2.build();
        } catch (JsonException | IllegalArgumentException e) {
            Logger.error(e, "Failed to parse job from bundle.", new Object[0]);
            return null;
        }
    }

    static JobInfo fromPersistableBundle(PersistableBundle persistableBundle) {
        if (persistableBundle == null) {
            return new Builder().build();
        }
        try {
            Builder persistent2 = new Builder().setAction(persistableBundle.getString(EXTRA_JOB_ACTION)).setInitialDelay(persistableBundle.getLong(EXTRA_INITIAL_DELAY, 0), TimeUnit.MILLISECONDS).setExtras(JsonValue.parseString(persistableBundle.getString(EXTRA_JOB_EXTRAS)).optMap()).setAirshipComponent(persistableBundle.getString(EXTRA_AIRSHIP_COMPONENT)).setNetworkAccessRequired(persistableBundle.getBoolean(EXTRA_IS_NETWORK_ACCESS_REQUIRED)).setPersistent(persistableBundle.getBoolean(EXTRA_PERSISTENT, false));
            persistent2.setId(persistableBundle.getInt(EXTRA_JOB_ID, 0));
            return persistent2.build();
        } catch (Exception e) {
            Logger.error(e, "Failed to parse job from bundle.", new Object[0]);
            return null;
        }
    }

    public String toString() {
        return "JobInfo{action=" + this.action + ", id=" + this.id + ", extras='" + this.extras + '\'' + ", airshipComponentName='" + this.airshipComponentName + '\'' + ", isNetworkAccessRequired=" + this.isNetworkAccessRequired + ", initialDelay=" + this.initialDelay + ", persistent=" + this.persistent + '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String action;
        /* access modifiers changed from: private */
        public String airshipComponentName;
        /* access modifiers changed from: private */
        public JsonMap extras;
        /* access modifiers changed from: private */
        public long initialDelay;
        /* access modifiers changed from: private */
        public boolean isNetworkAccessRequired;
        /* access modifiers changed from: private */
        public int jobId;
        /* access modifiers changed from: private */
        public boolean persistent;

        private Builder() {
            this.jobId = -1;
        }

        public Builder setAction(String str) {
            this.action = str;
            return this;
        }

        public Builder setId(int i) {
            this.jobId = i;
            return this;
        }

        public Builder generateUniqueId(Context context) {
            synchronized (JobInfo.preferenceLock) {
                if (JobInfo.sharedPreferences == null) {
                    JobInfo.sharedPreferences = context.getSharedPreferences(JobInfo.SHARED_PREFERENCES_FILE, 0);
                }
                int i = JobInfo.sharedPreferences.getInt(JobInfo.NEXT_GENERATED_ID_KEY, 0);
                JobInfo.sharedPreferences.edit().putInt(JobInfo.NEXT_GENERATED_ID_KEY, (i + 1) % 50).apply();
                this.jobId = i + 49;
            }
            return this;
        }

        public Builder setNetworkAccessRequired(boolean z) {
            this.isNetworkAccessRequired = z;
            return this;
        }

        public Builder setAirshipComponent(Class<? extends AirshipComponent> cls) {
            this.airshipComponentName = cls.getName();
            return this;
        }

        public Builder setInitialDelay(long j, TimeUnit timeUnit) {
            this.initialDelay = timeUnit.toMillis(j);
            return this;
        }

        public Builder setPersistent(boolean z) {
            this.persistent = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAirshipComponent(String str) {
            this.airshipComponentName = str;
            return this;
        }

        public Builder setExtras(JsonMap jsonMap) {
            this.extras = jsonMap;
            return this;
        }

        public JobInfo build() {
            Checks.checkNotNull(this.action, "Missing action.");
            return new JobInfo(this);
        }
    }
}
