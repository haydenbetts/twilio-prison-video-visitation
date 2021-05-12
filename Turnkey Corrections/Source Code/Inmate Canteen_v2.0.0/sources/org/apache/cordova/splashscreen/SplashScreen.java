package org.apache.cordova.splashscreen;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class SplashScreen extends CordovaPlugin {
    private static final int DEFAULT_FADE_DURATION = 500;
    private static final int DEFAULT_SPLASHSCREEN_DURATION = 3000;
    private static final boolean HAS_BUILT_IN_SPLASH_SCREEN;
    private static final String LOG_TAG = "SplashScreen";
    private static boolean firstShow = true;
    /* access modifiers changed from: private */
    public static boolean lastHideAfterDelay;
    /* access modifiers changed from: private */
    public static ProgressDialog spinnerDialog;
    /* access modifiers changed from: private */
    public static Dialog splashDialog;
    private int orientation;
    /* access modifiers changed from: private */
    public ImageView splashImageView;

    static {
        boolean z = false;
        if (Integer.valueOf(CordovaWebView.CORDOVA_VERSION.split("\\.")[0]).intValue() < 4) {
            z = true;
        }
        HAS_BUILT_IN_SPLASH_SCREEN = z;
    }

    /* access modifiers changed from: private */
    public View getView() {
        try {
            return (View) this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
        } catch (Exception unused) {
            return (View) this.webView;
        }
    }

    private int getSplashId() {
        String string = this.preferences.getString(LOG_TAG, "screen");
        if (string == null) {
            return 0;
        }
        int identifier = this.cordova.getActivity().getResources().getIdentifier(string, "drawable", this.cordova.getActivity().getClass().getPackage().getName());
        return identifier == 0 ? this.cordova.getActivity().getResources().getIdentifier(string, "drawable", this.cordova.getActivity().getPackageName()) : identifier;
    }

    /* access modifiers changed from: protected */
    public void pluginInitialize() {
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    SplashScreen.this.getView().setVisibility(4);
                }
            });
            getSplashId();
            this.orientation = this.cordova.getActivity().getResources().getConfiguration().orientation;
            if (firstShow) {
                showSplashScreen(this.preferences.getBoolean("AutoHideSplashScreen", true));
            }
            if (this.preferences.getBoolean("SplashShowOnlyFirstTime", true)) {
                firstShow = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isMaintainAspectRatio() {
        return this.preferences.getBoolean("SplashMaintainAspectRatio", false);
    }

    /* access modifiers changed from: private */
    public int getFadeDuration() {
        int integer = this.preferences.getBoolean("FadeSplashScreen", true) ? this.preferences.getInteger("FadeSplashScreenDuration", DEFAULT_FADE_DURATION) : 0;
        return integer < 30 ? integer * 1000 : integer;
    }

    public void onPause(boolean z) {
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            removeSplashScreen(true);
        }
    }

    public void onDestroy() {
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            removeSplashScreen(true);
        }
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (str.equals("hide")) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    SplashScreen.this.webView.postMessage("splashscreen", "hide");
                }
            });
        } else if (!str.equals("show")) {
            return false;
        } else {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    SplashScreen.this.webView.postMessage("splashscreen", "show");
                }
            });
        }
        callbackContext.success();
        return true;
    }

    public Object onMessage(String str, Object obj) {
        if (HAS_BUILT_IN_SPLASH_SCREEN) {
            return null;
        }
        if ("splashscreen".equals(str)) {
            if ("hide".equals(obj.toString())) {
                removeSplashScreen(false);
            } else {
                showSplashScreen(false);
            }
        } else if ("spinner".equals(str)) {
            if ("stop".equals(obj.toString())) {
                getView().setVisibility(0);
            }
        } else if ("onReceivedError".equals(str)) {
            spinnerStop();
        }
        return null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        int splashId;
        if (configuration.orientation != this.orientation) {
            this.orientation = configuration.orientation;
            if (this.splashImageView != null && (splashId = getSplashId()) != 0) {
                this.splashImageView.setImageDrawable(this.cordova.getActivity().getResources().getDrawable(splashId));
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeSplashScreen(final boolean z) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (SplashScreen.splashDialog != null && SplashScreen.splashDialog.isShowing()) {
                    int access$200 = SplashScreen.this.getFadeDuration();
                    if (access$200 <= 0 || z) {
                        SplashScreen.this.spinnerStop();
                        SplashScreen.splashDialog.dismiss();
                        Dialog unused = SplashScreen.splashDialog = null;
                        ImageView unused2 = SplashScreen.this.splashImageView = null;
                        return;
                    }
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                    alphaAnimation.setInterpolator(new DecelerateInterpolator());
                    alphaAnimation.setDuration((long) access$200);
                    SplashScreen.this.splashImageView.setAnimation(alphaAnimation);
                    SplashScreen.this.splashImageView.startAnimation(alphaAnimation);
                    alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                            SplashScreen.this.spinnerStop();
                        }

                        public void onAnimationEnd(Animation animation) {
                            if (SplashScreen.splashDialog != null && SplashScreen.splashDialog.isShowing()) {
                                SplashScreen.splashDialog.dismiss();
                                Dialog unused = SplashScreen.splashDialog = null;
                                ImageView unused2 = SplashScreen.this.splashImageView = null;
                            }
                        }
                    });
                }
            }
        });
    }

    private void showSplashScreen(final boolean z) {
        int integer = this.preferences.getInteger("SplashScreenDelay", 3000);
        final int splashId = getSplashId();
        final int max = Math.max(0, integer - getFadeDuration());
        lastHideAfterDelay = z;
        if (!this.cordova.getActivity().isFinishing()) {
            Dialog dialog = splashDialog;
            if ((dialog != null && dialog.isShowing()) || splashId == 0) {
                return;
            }
            if (integer > 0 || !z) {
                this.cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Display defaultDisplay = SplashScreen.this.cordova.getActivity().getWindowManager().getDefaultDisplay();
                        Context context = SplashScreen.this.webView.getContext();
                        ImageView unused = SplashScreen.this.splashImageView = new ImageView(context);
                        SplashScreen.this.splashImageView.setImageResource(splashId);
                        SplashScreen.this.splashImageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                        SplashScreen.this.splashImageView.setMinimumHeight(defaultDisplay.getHeight());
                        SplashScreen.this.splashImageView.setMinimumWidth(defaultDisplay.getWidth());
                        SplashScreen.this.splashImageView.setBackgroundColor(SplashScreen.this.preferences.getInteger("backgroundColor", ViewCompat.MEASURED_STATE_MASK));
                        if (SplashScreen.this.isMaintainAspectRatio()) {
                            SplashScreen.this.splashImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        } else {
                            SplashScreen.this.splashImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        Dialog unused2 = SplashScreen.splashDialog = new Dialog(context, 16973840);
                        if ((SplashScreen.this.cordova.getActivity().getWindow().getAttributes().flags & 1024) == 1024) {
                            SplashScreen.splashDialog.getWindow().setFlags(1024, 1024);
                        }
                        SplashScreen.splashDialog.setContentView(SplashScreen.this.splashImageView);
                        SplashScreen.splashDialog.setCancelable(false);
                        SplashScreen.splashDialog.show();
                        if (SplashScreen.this.preferences.getBoolean("ShowSplashScreenSpinner", true)) {
                            SplashScreen.this.spinnerStart();
                        }
                        if (z) {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    if (SplashScreen.lastHideAfterDelay) {
                                        SplashScreen.this.removeSplashScreen(false);
                                    }
                                }
                            }, (long) max);
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void spinnerStart() {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                String string;
                SplashScreen.this.spinnerStop();
                ProgressDialog unused = SplashScreen.spinnerDialog = new ProgressDialog(SplashScreen.this.webView.getContext());
                SplashScreen.spinnerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        ProgressDialog unused = SplashScreen.spinnerDialog = null;
                    }
                });
                SplashScreen.spinnerDialog.setCancelable(false);
                SplashScreen.spinnerDialog.setIndeterminate(true);
                RelativeLayout relativeLayout = new RelativeLayout(SplashScreen.this.cordova.getActivity());
                relativeLayout.setGravity(17);
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
                ProgressBar progressBar = new ProgressBar(SplashScreen.this.webView.getContext());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13, -1);
                progressBar.setLayoutParams(layoutParams);
                if (Build.VERSION.SDK_INT >= 21 && (string = SplashScreen.this.preferences.getString("SplashScreenSpinnerColor", (String) null)) != null) {
                    int parseColor = Color.parseColor(string);
                    progressBar.setIndeterminateTintList(new ColorStateList(new int[][]{new int[]{16842910}, new int[]{-16842910}, new int[]{-16842912}, new int[]{16842919}}, new int[]{parseColor, parseColor, parseColor, parseColor}));
                }
                relativeLayout.addView(progressBar);
                SplashScreen.spinnerDialog.getWindow().clearFlags(2);
                SplashScreen.spinnerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                SplashScreen.spinnerDialog.show();
                SplashScreen.spinnerDialog.setContentView(relativeLayout);
            }
        });
    }

    /* access modifiers changed from: private */
    public void spinnerStop() {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (SplashScreen.spinnerDialog != null && SplashScreen.spinnerDialog.isShowing()) {
                    SplashScreen.spinnerDialog.dismiss();
                    ProgressDialog unused = SplashScreen.spinnerDialog = null;
                }
            }
        });
    }
}
