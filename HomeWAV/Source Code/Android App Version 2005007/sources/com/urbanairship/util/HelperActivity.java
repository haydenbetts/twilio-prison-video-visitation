package com.urbanairship.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import androidx.core.content.ContextCompat;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;

public class HelperActivity extends Activity {
    public static final String PERMISSIONS_EXTRA = "com.urbanairship.util.helperactivity.PERMISSIONS_EXTRA";
    public static final String RESULT_INTENT_EXTRA = "com.urbanairship.util.helperactivity.RESULT_INTENT_EXTRA";
    public static final String RESULT_RECEIVER_EXTRA = "com.urbanairship.util.helperactivity.RESULT_RECEIVER_EXTRA";
    public static final String START_ACTIVITY_INTENT_EXTRA = "com.urbanairship.util.START_ACTIVITY_INTENT_EXTRA";
    private static int requestCode;
    private ResultReceiver resultReceiver;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Autopilot.automaticTakeOff(getApplication());
        if (UAirship.isTakingOff() || UAirship.isFlying()) {
            Intent intent = getIntent();
            if (intent == null) {
                Logger.error("HelperActivity - Started with null intent", new Object[0]);
                finish();
            } else if (bundle == null) {
                Intent intent2 = (Intent) intent.getParcelableExtra(START_ACTIVITY_INTENT_EXTRA);
                String[] stringArrayExtra = intent.getStringArrayExtra(PERMISSIONS_EXTRA);
                if (intent2 != null) {
                    this.resultReceiver = (ResultReceiver) intent.getParcelableExtra(RESULT_RECEIVER_EXTRA);
                    int i = requestCode + 1;
                    requestCode = i;
                    startActivityForResult(intent2, i);
                } else if (Build.VERSION.SDK_INT < 23 || stringArrayExtra == null) {
                    Logger.error("HelperActivity - Started without START_ACTIVITY_INTENT_EXTRA or PERMISSIONS_EXTRA extra.", new Object[0]);
                    finish();
                } else {
                    this.resultReceiver = (ResultReceiver) intent.getParcelableExtra(RESULT_RECEIVER_EXTRA);
                    int i2 = requestCode + 1;
                    requestCode = i2;
                    requestPermissions(stringArrayExtra, i2);
                }
            }
        } else {
            Logger.error("HelperActivity - unable to create activity, takeOff not called.", new Object[0]);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(RESULT_INTENT_EXTRA, intent);
            this.resultReceiver.send(i2, bundle);
        }
        super.onActivityResult(i, i2, intent);
        finish();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putIntArray(RESULT_INTENT_EXTRA, iArr);
            this.resultReceiver.send(-1, bundle);
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
        finish();
    }

    public static int[] requestPermissions(Context context, String... strArr) {
        Context applicationContext = context.getApplicationContext();
        int length = strArr.length;
        final int[] iArr = new int[length];
        boolean z = false;
        for (int i = 0; i < length; i++) {
            iArr[i] = ContextCompat.checkSelfPermission(applicationContext, strArr[i]);
            if (iArr[i] == -1) {
                z = true;
            }
        }
        if (!z || Build.VERSION.SDK_INT < 23) {
            return iArr;
        }
        Intent putExtra = new Intent(applicationContext, HelperActivity.class).addFlags(268435456).setPackage(UAirship.getPackageName()).putExtra(PERMISSIONS_EXTRA, strArr).putExtra(RESULT_RECEIVER_EXTRA, new ResultReceiver(new Handler(Looper.getMainLooper())) {
            public void onReceiveResult(int i, Bundle bundle) {
                int[] intArray = bundle.getIntArray(HelperActivity.RESULT_INTENT_EXTRA);
                if (intArray != null) {
                    int length = intArray.length;
                    int[] iArr = iArr;
                    if (length == iArr.length) {
                        System.arraycopy(intArray, 0, iArr, 0, iArr.length);
                    }
                }
                synchronized (iArr) {
                    iArr.notify();
                }
            }
        });
        synchronized (iArr) {
            applicationContext.startActivity(putExtra);
            try {
                iArr.wait();
            } catch (InterruptedException e) {
                Logger.error(e, "Thread interrupted when waiting for result from activity.", new Object[0]);
                Thread.currentThread().interrupt();
            }
        }
        return iArr;
    }

    public static ActivityResult startActivityForResult(Context context, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        final ActivityResult activityResult = new ActivityResult();
        Intent putExtra = new Intent(applicationContext, HelperActivity.class).addFlags(268435456).setPackage(UAirship.getPackageName()).putExtra(START_ACTIVITY_INTENT_EXTRA, intent).putExtra(RESULT_RECEIVER_EXTRA, new ResultReceiver(new Handler(Looper.getMainLooper())) {
            public void onReceiveResult(int i, Bundle bundle) {
                activityResult.setResult(i, (Intent) bundle.getParcelable(HelperActivity.RESULT_INTENT_EXTRA));
                synchronized (activityResult) {
                    activityResult.notify();
                }
            }
        });
        synchronized (activityResult) {
            applicationContext.startActivity(putExtra);
            try {
                activityResult.wait();
            } catch (InterruptedException e) {
                Logger.error(e, "Thread interrupted when waiting for result from activity.", new Object[0]);
                Thread.currentThread().interrupt();
                return new ActivityResult();
            }
        }
        return activityResult;
    }

    public static class ActivityResult {
        private Intent intent;
        private int resultCode = 0;

        public Intent getIntent() {
            return this.intent;
        }

        public int getResultCode() {
            return this.resultCode;
        }

        /* access modifiers changed from: private */
        public void setResult(int i, Intent intent2) {
            this.resultCode = i;
            this.intent = intent2;
        }
    }
}
