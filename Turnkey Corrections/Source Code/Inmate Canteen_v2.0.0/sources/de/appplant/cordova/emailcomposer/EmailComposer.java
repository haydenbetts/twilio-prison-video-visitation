package de.appplant.cordova.emailcomposer;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EmailComposer extends CordovaPlugin {
    private static final int EXEC_AVAIL_AFTER = 0;
    private static final int EXEC_CHECK_AFTER = 1;
    static final String LOG_TAG = "EmailComposer";
    private static final String PERMISSION = "android.permission.GET_ACCOUNTS";
    private JSONArray args;
    /* access modifiers changed from: private */
    public CallbackContext command;

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        new AssetUtil(getContext()).cleanupAttachmentFolder();
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        this.args = jSONArray;
        this.command = callbackContext;
        if ("open".equalsIgnoreCase(str)) {
            open(jSONArray.getJSONObject(0));
        } else if ("isAvailable".equalsIgnoreCase(str)) {
            if (this.cordova.hasPermission(PERMISSION)) {
                isAvailable(jSONArray.getString(0));
            } else {
                requestPermissions(0);
            }
        } else if ("hasPermission".equalsIgnoreCase(str)) {
            hasPermission();
        } else if (!"requestPermission".equalsIgnoreCase(str)) {
            return false;
        } else {
            requestPermissions(1);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public Context getContext() {
        return this.cordova.getActivity();
    }

    private void isAvailable(final String str) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                boolean[] canSendMail = new Impl(EmailComposer.this.getContext()).canSendMail(str);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new PluginResult(PluginResult.Status.OK, canSendMail[0]));
                arrayList.add(new PluginResult(PluginResult.Status.OK, canSendMail[1]));
                EmailComposer.this.command.sendPluginResult(new PluginResult(PluginResult.Status.OK, (List<PluginResult>) arrayList));
            }
        });
    }

    private void open(JSONObject jSONObject) throws JSONException {
        final Intent createChooser = Intent.createChooser(new Impl(getContext()).getDraft(jSONObject), jSONObject.optString("chooserHeader", "Open with"));
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                EmailComposer.this.cordova.startActivityForResult(this, createChooser, 0);
            }
        });
    }

    private void hasPermission() {
        this.command.sendPluginResult(new PluginResult(PluginResult.Status.OK, Boolean.valueOf(this.cordova.hasPermission(PERMISSION)).booleanValue()));
    }

    public void requestPermissions(int i) {
        this.cordova.requestPermission(this, i, PERMISSION);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        CallbackContext callbackContext = this.command;
        if (callbackContext != null) {
            callbackContext.success();
        }
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 0:
                isAvailable(this.args.getString(0));
                return;
            case 1:
                try {
                    hasPermission();
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }
}
