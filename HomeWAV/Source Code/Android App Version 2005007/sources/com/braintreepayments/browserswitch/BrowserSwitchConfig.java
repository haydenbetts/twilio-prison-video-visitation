package com.braintreepayments.browserswitch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

class BrowserSwitchConfig {
    BrowserSwitchConfig() {
    }

    static BrowserSwitchConfig newInstance() {
        return new BrowserSwitchConfig();
    }

    /* access modifiers changed from: package-private */
    public Intent createIntentToLaunchUriInBrowser(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.addFlags(268435456);
        Context applicationContext = context.getApplicationContext();
        if (ChromeCustomTabs.isAvailable(applicationContext)) {
            ChromeCustomTabs.addChromeCustomTabsExtras(applicationContext, intent);
        }
        return intent;
    }

    /* access modifiers changed from: package-private */
    public Intent createIntentForBrowserSwitchActivityQuery(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format("%s://", new Object[]{str})));
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        return intent;
    }
}
