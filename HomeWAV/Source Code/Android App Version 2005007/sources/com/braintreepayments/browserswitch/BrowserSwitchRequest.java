package com.braintreepayments.browserswitch;

import android.net.Uri;
import org.json.JSONException;
import org.json.JSONObject;

class BrowserSwitchRequest {
    static final String PENDING = "PENDING";
    static final String SUCCESS = "SUCCESS";
    private JSONObject metadata;
    private final int requestCode;
    private String state;
    private Uri uri;

    static BrowserSwitchRequest fromJson(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        int i = jSONObject.getInt("requestCode");
        String string = jSONObject.getString("url");
        return new BrowserSwitchRequest(i, Uri.parse(string), jSONObject.getString("state"), jSONObject.optJSONObject(TtmlNode.TAG_METADATA));
    }

    BrowserSwitchRequest(int i, Uri uri2, String str, JSONObject jSONObject) {
        this.uri = uri2;
        this.state = str;
        this.requestCode = i;
        this.metadata = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public void setUri(Uri uri2) {
        this.uri = uri2;
    }

    /* access modifiers changed from: package-private */
    public Uri getUri() {
        return this.uri;
    }

    /* access modifiers changed from: package-private */
    public int getRequestCode() {
        return this.requestCode;
    }

    /* access modifiers changed from: package-private */
    public String getState() {
        return this.state;
    }

    public JSONObject getMetadata() {
        return this.metadata;
    }

    /* access modifiers changed from: package-private */
    public void setState(String str) {
        this.state = str;
    }

    /* access modifiers changed from: package-private */
    public String toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestCode", this.requestCode);
        jSONObject.put("url", this.uri.toString());
        jSONObject.put("state", this.state);
        JSONObject jSONObject2 = this.metadata;
        if (jSONObject2 != null) {
            jSONObject.put(TtmlNode.TAG_METADATA, jSONObject2);
        }
        return jSONObject.toString();
    }
}
