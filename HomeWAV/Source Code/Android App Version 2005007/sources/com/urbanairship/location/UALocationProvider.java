package com.urbanairship.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import com.urbanairship.Cancelable;
import com.urbanairship.Logger;
import com.urbanairship.ResultCallback;
import com.urbanairship.google.PlayServicesUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UALocationProvider {
    private final List<LocationAdapter> adapters;
    private LocationAdapter availableAdapter;
    private final Context context;
    private boolean isConnected = false;
    private final Intent locationUpdateIntent;

    UALocationProvider(Context context2, Intent intent) {
        ArrayList arrayList = new ArrayList();
        this.adapters = arrayList;
        this.context = context2;
        this.locationUpdateIntent = intent;
        if (PlayServicesUtils.isGooglePlayStoreAvailable(context2) && PlayServicesUtils.isFusedLocationDependencyAvailable()) {
            arrayList.add(new FusedLocationAdapter(context2));
        }
        arrayList.add(new StandardLocationAdapter());
    }

    UALocationProvider(Context context2, Intent intent, LocationAdapter... locationAdapterArr) {
        ArrayList arrayList = new ArrayList();
        this.adapters = arrayList;
        this.context = context2;
        this.locationUpdateIntent = intent;
        arrayList.addAll(Arrays.asList(locationAdapterArr));
    }

    /* access modifiers changed from: package-private */
    public void cancelRequests() {
        Logger.verbose("UALocationProvider - Canceling location requests.", new Object[0]);
        connect();
        LocationAdapter locationAdapter = this.availableAdapter;
        if (locationAdapter == null) {
            Logger.debug("UALocationProvider - Ignoring request, connected adapter unavailable.", new Object[0]);
            return;
        }
        try {
            PendingIntent pendingIntent = getPendingIntent(locationAdapter, 536870912);
            if (pendingIntent != null) {
                this.availableAdapter.cancelLocationUpdates(this.context, pendingIntent);
            }
        } catch (Exception e) {
            Logger.error(e, "Unable to cancel location updates.", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void requestLocationUpdates(LocationRequestOptions locationRequestOptions) {
        connect();
        if (this.availableAdapter == null) {
            Logger.debug("UALocationProvider - Ignoring request, connected adapter unavailable.", new Object[0]);
            return;
        }
        Logger.verbose("UALocationProvider - Requesting location updates: %s", locationRequestOptions);
        try {
            PendingIntent pendingIntent = getPendingIntent(this.availableAdapter, 134217728);
            if (pendingIntent != null) {
                this.availableAdapter.requestLocationUpdates(this.context, locationRequestOptions, pendingIntent);
            } else {
                Logger.error("Unable to request location updates. Null pending intent.", new Object[0]);
            }
        } catch (Exception e) {
            Logger.error(e, "Unable to request location updates.", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public Cancelable requestSingleLocation(LocationRequestOptions locationRequestOptions, ResultCallback<Location> resultCallback) {
        connect();
        if (this.availableAdapter == null) {
            Logger.debug("UALocationProvider - Ignoring request, connected adapter unavailable.", new Object[0]);
        }
        Logger.verbose("UALocationProvider - Requesting single location update: %s", locationRequestOptions);
        try {
            return this.availableAdapter.requestSingleLocation(this.context, locationRequestOptions, resultCallback);
        } catch (Exception e) {
            Logger.error(e, "Unable to request location.", new Object[0]);
            return null;
        }
    }

    private void connect() {
        if (!this.isConnected) {
            for (LocationAdapter next : this.adapters) {
                Logger.verbose("UALocationProvider - Attempting to connect to location adapter: %s", next);
                if (next.isAvailable(this.context)) {
                    if (this.availableAdapter == null) {
                        Logger.verbose("UALocationProvider - Using adapter: %s", next);
                        this.availableAdapter = next;
                    }
                    try {
                        PendingIntent pendingIntent = getPendingIntent(next, 536870912);
                        if (pendingIntent != null) {
                            next.cancelLocationUpdates(this.context, pendingIntent);
                        }
                    } catch (Exception e) {
                        Logger.error(e, "Unable to cancel location updates.", new Object[0]);
                    }
                } else {
                    Logger.verbose("UALocationProvider - Adapter unavailable: %s", next);
                }
            }
            this.isConnected = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void onSystemLocationProvidersChanged(LocationRequestOptions locationRequestOptions) {
        PendingIntent pendingIntent;
        Logger.verbose("UALocationProvider - Available location providers changed.", new Object[0]);
        connect();
        LocationAdapter locationAdapter = this.availableAdapter;
        if (locationAdapter != null && (pendingIntent = getPendingIntent(locationAdapter, 134217728)) != null) {
            this.availableAdapter.onSystemLocationProvidersChanged(this.context, locationRequestOptions, pendingIntent);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean areUpdatesRequested() {
        connect();
        LocationAdapter locationAdapter = this.availableAdapter;
        if (locationAdapter == null || getPendingIntent(locationAdapter, 536870912) == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public PendingIntent getPendingIntent(LocationAdapter locationAdapter, int i) {
        try {
            return PendingIntent.getBroadcast(this.context, locationAdapter.getRequestCode(), this.locationUpdateIntent, i);
        } catch (Exception e) {
            Logger.error(e, "Unable to get pending intent.", new Object[0]);
            return null;
        }
    }
}
