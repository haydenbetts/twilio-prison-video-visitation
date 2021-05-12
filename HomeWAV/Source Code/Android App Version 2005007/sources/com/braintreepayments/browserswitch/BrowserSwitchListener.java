package com.braintreepayments.browserswitch;

import android.net.Uri;

public interface BrowserSwitchListener {
    void onBrowserSwitchResult(int i, BrowserSwitchResult browserSwitchResult, Uri uri);
}
