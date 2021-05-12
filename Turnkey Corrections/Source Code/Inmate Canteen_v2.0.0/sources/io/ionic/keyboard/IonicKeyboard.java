package io.ionic.keyboard;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class IonicKeyboard extends CordovaPlugin {
    /* access modifiers changed from: private */
    public ViewTreeObserver.OnGlobalLayoutListener list;
    /* access modifiers changed from: private */
    public View rootView;

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
    }

    public boolean execute(String str, JSONArray jSONArray, final CallbackContext callbackContext) throws JSONException {
        if ("hide".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    InputMethodManager inputMethodManager = (InputMethodManager) IonicKeyboard.this.cordova.getActivity().getSystemService("input_method");
                    View currentFocus = IonicKeyboard.this.cordova.getActivity().getCurrentFocus();
                    if (currentFocus == null) {
                        callbackContext.error("No current focus");
                        return;
                    }
                    inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
                    callbackContext.success();
                }
            });
            return true;
        } else if ("show".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    ((InputMethodManager) IonicKeyboard.this.cordova.getActivity().getSystemService("input_method")).toggleSoftInput(0, 1);
                    callbackContext.success();
                }
            });
            return true;
        } else if (!"init".equals(str)) {
            return false;
        } else {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    IonicKeyboard.this.cordova.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    final float f = displayMetrics.density;
                    IonicKeyboard ionicKeyboard = IonicKeyboard.this;
                    View unused = ionicKeyboard.rootView = ionicKeyboard.cordova.getActivity().getWindow().getDecorView().findViewById(16908290).getRootView();
                    ViewTreeObserver.OnGlobalLayoutListener unused2 = IonicKeyboard.this.list = new ViewTreeObserver.OnGlobalLayoutListener() {
                        int previousHeightDiff = 0;

                        public void onGlobalLayout() {
                            Rect rect = new Rect();
                            IonicKeyboard.this.rootView.getWindowVisibleDisplayFrame(rect);
                            int height = IonicKeyboard.this.rootView.getRootView().getHeight();
                            int i = rect.bottom;
                            if (Build.VERSION.SDK_INT >= 21) {
                                Display defaultDisplay = IonicKeyboard.this.cordova.getActivity().getWindowManager().getDefaultDisplay();
                                Point point = new Point();
                                defaultDisplay.getSize(point);
                                height = point.y;
                            }
                            int i2 = (int) (((float) (height - i)) / f);
                            if (i2 <= 100 || i2 == this.previousHeightDiff) {
                                int i3 = this.previousHeightDiff;
                                if (i2 != i3 && i3 - i2 > 100) {
                                    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "H");
                                    pluginResult.setKeepCallback(true);
                                    callbackContext.sendPluginResult(pluginResult);
                                }
                            } else {
                                PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK, "S" + Integer.toString(i2));
                                pluginResult2.setKeepCallback(true);
                                callbackContext.sendPluginResult(pluginResult2);
                            }
                            this.previousHeightDiff = i2;
                        }
                    };
                    IonicKeyboard.this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(IonicKeyboard.this.list);
                    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
                    pluginResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(pluginResult);
                }
            });
            return true;
        }
    }

    public void onDestroy() {
        this.rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this.list);
    }
}
