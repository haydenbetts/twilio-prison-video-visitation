package com.urbanairship.modules.automation;

import android.content.Context;
import com.urbanairship.AirshipVersionInfo;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.NamedUser;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.modules.Module;
import com.urbanairship.push.PushManager;
import com.urbanairship.remotedata.RemoteData;

public interface AutomationModuleFactory extends AirshipVersionInfo {
    Module build(Context context, PreferenceDataStore preferenceDataStore, AirshipRuntimeConfig airshipRuntimeConfig, AirshipChannel airshipChannel, PushManager pushManager, Analytics analytics, RemoteData remoteData, NamedUser namedUser);
}
