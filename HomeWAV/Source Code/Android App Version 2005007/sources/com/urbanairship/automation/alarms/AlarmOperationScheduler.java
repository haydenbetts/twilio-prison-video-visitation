package com.urbanairship.automation.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.urbanairship.Logger;
import com.urbanairship.util.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlarmOperationScheduler implements OperationScheduler {
    private static AlarmOperationScheduler shared;
    private final Comparator<PendingOperation> OPERATION_COMPARATOR;
    private final Clock clock;
    private final Context context;
    private final AlarmManagerDelegate delegate;
    private final List<PendingOperation> pendingOperations;

    interface AlarmManagerDelegate {
        void onSchedule(long j, PendingIntent pendingIntent);
    }

    private static class PendingOperation {
        final Runnable operation;
        final long scheduledTime;

        PendingOperation(long j, Runnable runnable) {
            this.operation = runnable;
            this.scheduledTime = j;
        }
    }

    AlarmOperationScheduler(final Context context2) {
        this(context2, Clock.DEFAULT_CLOCK, new AlarmManagerDelegate() {
            public void onSchedule(long j, PendingIntent pendingIntent) {
                AlarmManager alarmManager = (AlarmManager) context2.getSystemService(NotificationCompat.CATEGORY_ALARM);
                if (alarmManager != null) {
                    alarmManager.set(3, j, pendingIntent);
                    return;
                }
                throw new IllegalStateException("AlarmManager unavailable");
            }
        });
    }

    AlarmOperationScheduler(Context context2, Clock clock2, AlarmManagerDelegate alarmManagerDelegate) {
        this.OPERATION_COMPARATOR = new Comparator<PendingOperation>() {
            public int compare(PendingOperation pendingOperation, PendingOperation pendingOperation2) {
                return Long.valueOf(pendingOperation.scheduledTime).compareTo(Long.valueOf(pendingOperation2.scheduledTime));
            }
        };
        this.pendingOperations = new ArrayList();
        this.context = context2;
        this.clock = clock2;
        this.delegate = alarmManagerDelegate;
    }

    public static AlarmOperationScheduler shared(Context context2) {
        synchronized (AlarmOperationScheduler.class) {
            if (shared == null) {
                shared = new AlarmOperationScheduler(context2.getApplicationContext());
            }
        }
        return shared;
    }

    public void schedule(long j, Runnable runnable) {
        PendingOperation pendingOperation = new PendingOperation(this.clock.elapsedRealtime() + j, runnable);
        Logger.verbose("AlarmOperationScheduler - Operation scheduled with %d delay", Long.valueOf(j));
        synchronized (this.pendingOperations) {
            this.pendingOperations.add(pendingOperation);
            Collections.sort(this.pendingOperations, this.OPERATION_COMPARATOR);
            scheduleAlarm();
        }
    }

    /* access modifiers changed from: package-private */
    public void onAlarmFired() {
        Logger.verbose("AlarmOperationScheduler - Alarm fired", new Object[0]);
        long elapsedRealtime = this.clock.elapsedRealtime();
        synchronized (this.pendingOperations) {
            for (PendingOperation pendingOperation : new ArrayList(this.pendingOperations)) {
                if (pendingOperation.scheduledTime <= elapsedRealtime) {
                    pendingOperation.operation.run();
                    this.pendingOperations.remove(pendingOperation);
                }
            }
            scheduleAlarm();
        }
    }

    private void scheduleAlarm() {
        synchronized (this.pendingOperations) {
            if (!this.pendingOperations.isEmpty()) {
                long j = this.pendingOperations.get(0).scheduledTime;
                try {
                    this.delegate.onSchedule(j, PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, AlarmOperationReceiver.class).setAction("com.urbanairship.automation.alarms.ALARM_FIRED"), 134217728));
                    Logger.verbose("AlarmOperationScheduler - Next alarm set %d", Long.valueOf(j - this.clock.elapsedRealtime()));
                } catch (Exception e) {
                    Logger.error(e, "AlarmOperationScheduler - Failed to schedule alarm.", new Object[0]);
                }
            }
        }
    }
}
