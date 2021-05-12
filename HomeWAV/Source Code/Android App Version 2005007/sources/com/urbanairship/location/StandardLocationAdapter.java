package com.urbanairship.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.urbanairship.Cancelable;
import com.urbanairship.CancelableOperation;
import com.urbanairship.Logger;
import com.urbanairship.ResultCallback;
import com.urbanairship.util.UAStringUtil;

class StandardLocationAdapter implements LocationAdapter {
    private static final int REQUEST_CODE = 2;
    private static String currentProvider;

    public int getRequestCode() {
        return 2;
    }

    public boolean isAvailable(Context context) {
        return true;
    }

    StandardLocationAdapter() {
    }

    public void requestLocationUpdates(Context context, LocationRequestOptions locationRequestOptions, PendingIntent pendingIntent) {
        Criteria createCriteria = createCriteria(locationRequestOptions);
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        locationManager.removeUpdates(pendingIntent);
        for (String next : locationManager.getProviders(createCriteria, false)) {
            Logger.verbose("StandardLocationAdapter - Update listening provider enable/disabled for: %s", next);
            locationManager.requestLocationUpdates(next, Long.MAX_VALUE, Float.MAX_VALUE, pendingIntent);
        }
        String bestProvider = getBestProvider(context, createCriteria, locationRequestOptions);
        if (!UAStringUtil.isEmpty(bestProvider)) {
            Logger.verbose("StandardLocationAdapter - Requesting location updates from provider: %s", bestProvider);
            currentProvider = bestProvider;
            locationManager.requestLocationUpdates(bestProvider, locationRequestOptions.getMinTime(), locationRequestOptions.getMinDistance(), pendingIntent);
        }
    }

    public void onSystemLocationProvidersChanged(Context context, LocationRequestOptions locationRequestOptions, PendingIntent pendingIntent) {
        String bestProvider = getBestProvider(context, createCriteria(locationRequestOptions), locationRequestOptions);
        if (UAStringUtil.isEmpty(currentProvider) || !currentProvider.equals(bestProvider)) {
            Logger.verbose("StandardLocationAdapter - Refreshing updates, best provider might of changed.", new Object[0]);
            requestLocationUpdates(context, locationRequestOptions, pendingIntent);
            return;
        }
        Logger.verbose("StandardLocationAdapter - Already listening for updates from the best provider: %s", currentProvider);
    }

    public void cancelLocationUpdates(Context context, PendingIntent pendingIntent) {
        ((LocationManager) context.getSystemService("location")).removeUpdates(pendingIntent);
        Logger.verbose("StandardLocationAdapter - Canceling location updates.", new Object[0]);
        currentProvider = null;
    }

    public Cancelable requestSingleLocation(Context context, LocationRequestOptions locationRequestOptions, ResultCallback<Location> resultCallback) {
        SingleLocationRequest singleLocationRequest = new SingleLocationRequest(context, locationRequestOptions, resultCallback);
        singleLocationRequest.run();
        return singleLocationRequest;
    }

    /* access modifiers changed from: private */
    public Criteria createCriteria(LocationRequestOptions locationRequestOptions) {
        Criteria criteria = new Criteria();
        int priority = locationRequestOptions.getPriority();
        if (priority == 1) {
            criteria.setAccuracy(1);
            criteria.setPowerRequirement(3);
        } else if (priority == 2) {
            criteria.setAccuracy(2);
            criteria.setPowerRequirement(2);
        } else if (priority == 3 || priority == 4) {
            criteria.setAccuracy(0);
            criteria.setPowerRequirement(1);
        }
        return criteria;
    }

    /* access modifiers changed from: private */
    public String getBestProvider(Context context, Criteria criteria, LocationRequestOptions locationRequestOptions) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationRequestOptions.getPriority() != 4) {
            return locationManager.getBestProvider(criteria, true);
        }
        if (locationManager.getProviders(criteria, true).contains("passive")) {
            return "passive";
        }
        return null;
    }

    private class SingleLocationRequest extends CancelableOperation {
        private final Context context;
        /* access modifiers changed from: private */
        public final Criteria criteria;
        /* access modifiers changed from: private */
        public String currentProvider = null;
        private final AndroidLocationListener currentProviderListener;
        private final LocationManager locationManager;
        private final LocationRequestOptions options;
        private final AndroidLocationListener providerEnabledListeners;

        SingleLocationRequest(final Context context2, final LocationRequestOptions locationRequestOptions, final ResultCallback<Location> resultCallback) {
            this.context = context2.getApplicationContext();
            this.options = locationRequestOptions;
            this.criteria = StandardLocationAdapter.this.createCriteria(locationRequestOptions);
            this.locationManager = (LocationManager) context2.getSystemService("location");
            this.currentProviderListener = new AndroidLocationListener(StandardLocationAdapter.this) {
                public void onLocationChanged(Location location) {
                    SingleLocationRequest.this.stopUpdates();
                    resultCallback.onResult(location);
                }

                public void onProviderDisabled(String str) {
                    Logger.verbose("StandardLocationAdapter - Provider disabled: %s", str);
                    synchronized (SingleLocationRequest.this) {
                        if (!SingleLocationRequest.this.isDone()) {
                            SingleLocationRequest.this.listenForLocationChanges(context2);
                        }
                    }
                }
            };
            this.providerEnabledListeners = new AndroidLocationListener(StandardLocationAdapter.this) {
                public void onProviderEnabled(String str) {
                    String access$500;
                    Logger.verbose("StandardLocationAdapter - Provider enabled: %s", str);
                    synchronized (SingleLocationRequest.this) {
                        if (!SingleLocationRequest.this.isDone() && (access$500 = StandardLocationAdapter.this.getBestProvider(context2, SingleLocationRequest.this.criteria, locationRequestOptions)) != null && !access$500.equals(SingleLocationRequest.this.currentProvider)) {
                            SingleLocationRequest.this.listenForLocationChanges(context2);
                        }
                    }
                }
            };
        }

        /* access modifiers changed from: protected */
        public void onRun() {
            if (this.options.getPriority() != 4) {
                listenForProvidersEnabled();
            }
            listenForLocationChanges(this.context);
        }

        /* access modifiers changed from: private */
        public void listenForLocationChanges(Context context2) {
            if (this.currentProvider != null) {
                this.locationManager.removeUpdates(this.currentProviderListener);
            }
            String access$500 = StandardLocationAdapter.this.getBestProvider(context2, this.criteria, this.options);
            this.currentProvider = access$500;
            if (access$500 != null) {
                Logger.verbose("StandardLocationAdapter - Single request using provider: %s", access$500);
                this.locationManager.requestLocationUpdates(access$500, 0, 0.0f, this.currentProviderListener);
            }
        }

        private void listenForProvidersEnabled() {
            for (String next : this.locationManager.getProviders(this.criteria, false)) {
                Logger.verbose("StandardLocationAdapter - Single location request listening provider enable/disabled for: %s", next);
                this.locationManager.requestLocationUpdates(next, Long.MAX_VALUE, Float.MAX_VALUE, this.providerEnabledListeners);
            }
        }

        /* access modifiers changed from: protected */
        public void onCancel() {
            Logger.verbose("StandardLocationAdapter - Canceling single request.", new Object[0]);
            stopUpdates();
        }

        /* access modifiers changed from: private */
        public void stopUpdates() {
            this.locationManager.removeUpdates(this.currentProviderListener);
            this.locationManager.removeUpdates(this.providerEnabledListeners);
        }
    }

    private static class AndroidLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
        }

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        private AndroidLocationListener() {
        }
    }
}
