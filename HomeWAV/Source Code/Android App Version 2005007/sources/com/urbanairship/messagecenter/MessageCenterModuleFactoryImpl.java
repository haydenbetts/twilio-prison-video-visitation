package com.urbanairship.messagecenter;

import android.content.Context;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.modules.Module;
import com.urbanairship.modules.messagecenter.MessageCenterModuleFactory;
import com.urbanairship.push.PushManager;

public class MessageCenterModuleFactoryImpl implements MessageCenterModuleFactory {
    public String getAirshipVersion() {
        return "14.0.2";
    }

    public String getPackageVersion() {
        return BuildConfig.SDK_VERSION;
    }

    public Module build(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, PushManager pushManager) {
        return Module.singleComponent(new MessageCenter(context, preferenceDataStore, airshipChannel, pushManager), R.xml.ua_message_center_actions);
    }
}
