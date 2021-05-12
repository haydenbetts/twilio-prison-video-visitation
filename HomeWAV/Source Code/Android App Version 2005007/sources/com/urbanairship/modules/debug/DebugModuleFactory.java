package com.urbanairship.modules.debug;

import android.content.Context;
import com.urbanairship.AirshipVersionInfo;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.modules.Module;

public interface DebugModuleFactory extends AirshipVersionInfo {
    Module build(Context context, PreferenceDataStore preferenceDataStore);
}
