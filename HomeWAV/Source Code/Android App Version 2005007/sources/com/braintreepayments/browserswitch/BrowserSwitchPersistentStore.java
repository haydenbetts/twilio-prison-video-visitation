package com.braintreepayments.browserswitch;

import android.content.Context;
import android.util.Log;
import java.util.Arrays;
import org.json.JSONException;

class BrowserSwitchPersistentStore {
    private static final BrowserSwitchPersistentStore INSTANCE = new BrowserSwitchPersistentStore();
    static final String REQUEST_KEY = "browserSwitch.request";
    private static final String TAG = "BrowserSwitch";

    static BrowserSwitchPersistentStore getInstance() {
        return INSTANCE;
    }

    private BrowserSwitchPersistentStore() {
    }

    /* access modifiers changed from: package-private */
    public BrowserSwitchRequest getActiveRequest(Context context) {
        String str = PersistentStore.get(REQUEST_KEY, context);
        if (str != null) {
            try {
                return BrowserSwitchRequest.fromJson(str);
            } catch (JSONException e) {
                Log.d(TAG, e.getMessage());
                Log.d(TAG, Arrays.toString(e.getStackTrace()));
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void putActiveRequest(BrowserSwitchRequest browserSwitchRequest, Context context) {
        try {
            PersistentStore.put(REQUEST_KEY, browserSwitchRequest.toJson(), context);
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            Log.d(TAG, Arrays.toString(e.getStackTrace()));
        }
    }

    /* access modifiers changed from: package-private */
    public void clearActiveRequest(Context context) {
        PersistentStore.remove(REQUEST_KEY, context);
    }
}
