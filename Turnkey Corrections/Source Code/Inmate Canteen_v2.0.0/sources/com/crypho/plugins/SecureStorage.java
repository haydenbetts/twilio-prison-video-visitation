package com.crypho.plugins;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import java.util.Hashtable;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecureStorage extends CordovaPlugin {
    private static final String MSG_DEVICE_NOT_SECURE = "Device is not secure";
    private static final String MSG_NOT_SUPPORTED = ("API 20 (Android 5.0 Lollipop) is required. This device is running API " + Build.VERSION.SDK_INT);
    private static final boolean SUPPORTED = (Build.VERSION.SDK_INT >= 21);
    private static final String TAG = "SecureStorage";
    /* access modifiers changed from: private */
    public String INIT_SERVICE;
    private Hashtable<String, SharedPreferencesHandler> SERVICE_STORAGE = new Hashtable<>();
    /* access modifiers changed from: private */
    public volatile CallbackContext initContext;
    /* access modifiers changed from: private */
    public volatile boolean initContextRunning = false;
    private volatile CallbackContext secureDeviceContext;

    public void onResume(boolean z) {
        if (this.secureDeviceContext != null) {
            if (isDeviceSecure()) {
                this.secureDeviceContext.success();
            } else {
                this.secureDeviceContext.error(MSG_DEVICE_NOT_SECURE);
            }
            this.secureDeviceContext = null;
        }
        if (this.initContext != null && !this.initContextRunning) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    boolean unused = SecureStorage.this.initContextRunning = true;
                    try {
                        String access$200 = SecureStorage.this.service2alias(SecureStorage.this.INIT_SERVICE);
                        if (!RSA.isEntryAvailable(access$200)) {
                            SecureStorage.this.getStorage(SecureStorage.this.INIT_SERVICE).clear();
                            RSA.createKeyPair(SecureStorage.this.getContext(), access$200);
                        }
                        SecureStorage.this.initSuccess(SecureStorage.this.initContext);
                    } catch (Exception e) {
                        Log.e(SecureStorage.TAG, "Init failed :", e);
                        SecureStorage.this.initContext.error(e.getMessage());
                    } catch (Throwable th) {
                        CallbackContext unused2 = SecureStorage.this.initContext = null;
                        boolean unused3 = SecureStorage.this.initContextRunning = false;
                        throw th;
                    }
                    CallbackContext unused4 = SecureStorage.this.initContext = null;
                    boolean unused5 = SecureStorage.this.initContextRunning = false;
                }
            });
        }
    }

    public boolean execute(String str, CordovaArgs cordovaArgs, CallbackContext callbackContext) throws JSONException {
        if (!SUPPORTED) {
            Log.w(TAG, MSG_NOT_SUPPORTED);
            callbackContext.error(MSG_NOT_SUPPORTED);
            return false;
        } else if ("init".equals(str)) {
            String string = cordovaArgs.getString(0);
            String service2alias = service2alias(string);
            this.INIT_SERVICE = string;
            this.SERVICE_STORAGE.put(string, new SharedPreferencesHandler(service2alias, getContext()));
            if (!isDeviceSecure()) {
                Log.e(TAG, MSG_DEVICE_NOT_SECURE);
                callbackContext.error(MSG_DEVICE_NOT_SECURE);
            } else if (!RSA.isEntryAvailable(service2alias)) {
                this.initContext = callbackContext;
                unlockCredentials();
            } else {
                initSuccess(callbackContext);
            }
            return true;
        } else if ("set".equals(str)) {
            final String string2 = cordovaArgs.getString(0);
            final String string3 = cordovaArgs.getString(1);
            final String string4 = cordovaArgs.getString(2);
            final String str2 = string2;
            final CallbackContext callbackContext2 = callbackContext;
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        JSONObject encrypt = AES.encrypt(string4.getBytes(), str2.getBytes());
                        encrypt.put("key", Base64.encodeToString(RSA.encrypt(Base64.decode(encrypt.getString("key"), 0), SecureStorage.this.service2alias(string2)), 0));
                        SecureStorage.this.getStorage(string2).store(string3, encrypt.toString());
                        callbackContext2.success(string3);
                    } catch (Exception e) {
                        Log.e(SecureStorage.TAG, "Encrypt failed :", e);
                        callbackContext2.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("get".equals(str)) {
            final String string5 = cordovaArgs.getString(0);
            String string6 = cordovaArgs.getString(1);
            String fetch = getStorage(string5).fetch(string6);
            if (fetch != null) {
                JSONObject jSONObject = new JSONObject(fetch);
                final byte[] decode = Base64.decode(jSONObject.getString("key"), 0);
                JSONObject jSONObject2 = jSONObject.getJSONObject("value");
                final byte[] decode2 = Base64.decode(jSONObject2.getString("ct"), 0);
                final byte[] decode3 = Base64.decode(jSONObject2.getString("iv"), 0);
                final byte[] decode4 = Base64.decode(jSONObject2.getString("adata"), 0);
                final CallbackContext callbackContext3 = callbackContext;
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        try {
                            callbackContext3.success(new String(AES.decrypt(decode2, RSA.decrypt(decode, SecureStorage.this.service2alias(string5)), decode3, decode4)));
                        } catch (Exception e) {
                            Log.e(SecureStorage.TAG, "Decrypt failed :", e);
                            callbackContext3.error(e.getMessage());
                        }
                    }
                });
            } else {
                callbackContext.error("Key [" + string6 + "] not found.");
            }
            return true;
        } else if ("secureDevice".equals(str)) {
            this.secureDeviceContext = callbackContext;
            unlockCredentials();
            return true;
        } else if ("remove".equals(str)) {
            String string7 = cordovaArgs.getString(0);
            String string8 = cordovaArgs.getString(1);
            getStorage(string7).remove(string8);
            callbackContext.success(string8);
            return true;
        } else if ("keys".equals(str)) {
            callbackContext.success(new JSONArray(getStorage(cordovaArgs.getString(0)).keys()));
            return true;
        } else if (!"clear".equals(str)) {
            return false;
        } else {
            getStorage(cordovaArgs.getString(0)).clear();
            callbackContext.success();
            return true;
        }
    }

    private boolean isDeviceSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) getContext().getSystemService("keyguard");
        try {
            return ((Boolean) keyguardManager.getClass().getMethod("isDeviceSecure", new Class[0]).invoke(keyguardManager, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return keyguardManager.isKeyguardSecure();
        }
    }

    /* access modifiers changed from: private */
    public String service2alias(String str) {
        return getContext().getPackageName() + "." + str;
    }

    /* access modifiers changed from: private */
    public SharedPreferencesHandler getStorage(String str) {
        return this.SERVICE_STORAGE.get(str);
    }

    /* access modifiers changed from: private */
    public void initSuccess(CallbackContext callbackContext) {
        callbackContext.success();
    }

    private void unlockCredentials() {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SecureStorage.this.startActivity(new Intent("com.android.credentials.UNLOCK"));
            }
        });
    }

    /* access modifiers changed from: private */
    public Context getContext() {
        return this.cordova.getActivity().getApplicationContext();
    }

    /* access modifiers changed from: private */
    public void startActivity(Intent intent) {
        this.cordova.getActivity().startActivity(intent);
    }
}
