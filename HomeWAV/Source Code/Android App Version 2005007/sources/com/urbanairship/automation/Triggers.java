package com.urbanairship.automation;

import com.urbanairship.analytics.CustomEvent;
import com.urbanairship.analytics.location.RegionEvent;
import com.urbanairship.json.JsonMatcher;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.JsonValue;
import com.urbanairship.json.ValueMatcher;
import com.urbanairship.util.UAStringUtil;
import com.urbanairship.util.VersionUtils;

public class Triggers {
    public static LifeCycleTriggerBuilder newForegroundTriggerBuilder() {
        return new LifeCycleTriggerBuilder(1);
    }

    public static LifeCycleTriggerBuilder newBackgroundTriggerBuilder() {
        return new LifeCycleTriggerBuilder(2);
    }

    public static LifeCycleTriggerBuilder newAppInitTriggerBuilder() {
        return new LifeCycleTriggerBuilder(8);
    }

    public static RegionTriggerBuilder newEnterRegionTriggerBuilder() {
        return new RegionTriggerBuilder(3);
    }

    public static RegionTriggerBuilder newExitRegionTriggerBuilder() {
        return new RegionTriggerBuilder(4);
    }

    public static ScreenTriggerBuilder newScreenTriggerBuilder() {
        return new ScreenTriggerBuilder();
    }

    public static CustomEventTriggerBuilder newCustomEventTriggerBuilder() {
        return new CustomEventTriggerBuilder();
    }

    public static ActiveSessionTriggerBuilder newActiveSessionTriggerBuilder() {
        return new ActiveSessionTriggerBuilder();
    }

    public static VersionTriggerBuilder newVersionTriggerBuilder(ValueMatcher valueMatcher) {
        return new VersionTriggerBuilder(valueMatcher);
    }

    public static class LifeCycleTriggerBuilder {
        private double goal;
        private final int type;

        private LifeCycleTriggerBuilder(int i) {
            this.goal = 1.0d;
            this.type = i;
        }

        public LifeCycleTriggerBuilder setGoal(double d) {
            this.goal = d;
            return this;
        }

        public Trigger build() {
            return new Trigger(this.type, this.goal, (JsonPredicate) null);
        }
    }

    public static class RegionTriggerBuilder {
        private double goal;
        private String regionId;
        private final int type;

        private RegionTriggerBuilder(int i) {
            this.goal = 1.0d;
            this.type = i;
        }

        public RegionTriggerBuilder setGoal(double d) {
            this.goal = d;
            return this;
        }

        public RegionTriggerBuilder setRegionId(String str) {
            this.regionId = str;
            return this;
        }

        public Trigger build() {
            JsonPredicate jsonPredicate;
            if (UAStringUtil.isEmpty(this.regionId)) {
                jsonPredicate = null;
            } else {
                jsonPredicate = JsonPredicate.newBuilder().addMatcher(JsonMatcher.newBuilder().setKey(RegionEvent.REGION_ID).setValueMatcher(ValueMatcher.newValueMatcher(JsonValue.wrap(this.regionId))).build()).build();
            }
            return new Trigger(this.type, this.goal, jsonPredicate);
        }
    }

    public static class ScreenTriggerBuilder {
        private double goal;
        private String screenName;

        private ScreenTriggerBuilder() {
            this.goal = 1.0d;
        }

        public ScreenTriggerBuilder setGoal(double d) {
            this.goal = d;
            return this;
        }

        public ScreenTriggerBuilder setScreenName(String str) {
            this.screenName = str;
            return this;
        }

        public Trigger build() {
            JsonPredicate jsonPredicate;
            if (UAStringUtil.isEmpty(this.screenName)) {
                jsonPredicate = null;
            } else {
                jsonPredicate = JsonPredicate.newBuilder().addMatcher(JsonMatcher.newBuilder().setValueMatcher(ValueMatcher.newValueMatcher(JsonValue.wrap(this.screenName))).build()).build();
            }
            return new Trigger(7, this.goal, jsonPredicate);
        }
    }

    public static class CustomEventTriggerBuilder {
        private String eventName;
        private double goal;
        private int type;

        private CustomEventTriggerBuilder() {
            this.goal = 1.0d;
            this.type = 5;
        }

        public CustomEventTriggerBuilder setCountGoal(double d) {
            this.type = 5;
            this.goal = d;
            return this;
        }

        public CustomEventTriggerBuilder setValueGoal(double d) {
            this.type = 6;
            this.goal = d;
            return this;
        }

        public CustomEventTriggerBuilder setEventName(String str) {
            this.eventName = str;
            return this;
        }

        public Trigger build() {
            if (UAStringUtil.isEmpty(this.eventName)) {
                return new Trigger(this.type, this.goal, (JsonPredicate) null);
            }
            return new Trigger(this.type, this.goal, JsonPredicate.newBuilder().setPredicateType(JsonPredicate.AND_PREDICATE_TYPE).addMatcher(JsonMatcher.newBuilder().setKey(CustomEvent.EVENT_NAME).setValueMatcher(ValueMatcher.newValueMatcher(JsonValue.wrap(this.eventName))).build()).build());
        }
    }

    public static class ActiveSessionTriggerBuilder {
        private double goal;

        private ActiveSessionTriggerBuilder() {
            this.goal = 1.0d;
        }

        public ActiveSessionTriggerBuilder setGoal(double d) {
            this.goal = d;
            return this;
        }

        public Trigger build() {
            return new Trigger(9, this.goal, (JsonPredicate) null);
        }
    }

    public static class VersionTriggerBuilder {
        private double goal;
        private final ValueMatcher versionMatcher;

        private VersionTriggerBuilder(ValueMatcher valueMatcher) {
            this.goal = 1.0d;
            this.versionMatcher = valueMatcher;
        }

        public VersionTriggerBuilder setGoal(double d) {
            this.goal = d;
            return this;
        }

        public Trigger build() {
            ValueMatcher valueMatcher = this.versionMatcher;
            return new Trigger(10, this.goal, valueMatcher != null ? VersionUtils.createVersionPredicate(valueMatcher) : null);
        }
    }
}
