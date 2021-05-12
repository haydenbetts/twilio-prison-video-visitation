package com.urbanairship.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import androidx.core.location.LocationManagerCompat;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Cancelable;
import com.urbanairship.Logger;
import com.urbanairship.PendingResult;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.ResultCallback;
import com.urbanairship.UAirship;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.analytics.location.LocationEvent;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.ApplicationListener;
import com.urbanairship.app.GlobalActivityMonitor;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.modules.location.AirshipLocationClient;
import com.urbanairship.util.AirshipHandlerThread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AirshipLocationManager extends AirshipComponent implements AirshipLocationClient {
    private static final String ALWAYS_ALLOWED = "ALWAYS_ALLOWED";
    private static final String BACKGROUND_UPDATES_ALLOWED_KEY = "com.urbanairship.location.BACKGROUND_UPDATES_ALLOWED";
    private static final String LAST_REQUESTED_LOCATION_OPTIONS_KEY = "com.urbanairship.location.LAST_REQUESTED_LOCATION_OPTIONS";
    private static final String LOCATION_OPTIONS_KEY = "com.urbanairship.location.LOCATION_OPTIONS";
    private static final String LOCATION_UPDATES_ENABLED_KEY = "com.urbanairship.location.LOCATION_UPDATES_ENABLED";
    private static final String NOT_ALLOWED = "NOT_ALLOWED";
    private static final String SYSTEM_LOCATION_DISABLED = "SYSTEM_LOCATION_DISABLED";
    private final ActivityMonitor activityMonitor;
    private final AirshipChannel airshipChannel;
    private final Analytics analytics;
    private Handler backgroundHandler;
    final HandlerThread backgroundThread;
    private final Context context;
    private final ApplicationListener listener;
    /* access modifiers changed from: private */
    public final List<LocationListener> locationListeners;
    /* access modifiers changed from: private */
    public final UALocationProvider locationProvider;
    private final PreferenceDataStore.PreferenceChangeListener preferenceChangeListener;
    private final PreferenceDataStore preferenceDataStore;

    public int getComponentGroup() {
        return 6;
    }

    public static AirshipLocationManager shared() {
        return (AirshipLocationManager) UAirship.shared().requireComponent(AirshipLocationManager.class);
    }

    public AirshipLocationManager(Context context2, PreferenceDataStore preferenceDataStore2, AirshipChannel airshipChannel2, Analytics analytics2) {
        this(context2, preferenceDataStore2, airshipChannel2, analytics2, GlobalActivityMonitor.shared(context2));
    }

    AirshipLocationManager(Context context2, PreferenceDataStore preferenceDataStore2, AirshipChannel airshipChannel2, Analytics analytics2, ActivityMonitor activityMonitor2) {
        super(context2, preferenceDataStore2);
        this.locationListeners = new ArrayList();
        this.preferenceChangeListener = new PreferenceDataStore.PreferenceChangeListener() {
            public void onPreferenceChange(String str) {
                str.hashCode();
                char c = 65535;
                switch (str.hashCode()) {
                    case 56233632:
                        if (str.equals(AirshipLocationManager.LOCATION_OPTIONS_KEY)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 283482798:
                        if (str.equals(AirshipLocationManager.LOCATION_UPDATES_ENABLED_KEY)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 375109006:
                        if (str.equals(AirshipLocationManager.BACKGROUND_UPDATES_ALLOWED_KEY)) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                    case 1:
                    case 2:
                        AirshipLocationManager.this.updateServiceConnection();
                        return;
                    default:
                        return;
                }
            }
        };
        this.context = context2.getApplicationContext();
        this.preferenceDataStore = preferenceDataStore2;
        this.listener = new ApplicationListener() {
            public void onForeground(long j) {
                AirshipLocationManager.this.updateServiceConnection();
            }

            public void onBackground(long j) {
                AirshipLocationManager.this.updateServiceConnection();
            }
        };
        this.activityMonitor = activityMonitor2;
        this.locationProvider = new UALocationProvider(context2, new Intent(context2, LocationReceiver.class).setAction("com.urbanairship.location.ACTION_LOCATION_UPDATE"));
        this.backgroundThread = new AirshipHandlerThread("location");
        this.airshipChannel = airshipChannel2;
        this.analytics = analytics2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.backgroundThread.start();
        this.backgroundHandler = new Handler(this.backgroundThread.getLooper());
        this.preferenceDataStore.addListener(this.preferenceChangeListener);
        this.activityMonitor.addApplicationListener(this.listener);
        updateServiceConnection();
        this.airshipChannel.addChannelRegistrationPayloadExtender(new AirshipChannel.ChannelRegistrationPayloadExtender() {
            public ChannelRegistrationPayload.Builder extend(ChannelRegistrationPayload.Builder builder) {
                return AirshipLocationManager.this.isDataCollectionEnabled() ? builder.setLocationSettings(Boolean.valueOf(AirshipLocationManager.this.isLocationUpdatesEnabled())) : builder;
            }
        });
        this.analytics.addHeaderDelegate(new Analytics.AnalyticsHeaderDelegate() {
            public Map<String, String> onCreateAnalyticsHeaders() {
                return AirshipLocationManager.this.createAnalyticsHeaders();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onComponentEnableChange(boolean z) {
        updateServiceConnection();
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        this.activityMonitor.removeApplicationListener(this.listener);
        this.backgroundThread.quit();
    }

    public boolean isLocationUpdatesEnabled() {
        return this.preferenceDataStore.getBoolean(LOCATION_UPDATES_ENABLED_KEY, false);
    }

    public void setLocationUpdatesEnabled(boolean z) {
        this.preferenceDataStore.put(LOCATION_UPDATES_ENABLED_KEY, z);
    }

    public boolean isBackgroundLocationAllowed() {
        return this.preferenceDataStore.getBoolean(BACKGROUND_UPDATES_ALLOWED_KEY, false);
    }

    public void setBackgroundLocationAllowed(boolean z) {
        this.preferenceDataStore.put(BACKGROUND_UPDATES_ALLOWED_KEY, z);
    }

    public void setLocationRequestOptions(LocationRequestOptions locationRequestOptions) {
        this.preferenceDataStore.put(LOCATION_OPTIONS_KEY, (JsonSerializable) locationRequestOptions);
    }

    public LocationRequestOptions getLocationRequestOptions() {
        LocationRequestOptions parseLocationRequests = parseLocationRequests(LOCATION_OPTIONS_KEY);
        return parseLocationRequests == null ? LocationRequestOptions.newBuilder().build() : parseLocationRequests;
    }

    public void addLocationListener(LocationListener locationListener) {
        if (!UAirship.isMainProcess()) {
            Logger.error("Continuous location update are only available on the main process.", new Object[0]);
            return;
        }
        synchronized (this.locationListeners) {
            this.locationListeners.add(locationListener);
        }
    }

    public void removeLocationListener(LocationListener locationListener) {
        synchronized (this.locationListeners) {
            this.locationListeners.remove(locationListener);
        }
    }

    public PendingResult<Location> requestSingleLocation() {
        return requestSingleLocation(getLocationRequestOptions());
    }

    public PendingResult<Location> requestSingleLocation(final LocationRequestOptions locationRequestOptions) {
        final PendingResult<Location> pendingResult = new PendingResult<>();
        if (!isLocationPermitted() || !isDataCollectionEnabled()) {
            pendingResult.cancel();
            return pendingResult;
        }
        pendingResult.addResultCallback(Looper.getMainLooper(), new ResultCallback<Location>() {
            public void onResult(Location location) {
                if (location != null) {
                    Logger.info("Received single location update: %s", location);
                    AirshipLocationManager.this.recordLocation(location, locationRequestOptions, 1);
                }
            }
        });
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                Cancelable requestSingleLocation;
                if (!pendingResult.isDone() && (requestSingleLocation = AirshipLocationManager.this.locationProvider.requestSingleLocation(locationRequestOptions, new ResultCallback<Location>() {
                    public void onResult(Location location) {
                        pendingResult.setResult(location);
                    }
                })) != null) {
                    pendingResult.addCancelable(requestSingleLocation);
                }
            }
        });
        return pendingResult;
    }

    /* access modifiers changed from: private */
    public void updateServiceConnection() {
        if (UAirship.isMainProcess()) {
            this.backgroundHandler.post(new Runnable() {
                public void run() {
                    if (AirshipLocationManager.this.isDataCollectionEnabled() && AirshipLocationManager.this.isComponentEnabled() && AirshipLocationManager.this.isContinuousLocationUpdatesAllowed()) {
                        LocationRequestOptions locationRequestOptions = AirshipLocationManager.this.getLocationRequestOptions();
                        if (!locationRequestOptions.equals(AirshipLocationManager.this.getLastUpdateOptions()) || !AirshipLocationManager.this.locationProvider.areUpdatesRequested()) {
                            Logger.info("Requesting location updates", new Object[0]);
                            AirshipLocationManager.this.locationProvider.requestLocationUpdates(locationRequestOptions);
                            AirshipLocationManager.this.setLastUpdateOptions(locationRequestOptions);
                        }
                    } else if (AirshipLocationManager.this.locationProvider.areUpdatesRequested()) {
                        Logger.info("Stopping location updates.", new Object[0]);
                        AirshipLocationManager.this.locationProvider.cancelRequests();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void setLastUpdateOptions(LocationRequestOptions locationRequestOptions) {
        this.preferenceDataStore.put(LAST_REQUESTED_LOCATION_OPTIONS_KEY, (JsonSerializable) locationRequestOptions);
    }

    /* access modifiers changed from: package-private */
    public LocationRequestOptions getLastUpdateOptions() {
        return parseLocationRequests(LAST_REQUESTED_LOCATION_OPTIONS_KEY);
    }

    /* access modifiers changed from: package-private */
    public boolean isContinuousLocationUpdatesAllowed() {
        return isDataCollectionEnabled() && isLocationUpdatesEnabled() && (isBackgroundLocationAllowed() || this.activityMonitor.isAppForegrounded());
    }

    /* access modifiers changed from: package-private */
    public boolean isLocationPermitted() {
        try {
            int checkSelfPermission = ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION");
            int checkSelfPermission2 = ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION");
            if (checkSelfPermission == 0 || checkSelfPermission2 == 0) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            Logger.error(e, "UALocationManager - Unable to retrieve location permissions.", new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void onLocationUpdate(final Location location) {
        if (isContinuousLocationUpdatesAllowed()) {
            Logger.info("Received location update: %s", location);
            synchronized (this.locationListeners) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        Iterator it = new ArrayList(AirshipLocationManager.this.locationListeners).iterator();
                        while (it.hasNext()) {
                            ((LocationListener) it.next()).onLocationChanged(location);
                        }
                    }
                });
            }
            recordLocation(location, getLocationRequestOptions(), 0);
        }
    }

    /* access modifiers changed from: package-private */
    public void onSystemLocationProvidersChanged() {
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                AirshipLocationManager.this.locationProvider.onSystemLocationProvidersChanged(AirshipLocationManager.this.getLocationRequestOptions());
            }
        });
    }

    public boolean isOptIn() {
        return isLocationPermitted() && isLocationUpdatesEnabled() && isDataCollectionEnabled();
    }

    /* access modifiers changed from: protected */
    public void onDataCollectionEnabledChanged(boolean z) {
        updateServiceConnection();
    }

    private LocationRequestOptions parseLocationRequests(String str) {
        JsonValue jsonValue = this.preferenceDataStore.getJsonValue(str);
        if (jsonValue.isNull()) {
            return null;
        }
        try {
            return LocationRequestOptions.fromJson(jsonValue);
        } catch (JsonException e) {
            Logger.error(e, "UALocationManager - Failed parsing LocationRequestOptions from JSON.", new Object[0]);
            return null;
        } catch (IllegalArgumentException e2) {
            Logger.error(e2, "UALocationManager - Invalid LocationRequestOptions from JSON.", new Object[0]);
            return null;
        }
    }

    public void recordLocation(Location location, LocationRequestOptions locationRequestOptions, int i) {
        int i2;
        int i3;
        if (locationRequestOptions == null) {
            i3 = -1;
            i2 = -1;
        } else {
            int minDistance = (int) locationRequestOptions.getMinDistance();
            if (locationRequestOptions.getPriority() == 1) {
                i2 = minDistance;
                i3 = 1;
            } else {
                i2 = minDistance;
                i3 = 2;
            }
        }
        this.analytics.addEvent(new LocationEvent(location, i, i3, i2, this.activityMonitor.isAppForegrounded()));
    }

    private boolean isSystemLocationServicesEnabled() {
        LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
        if (locationManager == null) {
            return false;
        }
        return LocationManagerCompat.isLocationEnabled(locationManager);
    }

    /* access modifiers changed from: private */
    public Map<String, String> createAnalyticsHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put("X-UA-Location-Permission", isLocationPermitted() ? isSystemLocationServicesEnabled() ? ALWAYS_ALLOWED : SYSTEM_LOCATION_DISABLED : NOT_ALLOWED);
        hashMap.put("X-UA-Location-Service-Enabled", Boolean.toString(isLocationUpdatesEnabled()));
        return hashMap;
    }
}
