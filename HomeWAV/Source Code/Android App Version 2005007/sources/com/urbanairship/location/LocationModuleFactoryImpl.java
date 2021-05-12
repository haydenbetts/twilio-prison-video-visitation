package com.urbanairship.location;

import android.content.Context;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.modules.location.LocationModule;
import com.urbanairship.modules.location.LocationModuleFactory;

public class LocationModuleFactoryImpl implements LocationModuleFactory {
    public String getAirshipVersion() {
        return "14.0.2";
    }

    public String getPackageVersion() {
        return BuildConfig.SDK_VERSION;
    }

    public LocationModule build(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, Analytics analytics) {
        AirshipLocationManager airshipLocationManager = new AirshipLocationManager(context, preferenceDataStore, airshipChannel, analytics);
        return new LocationModule(airshipLocationManager, airshipLocationManager);
    }
}
