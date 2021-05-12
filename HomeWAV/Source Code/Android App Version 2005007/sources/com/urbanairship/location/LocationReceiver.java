package com.urbanairship.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Parcelable;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LocationReceiver extends BroadcastReceiver {
    static final String ACTION_LOCATION_UPDATE = "com.urbanairship.location.ACTION_LOCATION_UPDATE";
    private static final long AIRSHIP_WAIT_TIME_MS = 5000;
    private static final long BROADCAST_INTENT_TIME_MS = 9000;
    private final Executor executor;
    private final Callable<AirshipLocationManager> locationManagerCallable;

    LocationReceiver(Executor executor2, Callable<AirshipLocationManager> callable) {
        this.executor = executor2;
        this.locationManagerCallable = callable;
    }

    public LocationReceiver() {
        this(AirshipExecutors.newSerialExecutor(), new Callable<AirshipLocationManager>() {
            public AirshipLocationManager call() {
                UAirship.waitForTakeOff(5000);
                return AirshipLocationManager.shared();
            }
        });
    }

    public void onReceive(Context context, final Intent intent) {
        Autopilot.automaticTakeOff(context);
        final BroadcastReceiver.PendingResult goAsync = goAsync();
        this.executor.execute(new Runnable() {
            public void run() {
                try {
                    AirshipExecutors.THREAD_POOL_EXECUTOR.submit(new Runnable() {
                        public void run() {
                            LocationReceiver.this.processIntent(intent);
                        }
                    }).get(LocationReceiver.BROADCAST_INTENT_TIME_MS, TimeUnit.MILLISECONDS);
                } catch (ExecutionException e) {
                    Logger.error(e, "Location update exception", new Object[0]);
                } catch (InterruptedException unused) {
                    Logger.error("Location update interrupted", new Object[0]);
                    Thread.currentThread().interrupt();
                } catch (TimeoutException unused2) {
                    Logger.error("Location update took too long, ending broadcast.", new Object[0]);
                }
                BroadcastReceiver.PendingResult pendingResult = goAsync;
                if (pendingResult != null) {
                    pendingResult.finish();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void processIntent(Intent intent) {
        if (intent != null && intent.getAction() != null) {
            if (!ACTION_LOCATION_UPDATE.equals(intent.getAction())) {
                Logger.verbose("LocationReceiver - Received intent with invalid action: %s", intent.getAction());
                return;
            }
            Logger.verbose("LocationReceiver - Received location update", new Object[0]);
            try {
                onLocationUpdate(this.locationManagerCallable.call(), intent);
            } catch (Exception unused) {
                Logger.error("Airship took too long to takeOff. Dropping location update.", new Object[0]);
            }
        }
    }

    private void onLocationUpdate(AirshipLocationManager airshipLocationManager, Intent intent) {
        Parcelable parcelable;
        try {
            if (intent.hasExtra("providerEnabled")) {
                Logger.debug("LocationReceiver - One of the location providers was enabled or disabled.", new Object[0]);
                airshipLocationManager.onSystemLocationProvidersChanged();
                return;
            }
            if (intent.hasExtra("location")) {
                parcelable = intent.getParcelableExtra("location");
            } else {
                parcelable = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);
            }
            Location location = (Location) parcelable;
            if (location != null) {
                airshipLocationManager.onLocationUpdate(location);
            }
        } catch (Exception e) {
            Logger.error(e, "LocationReceiver - Unable to extract location.", new Object[0]);
        }
    }
}
