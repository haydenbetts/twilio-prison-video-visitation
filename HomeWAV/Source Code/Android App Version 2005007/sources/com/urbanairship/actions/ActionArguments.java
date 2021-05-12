package com.urbanairship.actions;

import android.os.Bundle;

public final class ActionArguments {
    public static final String ACTION_SCHEDULE_ID_METADATA = "com.urbanairship.ACTION_SCHEDULE_ID";
    public static final String PUSH_MESSAGE_METADATA = "com.urbanairship.PUSH_MESSAGE";
    public static final String REGISTRY_ACTION_NAME_METADATA = "com.urbanairship.REGISTRY_ACTION_NAME";
    public static final String REMOTE_INPUT_METADATA = "com.urbanairship.REMOTE_INPUT";
    public static final String RICH_PUSH_ID_METADATA = "com.urbanairship.RICH_PUSH_ID_METADATA";
    private final Bundle metadata;
    private final int situation;
    private final ActionValue value;

    public ActionArguments(int i, ActionValue actionValue, Bundle bundle) {
        Bundle bundle2;
        this.situation = i;
        this.value = actionValue == null ? new ActionValue() : actionValue;
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
        }
        this.metadata = bundle2;
    }

    public ActionValue getValue() {
        return this.value;
    }

    public int getSituation() {
        return this.situation;
    }

    public Bundle getMetadata() {
        return this.metadata;
    }

    public String toString() {
        return "ActionArguments { situation: " + this.situation + ", value: " + this.value + ", metadata: " + this.metadata + " }";
    }
}
