package com.urbanairship.analytics.data;

import android.content.Context;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.analytics.Event;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.GlobalActivityMonitor;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.job.JobDispatcher;
import com.urbanairship.job.JobInfo;
import java.util.concurrent.TimeUnit;

public class EventManager {
    public static final String ACTION_SEND = "ACTION_SEND";
    private static final long HIGH_PRIORITY_BATCH_DELAY = 0;
    static final String LAST_SEND_KEY = "com.urbanairship.analytics.LAST_SEND";
    private static final long LOW_PRIORITY_BATCH_DELAY = 30000;
    private static final int MAX_BATCH_EVENT_COUNT = 500;
    static final String MAX_BATCH_SIZE_KEY = "com.urbanairship.analytics.MAX_BATCH_SIZE";
    static final String MAX_TOTAL_DB_SIZE_KEY = "com.urbanairship.analytics.MAX_TOTAL_DB_SIZE";
    static final String MIN_BATCH_INTERVAL_KEY = "com.urbanairship.analytics.MIN_BATCH_INTERVAL";
    private static final long MULTIPLE_BATCH_DELAY = 1000;
    private static final long NORMAL_PRIORITY_BATCH_DELAY = 10000;
    static final String SCHEDULED_SEND_TIME = "com.urbanairship.analytics.SCHEDULED_SEND_TIME";
    private final ActivityMonitor activityMonitor;
    private final EventApiClient apiClient;
    private final Object eventLock;
    private final EventResolver eventResolver;
    private boolean isScheduled;
    private final JobDispatcher jobDispatcher;
    private final PreferenceDataStore preferenceDataStore;
    private final AirshipRuntimeConfig runtimeConfig;

    public EventManager(Context context, PreferenceDataStore preferenceDataStore2, AirshipRuntimeConfig airshipRuntimeConfig) {
        this(preferenceDataStore2, airshipRuntimeConfig, JobDispatcher.shared(context), GlobalActivityMonitor.shared(context), new EventResolver(context), new EventApiClient(airshipRuntimeConfig));
    }

    EventManager(PreferenceDataStore preferenceDataStore2, AirshipRuntimeConfig airshipRuntimeConfig, JobDispatcher jobDispatcher2, ActivityMonitor activityMonitor2, EventResolver eventResolver2, EventApiClient eventApiClient) {
        this.eventLock = new Object();
        this.preferenceDataStore = preferenceDataStore2;
        this.runtimeConfig = airshipRuntimeConfig;
        this.jobDispatcher = jobDispatcher2;
        this.activityMonitor = activityMonitor2;
        this.eventResolver = eventResolver2;
        this.apiClient = eventApiClient;
    }

    public void scheduleEventUpload(long j, TimeUnit timeUnit) {
        long millis = timeUnit.toMillis(j);
        Logger.verbose("EventManager - Requesting to schedule event upload with delay %s ms.", Long.valueOf(millis));
        long currentTimeMillis = System.currentTimeMillis() + millis;
        long j2 = this.preferenceDataStore.getLong(SCHEDULED_SEND_TIME, 0);
        if (!this.isScheduled || j2 > currentTimeMillis || j2 < System.currentTimeMillis()) {
            Logger.verbose("EventManager - Scheduling upload in %s ms.", Long.valueOf(millis));
            this.jobDispatcher.dispatch(JobInfo.newBuilder().setId(0).setAction(ACTION_SEND).setNetworkAccessRequired(true).setAirshipComponent((Class<? extends AirshipComponent>) Analytics.class).setInitialDelay(millis, TimeUnit.MILLISECONDS).build());
            this.preferenceDataStore.put(SCHEDULED_SEND_TIME, currentTimeMillis);
            this.isScheduled = true;
            return;
        }
        Logger.verbose("EventManager - Event upload already scheduled for an earlier time.", new Object[0]);
    }

    public void addEvent(Event event, String str) {
        synchronized (this.eventLock) {
            this.eventResolver.insertEvent(event, str);
            this.eventResolver.trimDatabase(this.preferenceDataStore.getInt(MAX_TOTAL_DB_SIZE_KEY, 5242880));
        }
        int priority = event.getPriority();
        if (priority == 1) {
            scheduleEventUpload(Math.max(getNextSendDelay(), 10000), TimeUnit.MILLISECONDS);
        } else if (priority == 2) {
            scheduleEventUpload(0, TimeUnit.MILLISECONDS);
        } else if (this.activityMonitor.isAppForegrounded()) {
            scheduleEventUpload(Math.max(getNextSendDelay(), 30000), TimeUnit.MILLISECONDS);
        } else {
            scheduleEventUpload(Math.max(Math.max(this.runtimeConfig.getConfigOptions().backgroundReportingIntervalMS - (System.currentTimeMillis() - this.preferenceDataStore.getLong(LAST_SEND_KEY, 0)), getNextSendDelay()), 30000), TimeUnit.MILLISECONDS);
        }
    }

    public void deleteEvents() {
        synchronized (this.eventLock) {
            this.eventResolver.deleteAllEvents();
        }
    }

