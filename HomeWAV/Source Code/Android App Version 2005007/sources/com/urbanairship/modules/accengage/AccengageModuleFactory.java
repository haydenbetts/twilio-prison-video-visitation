package com.urbanairship.modules.accengage;

import android.content.Context;
import com.urbanairship.AirshipVersionInfo;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.push.PushManager;

public interface AccengageModuleFactory extends AirshipVersionInfo {
    AccengageModule build(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, PushManager pushManager, Analytics analytics);
}
