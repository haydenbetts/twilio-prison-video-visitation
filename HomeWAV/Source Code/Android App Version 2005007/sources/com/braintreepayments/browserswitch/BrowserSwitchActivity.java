package com.braintreepayments.browserswitch;

import android.app.Activity;
import android.os.Bundle;

public class BrowserSwitchActivity extends Activity {
    BrowserSwitchClient browserSwitchClient = BrowserSwitchClient.newInstance((String) null);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.browserSwitchClient.captureResult(getIntent(), this);
        finish();
    }
}
