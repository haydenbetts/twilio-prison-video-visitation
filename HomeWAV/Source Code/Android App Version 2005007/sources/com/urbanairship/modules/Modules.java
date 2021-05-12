package com.urbanairship.modules;

import android.content.Context;
import com.urbanairship.AirshipVersionInfo;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.NamedUser;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.modules.aaid.AdIdModuleFactory;
import com.urbanairship.modules.accengage.AccengageModule;
import com.urbanairship.modules.accengage.AccengageModuleFactory;
import com.urbanairship.modules.automation.AutomationModuleFactory;
import com.urbanairship.modules.debug.DebugModuleFactory;
import com.urbanairship.modules.location.LocationModule;
import com.urbanairship.modules.location.LocationModuleFactory;
import com.urbanairship.modules.messagecenter.MessageCenterModuleFactory;
import com.urbanairship.push.PushManager;
import com.urbanairship.remotedata.RemoteData;

public class Modules {
    private static final String ACCENGAGE_MODULE_FACTORY = "com.urbanairship.accengage.AccengageModuleFactoryImpl";
    private static final String AD_ID_FACTORY = "com.urbanairship.aaid.AdIdModuleFactoryImpl";
    private static final String AUTOMATION_MODULE_FACTORY = "com.urbanairship.automation.AutomationModuleFactoryImpl";
    private static final String DEBUG_MODULE_FACTORY = "com.urbanairship.debug.DebugModuleFactoryImpl";
    private static final String LOCATION_MODULE_FACTORY = "com.urbanairship.location.LocationModuleFactoryImpl";
    private static final String MESSAGE_CENTER_MODULE_FACTORY = "com.urbanairship.messagecenter.MessageCenterModuleFactoryImpl";

    public static AccengageModule accengage(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, PushManager pushManager, Analytics analytics) {
        try {
            AccengageModuleFactory accengageModuleFactory = (AccengageModuleFactory) createFactory(ACCENGAGE_MODULE_FACTORY, AccengageModuleFactory.class);
            if (accengageModuleFactory != null) {
                return accengageModuleFactory.build(context, preferenceDataStore, airshipChannel, pushManager, analytics);
            }
            return null;
        } catch (Exception e) {
            Logger.error(e, "Failed to build Accengage module", new Object[0]);
            return null;
        }
    }

    public static Module messageCenter(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, PushManager pushManager) {
        try {
            MessageCenterModuleFactory messageCenterModuleFactory = (MessageCenterModuleFactory) createFactory(MESSAGE_CENTER_MODULE_FACTORY, MessageCenterModuleFactory.class);
            if (messageCenterModuleFactory != null) {
                return messageCenterModuleFactory.build(context, preferenceDataStore, airshipChannel, pushManager);
            }
            return null;
        } catch (Exception e) {
            Logger.error(e, "Failed to build Message Center module", new Object[0]);
            return null;
        }
    }

    public static LocationModule location(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, Analytics analytics) {
        try {
            LocationModuleFactory locationModuleFactory = (LocationModuleFactory) createFactory(LOCATION_MODULE_FACTORY, LocationModuleFactory.class);
            if (locationModuleFactory != null) {
                return locationModuleFactory.build(context, preferenceDataStore, airshipChannel, analytics);
            }
            return null;
        } catch (Exception e) {
            Logger.error(e, "Failed to build Location module", new Object[0]);
            return null;
        }
    }

    public static Module automation(Context context, PreferenceDataStore preferenceDataStore, AirshipRuntimeConfig airshipRuntimeConfig, AirshipChannel airshipChannel, PushManager pushManager, Analytics analytics, RemoteData remoteData, NamedUser namedUser) {
        try {
            AutomationModuleFactory automationModuleFactory = (AutomationModuleFactory) createFactory(AUTOMATION_MODULE_FACTORY, AutomationModuleFactory.class);
            if (automationModuleFactory != null) {
                return automationModuleFactory.build(context, preferenceDataStore, airshipRuntimeConfig, airshipChannel, pushManager, analytics, remoteData, namedUser);
            }
            return null;
        } catch (Exception e) {
            Logger.error(e, "Failed to build Automation module", new Object[0]);
            return null;
        }
    }

    public static Module debug(Context context, PreferenceDataStore preferenceDataStore) {
        try {
            DebugModuleFactory debugModuleFactory = (DebugModuleFactory) createFactory(DEBUG_MODULE_FACTORY, DebugModuleFactory.class);
            if (debugModuleFactory != null) {
                return debugModuleFactory.build(context, preferenceDataStore);
            }
            return null;
        } catch (Exception e) {
            Logger.error(e, "Failed to build Debug module", new Object[0]);
            return null;
        }
    }

    public static Module adId(Context context, PreferenceDataStore preferenceDataStore) {
        try {
            AdIdModuleFactory adIdModuleFactory = (AdIdModuleFactory) createFactory(AD_ID_FACTORY, AdIdModuleFactory.class);
            if (adIdModuleFactory != null) {
                return adIdModuleFactory.build(context, preferenceDataStore);
            }
            return null;
        } catch (Exception e) {
            Logger.error(e, "Failed to build Ad Id module", new Object[0]);
            return null;
        }
    }

    private static <T extends AirshipVersionInfo> T createFactory(String str, Class<T> cls) {
        try {
            T t = (AirshipVersionInfo) Class.forName(str).asSubclass(cls).newInstance();
            if (UAirship.getVersion().equals(t.getAirshipVersion())) {
                return t;
            }
            Logger.error("Unable to load module with factory %s, versions do not match. Core Version: %s, Module Version: %s.", cls, UAirship.getVersion(), t.getAirshipVersion());
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Exception e) {
            Logger.error(e, "Unable to create module factory %s", cls);
            return null;
        }
    }
}
