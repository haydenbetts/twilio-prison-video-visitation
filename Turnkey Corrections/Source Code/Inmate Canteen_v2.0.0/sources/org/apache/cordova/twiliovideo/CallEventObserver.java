package org.apache.cordova.twiliovideo;

import org.json.JSONObject;

public interface CallEventObserver {
    void onEvent(String str, JSONObject jSONObject);
}
