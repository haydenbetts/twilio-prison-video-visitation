package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaInterfaceImpl implements CordovaInterface {
    private static final String TAG = "CordovaInterfaceImpl";
    protected Activity activity;
    protected CordovaPlugin activityResultCallback;
    protected int activityResultRequestCode;
    protected boolean activityWasDestroyed;
    protected String initCallbackService;
    protected CallbackMap permissionResultCallbacks;
    protected PluginManager pluginManager;
    protected Bundle savedPluginState;
    protected ActivityResultHolder savedResult;
    protected ExecutorService threadPool;

    public CordovaInterfaceImpl(Activity activity2) {
        this(activity2, Executors.newCachedThreadPool());
    }

    public CordovaInterfaceImpl(Activity activity2, ExecutorService executorService) {
        this.activityWasDestroyed = false;
        this.activity = activity2;
        this.threadPool = executorService;
        this.permissionResultCallbacks = new CallbackMap();
    }

    public void startActivityForResult(CordovaPlugin cordovaPlugin, Intent intent, int i) {
        setActivityResultCallback(cordovaPlugin);
        try {
            this.activity.startActivityForResult(intent, i);
        } catch (RuntimeException e) {
            this.activityResultCallback = null;
            throw e;
        }
    }

    public void setActivityResultCallback(CordovaPlugin cordovaPlugin) {
        CordovaPlugin cordovaPlugin2 = this.activityResultCallback;
        if (cordovaPlugin2 != null) {
            cordovaPlugin2.onActivityResult(this.activityResultRequestCode, 0, (Intent) null);
        }
        this.activityResultCallback = cordovaPlugin;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public Context getContext() {
        return this.activity;
    }

    public Object onMessage(String str, Object obj) {
        if (!"exit".equals(str)) {
            return null;
        }
        this.activity.finish();
        return null;
    }

    public ExecutorService getThreadPool() {
        return this.threadPool;
    }

    public void onCordovaInit(PluginManager pluginManager2) {
        CoreAndroid coreAndroid;
        this.pluginManager = pluginManager2;
        ActivityResultHolder activityResultHolder = this.savedResult;
        if (activityResultHolder != null) {
            onActivityResult(activityResultHolder.requestCode, this.savedResult.resultCode, this.savedResult.intent);
        } else if (this.activityWasDestroyed) {
            this.activityWasDestroyed = false;
            if (pluginManager2 != null && (coreAndroid = (CoreAndroid) pluginManager2.getPlugin(CoreAndroid.PLUGIN_NAME)) != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("action", "resume");
                } catch (JSONException e) {
                    LOG.e(TAG, "Failed to create event message", (Throwable) e);
                }
                coreAndroid.sendResumeEvent(new PluginResult(PluginResult.Status.OK, jSONObject));
            }
        }
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        CordovaPlugin cordovaPlugin = this.activityResultCallback;
        if (cordovaPlugin == null && this.initCallbackService != null) {
            this.savedResult = new ActivityResultHolder(i, i2, intent);
            PluginManager pluginManager2 = this.pluginManager;
            if (!(pluginManager2 == null || (cordovaPlugin = pluginManager2.getPlugin(this.initCallbackService)) == null)) {
                cordovaPlugin.onRestoreStateForActivityResult(this.savedPluginState.getBundle(cordovaPlugin.getServiceName()), new ResumeCallback(cordovaPlugin.getServiceName(), this.pluginManager));
            }
        }
        this.activityResultCallback = null;
        if (cordovaPlugin != null) {
            LOG.d(TAG, "Sending activity result to plugin");
            this.initCallbackService = null;
            this.savedResult = null;
            cordovaPlugin.onActivityResult(i, i2, intent);
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Got an activity result, but no plugin was registered to receive it");
        sb.append(this.savedResult != null ? " yet!" : ".");
        LOG.w(TAG, sb.toString());
        return false;
    }

    public void setActivityResultRequestCode(int i) {
        this.activityResultRequestCode = i;
    }

    public void onSaveInstanceState(Bundle bundle) {
        CordovaPlugin cordovaPlugin = this.activityResultCallback;
        if (cordovaPlugin != null) {
            bundle.putString("callbackService", cordovaPlugin.getServiceName());
        }
        PluginManager pluginManager2 = this.pluginManager;
        if (pluginManager2 != null) {
            bundle.putBundle("plugin", pluginManager2.onSaveInstanceState());
        }
    }

    public void restoreInstanceState(Bundle bundle) {
        this.initCallbackService = bundle.getString("callbackService");
        this.savedPluginState = bundle.getBundle("plugin");
        this.activityWasDestroyed = true;
    }

    private static class ActivityResultHolder {
        /* access modifiers changed from: private */
        public Intent intent;
        /* access modifiers changed from: private */
        public int requestCode;
        /* access modifiers changed from: private */
        public int resultCode;

        public ActivityResultHolder(int i, int i2, Intent intent2) {
            this.requestCode = i;
            this.resultCode = i2;
            this.intent = intent2;
        }
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) throws JSONException {
        Pair<CordovaPlugin, Integer> andRemoveCallback = this.permissionResultCallbacks.getAndRemoveCallback(i);
        if (andRemoveCallback != null) {
            ((CordovaPlugin) andRemoveCallback.first).onRequestPermissionResult(((Integer) andRemoveCallback.second).intValue(), strArr, iArr);
        }
    }

    public void requestPermission(CordovaPlugin cordovaPlugin, int i, String str) {
        requestPermissions(cordovaPlugin, i, new String[]{str});
    }

    @SuppressLint({"NewApi"})
    public void requestPermissions(CordovaPlugin cordovaPlugin, int i, String[] strArr) {
        getActivity().requestPermissions(strArr, this.permissionResultCallbacks.registerCallback(cordovaPlugin, i));
    }

    public boolean hasPermission(String str) {
        if (Build.VERSION.SDK_INT < 23 || this.activity.checkSelfPermission(str) == 0) {
            return true;
        }
        return false;
    }
}
