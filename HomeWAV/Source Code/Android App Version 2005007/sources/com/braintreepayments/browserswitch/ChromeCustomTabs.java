package com.braintreepayments.browserswitch;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

public class ChromeCustomTabs {
    public static boolean isAvailable(Context context) {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        Intent intent = new Intent("android.support.customtabs.action.CustomTabsService").setPackage("com.android.chrome");
        AnonymousClass1 r1 = new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            }

            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
        boolean bindService = context.bindService(intent, r1, 33);
        context.unbindService(r1);
        return bindService;
    }

    public static Intent addChromeCustomTabsExtras(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 18 && isAvailable(context)) {
            Bundle bundle = new Bundle();
            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
            intent.putExtras(bundle);
            intent.addFlags(134250496);
        }
        return intent;
    }
}
