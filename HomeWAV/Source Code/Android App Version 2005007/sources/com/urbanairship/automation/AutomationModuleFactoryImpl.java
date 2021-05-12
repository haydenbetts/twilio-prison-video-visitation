package com.urbanairship.automation;

import android.content.Context;
import com.urbanairship.AirshipComponent;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.NamedUser;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.iam.LegacyInAppMessageManager;
import com.urbanairship.modules.Module;
import com.urbanairship.modules.automation.AutomationModuleFactory;
import com.urbanairship.push.PushManager;
import com.urbanairship.remotedata.RemoteData;
import java.util.Arrays;

public class AutomationModuleFactoryImpl implements AutomationModuleFactory {
    public String getAirshipVersion() {
        return "14.0.2";
    }

    public String getPackageVersion() {
        return BuildConfig.SDK_VERSION;
    }

    public Module build(Context context, PreferenceDataStore preferenceDataStore, AirshipRuntimeConfig airshipRuntimeConfig, AirshipChannel airshipChannel, PushManager pushManager, Analytics analytics, RemoteData remoteData, NamedUser namedUser) {
        Context context2 = context;
        PreferenceDataStore preferenceDataStore2 = preferenceDataStore;
        Analytics analytics2 = analytics;
        InAppAutomation inAppAutomation = new InAppAutomation(context2, preferenceDataStore2, airshipRuntimeConfig, analytics2, remoteData, airshipChannel, namedUser);
        return Module.multipleComponents(Arrays.asList(new AirshipComponent[]{inAppAutomation, new LegacyInAppMessageManager(context2, preferenceDataStore2, inAppAutomation, analytics2, pushManager)}), R.xml.ua_automation_actions);
    }
}
