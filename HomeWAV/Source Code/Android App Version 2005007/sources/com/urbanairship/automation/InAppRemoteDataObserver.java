package com.urbanairship.automation;

import android.os.Looper;
import com.urbanairship.Logger;
import com.urbanairship.Predicate;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.automation.Schedule;
import com.urbanairship.automation.ScheduleEdits;
import com.urbanairship.automation.actions.Actions;
import com.urbanairship.automation.deferred.Deferred;
import com.urbanairship.iam.InAppAutomationScheduler;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.reactive.Schedulers;
import com.urbanairship.reactive.Subscriber;
import com.urbanairship.reactive.Subscription;
import com.urbanairship.remotedata.RemoteData;
import com.urbanairship.remotedata.RemoteDataPayload;
import com.urbanairship.util.DateUtils;
import com.urbanairship.util.UAStringUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class InAppRemoteDataObserver {
    private static final String ACTIONS_KEY = "actions";
    private static final String AUDIENCE_KEY = "audience";
    private static final String CREATED_JSON_KEY = "created";
    private static final String DEFERRED_KEY = "deferred";
    private static final String DELAY_KEY = "delay";
    private static final String EDIT_GRACE_PERIOD_KEY = "edit_grace_period";
    private static final String END_KEY = "end";
    private static final String GROUP_KEY = "group";
    private static final String IAM_PAYLOAD_TYPE = "in_app_messages";
    private static final String INTERVAL_KEY = "interval";
    private static final String LAST_PAYLOAD_METADATA = "com.urbanairship.iam.data.LAST_PAYLOAD_METADATA";
    private static final String LAST_PAYLOAD_TIMESTAMP_KEY = "com.urbanairship.iam.data.LAST_PAYLOAD_TIMESTAMP";
    private static final String LEGACY_MESSAGE_ID_KEY = "message_id";
    private static final String LIMIT_KEY = "limit";
    private static final String MESSAGES_JSON_KEY = "in_app_messages";
    private static final String MESSAGE_KEY = "message";
    private static final String PRIORITY_KEY = "priority";
    static final String REMOTE_DATA_METADATA = "com.urbanairship.iaa.REMOTE_DATA_METADATA";
    private static final String SCHEDULE_ID_KEY = "id";
    private static final String SCHEDULE_NEW_USER_CUTOFF_TIME_KEY = "com.urbanairship.iam.data.NEW_USER_TIME";
    private static final String START_KEY = "start";
    private static final String TRIGGERS_KEY = "triggers";
    private static final String TYPE_KEY = "type";
    private static final String UPDATED_JSON_KEY = "last_updated";
    private final List<Listener> listeners = new ArrayList();
    /* access modifiers changed from: private */
    public final PreferenceDataStore preferenceDataStore;
    private final RemoteData remoteData;
    private Subscription subscription;

    interface Listener {
        void onSchedulesUpdated();
    }

    InAppRemoteDataObserver(PreferenceDataStore preferenceDataStore2, RemoteData remoteData2) {
        this.preferenceDataStore = preferenceDataStore2;
        this.remoteData = remoteData2;
    }

    /* access modifiers changed from: package-private */
    public void addListener(Listener listener) {
        synchronized (this.listeners) {
            this.listeners.add(listener);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeListener(Listener listener) {
        synchronized (this.listeners) {
            this.listeners.remove(listener);
        }
    }

    /* access modifiers changed from: package-private */
    public void subscribe(Looper looper, final InAppAutomationScheduler inAppAutomationScheduler) {
        cancel();
        this.subscription = this.remoteData.payloadsForType("in_app_messages").filter(new Predicate<RemoteDataPayload>() {
            public boolean apply(RemoteDataPayload remoteDataPayload) {
                if (remoteDataPayload.getTimestamp() != InAppRemoteDataObserver.this.preferenceDataStore.getLong(InAppRemoteDataObserver.LAST_PAYLOAD_TIMESTAMP_KEY, -1)) {
                    return true;
                }
                return !remoteDataPayload.getMetadata().equals(InAppRemoteDataObserver.this.getLastPayloadMetadata());
            }
        }).observeOn(Schedulers.looper(looper)).subscribe(new Subscriber<RemoteDataPayload>() {
            public void onNext(RemoteDataPayload remoteDataPayload) {
                try {
                    InAppRemoteDataObserver.this.processPayload(remoteDataPayload, inAppAutomationScheduler);
                    Logger.debug("InAppRemoteDataObserver - Finished processing messages.", new Object[0]);
                } catch (Exception e) {
                    Logger.error(e, "InAppRemoteDataObserver - Failed to process payload: ", new Object[0]);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        Subscription subscription2 = this.subscription;
        if (subscription2 != null) {
            subscription2.cancel();
        }
    }

    /* access modifiers changed from: private */
    public void processPayload(RemoteDataPayload remoteDataPayload, InAppAutomationScheduler inAppAutomationScheduler) throws ExecutionException, InterruptedException {
        long j;
        long j2;
        InAppAutomationScheduler inAppAutomationScheduler2 = inAppAutomationScheduler;
        long j3 = this.preferenceDataStore.getLong(LAST_PAYLOAD_TIMESTAMP_KEY, -1);
        JsonMap lastPayloadMetadata = getLastPayloadMetadata();
        JsonMap build = JsonMap.newBuilder().put(REMOTE_DATA_METADATA, (JsonSerializable) remoteDataPayload.getMetadata()).build();
        boolean equals = remoteDataPayload.getMetadata().equals(lastPayloadMetadata);
        ArrayList arrayList = new ArrayList();
        Set<String> filterRemoteSchedules = filterRemoteSchedules(inAppAutomationScheduler.getSchedules().get());
        ArrayList arrayList2 = new ArrayList();
        Iterator<JsonValue> it = remoteDataPayload.getData().opt("in_app_messages").optList().iterator();
        while (it.hasNext()) {
            JsonValue next = it.next();
            try {
                long parseIso8601 = DateUtils.parseIso8601(next.optMap().opt(CREATED_JSON_KEY).getString());
                long parseIso86012 = DateUtils.parseIso8601(next.optMap().opt(UPDATED_JSON_KEY).getString());
                String parseScheduleId = parseScheduleId(next);
                if (UAStringUtil.isEmpty(parseScheduleId)) {
                    Logger.error("Missing schedule ID: %s", next);
                } else {
                    arrayList2.add(parseScheduleId);
                    if (!equals || parseIso86012 > j3) {
                        if (parseIso8601 > j3) {
                            try {
                                Schedule<? extends ScheduleData> parseSchedule = parseSchedule(parseScheduleId, next, build);
                                if (checkSchedule(parseSchedule, parseIso8601)) {
                                    arrayList.add(parseSchedule);
                                    Logger.debug("New in-app automation: %s", parseSchedule);
                                }
                            } catch (Exception e) {
                                Logger.error(e, "Failed to parse in-app automation: %s", next);
                            }
                        } else if (filterRemoteSchedules.contains(parseScheduleId)) {
                            try {
                                ScheduleEdits<? extends ScheduleData> parseEdits = parseEdits(next);
                                ScheduleEdits.Builder<? extends ScheduleData> metadata = ScheduleEdits.newBuilder(parseEdits).setMetadata(build);
                                if (parseEdits.getEnd() == null) {
                                    j = j3;
                                    j2 = -1;
                                } else {
                                    j = j3;
                                    j2 = parseEdits.getEnd().longValue();
                                }
                                try {
                                    ScheduleEdits<? extends ScheduleData> build2 = metadata.setEnd(j2).setStart(parseEdits.getStart() == null ? -1 : parseEdits.getStart().longValue()).build();
                                    Boolean bool = inAppAutomationScheduler2.editSchedule(parseScheduleId, build2).get();
                                    if (bool != null && bool.booleanValue()) {
                                        Logger.debug("Updated in-app automation: %s with edits: %s", parseScheduleId, build2);
                                    }
                                } catch (JsonException e2) {
                                    e = e2;
                                    Logger.error(e, "Failed to parse in-app automation edits: %s", parseScheduleId);
                                    j3 = j;
                                }
                            } catch (JsonException e3) {
                                e = e3;
                                j = j3;
                                Logger.error(e, "Failed to parse in-app automation edits: %s", parseScheduleId);
                                j3 = j;
                            }
                            j3 = j;
                        }
                        j = j3;
                        j3 = j;
                    }
                }
            } catch (ParseException e4) {
                j = j3;
                Logger.error(e4, "Failed to parse in-app message timestamps: %s", next);
            }
        }
        if (!arrayList.isEmpty()) {
            inAppAutomationScheduler2.schedule((List<Schedule<? extends ScheduleData>>) arrayList).get();
        }
        HashSet<String> hashSet = new HashSet<>(filterRemoteSchedules);
        hashSet.removeAll(arrayList2);
        if (!hashSet.isEmpty()) {
            ScheduleEdits<?> build3 = ScheduleEdits.newBuilder().setMetadata(build).setStart(remoteDataPayload.getTimestamp()).setEnd(remoteDataPayload.getTimestamp()).build();
            for (String editSchedule : hashSet) {
                inAppAutomationScheduler2.editSchedule(editSchedule, build3).get();
            }
        }
        this.preferenceDataStore.put(LAST_PAYLOAD_TIMESTAMP_KEY, remoteDataPayload.getTimestamp());
        this.preferenceDataStore.put(LAST_PAYLOAD_METADATA, (JsonSerializable) remoteDataPayload.getMetadata());
        synchronized (this.listeners) {
            if (!this.listeners.isEmpty()) {
                for (Listener onSchedulesUpdated : new ArrayList(this.listeners)) {
                    onSchedulesUpdated.onSchedulesUpdated();
                }
            }
        }
    }

    private static String parseScheduleId(JsonValue jsonValue) {
        String string = jsonValue.optMap().opt("id").getString();
        return string == null ? jsonValue.optMap().opt("message").optMap().opt("message_id").getString() : string;
    }

    private static Audience parseAudience(JsonValue jsonValue) throws JsonException {
        JsonValue jsonValue2 = jsonValue.optMap().get(AUDIENCE_KEY);
        if (jsonValue2 == null) {
            jsonValue2 = jsonValue.optMap().opt("message").optMap().get(AUDIENCE_KEY);
        }
        if (jsonValue2 == null) {
            return null;
        }
        return Audience.fromJson(jsonValue2);
    }

    private boolean checkSchedule(Schedule<? extends ScheduleData> schedule, long j) {
        return AudienceChecks.checkAudienceForScheduling(UAirship.getApplicationContext(), schedule.getAudience(), j <= getScheduleNewUserCutOffTime());
    }

    private Set<String> filterRemoteSchedules(Collection<Schedule<? extends ScheduleData>> collection) {
        if (collection == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (Schedule next : collection) {
            if (isRemoteSchedule(next)) {
                hashSet.add(next.getId());
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: package-private */
    public long getScheduleNewUserCutOffTime() {
        return this.preferenceDataStore.getLong(SCHEDULE_NEW_USER_CUTOFF_TIME_KEY, -1);
    }

    /* access modifiers changed from: package-private */
    public void setScheduleNewUserCutOffTime(long j) {
        this.preferenceDataStore.put(SCHEDULE_NEW_USER_CUTOFF_TIME_KEY, j);
    }

    /* access modifiers changed from: private */
    public JsonMap getLastPayloadMetadata() {
        return this.preferenceDataStore.getJsonValue(LAST_PAYLOAD_METADATA).optMap();
    }

    public static Schedule<? extends ScheduleData> parseSchedule(String str, JsonValue jsonValue, JsonMap jsonMap) throws JsonException {
        Schedule.Builder builder;
        JsonMap optMap = jsonValue.optMap();
        String string = optMap.opt("type").getString("in_app_message");
        string.hashCode();
        char c = 65535;
        switch (string.hashCode()) {
            case -1161803523:
                if (string.equals("actions")) {
                    c = 0;
                    break;
                }
                break;
            case -379237425:
                if (string.equals("in_app_message")) {
                    c = 1;
                    break;
                }
                break;
            case 647890911:
                if (string.equals("deferred")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                JsonMap map = optMap.opt("actions").getMap();
                if (map != null) {
                    builder = Schedule.newBuilder(new Actions(map));
                    break;
                } else {
                    throw new JsonException("Missing actions payload");
                }
            case 1:
                builder = Schedule.newBuilder(InAppMessage.fromJson(optMap.opt("message"), InAppMessage.SOURCE_REMOTE_DATA));
                break;
            case 2:
                builder = Schedule.newBuilder(Deferred.fromJson(optMap.opt("deferred")));
                break;
            default:
                throw new JsonException("Unexpected type: " + string);
        }
        builder.setId(str).setMetadata(jsonMap).setGroup(optMap.opt(GROUP_KEY).getString()).setLimit(optMap.opt("limit").getInt(1)).setPriority(optMap.opt("priority").getInt(0)).setAudience(parseAudience(jsonValue));
        if (optMap.containsKey("end")) {
            try {
                builder.setEnd(DateUtils.parseIso8601(optMap.opt("end").getString()));
            } catch (ParseException e) {
                throw new JsonException("Invalid schedule end time", e);
            }
        }
        if (optMap.containsKey("start")) {
            try {
                builder.setStart(DateUtils.parseIso8601(optMap.opt("start").getString()));
            } catch (ParseException e2) {
                throw new JsonException("Invalid schedule start time", e2);
            }
        }
        Iterator<JsonValue> it = optMap.opt("triggers").optList().iterator();
        while (it.hasNext()) {
            builder.addTrigger(Trigger.fromJson(it.next()));
        }
        if (optMap.containsKey(DELAY_KEY)) {
            builder.setDelay(ScheduleDelay.fromJson(optMap.opt(DELAY_KEY)));
        }
        if (optMap.containsKey(EDIT_GRACE_PERIOD_KEY)) {
            builder.setEditGracePeriod(optMap.opt(EDIT_GRACE_PERIOD_KEY).getLong(0), TimeUnit.DAYS);
        }
        if (optMap.containsKey(INTERVAL_KEY)) {
            builder.setInterval(optMap.opt(INTERVAL_KEY).getLong(0), TimeUnit.SECONDS);
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException e3) {
            throw new JsonException("Invalid schedule", e3);
        }
    }

    public static ScheduleEdits<? extends ScheduleData> parseEdits(JsonValue jsonValue) throws JsonException {
        ScheduleEdits.Builder builder;
        JsonMap optMap = jsonValue.optMap();
        String string = optMap.opt("type").getString("in_app_message");
        string.hashCode();
        char c = 65535;
        switch (string.hashCode()) {
            case -1161803523:
                if (string.equals("actions")) {
                    c = 0;
                    break;
                }
                break;
            case -379237425:
                if (string.equals("in_app_message")) {
                    c = 1;
                    break;
                }
                break;
            case 647890911:
                if (string.equals("deferred")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                JsonMap map = optMap.opt("actions").getMap();
                if (map != null) {
                    builder = ScheduleEdits.newBuilder(new Actions(map));
                    break;
                } else {
                    throw new JsonException("Missing actions payload");
                }
            case 1:
                builder = ScheduleEdits.newBuilder(InAppMessage.fromJson(optMap.opt("message"), InAppMessage.SOURCE_REMOTE_DATA));
                break;
            case 2:
                builder = ScheduleEdits.newBuilder(Deferred.fromJson(optMap.opt("deferred")));
                break;
            default:
                throw new JsonException("Unexpected schedule type: " + string);
        }
        if (optMap.containsKey("limit")) {
            builder.setLimit(optMap.opt("limit").getInt(1));
        }
        if (optMap.containsKey("priority")) {
            builder.setPriority(optMap.opt("priority").getInt(0));
        }
        if (optMap.containsKey("end")) {
            try {
                builder.setEnd(DateUtils.parseIso8601(optMap.opt("end").getString()));
            } catch (ParseException e) {
                throw new JsonException("Invalid schedule end time", e);
            }
        }
        if (optMap.containsKey("start")) {
            try {
                builder.setStart(DateUtils.parseIso8601(optMap.opt("start").getString()));
            } catch (ParseException e2) {
                throw new JsonException("Invalid schedule start time", e2);
            }
        }
        if (optMap.containsKey(EDIT_GRACE_PERIOD_KEY)) {
            builder.setEditGracePeriod(optMap.opt(EDIT_GRACE_PERIOD_KEY).getLong(0), TimeUnit.DAYS);
        }
        if (optMap.containsKey(INTERVAL_KEY)) {
            builder.setInterval(optMap.opt(INTERVAL_KEY).getLong(0), TimeUnit.SECONDS);
        }
        builder.setAudience(parseAudience(jsonValue));
        return builder.build();
    }

    public boolean isUpToDate() {
        return this.remoteData.isMetadataCurrent(getLastPayloadMetadata());
    }

    public boolean isScheduleValid(Schedule<? extends ScheduleData> schedule) {
        return this.remoteData.isMetadataCurrent(schedule.getMetadata().opt(REMOTE_DATA_METADATA).optMap());
    }

    /* access modifiers changed from: package-private */
    public boolean isRemoteSchedule(Schedule<? extends ScheduleData> schedule) {
        if (schedule.getMetadata().containsKey(REMOTE_DATA_METADATA)) {
            return true;
        }
        if ("in_app_message".equals(schedule.getType())) {
            return InAppMessage.SOURCE_REMOTE_DATA.equals(((InAppMessage) schedule.coerceType()).getSource());
        }
        return false;
    }
}
