package com.urbanairship.modules.aaid;

import android.content.Context;
import com.urbanairship.AirshipVersionInfo;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.modules.Module;

public interface AdIdModuleFactory extends AirshipVersionInfo {
    Module build(Context context, PreferenceDataStore preferenceDataStore);
}
