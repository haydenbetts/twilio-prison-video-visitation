package org.apache.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CoreAndroid extends CordovaPlugin {
    public static final String PLUGIN_NAME = "CoreAndroid";
    protected static final String TAG = "CordovaApp";
    private CallbackContext messageChannel;
    private final Object messageChannelLock = new Object();
    private PluginResult pendingPause;
    private PluginResult pendingResume;
    private BroadcastReceiver telephonyReceiver;

    public void fireJavascriptEvent(String str) {
        sendEventMessage(str);
    }

    public void pluginInitialize() {
        initTelephonyReceiver();
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        PluginResult.Status status = PluginResult.Status.OK;
        try {
            if (str.equals("clearCache")) {
                clearCache();
            } else if (str.equals("show")) {
                this.cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        CoreAndroid.this.webView.getPluginManager().postMessage("spinner", "stop");
                    }
                });
            } else if (str.equals("loadUrl")) {
                loadUrl(jSONArray.getString(0), jSONArray.optJSONObject(1));
            } else if (!str.equals("cancelLoadUrl")) {
                if (str.equals("clearHistory")) {
                    clearHistory();
                } else if (str.equals("backHistory")) {
                    backHistory();
                } else if (str.equals("overrideButton")) {
                    overrideButton(jSONArray.getString(0), jSONArray.getBoolean(1));
                } else if (str.equals("overrideBackbutton")) {
                    overrideBackbutton(jSONArray.getBoolean(0));
                } else if (str.equals("exitApp")) {
                    exitApp();
                } else if (str.equals("messageChannel")) {
                    synchronized (this.messageChannelLock) {
                        this.messageChannel = callbackContext;
                        if (this.pendingPause != null) {
                            sendEventMessage(this.pendingPause);
                            this.pendingPause = null;
                        }
                        if (this.pendingResume != null) {
                            sendEventMessage(this.pendingResume);
                            this.pendingResume = null;
                        }
                    }
                    return true;
                }
            }
            callbackContext.sendPluginResult(new PluginResult(status, ""));
            return true;
        } catch (JSONException unused) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
            return false;
        }
    }

    public void clearCache() {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CoreAndroid.this.webView.clearCache();
            }
        });
    }

    public void loadUrl(String str, JSONObject jSONObject) throws JSONException {
        boolean z;
        boolean z2;
        LOG.d("App", "App.loadUrl(" + str + "," + jSONObject + ")");
        HashMap hashMap = new HashMap();
        int i = 0;
        if (jSONObject != null) {
            JSONArray names = jSONObject.names();
            int i2 = 0;
            z2 = false;
            z = false;
            while (i < names.length()) {
                String string = names.getString(i);
                if (string.equals("wait")) {
                    i2 = jSONObject.getInt(string);
                } else if (string.equalsIgnoreCase("openexternal")) {
                    z2 = jSONObject.getBoolean(string);
                } else if (string.equalsIgnoreCase("clearhistory")) {
                    z = jSONObject.getBoolean(string);
                } else {
                    Object obj = jSONObject.get(string);
                    if (obj != null) {
                        if (obj.getClass().equals(String.class)) {
                            hashMap.put(string, (String) obj);
                        } else if (obj.getClass().equals(Boolean.class)) {
                            hashMap.put(string, (Boolean) obj);
                        } else if (obj.getClass().equals(Integer.class)) {
                            hashMap.put(string, (Integer) obj);
                        }
                    }
                }
                i++;
            }
            i = i2;
        } else {
            z2 = false;
            z = false;
        }
        if (i > 0) {
            try {
                synchronized (this) {
                    wait((long) i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.webView.showWebPage(str, z2, z, hashMap);
    }

    public void clearHistory() {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CoreAndroid.this.webView.clearHistory();
            }
        });
    }

    public void backHistory() {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CoreAndroid.this.webView.backHistory();
            }
        });
    }

    public void overrideBackbutton(boolean z) {
        LOG.i("App", "WARNING: Back Button Default Behavior will be overridden.  The backbutton event will be fired!");
        this.webView.setButtonPlumbedToJs(4, z);
    }

    public void overrideButton(String str, boolean z) {
        LOG.i("App", "WARNING: Volume Button Default Behavior will be overridden.  The volume event will be fired!");
        if (str.equals("volumeup")) {
            this.webView.setButtonPlumbedToJs(24, z);
        } else if (str.equals("volumedown")) {
            this.webView.setButtonPlumbedToJs(25, z);
        } else if (str.equals("menubutton")) {
            this.webView.setButtonPlumbedToJs(82, z);
        }
    }

    public boolean isBackbuttonOverridden() {
        return this.webView.isButtonPlumbedToJs(4);
    }

    public void exitApp() {
        this.webView.getPluginManager().postMessage("exit", (Object) null);
    }

    private void initTelephonyReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.telephonyReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction().equals("android.intent.action.PHONE_STATE") && intent.hasExtra("state")) {
                    String stringExtra = intent.getStringExtra("state");
                    if (stringExtra.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                        LOG.i(CoreAndroid.TAG, "Telephone RINGING");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "ringing");
                    } else if (stringExtra.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                        LOG.i(CoreAndroid.TAG, "Telephone OFFHOOK");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "offhook");
                    } else if (stringExtra.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                        LOG.i(CoreAndroid.TAG, "Telephone IDLE");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "idle");
                    }
                }
            }
        };
        this.webView.getContext().registerReceiver(this.telephonyReceiver, intentFilter);
    }

    private void sendEventMessage(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", str);
        } catch (JSONException e) {
            LOG.e(TAG, "Failed to create event message", (Throwable) e);
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, jSONObject);
        if (this.messageChannel == null) {
            LOG.i(TAG, "Request to send event before messageChannel initialised: " + str);
            if ("pause".equals(str)) {
                this.pendingPause = pluginResult;
            } else if ("resume".equals(str)) {
                this.pendingPause = null;
            }
        } else {
            sendEventMessage(pluginResult);
        }
    }

    private void sendEventMessage(PluginResult pluginResult) {
        pluginResult.setKeepCallback(true);
        CallbackContext callbackContext = this.messageChannel;
        if (callbackContext != null) {
            callbackContext.sendPluginResult(pluginResult);
        }
    }

    public void onDestroy() {
        this.webView.getContext().unregisterReceiver(this.telephonyReceiver);
    }

    public void sendResumeEvent(PluginResult pluginResult) {
        synchronized (this.messageChannelLock) {
            if (this.messageChannel != null) {
                sendEventMessage(pluginResult);
            } else {
                this.pendingResume = pluginResult;
            }
        }
    }

    public static Object getBuildConfigValue(Context context, String str) {
        try {
            return Class.forName(context.getPackageName() + ".BuildConfig").getField(str).get((Object) null);
        } catch (ClassNotFoundException e) {
            LOG.d(TAG, "Unable to get the BuildConfig, is this built with ANT?");
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException unused) {
            LOG.d(TAG, str + " is not a valid field. Check your build.gradle");
            return null;
        } catch (IllegalAccessException e2) {
            LOG.d(TAG, "Illegal Access Exception: Let's print a stack trace.");
            e2.printStackTrace();
            return null;
        }
    }
}
