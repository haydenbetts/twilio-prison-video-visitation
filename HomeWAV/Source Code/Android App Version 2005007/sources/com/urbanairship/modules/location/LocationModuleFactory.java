package com.urbanairship.modules.location;

import android.content.Context;
import com.urbanairship.AirshipVersionInfo;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.channel.AirshipChannel;

public interface LocationModuleFactory extends AirshipVersionInfo {
    LocationModule build(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, Analytics analytics);
}
