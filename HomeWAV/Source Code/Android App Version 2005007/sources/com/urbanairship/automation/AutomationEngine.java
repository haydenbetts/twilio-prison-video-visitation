package com.urbanairship.automation;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.SparseArray;
import com.urbanairship.CancelableOperation;
import com.urbanairship.Logger;
import com.urbanairship.PendingResult;
import com.urbanairship.Predicate;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.analytics.AnalyticsListener;
import com.urbanairship.analytics.CustomEvent;
import com.urbanairship.analytics.location.RegionEvent;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.ApplicationListener;
import com.urbanairship.automation.AutomationDriver;
import com.urbanairship.automation.alarms.AlarmOperationScheduler;
import com.urbanairship.automation.alarms.OperationScheduler;
import com.urbanairship.automation.storage.AutomationDao;
import com.urbanairship.automation.storage.AutomationDatabase;
import com.urbanairship.automation.storage.FullSchedule;
import com.urbanairship.automation.storage.LegacyDataMigrator;
import com.urbanairship.automation.storage.ScheduleEntity;
import com.urbanairship.automation.storage.TriggerEntity;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.iam.InAppActivityMonitor;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.reactive.Function;
import com.urbanairship.reactive.Observable;
import com.urbanairship.reactive.Scheduler;
import com.urbanairship.reactive.Schedulers;
import com.urbanairship.reactive.Subject;
import com.urbanairship.reactive.Subscriber;
import com.urbanairship.reactive.Subscription;
import com.urbanairship.util.AirshipHandlerThread;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AutomationEngine {
    private final List<Integer> COMPOUND_TRIGGER_TYPES;
    /* access modifiers changed from: private */
    public long SCHEDULE_LIMIT;
    private final Comparator<FullSchedule> SCHEDULE_PRIORITY_COMPARATOR;
    private final ActivityMonitor activityMonitor;
    private final Analytics analytics;
    private final AnalyticsListener analyticsListener;
    private final ApplicationListener applicationListener;
    /* access modifiers changed from: private */
    public Handler backgroundHandler;
    /* access modifiers changed from: private */
    public Scheduler backgroundScheduler;
    HandlerThread backgroundThread;
    private Subscription compoundTriggerSubscription;
    /* access modifiers changed from: private */
    public final AutomationDao dao;
    /* access modifiers changed from: private */
    public AutomationDriver driver;
    /* access modifiers changed from: private */
    public final AtomicBoolean isPaused;
    private boolean isStarted;
    /* access modifiers changed from: private */
    public final LegacyDataMigrator legacyDataMigrator;
    private final Handler mainHandler;
    /* access modifiers changed from: private */
    public final List<ScheduleOperation> pendingAlarmOperations;
    /* access modifiers changed from: private */
    public String regionId;
    /* access modifiers changed from: private */
    public ScheduleListener scheduleListener;
    private final OperationScheduler scheduler;
    /* access modifiers changed from: private */
    public String screen;
    /* access modifiers changed from: private */
    public long startTime;
    /* access modifiers changed from: private */
    public final SparseArray<Long> stateChangeTimeStamps;
    /* access modifiers changed from: private */
    public Subject<TriggerUpdate> stateObservableUpdates;

    private interface NotifySchedule {
        void notify(ScheduleListener scheduleListener, Schedule<? extends ScheduleData> schedule);
    }

    public interface ScheduleListener {
        void onNewSchedule(Schedule<? extends ScheduleData> schedule);

        void onScheduleCancelled(Schedule<? extends ScheduleData> schedule);

        void onScheduleExpired(Schedule<? extends ScheduleData> schedule);

        void onScheduleLimitReached(Schedule<? extends ScheduleData> schedule);
    }

    AutomationEngine(Context context, AirshipRuntimeConfig airshipRuntimeConfig, Analytics analytics2, PreferenceDataStore preferenceDataStore) {
        this(analytics2, InAppActivityMonitor.shared(context), AlarmOperationScheduler.shared(context), AutomationDatabase.createDatabase(context, airshipRuntimeConfig).getScheduleDao(), new LegacyDataMigrator(context, airshipRuntimeConfig, preferenceDataStore));
    }

    AutomationEngine(Analytics analytics2, ActivityMonitor activityMonitor2, OperationScheduler operationScheduler, AutomationDao automationDao, LegacyDataMigrator legacyDataMigrator2) {
        this.SCHEDULE_LIMIT = 1000;
        this.COMPOUND_TRIGGER_TYPES = Arrays.asList(new Integer[]{9, 10});
        this.SCHEDULE_PRIORITY_COMPARATOR = new Comparator<FullSchedule>() {
            public int compare(FullSchedule fullSchedule, FullSchedule fullSchedule2) {
                if (fullSchedule.schedule.priority == fullSchedule2.schedule.priority) {
                    return 0;
                }
                return fullSchedule.schedule.priority > fullSchedule2.schedule.priority ? 1 : -1;
            }
        };
        this.isPaused = new AtomicBoolean(false);
        this.stateChangeTimeStamps = new SparseArray<>();
        this.pendingAlarmOperations = new ArrayList();
        this.applicationListener = new ApplicationListener() {
            public void onForeground(long j) {
                AutomationEngine.this.onEventAdded(JsonValue.NULL, 1, 1.0d);
                AutomationEngine.this.onScheduleConditionsChanged();
            }

            public void onBackground(long j) {
                AutomationEngine.this.onEventAdded(JsonValue.NULL, 2, 1.0d);
                AutomationEngine.this.onScheduleConditionsChanged();
            }
        };
        this.analyticsListener = new AnalyticsListener() {
            public void onRegionEventAdded(RegionEvent regionEvent) {
                String unused = AutomationEngine.this.regionId = regionEvent.toJsonValue().optMap().opt(RegionEvent.REGION_ID).getString();
                AutomationEngine.this.onEventAdded(regionEvent.toJsonValue(), regionEvent.getBoundaryEvent() == 1 ? 3 : 4, 1.0d);
                AutomationEngine.this.onScheduleConditionsChanged();
            }

            public void onCustomEventAdded(CustomEvent customEvent) {
                AutomationEngine.this.onEventAdded(customEvent.toJsonValue(), 5, 1.0d);
                BigDecimal eventValue = customEvent.getEventValue();
                if (eventValue != null) {
                    AutomationEngine.this.onEventAdded(customEvent.toJsonValue(), 6, eventValue.doubleValue());
                }
            }

            public void onScreenTracked(String str) {
                String unused = AutomationEngine.this.screen = str;
                AutomationEngine.this.onEventAdded(JsonValue.wrap(str), 7, 1.0d);
                AutomationEngine.this.onScheduleConditionsChanged();
            }
        };
        this.analytics = analytics2;
        this.activityMonitor = activityMonitor2;
        this.scheduler = operationScheduler;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.dao = automationDao;
        this.legacyDataMigrator = legacyDataMigrator2;
    }

    public void start(AutomationDriver automationDriver) {
        if (!this.isStarted) {
            this.driver = automationDriver;
            this.startTime = System.currentTimeMillis();
            AirshipHandlerThread airshipHandlerThread = new AirshipHandlerThread(Modules.AUTOMATION_MODULE);
            this.backgroundThread = airshipHandlerThread;
            airshipHandlerThread.start();
            this.backgroundHandler = new Handler(this.backgroundThread.getLooper());
            this.backgroundScheduler = Schedulers.looper(this.backgroundThread.getLooper());
            this.activityMonitor.addApplicationListener(this.applicationListener);
            this.analytics.addAnalyticsListener(this.analyticsListener);
            this.backgroundHandler.post(new Runnable() {
                public void run() {
                    AutomationEngine.this.legacyDataMigrator.migrateData(AutomationEngine.this.dao);
                    AutomationEngine.this.cleanSchedules();
                    AutomationEngine.this.resetExecutingSchedules();
                    AutomationEngine.this.restoreDelayAlarms();
                    AutomationEngine.this.restoreIntervalAlarms();
                    AutomationEngine automationEngine = AutomationEngine.this;
                    automationEngine.prepareSchedules(automationEngine.dao.getSchedulesWithStates(6));
                }
            });
            restoreCompoundTriggers();
            onScheduleConditionsChanged();
            onEventAdded(JsonValue.NULL, 8, 1.0d);
            this.isStarted = true;
        }
    }

    public void setPaused(boolean z) {
        this.isPaused.set(z);
        if (!z) {
            onScheduleConditionsChanged();
        }
    }

    public void stop() {
        if (this.isStarted) {
            this.compoundTriggerSubscription.cancel();
            this.activityMonitor.removeApplicationListener(this.applicationListener);
            this.analytics.removeAnalyticsListener(this.analyticsListener);
            cancelAlarms();
            this.backgroundThread.quit();
            this.backgroundThread = null;
            this.isStarted = false;
        }
    }

    public PendingResult<Boolean> schedule(final Schedule<? extends ScheduleData> schedule) {
        final PendingResult<Boolean> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                AutomationEngine.this.cleanSchedules();
                if (((long) AutomationEngine.this.dao.getScheduleCount()) >= AutomationEngine.this.SCHEDULE_LIMIT) {
                    Logger.error("AutomationEngine - Unable to insert schedule due to schedule exceeded limit.", new Object[0]);
                    pendingResult.setResult(false);
                    return;
                }
                FullSchedule convert = ScheduleConverters.convert((Schedule<?>) schedule);
                AutomationEngine.this.dao.insert(convert);
                AutomationEngine.this.subscribeStateObservables(Collections.singletonList(convert));
                AutomationEngine.this.notifyNewSchedule(Collections.singletonList(schedule));
                Logger.verbose("AutomationEngine - Scheduled entries: %s", schedule);
                pendingResult.setResult(true);
            }
        });
        return pendingResult;
    }

    public PendingResult<Boolean> schedule(final List<Schedule<? extends ScheduleData>> list) {
        final PendingResult<Boolean> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                AutomationEngine.this.cleanSchedules();
                if (((long) (AutomationEngine.this.dao.getScheduleCount() + list.size())) > AutomationEngine.this.SCHEDULE_LIMIT) {
                    Logger.error("AutomationDataManager - Unable to insert schedule due to schedule exceeded limit.", new Object[0]);
                    pendingResult.setResult(false);
                    return;
                }
                List<FullSchedule> convertSchedules = ScheduleConverters.convertSchedules(list);
                if (convertSchedules.isEmpty()) {
                    pendingResult.setResult(false);
                    return;
                }
                AutomationEngine.this.dao.insert((Collection<FullSchedule>) convertSchedules);
                AutomationEngine.this.subscribeStateObservables(convertSchedules);
                Collection access$1400 = AutomationEngine.this.convertSchedulesUnknownTypes(convertSchedules);
                AutomationEngine.this.notifyNewSchedule(access$1400);
                Logger.verbose("AutomationEngine - Scheduled entries: %s", access$1400);
                pendingResult.setResult(true);
            }
        });
        return pendingResult;
    }

    public PendingResult<Boolean> cancel(final Collection<String> collection) {
        final PendingResult<Boolean> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                List<FullSchedule> schedules = AutomationEngine.this.dao.getSchedules(collection);
                if (schedules.isEmpty()) {
                    pendingResult.setResult(false);
                    return;
                }
                Logger.verbose("AutomationEngine - Cancelled schedules: %s", collection);
                AutomationEngine.this.dao.deleteSchedules(schedules);
                AutomationEngine.this.notifyCancelledSchedule(schedules);
                AutomationEngine.this.cancelScheduleAlarms(collection);
                pendingResult.setResult(true);
            }
        });
        return pendingResult;
    }

    public PendingResult<Boolean> cancelByType(final String str) {
        final PendingResult<Boolean> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                List<FullSchedule> schedulesByType = AutomationEngine.this.dao.getSchedulesByType(str);
                if (schedulesByType.isEmpty()) {
                    pendingResult.setResult(false);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (FullSchedule fullSchedule : schedulesByType) {
                    arrayList.add(fullSchedule.schedule.scheduleId);
                }
                Logger.verbose("AutomationEngine - Cancelled schedules: %s", arrayList);
                AutomationEngine.this.dao.deleteSchedules(schedulesByType);
                AutomationEngine.this.notifyCancelledSchedule(schedulesByType);
                AutomationEngine.this.cancelScheduleAlarms(arrayList);
                pendingResult.setResult(true);
            }
        });
        return pendingResult;
    }

    public PendingResult<Boolean> cancelGroup(final String str) {
        final PendingResult<Boolean> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                List<FullSchedule> schedulesWithGroup = AutomationEngine.this.dao.getSchedulesWithGroup(str);
                if (schedulesWithGroup.isEmpty()) {
                    Logger.verbose("AutomationEngine - Failed to cancel schedule group: %s", str);
                    pendingResult.setResult(false);
                    return;
                }
                AutomationEngine.this.dao.deleteSchedules(schedulesWithGroup);
                AutomationEngine.this.cancelGroupAlarms(Collections.singletonList(str));
                AutomationEngine.this.notifyCancelledSchedule(schedulesWithGroup);
            }
        });
        return pendingResult;
    }

    public PendingResult<Void> cancelGroups(final Collection<String> collection) {
        final PendingResult<Void> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                ArrayList arrayList = new ArrayList();
                for (String schedulesWithGroup : collection) {
                    arrayList.addAll(AutomationEngine.this.dao.getSchedulesWithGroup(schedulesWithGroup));
                }
                if (!arrayList.isEmpty()) {
                    AutomationEngine.this.notifyCancelledSchedule(arrayList);
                    AutomationEngine.this.cancelGroupAlarms(collection);
                    AutomationEngine.this.dao.deleteSchedules(arrayList);
                }
                Logger.verbose("AutomationEngine - Canceled schedule groups: %s", collection);
                pendingResult.setResult(null);
            }
        });
        return pendingResult;
    }

    public PendingResult<Void> cancelAll() {
        final PendingResult<Void> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                List<FullSchedule> schedules = AutomationEngine.this.dao.getSchedules();
                if (!schedules.isEmpty()) {
                    AutomationEngine.this.notifyCancelledSchedule(schedules);
                    AutomationEngine.this.dao.deleteSchedules(schedules);
                    AutomationEngine.this.cancelAlarms();
                }
                Logger.verbose("AutomationEngine - Canceled all schedules.", new Object[0]);
                pendingResult.setResult(null);
            }
        });
        return pendingResult;
    }

    public <T extends ScheduleData> PendingResult<Collection<Schedule<T>>> getSchedulesByType(final String str) {
        final PendingResult<Collection<Schedule<T>>> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                AutomationEngine.this.cleanSchedules();
                pendingResult.setResult(AutomationEngine.this.convertSchedules(AutomationEngine.this.dao.getSchedulesByType(str)));
            }
        });
        return pendingResult;
    }

    public <T extends ScheduleData> PendingResult<Schedule<T>> getSchedule(final String str, final String str2) {
        final PendingResult<Schedule<T>> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                AutomationEngine.this.cleanSchedules();
                AutomationEngine automationEngine = AutomationEngine.this;
                pendingResult.setResult(automationEngine.convert(automationEngine.dao.getSchedule(str, str2)));
            }
        });
        return pendingResult;
    }

    public PendingResult<Collection<Schedule<? extends ScheduleData>>> getSchedules(final Set<String> set) {
        final PendingResult<Collection<Schedule<? extends ScheduleData>>> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                AutomationEngine.this.cleanSchedules();
                PendingResult pendingResult = pendingResult;
                AutomationEngine automationEngine = AutomationEngine.this;
                pendingResult.setResult(automationEngine.convertSchedulesUnknownTypes(automationEngine.dao.getSchedules(set)));
            }
        });
        return pendingResult;
    }

    public <T extends ScheduleData> PendingResult<Collection<Schedule<T>>> getSchedules(final String str, final String str2) {
        final PendingResult<Collection<Schedule<T>>> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                AutomationEngine.this.cleanSchedules();
                AutomationEngine automationEngine = AutomationEngine.this;
                pendingResult.setResult(automationEngine.convertSchedules(automationEngine.dao.getSchedulesWithGroup(str, str2)));
            }
        });
        return pendingResult;
    }

    public PendingResult<Boolean> editSchedule(final String str, final ScheduleEdits<? extends ScheduleData> scheduleEdits) {
        final PendingResult<Boolean> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                boolean z;
                FullSchedule schedule = AutomationEngine.this.dao.getSchedule(str);
                if (schedule == null) {
                    Logger.error("AutomationEngine - Schedule no longer exists. Unable to edit: %s", str);
                    pendingResult.setResult(false);
                    return;
                }
                AutomationEngine.this.applyEdits(schedule, scheduleEdits);
                long j = -1;
                boolean access$2100 = AutomationEngine.this.isOverLimit(schedule);
                boolean access$2200 = AutomationEngine.this.isExpired(schedule);
                if (schedule.schedule.executionState != 4 || access$2100 || access$2200) {
                    if (schedule.schedule.executionState != 4 && (access$2100 || access$2200)) {
                        AutomationEngine.this.updateExecutionState(schedule, 4);
                        if (access$2100) {
                            AutomationEngine.this.notifyScheduleLimitReached(schedule);
                        } else {
                            AutomationEngine.this.notifyExpiredSchedules(Collections.singleton(schedule));
                        }
                    }
                    z = false;
                } else {
                    j = schedule.schedule.executionStateChangeDate;
                    AutomationEngine.this.updateExecutionState(schedule, 0);
                    z = true;
                }
                AutomationEngine.this.dao.update(schedule);
                if (z) {
                    AutomationEngine.this.subscribeStateObservables(schedule, j);
                }
                Logger.verbose("AutomationEngine - Updated schedule: %s", str);
                pendingResult.setResult(true);
            }
        });
        return pendingResult;
    }

    public void checkPendingSchedules() {
        if (this.isStarted) {
            onScheduleConditionsChanged();
        }
    }

    public PendingResult<Collection<Schedule<? extends ScheduleData>>> getSchedules() {
        final PendingResult<Collection<Schedule<? extends ScheduleData>>> pendingResult = new PendingResult<>();
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                PendingResult pendingResult = pendingResult;
                AutomationEngine automationEngine = AutomationEngine.this;
                pendingResult.setResult(automationEngine.convertSchedulesUnknownTypes(automationEngine.dao.getSchedules()));
            }
        });
        return pendingResult;
    }

    public void setScheduleListener(ScheduleListener scheduleListener2) {
        synchronized (this) {
            this.scheduleListener = scheduleListener2;
        }
    }

    private Observable<JsonSerializable> createEventObservable(int i) {
        if (i != 9) {
            return Observable.empty();
        }
        return TriggerObservables.newSession(this.activityMonitor);
    }

    /* access modifiers changed from: private */
    public Observable<JsonSerializable> createStateObservable(int i) {
        if (i == 9) {
            return TriggerObservables.foregrounded(this.activityMonitor);
        }
        if (i != 10) {
            return Observable.empty();
        }
        return TriggerObservables.appVersionUpdated();
    }

    private void restoreCompoundTriggers() {
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : this.COMPOUND_TRIGGER_TYPES) {
            final int intValue2 = intValue.intValue();
            arrayList.add(createEventObservable(intValue2).observeOn(this.backgroundScheduler).map(new Function<JsonSerializable, TriggerUpdate>() {
                public TriggerUpdate apply(JsonSerializable jsonSerializable) {
                    AutomationEngine.this.stateChangeTimeStamps.put(intValue2, Long.valueOf(System.currentTimeMillis()));
                    return new TriggerUpdate(AutomationEngine.this.dao.getActiveTriggers(intValue2), jsonSerializable, 1.0d);
                }
            }));
        }
        Observable merge = Observable.merge(arrayList);
        Subject<TriggerUpdate> create = Subject.create();
        this.stateObservableUpdates = create;
        this.compoundTriggerSubscription = Observable.merge(merge, create).subscribe(new Subscriber<TriggerUpdate>() {
            public void onNext(TriggerUpdate triggerUpdate) {
                AutomationEngine.this.updateTriggers(triggerUpdate.triggerEntities, triggerUpdate.json, triggerUpdate.value);
            }
        });
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                AutomationEngine automationEngine = AutomationEngine.this;
                automationEngine.subscribeStateObservables(automationEngine.dao.getSchedules());
            }
        });
    }

    /* access modifiers changed from: private */
    public void sortSchedulesByPriority(List<FullSchedule> list) {
        if (list.size() > 1) {
            Collections.sort(list, this.SCHEDULE_PRIORITY_COMPARATOR);
        }
    }

    /* access modifiers changed from: private */
    public void subscribeStateObservables(List<FullSchedule> list) {
        sortSchedulesByPriority(list);
        for (FullSchedule subscribeStateObservables : list) {
            subscribeStateObservables(subscribeStateObservables, -1);
        }
    }

    /* access modifiers changed from: private */
    public void subscribeStateObservables(final FullSchedule fullSchedule, final long j) {
        Observable.from(this.COMPOUND_TRIGGER_TYPES).filter(new Predicate<Integer>() {
            public boolean apply(Integer num) {
                if (((Long) AutomationEngine.this.stateChangeTimeStamps.get(num.intValue(), Long.valueOf(AutomationEngine.this.startTime))).longValue() <= j) {
                    return false;
                }
                for (TriggerEntity triggerEntity : fullSchedule.triggers) {
                    if (triggerEntity.triggerType == num.intValue()) {
                        return true;
                    }
                }
                return false;
            }
        }).flatMap(new Function<Integer, Observable<TriggerUpdate>>() {
            public Observable<TriggerUpdate> apply(final Integer num) {
                return AutomationEngine.this.createStateObservable(num.intValue()).observeOn(AutomationEngine.this.backgroundScheduler).map(new Function<JsonSerializable, TriggerUpdate>() {
                    public TriggerUpdate apply(JsonSerializable jsonSerializable) {
                        return new TriggerUpdate(AutomationEngine.this.dao.getActiveTriggers(num.intValue(), fullSchedule.schedule.scheduleId), jsonSerializable, 1.0d);
                    }
                });
            }
        }).subscribe(new Subscriber<TriggerUpdate>() {
            public void onNext(TriggerUpdate triggerUpdate) {
                AutomationEngine.this.stateObservableUpdates.onNext(triggerUpdate);
            }
        });
    }

    /* access modifiers changed from: private */
    public void resetExecutingSchedules() {
        List<FullSchedule> schedulesWithStates = this.dao.getSchedulesWithStates(2, 1);
        if (!schedulesWithStates.isEmpty()) {
            for (FullSchedule updateExecutionState : schedulesWithStates) {
                updateExecutionState(updateExecutionState, 6);
            }
            this.dao.updateSchedules(schedulesWithStates);
            Logger.verbose("AutomationEngine: Schedules reset state to STATE_PREPARING_SCHEDULE: %s", schedulesWithStates);
        }
    }

    /* access modifiers changed from: private */
    public void cleanSchedules() {
        long j;
        List<FullSchedule> activeExpiredSchedules = this.dao.getActiveExpiredSchedules();
        List<FullSchedule> schedulesWithStates = this.dao.getSchedulesWithStates(4);
        handleExpiredEntries(activeExpiredSchedules);
        HashSet hashSet = new HashSet();
        for (FullSchedule next : schedulesWithStates) {
            if (next.schedule.editGracePeriod == 0) {
                j = next.schedule.executionStateChangeDate;
            } else if (next.schedule.scheduleEnd >= 0) {
                j = next.schedule.scheduleEnd + next.schedule.editGracePeriod;
            }
            if (System.currentTimeMillis() >= j) {
                hashSet.add(next);
            }
        }
        if (!hashSet.isEmpty()) {
            Logger.verbose("AutomationEngine - Deleting finished schedules: %s", hashSet);
            this.dao.deleteSchedules(hashSet);
        }
    }

    /* access modifiers changed from: private */
    public void cancelScheduleAlarms(Collection<String> collection) {
        Iterator it = new ArrayList(this.pendingAlarmOperations).iterator();
        while (it.hasNext()) {
            ScheduleOperation scheduleOperation = (ScheduleOperation) it.next();
            if (collection.contains(scheduleOperation.scheduleId)) {
                scheduleOperation.cancel();
                this.pendingAlarmOperations.remove(scheduleOperation);
            }
        }
    }

    /* access modifiers changed from: private */
    public void cancelGroupAlarms(Collection<String> collection) {
        Iterator it = new ArrayList(this.pendingAlarmOperations).iterator();
        while (it.hasNext()) {
            ScheduleOperation scheduleOperation = (ScheduleOperation) it.next();
            if (collection.contains(scheduleOperation.group)) {
                scheduleOperation.cancel();
                this.pendingAlarmOperations.remove(scheduleOperation);
            }
        }
    }

    /* access modifiers changed from: private */
    public void cancelAlarms() {
        for (ScheduleOperation cancel : this.pendingAlarmOperations) {
            cancel.cancel();
        }
        this.pendingAlarmOperations.clear();
    }

    /* access modifiers changed from: private */
    public void restoreDelayAlarms() {
        List<FullSchedule> schedulesWithStates = this.dao.getSchedulesWithStates(5);
        if (!schedulesWithStates.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (FullSchedule next : schedulesWithStates) {
                if (next.schedule.seconds != 0) {
                    long min = Math.min(TimeUnit.SECONDS.toMillis(next.schedule.seconds), System.currentTimeMillis() - next.schedule.executionStateChangeDate);
                    if (min <= 0) {
                        updateExecutionState(next, 6);
                        arrayList.add(next);
                    } else {
                        scheduleDelayAlarm(next, min);
                    }
                }
            }
            this.dao.updateSchedules(arrayList);
        }
    }

    /* access modifiers changed from: private */
    public void restoreIntervalAlarms() {
        List<FullSchedule> schedulesWithStates = this.dao.getSchedulesWithStates(3);
        if (!schedulesWithStates.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (FullSchedule next : schedulesWithStates) {
                long currentTimeMillis = next.schedule.interval - (System.currentTimeMillis() - next.schedule.executionStateChangeDate);
                if (currentTimeMillis > 0) {
                    scheduleIntervalAlarm(next, currentTimeMillis);
                } else {
                    updateExecutionState(next, 0);
                    arrayList.add(next);
                }
            }
            this.dao.updateSchedules(arrayList);
        }
    }

    /* access modifiers changed from: private */
    public void onScheduleConditionsChanged() {
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                List<FullSchedule> schedulesWithStates = AutomationEngine.this.dao.getSchedulesWithStates(1);
                if (!schedulesWithStates.isEmpty()) {
                    AutomationEngine.this.sortSchedulesByPriority(schedulesWithStates);
                    for (FullSchedule access$3400 : schedulesWithStates) {
                        AutomationEngine.this.attemptExecution(access$3400);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void onEventAdded(JsonSerializable jsonSerializable, int i, double d) {
        final int i2 = i;
        final JsonSerializable jsonSerializable2 = jsonSerializable;
        final double d2 = d;
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                Logger.debug("Automation - Updating triggers with type: %s", Integer.valueOf(i2));
                List<TriggerEntity> activeTriggers = AutomationEngine.this.dao.getActiveTriggers(i2);
                if (!activeTriggers.isEmpty()) {
                    AutomationEngine.this.updateTriggers(activeTriggers, jsonSerializable2, d2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateTriggers(List<TriggerEntity> list, JsonSerializable jsonSerializable, double d) {
        final List<TriggerEntity> list2 = list;
        final JsonSerializable jsonSerializable2 = jsonSerializable;
        final double d2 = d;
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                if (!AutomationEngine.this.isPaused.get() && !list2.isEmpty()) {
                    HashSet hashSet = new HashSet();
                    HashSet hashSet2 = new HashSet();
                    HashMap hashMap = new HashMap();
                    ArrayList arrayList = new ArrayList();
                    for (TriggerEntity triggerEntity : list2) {
                        if (triggerEntity.jsonPredicate == null || triggerEntity.jsonPredicate.apply(jsonSerializable2)) {
                            arrayList.add(triggerEntity);
                            triggerEntity.progress += d2;
                            if (triggerEntity.progress >= triggerEntity.goal) {
                                triggerEntity.progress = 0.0d;
                                if (triggerEntity.isCancellation) {
                                    hashSet2.add(triggerEntity.parentScheduleId);
                                    AutomationEngine.this.cancelScheduleAlarms(Collections.singletonList(triggerEntity.parentScheduleId));
                                } else {
                                    hashSet.add(triggerEntity.parentScheduleId);
                                    hashMap.put(triggerEntity.parentScheduleId, new TriggerContext(ScheduleConverters.convert(triggerEntity), jsonSerializable2.toJsonValue()));
                                }
                            }
                        }
                    }
                    AutomationEngine.this.dao.updateTriggers(arrayList);
                    if (!hashSet2.isEmpty()) {
                        AutomationEngine automationEngine = AutomationEngine.this;
                        automationEngine.handleCancelledSchedules(automationEngine.dao.getSchedules(hashSet2));
                    }
                    if (!hashSet.isEmpty()) {
                        AutomationEngine automationEngine2 = AutomationEngine.this;
                        automationEngine2.handleTriggeredSchedules(automationEngine2.dao.getSchedules(hashSet), hashMap);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleCancelledSchedules(List<FullSchedule> list) {
        if (!list.isEmpty()) {
            for (FullSchedule updateExecutionState : list) {
                updateExecutionState(updateExecutionState, 0);
            }
            this.dao.updateSchedules(list);
        }
    }

    /* access modifiers changed from: private */
    public void handleTriggeredSchedules(List<FullSchedule> list, Map<String, TriggerContext> map) {
        if (!this.isPaused.get() && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (FullSchedule next : list) {
                if (next.schedule.executionState == 0) {
                    arrayList.add(next);
                    next.schedule.triggerContext = map.get(next.schedule.scheduleId);
                    if (isExpired(next)) {
                        arrayList2.add(next);
                    } else {
                        for (TriggerEntity next2 : next.triggers) {
                            if (next2.isCancellation) {
                                next2.progress = 0.0d;
                            }
                        }
                        if (next.schedule.seconds > 0) {
                            updateExecutionState(next, 5);
                            scheduleDelayAlarm(next, TimeUnit.SECONDS.toMillis(next.schedule.seconds));
                        } else {
                            updateExecutionState(next, 6);
                            arrayList3.add(next);
                        }
                    }
                }
            }
            this.dao.updateSchedules(arrayList);
            prepareSchedules(arrayList3);
            handleExpiredEntries(arrayList2);
        }
    }

    /* access modifiers changed from: private */
    public void prepareSchedules(List<FullSchedule> list) {
        if (list != null && !list.isEmpty()) {
            sortSchedulesByPriority(list);
            for (FullSchedule next : list) {
                Schedule convert = convert(next);
                if (convert != null) {
                    final String id = convert.getId();
                    this.driver.onPrepareSchedule(convert, next.schedule.triggerContext, new AutomationDriver.PrepareScheduleCallback() {
                        public void onFinish(final int i) {
                            AutomationEngine.this.backgroundHandler.post(new Runnable() {
                                public void run() {
                                    FullSchedule schedule = AutomationEngine.this.dao.getSchedule(id);
                                    if (schedule != null && schedule.schedule.executionState == 6) {
                                        if (AutomationEngine.this.isExpired(schedule)) {
                                            AutomationEngine.this.handleExpiredEntry(schedule);
                                            return;
                                        }
                                        int i = i;
                                        if (i == 0) {
                                            AutomationEngine.this.updateExecutionState(schedule, 1);
                                            AutomationEngine.this.dao.update(schedule);
                                            AutomationEngine.this.attemptExecution(schedule);
                                        } else if (i == 1) {
                                            AutomationEngine.this.dao.delete(schedule);
                                            AutomationEngine.this.notifyCancelledSchedule(Collections.singleton(schedule));
                                        } else if (i == 2) {
                                            AutomationEngine.this.onScheduleFinishedExecuting(schedule);
                                        } else if (i == 3) {
                                            AutomationEngine.this.updateExecutionState(schedule, 0);
                                            AutomationEngine.this.dao.update(schedule);
                                        } else if (i == 4) {
                                            AutomationEngine.this.prepareSchedules(Collections.singletonList(schedule));
                                        }
                                    }
                                }
                            });
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public <T extends ScheduleData> Schedule<T> convert(FullSchedule fullSchedule) {
        if (fullSchedule == null) {
            return null;
        }
        try {
            return ScheduleConverters.convert(fullSchedule);
        } catch (ClassCastException e) {
            Logger.error(e, "Exception converting entity to schedule %s", fullSchedule.schedule.scheduleId);
            return null;
        } catch (Exception e2) {
            Logger.error(e2, "Exception converting entity to schedule %s. Cancelling.", fullSchedule.schedule.scheduleId);
            cancel(Collections.singleton(fullSchedule.schedule.scheduleId));
            return null;
        }
    }

    /* access modifiers changed from: private */
    public Collection<Schedule<? extends ScheduleData>> convertSchedulesUnknownTypes(Collection<FullSchedule> collection) {
        ArrayList arrayList = new ArrayList();
        for (FullSchedule convert : collection) {
            Schedule convert2 = convert(convert);
            if (convert2 != null) {
                arrayList.add(convert2);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public <T extends ScheduleData> Collection<Schedule<T>> convertSchedules(Collection<FullSchedule> collection) {
        ArrayList arrayList = new ArrayList();
        for (FullSchedule convert : collection) {
            Schedule convert2 = convert(convert);
            if (convert2 != null) {
                arrayList.add(convert2);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void attemptExecution(FullSchedule fullSchedule) {
        if (fullSchedule.schedule.executionState != 1) {
            Logger.error("Unable to execute schedule when state is %s scheduleID: %s", Integer.valueOf(fullSchedule.schedule.executionState), fullSchedule.schedule.scheduleId);
        } else if (isExpired(fullSchedule)) {
            handleExpiredEntry(fullSchedule);
        } else {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            final FullSchedule fullSchedule2 = fullSchedule;
            final CountDownLatch countDownLatch2 = countDownLatch;
            AnonymousClass28 r4 = new ScheduleRunnable<Integer>(fullSchedule.schedule.scheduleId, fullSchedule.schedule.group) {
                public void run() {
                    this.result = 0;
                    if (!AutomationEngine.this.isPaused.get()) {
                        Schedule schedule = null;
                        if (AutomationEngine.this.isScheduleConditionsSatisfied(fullSchedule2)) {
                            try {
                                schedule = ScheduleConverters.convert(fullSchedule2);
                                this.result = Integer.valueOf(AutomationEngine.this.driver.onCheckExecutionReadiness(schedule));
                            } catch (Exception e) {
                                Logger.error(e, "Unable to create schedule.", new Object[0]);
                                this.exception = e;
                            }
                        }
                        countDownLatch2.countDown();
                        if (1 == ((Integer) this.result).intValue() && schedule != null) {
                            AutomationEngine.this.driver.onExecuteTriggeredSchedule(schedule, new ScheduleExecutorCallback(fullSchedule2.schedule.scheduleId));
                        }
                    }
                }
            };
            this.mainHandler.post(r4);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                Logger.error(e, "Failed to execute schedule. ", new Object[0]);
                Thread.currentThread().interrupt();
            }
            if (r4.exception != null) {
                Logger.error("Failed to check conditions. Deleting schedule: %s", fullSchedule.schedule.scheduleId);
                this.dao.delete(fullSchedule);
                notifyCancelledSchedule(Collections.singleton(fullSchedule));
                return;
            }
            int intValue = r4.result == null ? 0 : ((Integer) r4.result).intValue();
            if (intValue == -1) {
                Logger.verbose("AutomationEngine - Schedule invalidated: %s", fullSchedule.schedule.scheduleId);
                updateExecutionState(fullSchedule, 6);
                this.dao.update(fullSchedule);
                prepareSchedules(Collections.singletonList(this.dao.getSchedule(fullSchedule.schedule.scheduleId)));
            } else if (intValue == 0) {
                Logger.verbose("AutomationEngine - Schedule not ready for execution: %s", fullSchedule.schedule.scheduleId);
            } else if (intValue == 1) {
                Logger.verbose("AutomationEngine - Schedule executing: %s", fullSchedule.schedule.scheduleId);
                updateExecutionState(fullSchedule, 2);
                this.dao.update(fullSchedule);
            }
        }
    }

    /* access modifiers changed from: private */
    public void notifyExpiredSchedules(Collection<FullSchedule> collection) {
        notifyHelper(convertSchedulesUnknownTypes(collection), new NotifySchedule() {
            public void notify(ScheduleListener scheduleListener, Schedule<? extends ScheduleData> schedule) {
                scheduleListener.onScheduleExpired(schedule);
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifyCancelledSchedule(Collection<FullSchedule> collection) {
        notifyHelper(convertSchedulesUnknownTypes(collection), new NotifySchedule() {
            public void notify(ScheduleListener scheduleListener, Schedule<? extends ScheduleData> schedule) {
                scheduleListener.onScheduleCancelled(schedule);
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifyScheduleLimitReached(FullSchedule fullSchedule) {
        notifyHelper(convertSchedulesUnknownTypes(Collections.singleton(fullSchedule)), new NotifySchedule() {
            public void notify(ScheduleListener scheduleListener, Schedule<? extends ScheduleData> schedule) {
                scheduleListener.onScheduleLimitReached(schedule);
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifyNewSchedule(Collection<Schedule<? extends ScheduleData>> collection) {
        notifyHelper(collection, new NotifySchedule() {
            public void notify(ScheduleListener scheduleListener, Schedule schedule) {
                scheduleListener.onNewSchedule(schedule);
            }
        });
    }

    private void notifyHelper(final Collection<Schedule<? extends ScheduleData>> collection, final NotifySchedule notifySchedule) {
        if (this.scheduleListener != null && !collection.isEmpty()) {
            this.mainHandler.post(new Runnable() {
                public void run() {
                    for (Schedule schedule : collection) {
                        ScheduleListener access$4300 = AutomationEngine.this.scheduleListener;
                        if (access$4300 != null) {
                            notifySchedule.notify(access$4300, schedule);
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void onScheduleFinishedExecuting(FullSchedule fullSchedule) {
        if (fullSchedule != null) {
            Logger.verbose("AutomationEngine - Schedule finished: %s", fullSchedule.schedule.scheduleId);
            fullSchedule.schedule.count++;
            boolean isOverLimit = isOverLimit(fullSchedule);
            if (isExpired(fullSchedule)) {
                handleExpiredEntry(fullSchedule);
                return;
            }
            if (isOverLimit) {
                updateExecutionState(fullSchedule, 4);
                notifyScheduleLimitReached(fullSchedule);
                if (fullSchedule.schedule.editGracePeriod <= 0) {
                    this.dao.delete(fullSchedule);
                    return;
                }
            } else if (fullSchedule.schedule.interval > 0) {
                updateExecutionState(fullSchedule, 3);
                scheduleIntervalAlarm(fullSchedule, fullSchedule.schedule.interval);
            } else {
                updateExecutionState(fullSchedule, 0);
            }
            this.dao.update(fullSchedule);
        }
    }

    private void scheduleDelayAlarm(FullSchedule fullSchedule, long j) {
        final AnonymousClass34 r0 = new ScheduleOperation(fullSchedule.schedule.scheduleId, fullSchedule.schedule.group) {
            /* access modifiers changed from: protected */
            public void onRun() {
                FullSchedule schedule = AutomationEngine.this.dao.getSchedule(this.scheduleId);
                if (schedule != null && schedule.schedule.executionState == 5) {
                    if (AutomationEngine.this.isExpired(schedule)) {
                        AutomationEngine.this.handleExpiredEntry(schedule);
                        return;
                    }
                    AutomationEngine.this.updateExecutionState(schedule, 6);
                    AutomationEngine.this.dao.update(schedule);
                    AutomationEngine.this.prepareSchedules(Collections.singletonList(schedule));
                }
            }
        };
        r0.addOnRun(new Runnable() {
            public void run() {
                AutomationEngine.this.pendingAlarmOperations.remove(r0);
            }
        });
        this.pendingAlarmOperations.add(r0);
        this.scheduler.schedule(j, r0);
    }

    private void scheduleIntervalAlarm(FullSchedule fullSchedule, long j) {
        final AnonymousClass36 r0 = new ScheduleOperation(fullSchedule.schedule.scheduleId, fullSchedule.schedule.group) {
            /* access modifiers changed from: protected */
            public void onRun() {
                FullSchedule schedule = AutomationEngine.this.dao.getSchedule(this.scheduleId);
                if (schedule != null && schedule.schedule.executionState == 3) {
                    if (AutomationEngine.this.isExpired(schedule)) {
                        AutomationEngine.this.handleExpiredEntry(schedule);
                        return;
                    }
                    long j = schedule.schedule.executionStateChangeDate;
                    AutomationEngine.this.updateExecutionState(schedule, 0);
                    AutomationEngine.this.dao.update(schedule);
                    AutomationEngine.this.subscribeStateObservables(schedule, j);
                }
            }
        };
        r0.addOnRun(new Runnable() {
            public void run() {
                AutomationEngine.this.pendingAlarmOperations.remove(r0);
            }
        });
        this.pendingAlarmOperations.add(r0);
        this.scheduler.schedule(j, r0);
    }

    /* access modifiers changed from: private */
    public boolean isScheduleConditionsSatisfied(FullSchedule fullSchedule) {
        if (fullSchedule.schedule.screens != null && !fullSchedule.schedule.screens.isEmpty() && !fullSchedule.schedule.screens.contains(this.screen)) {
            return false;
        }
        if (fullSchedule.schedule.regionId != null && !fullSchedule.schedule.regionId.equals(this.regionId)) {
            return false;
        }
        int i = fullSchedule.schedule.appState;
        if (i != 2) {
            if (i == 3 && this.activityMonitor.isAppForegrounded()) {
                return false;
            }
            return true;
        } else if (!this.activityMonitor.isAppForegrounded()) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void handleExpiredEntry(FullSchedule fullSchedule) {
        handleExpiredEntries(Collections.singleton(fullSchedule));
    }

    private void handleExpiredEntries(Collection<FullSchedule> collection) {
        if (!collection.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (FullSchedule next : collection) {
                updateExecutionState(next, 4);
                if (next.schedule.editGracePeriod > 0) {
                    arrayList2.add(next);
                } else {
                    arrayList.add(next);
                }
            }
            this.dao.updateSchedules(arrayList2);
            this.dao.deleteSchedules(arrayList);
            notifyExpiredSchedules(collection);
        }
    }

    private class ScheduleOperation extends CancelableOperation {
        final String group;
        final String scheduleId;

        ScheduleOperation(String str, String str2) {
            super(AutomationEngine.this.backgroundHandler.getLooper());
            this.scheduleId = str;
            this.group = str2;
        }
    }

    private static abstract class ScheduleRunnable<T> implements Runnable {
        Exception exception;
        final String group;
        T result;
        final String scheduleId;

        ScheduleRunnable(String str, String str2) {
            this.scheduleId = str;
            this.group = str2;
        }
    }

    private class ScheduleExecutorCallback implements AutomationDriver.ExecutionCallback {
        /* access modifiers changed from: private */
        public final String scheduleId;

        ScheduleExecutorCallback(String str) {
            this.scheduleId = str;
        }

        public void onFinish() {
            AutomationEngine.this.backgroundHandler.post(new Runnable() {
                public void run() {
                    AutomationEngine.this.onScheduleFinishedExecuting(AutomationEngine.this.dao.getSchedule(ScheduleExecutorCallback.this.scheduleId));
                }
            });
        }
    }

    public void applyEdits(FullSchedule fullSchedule, ScheduleEdits scheduleEdits) {
        ScheduleEntity scheduleEntity = fullSchedule.schedule;
        scheduleEntity.scheduleStart = scheduleEdits.getStart() == null ? scheduleEntity.scheduleStart : scheduleEdits.getStart().longValue();
        scheduleEntity.scheduleEnd = scheduleEdits.getEnd() == null ? scheduleEntity.scheduleEnd : scheduleEdits.getEnd().longValue();
        scheduleEntity.limit = scheduleEdits.getLimit() == null ? scheduleEntity.limit : scheduleEdits.getLimit().intValue();
        scheduleEntity.data = scheduleEdits.getData() == null ? scheduleEntity.data : scheduleEdits.getData().toJsonValue();
        scheduleEntity.priority = scheduleEdits.getPriority() == null ? scheduleEntity.priority : scheduleEdits.getPriority().intValue();
        scheduleEntity.interval = scheduleEdits.getInterval() == null ? scheduleEntity.interval : scheduleEdits.getInterval().longValue();
        scheduleEntity.editGracePeriod = scheduleEdits.getEditGracePeriod() == null ? scheduleEntity.editGracePeriod : scheduleEdits.getEditGracePeriod().longValue();
        scheduleEntity.metadata = scheduleEdits.getMetadata() == null ? scheduleEntity.metadata : scheduleEdits.getMetadata();
        scheduleEntity.scheduleType = scheduleEdits.getType() == null ? scheduleEntity.scheduleType : scheduleEdits.getType();
        scheduleEntity.audience = scheduleEdits.getAudience() == null ? scheduleEntity.audience : scheduleEdits.getAudience();
    }

    /* access modifiers changed from: private */
    public boolean isExpired(FullSchedule fullSchedule) {
        return fullSchedule.schedule.scheduleEnd >= 0 && fullSchedule.schedule.scheduleEnd < System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public boolean isOverLimit(FullSchedule fullSchedule) {
        return fullSchedule.schedule.limit > 0 && fullSchedule.schedule.count >= fullSchedule.schedule.limit;
    }

    /* access modifiers changed from: private */
    public void updateExecutionState(FullSchedule fullSchedule, int i) {
        if (fullSchedule.schedule.executionState != i) {
            fullSchedule.schedule.executionState = i;
            fullSchedule.schedule.executionStateChangeDate = System.currentTimeMillis();
        }
    }

    private static class TriggerUpdate {
        final JsonSerializable json;
        final List<TriggerEntity> triggerEntities;
        final double value;

        TriggerUpdate(List<TriggerEntity> list, JsonSerializable jsonSerializable, double d) {
            this.triggerEntities = list;
            this.json = jsonSerializable;
            this.value = d;
        }
    }
}
