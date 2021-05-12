package com.braintreepayments.browserswitch;

import android.content.Intent;
import android.net.Uri;
import org.json.JSONObject;

public class BrowserSwitchOptions {
    private Intent intent;
    private JSONObject metadata;
    private int requestCode;
    private Uri url;

    public BrowserSwitchOptions intent(Intent intent2) {
        this.intent = intent2;
        return this;
    }

    public BrowserSwitchOptions metadata(JSONObject jSONObject) {
        this.metadata = jSONObject;
        return this;
    }

    public BrowserSwitchOptions requestCode(int i) {
        this.requestCode = i;
        return this;
    }

    public BrowserSwitchOptions url(Uri uri) {
        this.url = uri;
        return this;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public JSONObject getMetadata() {
        return this.metadata;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public Uri getUrl() {
        return this.url;
    }
}
