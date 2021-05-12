package com.urbanairship.google;

import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NetworkProviderInstaller {
    public static final int PROVIDER_ERROR = 2;
    public static final int PROVIDER_INSTALLED = 0;
    public static final int PROVIDER_RECOVERABLE_ERROR = 1;
    private static Boolean isProviderInstallerDependencyAvailable;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    private static boolean isProviderInstallerDependencyAvailable() {
        if (isProviderInstallerDependencyAvailable == null) {
            if (!PlayServicesUtils.isGooglePlayServicesDependencyAvailable()) {
                isProviderInstallerDependencyAvailable = false;
            } else {
                try {
                    Class.forName("com.google.android.gms.security.ProviderInstaller");
                    isProviderInstallerDependencyAvailable = true;
                } catch (ClassNotFoundException unused) {
                    isProviderInstallerDependencyAvailable = false;
                }
            }
        }
        return isProviderInstallerDependencyAvailable.booleanValue();
    }

    public static int installSecurityProvider(Context context) {
        if (!isProviderInstallerDependencyAvailable()) {
            return 2;
        }
        return ProviderInstallerWrapper.installIfNeeded(context);
    }
}
