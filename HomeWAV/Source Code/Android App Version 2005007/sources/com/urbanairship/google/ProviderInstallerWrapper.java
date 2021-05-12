package com.urbanairship.google;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

class ProviderInstallerWrapper {
    ProviderInstallerWrapper() {
    }

    static int installIfNeeded(Context context) {
        try {
            ProviderInstaller.installIfNeeded(context);
            return 0;
        } catch (GooglePlayServicesRepairableException unused) {
            return 1;
        } catch (GooglePlayServicesNotAvailableException unused2) {
            return 2;
        }
    }
}
