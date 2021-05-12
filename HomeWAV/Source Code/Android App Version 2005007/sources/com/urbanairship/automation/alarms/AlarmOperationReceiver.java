package com.urbanairship.automation.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmOperationReceiver extends BroadcastReceiver {
    static final String ACTION = "com.urbanairship.automation.alarms.ALARM_FIRED";

    public void onReceive(Context context, Intent intent) {
        if (intent != null && ACTION.equals(intent.getAction())) {
            AlarmOperationScheduler.shared(context).onAlarmFired();
        }
    }
}