    private long getNextSendDelay() {
        return Math.max((this.preferenceDataStore.getLong(LAST_SEND_KEY, 0) + ((long) this.preferenceDataStore.getInt(MIN_BATCH_INTERVAL_KEY, 60000))) - System.currentTimeMillis(), 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004b, code lost:
        if (r4.isEmpty() == false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004e, code lost:
        r10 = r9.apiClient.sendEvents(r4.values(), r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0058, code lost:
        if (r10 == null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0060, code lost:
        if (r10.getStatus() == 200) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0063, code lost:
        com.urbanairship.Logger.debug("EventManager - Analytic events uploaded.", new java.lang.Object[0]);
        r1 = r9.eventLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r9.eventResolver.deleteEvents(r4.keySet());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0076, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0077, code lost:
        r9.preferenceDataStore.put(MAX_TOTAL_DB_SIZE_KEY, r10.getMaxTotalSize());
        r9.preferenceDataStore.put(MAX_BATCH_SIZE_KEY, r10.getMaxBatchSize());
        r9.preferenceDataStore.put(MIN_BATCH_INTERVAL_KEY, r10.getMinBatchInterval());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009d, code lost:
        if ((r2 - r4.size()) <= 0) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009f, code lost:
        scheduleEventUpload(1000, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a6, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00aa, code lost:
        com.urbanairship.Logger.debug("EventManager - Analytic upload failed.", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b1, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean uploadEvents(java.util.Map<java.lang.String, java.lang.String> r10) {
        /*
            r9 = this;
            r0 = 0
            r9.isScheduled = r0
            com.urbanairship.PreferenceDataStore r1 = r9.preferenceDataStore
            java.lang.String r2 = "com.urbanairship.analytics.LAST_SEND"
            long r3 = java.lang.System.currentTimeMillis()
            r1.put((java.lang.String) r2, (long) r3)
            java.lang.Object r1 = r9.eventLock
            monitor-enter(r1)
            com.urbanairship.analytics.data.EventResolver r2 = r9.eventResolver     // Catch:{ all -> 0x00b2 }
            int r2 = r2.getEventCount()     // Catch:{ all -> 0x00b2 }
            r3 = 1
            if (r2 > 0) goto L_0x0023
            java.lang.String r10 = "EventManager - No events to send."
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x00b2 }
            com.urbanairship.Logger.debug(r10, r0)     // Catch:{ all -> 0x00b2 }
            monitor-exit(r1)     // Catch:{ all -> 0x00b2 }
            return r3
        L_0x0023:
            com.urbanairship.analytics.data.EventResolver r4 = r9.eventResolver     // Catch:{ all -> 0x00b2 }
            int r4 = r4.getDatabaseSize()     // Catch:{ all -> 0x00b2 }
            int r4 = r4 / r2
            int r4 = java.lang.Math.max(r3, r4)     // Catch:{ all -> 0x00b2 }
            r5 = 500(0x1f4, float:7.0E-43)
            com.urbanairship.PreferenceDataStore r6 = r9.preferenceDataStore     // Catch:{ all -> 0x00b2 }
            java.lang.String r7 = "com.urbanairship.analytics.MAX_BATCH_SIZE"
            r8 = 512000(0x7d000, float:7.17465E-40)
            int r6 = r6.getInt(r7, r8)     // Catch:{ all -> 0x00b2 }
            int r6 = r6 / r4
            int r4 = java.lang.Math.min(r5, r6)     // Catch:{ all -> 0x00b2 }
            com.urbanairship.analytics.data.EventResolver r5 = r9.eventResolver     // Catch:{ all -> 0x00b2 }
            java.util.Map r4 = r5.getEvents(r4)     // Catch:{ all -> 0x00b2 }
            monitor-exit(r1)     // Catch:{ all -> 0x00b2 }
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L_0x004e
            return r3
        L_0x004e:
            com.urbanairship.analytics.data.EventApiClient r1 = r9.apiClient
            java.util.Collection r5 = r4.values()
            com.urbanairship.analytics.data.EventResponse r10 = r1.sendEvents(r5, r10)
            if (r10 == 0) goto L_0x00aa
            int r1 = r10.getStatus()
            r5 = 200(0xc8, float:2.8E-43)
            if (r1 == r5) goto L_0x0063
            goto L_0x00aa
        L_0x0063:
            java.lang.String r1 = "EventManager - Analytic events uploaded."
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.urbanairship.Logger.debug(r1, r0)
            java.lang.Object r1 = r9.eventLock
            monitor-enter(r1)
            com.urbanairship.analytics.data.EventResolver r0 = r9.eventResolver     // Catch:{ all -> 0x00a7 }
            java.util.Set r5 = r4.keySet()     // Catch:{ all -> 0x00a7 }
            r0.deleteEvents(r5)     // Catch:{ all -> 0x00a7 }
            monitor-exit(r1)     // Catch:{ all -> 0x00a7 }
            com.urbanairship.PreferenceDataStore r0 = r9.preferenceDataStore
            java.lang.String r1 = "com.urbanairship.analytics.MAX_TOTAL_DB_SIZE"
            int r5 = r10.getMaxTotalSize()
            r0.put((java.lang.String) r1, (int) r5)
            com.urbanairship.PreferenceDataStore r0 = r9.preferenceDataStore
            java.lang.String r1 = "com.urbanairship.analytics.MAX_BATCH_SIZE"
            int r5 = r10.getMaxBatchSize()
            r0.put((java.lang.String) r1, (int) r5)
            com.urbanairship.PreferenceDataStore r0 = r9.preferenceDataStore
            java.lang.String r1 = "com.urbanairship.analytics.MIN_BATCH_INTERVAL"
            int r10 = r10.getMinBatchInterval()
            r0.put((java.lang.String) r1, (int) r10)
            int r10 = r4.size()
            int r2 = r2 - r10
            if (r2 <= 0) goto L_0x00a6
            r0 = 1000(0x3e8, double:4.94E-321)
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS
            r9.scheduleEventUpload(r0, r10)
        L_0x00a6:
            return r3
        L_0x00a7:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00a7 }
            throw r10
        L_0x00aa:
            java.lang.String r10 = "EventManager - Analytic upload failed."
            java.lang.Object[] r1 = new java.lang.Object[r0]
            com.urbanairship.Logger.debug(r10, r1)
            return r0
        L_0x00b2:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00b2 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.analytics.data.EventManager.uploadEvents(java.util.Map):boolean");
    }
}
