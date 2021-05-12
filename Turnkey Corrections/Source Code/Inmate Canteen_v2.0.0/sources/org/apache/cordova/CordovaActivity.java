package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaActivity extends Activity {
    private static int ACTIVITY_EXITING = 2;
    private static int ACTIVITY_RUNNING = 1;
    private static int ACTIVITY_STARTING = 0;
    public static String TAG = "CordovaActivity";
    protected CordovaWebView appView;
    protected CordovaInterfaceImpl cordovaInterface;
    protected boolean immersiveMode;
    protected boolean keepRunning = true;
    protected String launchUrl;
    protected ArrayList<PluginEntry> pluginEntries;
    protected CordovaPreferences preferences;

    public void onCreate(Bundle bundle) {
        loadConfig();
        LOG.setLogLevel(this.preferences.getString("loglevel", "ERROR"));
        LOG.i(TAG, "Apache Cordova native platform version 8.1.0 is starting");
        LOG.d(TAG, "CordovaActivity.onCreate()");
        if (!this.preferences.getBoolean("ShowTitle", false)) {
            getWindow().requestFeature(1);
        }
        if (this.preferences.getBoolean("SetFullscreen", false)) {
            LOG.d(TAG, "The SetFullscreen configuration is deprecated in favor of Fullscreen, and will be removed in a future version.");
            this.preferences.set("Fullscreen", true);
        }
        if (!this.preferences.getBoolean("Fullscreen", false)) {
            getWindow().setFlags(2048, 2048);
        } else if (!this.preferences.getBoolean("FullscreenNotImmersive", false)) {
            this.immersiveMode = true;
        } else {
            getWindow().setFlags(1024, 1024);
        }
        super.onCreate(bundle);
        this.cordovaInterface = makeCordovaInterface();
        if (bundle != null) {
            this.cordovaInterface.restoreInstanceState(bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.appView = makeWebView();
        createViews();
        if (!this.appView.isInitialized()) {
            this.appView.init(this.cordovaInterface, this.pluginEntries, this.preferences);
        }
        this.cordovaInterface.onCordovaInit(this.appView.getPluginManager());
        if ("media".equals(this.preferences.getString("DefaultVolumeStream", "").toLowerCase(Locale.ENGLISH))) {
            setVolumeControlStream(3);
        }
    }

    /* access modifiers changed from: protected */
    public void loadConfig() {
        ConfigXmlParser configXmlParser = new ConfigXmlParser();
        configXmlParser.parse((Context) this);
        this.preferences = configXmlParser.getPreferences();
        this.preferences.setPreferencesBundle(getIntent().getExtras());
        this.launchUrl = configXmlParser.getLaunchUrl();
        this.pluginEntries = configXmlParser.getPluginEntries();
        Config.parser = configXmlParser;
    }

    /* access modifiers changed from: protected */
    public void createViews() {
        this.appView.getView().setId(100);
        this.appView.getView().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setContentView(this.appView.getView());
        if (this.preferences.contains("BackgroundColor")) {
            try {
                this.appView.getView().setBackgroundColor(this.preferences.getInteger("BackgroundColor", ViewCompat.MEASURED_STATE_MASK));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        this.appView.getView().requestFocusFromTouch();
    }

    /* access modifiers changed from: protected */
    public CordovaWebView makeWebView() {
        return new CordovaWebViewImpl(makeWebViewEngine());
    }

    /* access modifiers changed from: protected */
    public CordovaWebViewEngine makeWebViewEngine() {
        return CordovaWebViewImpl.createEngine(this, this.preferences);
    }

    /* access modifiers changed from: protected */
    public CordovaInterfaceImpl makeCordovaInterface() {
        return new CordovaInterfaceImpl(this) {
            public Object onMessage(String str, Object obj) {
                return CordovaActivity.this.onMessage(str, obj);
            }
        };
    }

    public void loadUrl(String str) {
        if (this.appView == null) {
            init();
        }
        this.keepRunning = this.preferences.getBoolean("KeepRunning", true);
        this.appView.loadUrlIntoView(str, true);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LOG.d(TAG, "Paused the activity.");
        if (this.appView != null) {
            this.appView.handlePause(this.keepRunning || this.cordovaInterface.activityResultCallback != null);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        CordovaWebView cordovaWebView = this.appView;
        if (cordovaWebView != null) {
            cordovaWebView.onNewIntent(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LOG.d(TAG, "Resumed the activity.");
        if (this.appView != null) {
            if (!getWindow().getDecorView().hasFocus()) {
                getWindow().getDecorView().requestFocus();
            }
            this.appView.handleResume(this.keepRunning);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        LOG.d(TAG, "Stopped the activity.");
        CordovaWebView cordovaWebView = this.appView;
        if (cordovaWebView != null) {
            cordovaWebView.handleStop();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        LOG.d(TAG, "Started the activity.");
        CordovaWebView cordovaWebView = this.appView;
        if (cordovaWebView != null) {
            cordovaWebView.handleStart();
        }
    }

    public void onDestroy() {
        LOG.d(TAG, "CordovaActivity.onDestroy()");
        super.onDestroy();
        CordovaWebView cordovaWebView = this.appView;
        if (cordovaWebView != null) {
            cordovaWebView.handleDestroy();
        }
    }

    @SuppressLint({"InlinedApi"})
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.immersiveMode) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    @SuppressLint({"NewApi"})
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        this.cordovaInterface.setActivityResultRequestCode(i);
        super.startActivityForResult(intent, i, bundle);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        String str = TAG;
        LOG.d(str, "Incoming Result. Request code = " + i);
        super.onActivityResult(i, i2, intent);
        this.cordovaInterface.onActivityResult(i, i2, intent);
    }

    public void onReceivedError(int i, String str, String str2) {
        final String string = this.preferences.getString("errorUrl", (String) null);
        if (string == null || str2.equals(string) || this.appView == null) {
            final boolean z = i != -2;
            final String str3 = str;
            final String str4 = str2;
            runOnUiThread(new Runnable() {
                public void run() {
                    if (z) {
                        this.appView.getView().setVisibility(8);
                        CordovaActivity cordovaActivity = this;
                        cordovaActivity.displayError("Application Error", str3 + " (" + str4 + ")", "OK", z);
                    }
                }
            });
            return;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                this.appView.showWebPage(string, false, true, (Map<String, Object>) null);
            }
        });
    }

    public void displayError(String str, String str2, String str3, boolean z) {
        final String str4 = str2;
        final String str5 = str;
        final String str6 = str3;
        final boolean z2 = z;
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(str4);
                    builder.setTitle(str5);
                    builder.setCancelable(false);
                    builder.setPositiveButton(str6, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (z2) {
                                CordovaActivity.this.finish();
                            }
                        }
                    });
                    builder.create();
                    builder.show();
                } catch (Exception unused) {
                    CordovaActivity.this.finish();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        CordovaWebView cordovaWebView = this.appView;
        if (cordovaWebView != null) {
            cordovaWebView.getPluginManager().postMessage("onCreateOptionsMenu", menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        CordovaWebView cordovaWebView = this.appView;
        if (cordovaWebView == null) {
            return true;
        }
        cordovaWebView.getPluginManager().postMessage("onPrepareOptionsMenu", menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        CordovaWebView cordovaWebView = this.appView;
        if (cordovaWebView == null) {
            return true;
        }
        cordovaWebView.getPluginManager().postMessage("onOptionsItemSelected", menuItem);
        return true;
    }

    public Object onMessage(String str, Object obj) {
        if ("onReceivedError".equals(str)) {
            JSONObject jSONObject = (JSONObject) obj;
            try {
                onReceivedError(jSONObject.getInt("errorCode"), jSONObject.getString("description"), jSONObject.getString(ImagesContract.URL));
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else if (!"exit".equals(str)) {
            return null;
        } else {
            finish();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        this.cordovaInterface.onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        PluginManager pluginManager;
        super.onConfigurationChanged(configuration);
        CordovaWebView cordovaWebView = this.appView;
        if (cordovaWebView != null && (pluginManager = cordovaWebView.getPluginManager()) != null) {
            pluginManager.onConfigurationChanged(configuration);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        try {
            this.cordovaInterface.onRequestPermissionResult(i, strArr, iArr);
        } catch (JSONException e) {
            LOG.d(TAG, "JSONException: Parameters fed into the method are not valid");
            e.printStackTrace();
        }
    }
}
