package com.urbanairship.modules.messagecenter;

import android.content.Context;
import com.urbanairship.AirshipVersionInfo;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.modules.Module;
import com.urbanairship.push.PushManager;

public interface MessageCenterModuleFactory extends AirshipVersionInfo {
    Module build(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, PushManager pushManager);
}
