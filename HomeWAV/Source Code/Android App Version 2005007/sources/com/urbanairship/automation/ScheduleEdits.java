package com.urbanairship.automation;

import com.urbanairship.automation.ScheduleData;
import com.urbanairship.automation.actions.Actions;
import com.urbanairship.automation.deferred.Deferred;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.json.JsonMap;
import java.util.concurrent.TimeUnit;

public class ScheduleEdits<T extends ScheduleData> {
    private final Audience audience;
    /* access modifiers changed from: private */
    public final T data;
    private final Long editGracePeriod;
    /* access modifiers changed from: private */
    public final Long end;
    private final Long interval;
    /* access modifiers changed from: private */
    public final Integer limit;
    private final JsonMap metadata;
    /* access modifiers changed from: private */
    public final Integer priority;
    /* access modifiers changed from: private */
    public final Long start;
    /* access modifiers changed from: private */
    public final String type;

    private ScheduleEdits(Builder<T> builder) {
        this.limit = builder.limit;
        this.start = builder.start;
        this.end = builder.end;
        this.data = builder.data;
        this.type = builder.type;
        this.priority = builder.priority;
        this.interval = builder.interval;
        this.editGracePeriod = builder.editGracePeriod;
        this.metadata = builder.metadata;
        this.audience = builder.audience;
    }

    public T getData() {
        return this.data;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public Long getStart() {
        return this.start;
    }

    public Long getEnd() {
        return this.end;
    }

    public Long getInterval() {
        return this.interval;
    }

    public Long getEditGracePeriod() {
        return this.editGracePeriod;
    }

    public JsonMap getMetadata() {
        return this.metadata;
    }

    public String getType() {
        return this.type;
    }

    public Audience getAudience() {
        return this.audience;
    }

    public static Builder<?> newBuilder() {
        return new Builder<>();
    }

    public static Builder<Actions> newBuilder(Actions actions) {
        return new Builder<>(Schedule.TYPE_ACTION, actions);
    }

    public static Builder<InAppMessage> newBuilder(InAppMessage inAppMessage) {
        return new Builder<>("in_app_message", inAppMessage);
    }

    public static Builder<Deferred> newBuilder(Deferred deferred) {
        return new Builder<>(Schedule.TYPE_DEFERRED, deferred);
    }

    public static <T extends ScheduleData> Builder<T> newBuilder(ScheduleEdits<T> scheduleEdits) {
        return new Builder<>();
    }

    public static class Builder<T extends ScheduleData> {
        /* access modifiers changed from: private */
        public Audience audience;
        /* access modifiers changed from: private */
        public T data;
        /* access modifiers changed from: private */
        public Long editGracePeriod;
        /* access modifiers changed from: private */
        public Long end;
        /* access modifiers changed from: private */
        public Long interval;
        /* access modifiers changed from: private */
        public Integer limit;
        /* access modifiers changed from: private */
        public JsonMap metadata;
        /* access modifiers changed from: private */
        public Integer priority;
        /* access modifiers changed from: private */
        public Long start;
        /* access modifiers changed from: private */
        public String type;

        private Builder() {
        }

        private Builder(String str, T t) {
            this.type = str;
            this.data = t;
        }

        private Builder(ScheduleEdits<T> scheduleEdits) {
            this.limit = scheduleEdits.limit;
            this.start = scheduleEdits.start;
            this.end = scheduleEdits.end;
            this.data = scheduleEdits.data;
            this.priority = scheduleEdits.priority;
            this.type = scheduleEdits.type;
        }

        public Builder<T> setLimit(int i) {
            this.limit = Integer.valueOf(i);
            return this;
        }

        public Builder<T> setStart(long j) {
            this.start = Long.valueOf(j);
            return this;
        }

        public Builder<T> setEnd(long j) {
            this.end = Long.valueOf(j);
            return this;
        }

        public Builder<T> setPriority(int i) {
            this.priority = Integer.valueOf(i);
            return this;
        }

        public Builder<T> setEditGracePeriod(long j, TimeUnit timeUnit) {
            this.editGracePeriod = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        public Builder<T> setInterval(long j, TimeUnit timeUnit) {
            this.interval = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        public Builder<T> setMetadata(JsonMap jsonMap) {
            this.metadata = jsonMap;
            return this;
        }

        public Builder<T> setAudience(Audience audience2) {
            this.audience = audience2;
            return this;
        }

        public ScheduleEdits<T> build() {
            return new ScheduleEdits<>(this);
        }
    }
}
