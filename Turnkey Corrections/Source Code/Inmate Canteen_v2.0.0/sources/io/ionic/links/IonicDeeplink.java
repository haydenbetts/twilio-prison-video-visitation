package io.ionic.links;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IonicDeeplink extends CordovaPlugin {
    private static final String TAG = "IonicDeeplinkPlugin";
    private ArrayList<CallbackContext> _handlers = new ArrayList<>();
    private JSONObject lastEvent;

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        Log.d(TAG, "IonicDeepLinkPlugin: firing up...");
        handleIntent(cordovaInterface.getActivity().getIntent());
    }

    public void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    public void handleIntent(Intent intent) {
        String dataString = intent.getDataString();
        String action = intent.getAction();
        Uri data = intent.getData();
        JSONObject _bundleToJson = _bundleToJson(intent.getExtras());
        Log.d(TAG, "Got a new intent: " + dataString + " " + intent.getScheme() + " " + action + " " + data);
        if ("android.intent.action.VIEW".equals(action) && data != null) {
            try {
                this.lastEvent = new JSONObject();
                this.lastEvent.put(ImagesContract.URL, data.toString());
                this.lastEvent.put("path", data.getPath());
                this.lastEvent.put("queryString", data.getQuery());
                this.lastEvent.put("scheme", data.getScheme());
                this.lastEvent.put("host", data.getHost());
                this.lastEvent.put("fragment", data.getFragment());
                this.lastEvent.put("extra", _bundleToJson);
                consumeEvents();
            } catch (JSONException e) {
                Log.e(TAG, "Unable to process URL scheme deeplink", e);
            }
        }
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (str.equals("onDeepLink")) {
            addHandler(jSONArray, callbackContext);
            return true;
        } else if (str.equals("canOpenApp")) {
            canOpenApp(jSONArray.getString(0), callbackContext);
            return true;
        } else if (!str.equals("getHardwareInfo")) {
            return true;
        } else {
            getHardwareInfo(jSONArray, callbackContext);
            return true;
        }
    }

    private void consumeEvents() {
        if (this._handlers.size() != 0 && this.lastEvent != null) {
            Iterator<CallbackContext> it = this._handlers.iterator();
            while (it.hasNext()) {
                sendToJs(this.lastEvent, it.next());
            }
            this.lastEvent = null;
        }
    }

    private void sendToJs(JSONObject jSONObject, CallbackContext callbackContext) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, jSONObject);
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
    }

    private void addHandler(JSONArray jSONArray, CallbackContext callbackContext) {
        this._handlers.add(callbackContext);
        consumeEvents();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:15|16|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.put(r2, _wrap(r8.get(r2)));
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0041 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject _bundleToJson(android.os.Bundle r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x0008
            org.json.JSONObject r8 = new org.json.JSONObject
            r8.<init>()
            return r8
        L_0x0008:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.util.Set r1 = r8.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0015:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x004d
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Class r3 = r0.getClass()     // Catch:{ JSONException -> 0x0015 }
            r4 = 1
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ JSONException -> 0x0015 }
            r5 = 0
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r4[r5] = r6     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r5 = "wrap"
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r5, r4)     // Catch:{ NoSuchMethodException -> 0x0041 }
            if (r3 == 0) goto L_0x0015
            java.lang.Object r3 = r8.get(r2)     // Catch:{ NoSuchMethodException -> 0x0041 }
            java.lang.Object r3 = org.json.JSONObject.wrap(r3)     // Catch:{ NoSuchMethodException -> 0x0041 }
            r0.put(r2, r3)     // Catch:{ NoSuchMethodException -> 0x0041 }
            goto L_0x0015
        L_0x0041:
            java.lang.Object r3 = r8.get(r2)     // Catch:{ JSONException -> 0x0015 }
            java.lang.Object r3 = r7._wrap(r3)     // Catch:{ JSONException -> 0x0015 }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0015 }
            goto L_0x0015
        L_0x004d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.links.IonicDeeplink._bundleToJson(android.os.Bundle):org.json.JSONObject");
    }

    private Object _wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || obj.equals((Object) null)) {
            return obj;
        }
        try {
            if (obj instanceof Collection) {
                return new JSONArray((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return new JSONArray(obj);
            }
            if (obj instanceof Map) {
                return new JSONObject((Map) obj);
            }
            if (!(obj instanceof Boolean) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Double) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Short)) {
                if (!(obj instanceof String)) {
                    if (obj.getClass().getPackage().getName().startsWith("java.")) {
                        return obj.toString();
                    }
                    return null;
                }
            }
            return obj;
        } catch (Exception unused) {
        }
    }

    private void canOpenApp(String str, CallbackContext callbackContext) {
        try {
            this.cordova.getActivity().getApplicationContext().getPackageManager().getPackageInfo(str, 1);
            callbackContext.success();
        } catch (PackageManager.NameNotFoundException unused) {
        }
        callbackContext.error("");
    }

    private void getHardwareInfo(JSONArray jSONArray, CallbackContext callbackContext) {
        String string = Settings.Secure.getString(this.cordova.getActivity().getContentResolver(), "android_id");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", string);
            jSONObject.put("platform", getPlatform());
            jSONObject.put("tz", getTimeZoneID());
            jSONObject.put("tz_offset", getTimeZoneOffset());
            jSONObject.put("os_version", getOSVersion());
            jSONObject.put("sdk_version", getSDKVersion());
        } catch (JSONException unused) {
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
    }

    private boolean isAmazonDevice() {
        return Build.MANUFACTURER.equals("Amazon");
    }

    private String getTimeZoneID() {
        return TimeZone.getDefault().getID();
    }

    private int getTimeZoneOffset() {
        return (TimeZone.getDefault().getOffset(new Date().getTime()) / 1000) / 60;
    }

    private String getSDKVersion() {
        return Build.VERSION.SDK;
    }

    private String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    private String getPlatform() {
        return isAmazonDevice() ? "amazon-fireos" : "android";
    }
}
