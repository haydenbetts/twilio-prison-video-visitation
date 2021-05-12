package com.urbanairship.actions;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.R;
import com.urbanairship.UAirship;
import com.urbanairship.activity.ThemedActivity;

public class RateAppActivity extends ThemedActivity {
    private AlertDialog dialog;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Autopilot.automaticTakeOff(getApplication());
        if (!UAirship.isTakingOff() && !UAirship.isFlying()) {
            Logger.error("RateAppActivity - unable to create activity, takeOff not called.", new Object[0]);
            finish();
        }
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.debug("RateAppActivity - New intent received for rate app activity", new Object[0]);
        restartActivity(intent.getData(), intent.getExtras());
    }

    public void onResume() {
        super.onResume();
        displayDialog();
    }

    public void onPause() {
        super.onPause();
    }

    public void onCloseButtonClick(View view) {
        finish();
    }

    private void displayDialog() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            Intent intent = getIntent();
            if (intent == null) {
                Logger.error("RateAppActivity - Started activity with null intent.", new Object[0]);
                finish();
                return;
            }
            final Uri uri = (Uri) intent.getParcelableExtra("store_uri");
            if (uri == null) {
                Logger.error("RateAppActivity - Missing store URI.", new Object[0]);
                finish();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (intent.getStringExtra("title") != null) {
                builder.setTitle(intent.getStringExtra("title"));
            } else {
                builder.setTitle(getString(R.string.ua_rate_app_action_default_title, new Object[]{getAppName()}));
            }
            if (intent.getStringExtra("body") != null) {
                builder.setMessage(intent.getStringExtra("body"));
            } else {
                String string = getString(R.string.ua_rate_app_action_default_rate_positive_button);
                builder.setMessage(getString(R.string.ua_rate_app_action_default_body, new Object[]{string}));
            }
            builder.setPositiveButton(getString(R.string.ua_rate_app_action_default_rate_positive_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        RateAppActivity.this.startActivity(new Intent("android.intent.action.VIEW", uri));
                    } catch (ActivityNotFoundException e) {
                        Logger.error(e, "No web browser available to handle request to open the store link.", new Object[0]);
                    }
                    dialogInterface.cancel();
                    RateAppActivity.this.finish();
                }
            });
            builder.setNegativeButton(getString(R.string.ua_rate_app_action_default_rate_negative_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    RateAppActivity.this.finish();
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.cancel();
                    RateAppActivity.this.finish();
                }
            });
            AlertDialog create = builder.create();
            this.dialog = create;
            create.setCancelable(true);
            this.dialog.show();
        }
    }

    private void restartActivity(Uri uri, Bundle bundle) {
        Logger.debug("Relaunching activity", new Object[0]);
        finish();
        Intent flags = new Intent().setClass(this, getClass()).setData(uri).setFlags(268435456);
        if (bundle != null) {
            flags.putExtras(bundle);
        }
        startActivity(flags);
    }

    private String getAppName() {
        String packageName = UAirship.getApplicationContext().getPackageName();
        PackageManager packageManager = UAirship.getApplicationContext().getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 128));
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }
}
