package com.urbanairship.automation;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.urbanairship.AirshipComponent;
import com.urbanairship.AirshipLoopers;
import com.urbanairship.Logger;
import com.urbanairship.PendingResult;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionCompletionCallback;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.actions.ActionRunRequestFactory;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.automation.AutomationDriver;
import com.urbanairship.automation.AutomationEngine;
import com.urbanairship.automation.InAppRemoteDataObserver;
import com.urbanairship.automation.actions.Actions;
import com.urbanairship.automation.auth.AuthException;
import com.urbanairship.automation.auth.AuthManager;
import com.urbanairship.automation.deferred.Deferred;
import com.urbanairship.automation.deferred.DeferredScheduleClient;
import com.urbanairship.automation.tags.AudienceManager;
import com.urbanairship.automation.tags.TagGroupResult;
import com.urbanairship.automation.tags.TagGroupUtils;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.NamedUser;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.Response;
import com.urbanairship.iam.InAppAutomationScheduler;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.InAppMessageManager;
import com.urbanairship.json.JsonMap;
import com.urbanairship.remotedata.RemoteData;
import com.urbanairship.util.RetryingExecutor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class InAppAutomation extends AirshipComponent implements InAppAutomationScheduler {
    private static final String ENABLE_KEY = "com.urbanairship.iam.enabled";
    private static final String PAUSE_KEY = "com.urbanairship.iam.paused";
    private final ActionRunRequestFactory actionRunRequestFactory;
    private final AirshipChannel airshipChannel;
    /* access modifiers changed from: private */
    public final AudienceManager audienceManager;
    /* access modifiers changed from: private */
    public final AutomationEngine automationEngine;
    private final Handler backgroundHandler;
    private final DeferredScheduleClient deferredScheduleClient;
    private final AutomationDriver driver = new AutomationDriver() {
        public void onPrepareSchedule(Schedule schedule, TriggerContext triggerContext, AutomationDriver.PrepareScheduleCallback prepareScheduleCallback) {
            InAppAutomation.this.onPrepareSchedule(schedule, triggerContext, prepareScheduleCallback);
        }

        public int onCheckExecutionReadiness(Schedule schedule) {
            return InAppAutomation.this.onCheckExecutionReadiness(schedule);
        }

        public void onExecuteTriggeredSchedule(Schedule schedule, AutomationDriver.ExecutionCallback executionCallback) {
            InAppAutomation.this.onExecuteTriggeredSchedule(schedule, executionCallback);
        }
    };
    /* access modifiers changed from: private */
    public final InAppMessageManager inAppMessageManager;
    /* access modifiers changed from: private */
    public final InAppRemoteDataObserver remoteDataSubscriber;
    private final RetryingExecutor retryingExecutor;

    public int getComponentGroup() {
        return 3;
    }

    public static InAppAutomation shared() {
        return (InAppAutomation) UAirship.shared().requireComponent(InAppAutomation.class);
    }

    public InAppAutomation(Context context, PreferenceDataStore preferenceDataStore, AirshipRuntimeConfig airshipRuntimeConfig, Analytics analytics, RemoteData remoteData, AirshipChannel airshipChannel2, NamedUser namedUser) {
        super(context, preferenceDataStore);
        this.automationEngine = new AutomationEngine(context, airshipRuntimeConfig, analytics, preferenceDataStore);
        this.airshipChannel = airshipChannel2;
        this.audienceManager = new AudienceManager(airshipRuntimeConfig, airshipChannel2, namedUser, preferenceDataStore);
        this.remoteDataSubscriber = new InAppRemoteDataObserver(preferenceDataStore, remoteData);
        this.inAppMessageManager = new InAppMessageManager(context, preferenceDataStore, analytics, new InAppMessageManager.Delegate() {
            public void onReadinessChanged() {
                InAppAutomation.this.automationEngine.checkPendingSchedules();
            }
        });
        this.backgroundHandler = new Handler(AirshipLoopers.getBackgroundLooper());
        this.retryingExecutor = RetryingExecutor.newSerialExecutor(Looper.getMainLooper());
        this.actionRunRequestFactory = new ActionRunRequestFactory();
        this.deferredScheduleClient = new DeferredScheduleClient(airshipRuntimeConfig, new AuthManager(airshipRuntimeConfig, airshipChannel2));
    }

    InAppAutomation(Context context, PreferenceDataStore preferenceDataStore, AutomationEngine automationEngine2, AirshipChannel airshipChannel2, AudienceManager audienceManager2, InAppRemoteDataObserver inAppRemoteDataObserver, InAppMessageManager inAppMessageManager2, RetryingExecutor retryingExecutor2, ActionRunRequestFactory actionRunRequestFactory2, DeferredScheduleClient deferredScheduleClient2) {
        super(context, preferenceDataStore);
        this.automationEngine = automationEngine2;
        this.airshipChannel = airshipChannel2;
        this.audienceManager = audienceManager2;
        this.remoteDataSubscriber = inAppRemoteDataObserver;
        this.inAppMessageManager = inAppMessageManager2;
        this.retryingExecutor = retryingExecutor2;
        this.actionRunRequestFactory = actionRunRequestFactory2;
        this.deferredScheduleClient = deferredScheduleClient2;
        this.backgroundHandler = new Handler(AirshipLoopers.getBackgroundLooper());
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.audienceManager.setRequestTagsCallback(new AudienceManager.RequestTagsCallback() {
            public Map<String, Set<String>> getTags() throws ExecutionException, InterruptedException {
                HashMap hashMap = new HashMap();
                Collection<Schedule> collection = InAppAutomation.this.getSchedules().get();
                if (collection == null) {
                    return hashMap;
                }
                for (Schedule audience : collection) {
                    Audience audience2 = audience.getAudience();
                    if (!(audience2 == null || audience2.getTagSelector() == null || !audience2.getTagSelector().containsTagGroups())) {
                        TagGroupUtils.addAll(hashMap, audience2.getTagSelector().getTagGroups());
                    }
                }
                return hashMap;
            }
        });
        this.automationEngine.setScheduleListener(new AutomationEngine.ScheduleListener() {
            public void onScheduleExpired(Schedule<? extends ScheduleData> schedule) {
                if ("in_app_message".equals(schedule.getType())) {
                    InAppAutomation.this.inAppMessageManager.onMessageScheduleFinished(schedule.getId());
                }
            }

            public void onScheduleCancelled(Schedule<? extends ScheduleData> schedule) {
                if ("in_app_message".equals(schedule.getType())) {
                    InAppAutomation.this.inAppMessageManager.onMessageScheduleFinished(schedule.getId());
                }
            }

            public void onScheduleLimitReached(Schedule<? extends ScheduleData> schedule) {
                if ("in_app_message".equals(schedule.getType())) {
                    InAppAutomation.this.inAppMessageManager.onMessageScheduleFinished(schedule.getId());
                }
            }

            public void onNewSchedule(Schedule<? extends ScheduleData> schedule) {
                if ("in_app_message".equals(schedule.getType())) {
                    InAppAutomation.this.inAppMessageManager.onNewMessageSchedule(schedule.getId(), (InAppMessage) schedule.coerceType());
                }
            }
        });
        this.automationEngine.start(this.driver);
        updateEnginePauseState();
        if (this.remoteDataSubscriber.getScheduleNewUserCutOffTime() == -1) {
            this.remoteDataSubscriber.setScheduleNewUserCutOffTime(this.airshipChannel.getId() == null ? System.currentTimeMillis() : 0);
        }
    }

    public void onAirshipReady(UAirship uAirship) {
        super.onAirshipReady(uAirship);
        this.remoteDataSubscriber.subscribe(this.backgroundHandler.getLooper(), this);
        this.automationEngine.checkPendingSchedules();
        this.inAppMessageManager.onAirshipReady();
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        super.tearDown();
        this.remoteDataSubscriber.cancel();
        this.automationEngine.stop();
    }

    public void onNewConfig(JsonMap jsonMap) {
        InAppRemoteConfig fromJsonMap = InAppRemoteConfig.fromJsonMap(jsonMap);
        this.audienceManager.setEnabled(fromJsonMap.tagGroupsConfig.isEnabled);
        this.audienceManager.setCacheStaleReadTime(fromJsonMap.tagGroupsConfig.cacheStaleReadTimeSeconds, TimeUnit.SECONDS);
        this.audienceManager.setPreferLocalTagDataTime(fromJsonMap.tagGroupsConfig.cachePreferLocalTagDataTimeSeconds, TimeUnit.SECONDS);
        this.audienceManager.setCacheMaxAgeTime(fromJsonMap.tagGroupsConfig.cacheMaxAgeInSeconds, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: protected */
    public void onComponentEnableChange(boolean z) {
        updateEnginePauseState();
    }

    public InAppMessageManager getInAppMessageManager() {
        return this.inAppMessageManager;
    }

    public PendingResult<Boolean> schedule(List<Schedule<? extends ScheduleData>> list) {
        return this.automationEngine.schedule(list);
    }

    public PendingResult<Boolean> schedule(Schedule<? extends ScheduleData> schedule) {
        return this.automationEngine.schedule(schedule);
    }

    public PendingResult<Boolean> cancelSchedule(String str) {
        return this.automationEngine.cancel(Collections.singletonList(str));
    }

    public PendingResult<Boolean> cancelSchedules(String str) {
        return this.automationEngine.cancelByType(str);
    }

    public PendingResult<Boolean> cancelScheduleGroup(String str) {
        return this.automationEngine.cancelGroup(str);
    }

    public PendingResult<Collection<Schedule<Actions>>> getActionScheduleGroup(String str) {
        return this.automationEngine.getSchedules(str, Schedule.TYPE_ACTION);
    }

    public PendingResult<Schedule<Actions>> getActionSchedule(String str) {
        return this.automationEngine.getSchedule(str, Schedule.TYPE_ACTION);
    }

    public PendingResult<Collection<Schedule<Actions>>> getActionSchedules() {
        return this.automationEngine.getSchedulesByType(Schedule.TYPE_ACTION);
    }

    public PendingResult<Collection<Schedule<InAppMessage>>> getMessageScheduleGroup(String str) {
        return this.automationEngine.getSchedules(str, "in_app_message");
    }

    public PendingResult<Schedule<InAppMessage>> getMessageSchedule(String str) {
        return this.automationEngine.getSchedule(str, "in_app_message");
    }

    public PendingResult<Collection<Schedule<InAppMessage>>> getMessageSchedules() {
        return this.automationEngine.getSchedulesByType("in_app_message");
    }

    public PendingResult<Collection<Schedule<? extends ScheduleData>>> getSchedules() {
        return this.automationEngine.getSchedules();
    }

    public PendingResult<Boolean> editSchedule(String str, ScheduleEdits<? extends ScheduleData> scheduleEdits) {
        return this.automationEngine.editSchedule(str, scheduleEdits);
    }

    public void setPaused(boolean z) {
        if (getDataStore().getBoolean(PAUSE_KEY, z) && !z) {
            this.automationEngine.checkPendingSchedules();
        }
        getDataStore().put(PAUSE_KEY, z);
    }

    public boolean isPaused() {
        return getDataStore().getBoolean(PAUSE_KEY, false);
    }

    public void setEnabled(boolean z) {
        getDataStore().put(ENABLE_KEY, z);
        updateEnginePauseState();
    }

    public boolean isEnabled() {
        return getDataStore().getBoolean(ENABLE_KEY, true);
    }

    /* access modifiers changed from: private */
    public void onPrepareSchedule(final Schedule<? extends ScheduleData> schedule, final TriggerContext triggerContext, final AutomationDriver.PrepareScheduleCallback prepareScheduleCallback) {
        Logger.verbose("InAppAutomation - onPrepareSchedule schedule: %s, trigger context: %s", schedule.getId(), triggerContext);
        if (isScheduleInvalid(schedule)) {
            this.backgroundHandler.post(new Runnable() {
                public void run() {
                    if (InAppAutomation.this.remoteDataSubscriber.isUpToDate()) {
                        prepareScheduleCallback.onFinish(4);
                    } else {
                        InAppAutomation.this.remoteDataSubscriber.addListener(new InAppRemoteDataObserver.Listener() {
                            public void onSchedulesUpdated() {
                                prepareScheduleCallback.onFinish(4);
                                InAppAutomation.this.remoteDataSubscriber.removeListener(this);
                            }
                        });
                    }
                }
            });
            return;
        }
        AnonymousClass6 r1 = new RetryingExecutor.Operation() {
            public int run() {
                Map<String, Set<String>> map;
                if (schedule.getAudience() == null) {
                    return 0;
                }
                if (schedule.getAudience().getTagSelector() == null || !schedule.getAudience().getTagSelector().containsTagGroups()) {
                    map = null;
                } else {
                    TagGroupResult tags = InAppAutomation.this.audienceManager.getTags(schedule.getAudience().getTagSelector().getTagGroups());
                    if (!tags.success) {
                        return 1;
                    }
                    map = tags.tagGroups;
                }
                if (AudienceChecks.checkAudience(InAppAutomation.this.getContext(), schedule.getAudience(), map)) {
                    return 0;
                }
                prepareScheduleCallback.onFinish(InAppAutomation.this.getPrepareResultMissedAudience(schedule));
                return 2;
            }
        };
        AnonymousClass7 r4 = new RetryingExecutor.Operation() {
            public int run() {
                String type = schedule.getType();
                type.hashCode();
                char c = 65535;
                switch (type.hashCode()) {
                    case -1161803523:
                        if (type.equals(Schedule.TYPE_ACTION)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -379237425:
                        if (type.equals("in_app_message")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 647890911:
                        if (type.equals(Schedule.TYPE_DEFERRED)) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        prepareScheduleCallback.onFinish(0);
                        return 0;
                    case 1:
                        InAppAutomation.this.inAppMessageManager.onPrepare(schedule.getId(), (InAppMessage) schedule.coerceType(), prepareScheduleCallback);
                        return 0;
                    case 2:
                        return InAppAutomation.this.onPrepareDeferredSchedule(schedule, triggerContext, prepareScheduleCallback);
                    default:
                        Logger.error("Unexpected schedule type: %s", schedule.getType());
                        return 0;
                }
            }
        };
        this.retryingExecutor.execute(r1, r4);
    }

    /* access modifiers changed from: private */
    public int onPrepareDeferredSchedule(Schedule<? extends ScheduleData> schedule, TriggerContext triggerContext, AutomationDriver.PrepareScheduleCallback prepareScheduleCallback) {
        Deferred deferred = (Deferred) schedule.coerceType();
        String id = this.airshipChannel.getId();
        if (id == null) {
            return 1;
        }
        try {
            Response<DeferredScheduleClient.Result> performRequest = this.deferredScheduleClient.performRequest(deferred.url, id, triggerContext, this.audienceManager.getTagOverrides(), this.audienceManager.getAttributeOverrides());
            if (!performRequest.isSuccessful()) {
                Logger.debug("Failed to resolve deferred schedule, will retry. Schedule: %s, Response: %", schedule.getId(), performRequest);
                return 1;
            } else if (!performRequest.getResult().isAudienceMatch()) {
                prepareScheduleCallback.onFinish(getPrepareResultMissedAudience(schedule));
                return 2;
            } else {
                InAppMessage message = performRequest.getResult().getMessage();
                if (message != null) {
                    this.inAppMessageManager.onPrepare(schedule.getId(), message, prepareScheduleCallback);
                } else {
                    prepareScheduleCallback.onFinish(2);
                }
                return 0;
            }
        } catch (RequestException e) {
            if (deferred.retryOnTimeout) {
                Logger.debug(e, "Failed to resolve deferred schedule, will retry. Schedule: %s", schedule.getId());
                return 1;
            }
            Logger.debug(e, "Failed to resolve deferred schedule. Schedule: %s", schedule.getId());
            prepareScheduleCallback.onFinish(2);
            return 2;
        } catch (AuthException e2) {
            Logger.debug(e2, "Failed to resolve deferred schedule: %s", schedule.getId());
            return 1;
        }
    }

    /* access modifiers changed from: private */
    public int onCheckExecutionReadiness(Schedule<? extends ScheduleData> schedule) {
        Logger.verbose("InAppAutomation - onCheckExecutionReadiness schedule: %s", schedule.getId());
        if (isPaused()) {
            return 0;
        }
        String type = schedule.getType();
        type.hashCode();
        if (!type.equals(Schedule.TYPE_ACTION)) {
            if (!type.equals("in_app_message")) {
                Logger.error("Unexpected schedule type: %s", schedule.getType());
                return 1;
            } else if (!isScheduleInvalid(schedule)) {
                return this.inAppMessageManager.onCheckExecutionReadiness(schedule.getId());
            } else {
                this.inAppMessageManager.onExecutionInvalidated(schedule.getId());
                return -1;
            }
        } else if (isScheduleInvalid(schedule)) {
            return -1;
        } else {
            return 1;
        }
    }

    /* access modifiers changed from: private */
    public void onExecuteTriggeredSchedule(Schedule<? extends ScheduleData> schedule, AutomationDriver.ExecutionCallback executionCallback) {
        Logger.verbose("InAppAutomation - onExecuteTriggeredSchedule schedule: %s", schedule.getId());
        String type = schedule.getType();
        type.hashCode();
        if (type.equals(Schedule.TYPE_ACTION)) {
            executeActions(schedule, (Actions) schedule.coerceType(), executionCallback);
        } else if (!type.equals("in_app_message")) {
            Logger.error("Unexpected schedule type: %s", schedule.getType());
            executionCallback.onFinish();
        } else {
            this.inAppMessageManager.onExecute(schedule.getId(), executionCallback);
        }
    }

    private void updateEnginePauseState() {
        this.automationEngine.setPaused(!isEnabled() || !isComponentEnabled());
    }

    private boolean isScheduleInvalid(Schedule<? extends ScheduleData> schedule) {
        return this.remoteDataSubscriber.isRemoteSchedule(schedule) && !this.remoteDataSubscriber.isScheduleValid(schedule);
    }

    private void executeActions(Schedule<? extends ScheduleData> schedule, Actions actions, AutomationDriver.ExecutionCallback executionCallback) {
        Bundle bundle = new Bundle();
        bundle.putString(ActionArguments.ACTION_SCHEDULE_ID_METADATA, schedule.getId());
        ActionCallback actionCallback = new ActionCallback(executionCallback, actions.getActionsMap().size());
        for (Map.Entry next : actions.getActionsMap().entrySet()) {
            this.actionRunRequestFactory.createActionRequest((String) next.getKey()).setValue(next.getValue()).setSituation(6).setMetadata(bundle).run(Looper.getMainLooper(), actionCallback);
        }
    }

    /* access modifiers changed from: private */
    public int getPrepareResultMissedAudience(Schedule schedule) {
        String missBehavior = schedule.getAudience().getMissBehavior();
        missBehavior.hashCode();
        char c = 65535;
        switch (missBehavior.hashCode()) {
            case -1367724422:
                if (missBehavior.equals("cancel")) {
                    c = 0;
                    break;
                }
                break;
            case 3532159:
                if (missBehavior.equals(Audience.MISS_BEHAVIOR_SKIP)) {
                    c = 1;
                    break;
                }
                break;
            case 311930832:
                if (missBehavior.equals(Audience.MISS_BEHAVIOR_PENALIZE)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 3;
            default:
                return 2;
        }
    }

    private static class ActionCallback implements ActionCompletionCallback {
        private final AutomationDriver.ExecutionCallback callback;
        private int pendingActionCallbacks;

        ActionCallback(AutomationDriver.ExecutionCallback executionCallback, int i) {
            this.callback = executionCallback;
            this.pendingActionCallbacks = i;
        }

        public void onFinish(ActionArguments actionArguments, ActionResult actionResult) {
            int i = this.pendingActionCallbacks - 1;
            this.pendingActionCallbacks = i;
            if (i == 0) {
                this.callback.onFinish();
            }
        }
    }
}
