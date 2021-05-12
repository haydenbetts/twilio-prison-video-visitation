package com.urbanairship.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.urbanairship.Cancelable;
import com.urbanairship.CancelableOperation;
import com.urbanairship.Logger;
import com.urbanairship.ResultCallback;
import com.urbanairship.google.GooglePlayServicesUtilWrapper;

class FusedLocationAdapter implements LocationAdapter {
    private static final int REQUEST_CODE = 1;
    /* access modifiers changed from: private */
    public final FusedLocationProviderClient client;

    public int getRequestCode() {
        return 1;
    }

    public void onSystemLocationProvidersChanged(Context context, LocationRequestOptions locationRequestOptions, PendingIntent pendingIntent) {
    }

    public FusedLocationAdapter(Context context) {
        this.client = LocationServices.getFusedLocationProviderClient(context);
    }

    public Cancelable requestSingleLocation(Context context, LocationRequestOptions locationRequestOptions, ResultCallback<Location> resultCallback) {
        SingleLocationRequest singleLocationRequest = new SingleLocationRequest(locationRequestOptions, resultCallback);
        singleLocationRequest.run();
        return singleLocationRequest;
    }

    public void cancelLocationUpdates(Context context, PendingIntent pendingIntent) {
        Logger.verbose("FusedLocationAdapter - Canceling updates.", new Object[0]);
        this.client.removeLocationUpdates(pendingIntent);
        pendingIntent.cancel();
    }

    public void requestLocationUpdates(Context context, LocationRequestOptions locationRequestOptions, PendingIntent pendingIntent) {
        Logger.verbose("FusedLocationAdapter - Requesting updates: %s", locationRequestOptions);
        this.client.requestLocationUpdates(createLocationRequest(locationRequestOptions), pendingIntent);
    }

    public boolean isAvailable(Context context) {
        try {
            if (GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(context) == 0) {
                return true;
            }
            Logger.debug("FusedLocationAdapter - Google Play services is currently unavailable, unable to connect for fused location.", new Object[0]);
            return false;
        } catch (IllegalStateException e) {
            Logger.debug(e, "FusedLocationAdapter - Google Play services is currently unavailable, unable to connect for fused location.", new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public LocationRequest createLocationRequest(LocationRequestOptions locationRequestOptions) {
        LocationRequest smallestDisplacement = LocationRequest.create().setInterval(locationRequestOptions.getMinTime()).setSmallestDisplacement(locationRequestOptions.getMinDistance());
        int priority = locationRequestOptions.getPriority();
        if (priority == 1) {
            smallestDisplacement.setPriority(100);
        } else if (priority == 2) {
            smallestDisplacement.setPriority(102);
        } else if (priority == 3) {
            smallestDisplacement.setPriority(104);
        } else if (priority == 4) {
            smallestDisplacement.setPriority(105);
        }
        return smallestDisplacement;
    }

    private class SingleLocationRequest extends CancelableOperation {
        private final LocationCallback locationCallback;
        private final LocationRequest locationRequest;

        SingleLocationRequest(LocationRequestOptions locationRequestOptions, final ResultCallback<Location> resultCallback) {
            super(Looper.getMainLooper());
            this.locationCallback = new LocationCallback(FusedLocationAdapter.this) {
                public void onLocationResult(LocationResult locationResult) {
                    resultCallback.onResult(locationResult.getLastLocation());
                }
            };
            this.locationRequest = FusedLocationAdapter.this.createLocationRequest(locationRequestOptions).setNumUpdates(1);
        }

        /* access modifiers changed from: protected */
        public void onCancel() {
            Logger.verbose("FusedLocationAdapter - Canceling single location request.", new Object[0]);
            FusedLocationAdapter.this.client.removeLocationUpdates(this.locationCallback);
        }

        /* access modifiers changed from: protected */
        public void onRun() {
            Logger.verbose("FusedLocationAdapter - Starting single location request.", new Object[0]);
            FusedLocationAdapter.this.client.requestLocationUpdates(this.locationRequest, this.locationCallback, Looper.getMainLooper());
        }
    }
}
