package org.apache.cordova.twiliovideo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TwilioVideo extends CordovaPlugin {
    public static final String[] PERMISSIONS_REQUIRED = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    private static final int PERMISSIONS_REQUIRED_REQUEST_CODE = 1;
    public static final String TAG = "TwilioPlugin";
    private CallbackContext callbackContext;
    /* access modifiers changed from: private */
    public CallConfig config = new CallConfig();
    private CordovaInterface cordova;
    private String roomId;
    private String token;

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        this.cordova = cordovaInterface;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean execute(java.lang.String r4, org.json.JSONArray r5, org.apache.cordova.CallbackContext r6) {
        /*
            r3 = this;
            r3.callbackContext = r6
            int r0 = r4.hashCode()
            r1 = -862440853(0xffffffffcc98326b, float:-7.9795032E7)
            r2 = 1
            if (r0 == r1) goto L_0x003a
            r1 = -504699323(0xffffffffe1eae645, float:-5.4164135E20)
            if (r0 == r1) goto L_0x0030
            r1 = -482535693(0xffffffffe33d16f3, float:-3.4880883E21)
            if (r0 == r1) goto L_0x0026
            r1 = 1669188213(0x637dca75, float:4.6816148E21)
            if (r0 == r1) goto L_0x001c
            goto L_0x0044
        L_0x001c:
            java.lang.String r0 = "requestPermissions"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0044
            r4 = 3
            goto L_0x0045
        L_0x0026:
            java.lang.String r0 = "closeRoom"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0044
            r4 = 1
            goto L_0x0045
        L_0x0030:
            java.lang.String r0 = "openRoom"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0044
            r4 = 0
            goto L_0x0045
        L_0x003a:
            java.lang.String r0 = "hasRequiredPermissions"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0044
            r4 = 2
            goto L_0x0045
        L_0x0044:
            r4 = -1
        L_0x0045:
            switch(r4) {
                case 0: goto L_0x0055;
                case 1: goto L_0x0051;
                case 2: goto L_0x004d;
                case 3: goto L_0x0049;
                default: goto L_0x0048;
            }
        L_0x0048:
            goto L_0x005b
        L_0x0049:
            r3.requestRequiredPermissions()
            goto L_0x005b
        L_0x004d:
            r3.hasRequiredPermissions(r6)
            goto L_0x005b
        L_0x0051:
            r3.closeRoom(r6)
            goto L_0x005b
        L_0x0055:
            r3.registerCallListener(r6)
            r3.openRoom(r5)
        L_0x005b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.twiliovideo.TwilioVideo.execute(java.lang.String, org.json.JSONArray, org.apache.cordova.CallbackContext):boolean");
    }

    public void openRoom(JSONArray jSONArray) {
        try {
            this.token = jSONArray.getString(0);
            this.roomId = jSONArray.getString(1);
            final String str = this.token;
            final String str2 = this.roomId;
            if (jSONArray.length() > 2) {
                this.config.parse(jSONArray.getJSONObject(2));
            }
            LOG.d(TAG, "TOKEN: " + str);
            LOG.d(TAG, "ROOMID: " + str2);
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setClass(this.cordova.getActivity().getBaseContext(), TwilioVideoActivity.class);
                    intent.setPackage(this.cordova.getActivity().getApplicationContext().getPackageName());
                    intent.putExtra("token", str);
                    intent.putExtra("roomId", str2);
                    intent.putExtra("config", TwilioVideo.this.config);
                    this.cordova.getActivity().startActivity(intent);
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Couldn't open room. No valid input params", e);
        }
    }

    private void registerCallListener(final CallbackContext callbackContext2) {
        if (callbackContext2 != null) {
            TwilioVideoManager.getInstance().setEventObserver(new CallEventObserver() {
                public void onEvent(String str, JSONObject jSONObject) {
                    Log.i(TwilioVideo.TAG, String.format("Event received: %s with data: %s", new Object[]{str, jSONObject}));
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.putOpt(NotificationCompat.CATEGORY_EVENT, str);
                        jSONObject2.putOpt("data", jSONObject);
                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, jSONObject2);
                        pluginResult.setKeepCallback(true);
                        callbackContext2.sendPluginResult(pluginResult);
                    } catch (JSONException unused) {
                        Log.e(TwilioVideo.TAG, "Failed to create event: " + str);
                    }
                }
            });
        }
    }

    private void closeRoom(CallbackContext callbackContext2) {
        if (TwilioVideoManager.getInstance().publishDisconnection()) {
            callbackContext2.success();
        } else {
            callbackContext2.error("Twilio video is not running");
        }
    }

    private void hasRequiredPermissions(CallbackContext callbackContext2) {
        boolean z = true;
        for (String hasPermission : PERMISSIONS_REQUIRED) {
            z = this.cordova.hasPermission(hasPermission);
            if (!z) {
                break;
            }
        }
        callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, z));
    }

    private void requestRequiredPermissions() {
        this.cordova.requestPermissions(this, 1, PERMISSIONS_REQUIRED);
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            int length = iArr.length;
            boolean z = true;
            for (int i2 = 0; i2 < length; i2++) {
                z &= iArr[i2] == 0;
            }
            this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, z));
            return;
        }
        this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, false));
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putString("token", this.token);
        bundle.putString("roomId", this.roomId);
        bundle.putSerializable("config", this.config);
        return bundle;
    }

    public void onRestoreStateForActivityResult(Bundle bundle, CallbackContext callbackContext2) {
        this.token = bundle.getString("token");
        this.roomId = bundle.getString("roomId");
        this.config = (CallConfig) bundle.getSerializable("config");
        this.callbackContext = callbackContext2;
    }
}
