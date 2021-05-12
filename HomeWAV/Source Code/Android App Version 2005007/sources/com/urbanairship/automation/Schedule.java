package com.urbanairship.automation;

import com.urbanairship.automation.ScheduleData;
import com.urbanairship.automation.actions.Actions;
import com.urbanairship.automation.deferred.Deferred;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class Schedule<T extends ScheduleData> {
    public static final long TRIGGER_LIMIT = 10;
    public static final String TYPE_ACTION = "actions";
    public static final String TYPE_DEFERRED = "deferred";
    public static final String TYPE_IN_APP_MESSAGE = "in_app_message";
    /* access modifiers changed from: private */
    public final Audience audience;
    /* access modifiers changed from: private */
    public final T data;
    /* access modifiers changed from: private */
    public final ScheduleDelay delay;
    /* access modifiers changed from: private */
    public final long editGracePeriod;
    /* access modifiers changed from: private */
    public final long end;
    private final String group;
    /* access modifiers changed from: private */
    public final String id;
    /* access modifiers changed from: private */
    public final long interval;
    /* access modifiers changed from: private */
    public final int limit;
    /* access modifiers changed from: private */
    public final JsonMap metadata;
    /* access modifiers changed from: private */
    public final int priority;
    /* access modifiers changed from: private */
    public final long start;
    /* access modifiers changed from: private */
    public final List<Trigger> triggers;
    /* access modifiers changed from: private */
    public final String type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private Schedule(Builder<T> builder) {
        this.id = builder.id == null ? UUID.randomUUID().toString() : builder.id;
        this.metadata = builder.metadata == null ? JsonMap.EMPTY_MAP : builder.metadata;
        this.limit = builder.limit;
        this.start = builder.start;
        this.end = builder.end;
        this.triggers = Collections.unmodifiableList(builder.triggers);
        this.delay = builder.delay == null ? ScheduleDelay.newBuilder().build() : builder.delay;
        this.priority = builder.priority;
        this.editGracePeriod = builder.editGracePeriod;
        this.interval = builder.interval;
        this.data = builder.data;
        this.type = builder.type;
        this.group = builder.group;
        this.audience = builder.audience;
    }

    public String getId() {
        return this.id;
    }

    public JsonMap getMetadata() {
        return this.metadata;
    }

    public Audience getAudience() {
        return this.audience;
    }

    public String getType() {
        return this.type;
    }

    public List<Trigger> getTriggers() {
        return this.triggers;
    }

    public T getData() {
        return this.data;
    }

    public <R extends ScheduleData> R coerceType() {
        try {
            return this.data;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Unexpected data", e);
        }
    }

    public int getLimit() {
        return this.limit;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getGroup() {
        return this.group;
    }

    public long getStart() {
        return this.start;
    }

    public long getEnd() {
        return this.end;
    }

    public ScheduleDelay getDelay() {
        return this.delay;
    }

    public long getEditGracePeriod() {
        return this.editGracePeriod;
    }

    public long getInterval() {
        return this.interval;
    }

    /* access modifiers changed from: package-private */
    public JsonValue getDataAsJson() {
        return this.data.toJsonValue();
    }

    public static Builder<Actions> newBuilder(Actions actions) {
        return new Builder<>(TYPE_ACTION, actions);
    }

    public static Builder<InAppMessage> newBuilder(InAppMessage inAppMessage) {
        return new Builder<>("in_app_message", inAppMessage);
    }

    static Builder<Deferred> newBuilder(Deferred deferred) {
        return new Builder<>(TYPE_DEFERRED, deferred);
    }

    public static <T extends ScheduleData> Builder<T> newBuilder(Schedule<T> schedule) {
        return new Builder<>();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Schedule schedule = (Schedule) obj;
        if (this.limit != schedule.limit || this.start != schedule.start || this.end != schedule.end || this.priority != schedule.priority || this.editGracePeriod != schedule.editGracePeriod || this.interval != schedule.interval || !this.id.equals(schedule.id)) {
            return false;
        }
        JsonMap jsonMap = this.metadata;
        if (jsonMap == null ? schedule.metadata != null : !jsonMap.equals(schedule.metadata)) {
            return false;
        }
        if (!this.triggers.equals(schedule.triggers)) {
            return false;
        }
        ScheduleDelay scheduleDelay = this.delay;
        if (scheduleDelay == null ? schedule.delay != null : !scheduleDelay.equals(schedule.delay)) {
            return false;
        }
        String str = this.group;
        if (str == null ? schedule.group != null : !str.equals(schedule.group)) {
            return false;
        }
        Audience audience2 = this.audience;
        if (audience2 == null ? schedule.audience != null : !audience2.equals(schedule.audience)) {
            return false;
        }
        if (!this.type.equals(schedule.type)) {
            return false;
        }
        return this.data.equals(schedule.data);
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        JsonMap jsonMap = this.metadata;
        int i = 0;
        int hashCode2 = jsonMap != null ? jsonMap.hashCode() : 0;
        long j = this.start;
        long j2 = this.end;
        int hashCode3 = (((((((((hashCode + hashCode2) * 31) + this.limit) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.triggers.hashCode()) * 31;
        ScheduleDelay scheduleDelay = this.delay;
        int hashCode4 = scheduleDelay != null ? scheduleDelay.hashCode() : 0;
        long j3 = this.editGracePeriod;
        long j4 = this.interval;
        int i2 = (((((((hashCode3 + hashCode4) * 31) + this.priority) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        String str = this.group;
        int hashCode5 = (i2 + (str != null ? str.hashCode() : 0)) * 31;
        Audience audience2 = this.audience;
        if (audience2 != null) {
            i = audience2.hashCode();
        }
        return ((((hashCode5 + i) * 31) + this.type.hashCode()) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "Schedule{id='" + this.id + '\'' + ", metadata=" + this.metadata + ", limit=" + this.limit + ", start=" + this.start + ", end=" + this.end + ", triggers=" + this.triggers + ", delay=" + this.delay + ", priority=" + this.priority + ", editGracePeriod=" + this.editGracePeriod + ", interval=" + this.interval + ", group='" + this.group + '\'' + ", audience=" + this.audience + ", type='" + this.type + '\'' + ", data=" + this.data + '}';
    }

    public static class Builder<T extends ScheduleData> {
        /* access modifiers changed from: private */
        public Audience audience;
        /* access modifiers changed from: private */
        public T data;
        /* access modifiers changed from: private */
        public ScheduleDelay delay;
        /* access modifiers changed from: private */
        public long editGracePeriod;
        /* access modifiers changed from: private */
        public long end;
        /* access modifiers changed from: private */
        public String group;
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public long interval;
        /* access modifiers changed from: private */
        public int limit;
        /* access modifiers changed from: private */
        public JsonMap metadata;
        /* access modifiers changed from: private */
        public int priority;
        /* access modifiers changed from: private */
        public long start;
        /* access modifiers changed from: private */
        public final List<Trigger> triggers;
        /* access modifiers changed from: private */
        public String type;

        private Builder(Schedule<T> schedule) {
            this.limit = 1;
            this.start = -1;
            this.end = -1;
            ArrayList arrayList = new ArrayList();
            this.triggers = arrayList;
            this.id = schedule.id;
            this.metadata = schedule.metadata == null ? JsonMap.EMPTY_MAP : schedule.metadata;
            this.limit = schedule.limit;
            this.start = schedule.start;
            this.end = schedule.end;
            arrayList.addAll(schedule.triggers);
            this.delay = schedule.delay;
            this.data = schedule.data;
            this.type = schedule.type;
            this.priority = schedule.priority;
            this.editGracePeriod = schedule.editGracePeriod;
            this.interval = schedule.interval;
            this.audience = schedule.audience;
        }

        private Builder(String str, T t) {
            this.limit = 1;
            this.start = -1;
            this.end = -1;
            this.triggers = new ArrayList();
            this.type = str;
            this.data = t;
        }

        public Builder<T> setAudience(Audience audience2) {
            this.audience = audience2;
            return this;
        }

        public Builder<T> setId(String str) {
            this.id = str;
            return this;
        }

        public Builder<T> setMetadata(JsonMap jsonMap) {
            this.metadata = jsonMap;
            return this;
        }

        public Builder<T> setLimit(int i) {
            this.limit = i;
            return this;
        }

        public Builder<T> setStart(long j) {
            this.start = j;
            return this;
        }

        public Builder<T> setEnd(long j) {
            this.end = j;
            return this;
        }

        public Builder<T> setDelay(ScheduleDelay scheduleDelay) {
            this.delay = scheduleDelay;
            return this;
        }

        public Builder<T> setPriority(int i) {
            this.priority = i;
            return this;
        }

        public Builder<T> setGroup(String str) {
            this.group = str;
            return this;
        }

        public Builder<T> addTrigger(Trigger trigger) {
            this.triggers.add(trigger);
            return this;
        }

        public Builder<T> addTriggers(List<Trigger> list) {
            this.triggers.addAll(list);
            return this;
        }

        public Builder<T> setTriggers(List<Trigger> list) {
            this.triggers.clear();
            if (list != null) {
                this.triggers.addAll(list);
            }
            return this;
        }

        public Builder<T> setEditGracePeriod(long j, TimeUnit timeUnit) {
            this.editGracePeriod = timeUnit.toMillis(j);
            return this;
        }

        public Builder<T> setInterval(long j, TimeUnit timeUnit) {
            this.interval = timeUnit.toMillis(j);
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0033  */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x0035  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0048  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.urbanairship.automation.Schedule<T> build() {
            /*
                r9 = this;
                T r0 = r9.data
                java.lang.String r1 = "Missing data."
                com.urbanairship.util.Checks.checkNotNull(r0, r1)
                java.lang.String r0 = r9.type
                java.lang.String r1 = "Missing type."
                com.urbanairship.util.Checks.checkNotNull(r0, r1)
                long r0 = r9.start
                r2 = 0
                r4 = 0
                r5 = 1
                int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r6 < 0) goto L_0x0025
                long r6 = r9.end
                int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r8 < 0) goto L_0x0025
                int r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
                if (r2 > 0) goto L_0x0023
                goto L_0x0025
            L_0x0023:
                r0 = 0
                goto L_0x0026
            L_0x0025:
                r0 = 1
            L_0x0026:
                java.lang.String r1 = "End must be on or after start."
                com.urbanairship.util.Checks.checkArgument(r0, r1)
                java.util.List<com.urbanairship.automation.Trigger> r0 = r9.triggers
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x0035
                r0 = 1
                goto L_0x0036
            L_0x0035:
                r0 = 0
            L_0x0036:
                java.lang.String r1 = "Must contain at least 1 trigger."
                com.urbanairship.util.Checks.checkArgument(r0, r1)
                java.util.List<com.urbanairship.automation.Trigger> r0 = r9.triggers
                int r0 = r0.size()
                long r0 = (long) r0
                r2 = 10
                int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r6 > 0) goto L_0x0049
                r4 = 1
            L_0x0049:
                java.lang.String r0 = "No more than 10 triggers allowed."
                com.urbanairship.util.Checks.checkArgument(r4, r0)
                com.urbanairship.automation.Schedule r0 = new com.urbanairship.automation.Schedule
                r1 = 0
                r0.<init>(r9)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.Schedule.Builder.build():com.urbanairship.automation.Schedule");
        }
    }
}
