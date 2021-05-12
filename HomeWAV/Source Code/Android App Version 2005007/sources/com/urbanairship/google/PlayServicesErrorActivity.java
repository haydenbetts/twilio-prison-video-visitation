package com.urbanairship.google;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.GoogleApiAvailability;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;

public class PlayServicesErrorActivity extends FragmentActivity {
    private static final String DIALOG_TAG = "error_dialog";
    private static final int REQUEST_RESOLVE_ERROR = 1000;

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1000) {
            return;
        }
        if (i2 == -1) {
            Logger.debug("Google Play services resolution received result ok.", new Object[0]);
            checkPlayServices();
            return;
        }
        Logger.debug("Google Play services resolution canceled.", new Object[0]);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (getSupportFragmentManager().findFragmentByTag(DIALOG_TAG) == null) {
            checkPlayServices();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (isFinishing() && GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(this) == 0 && UAirship.shared().getPushManager().isPushEnabled()) {
            UAirship.shared().getChannel().updateRegistration();
        }
    }

    private void checkPlayServices() {
        Logger.info("Checking Google Play services.", new Object[0]);
        int isGooglePlayServicesAvailable = GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(this);
        if (isGooglePlayServicesAvailable == 0) {
            Logger.debug("Google Play services available!", new Object[0]);
            finish();
        } else if (GooglePlayServicesUtilWrapper.isUserRecoverableError(isGooglePlayServicesAvailable)) {
            Logger.debug("Google Play services recoverable error: %s", Integer.valueOf(isGooglePlayServicesAvailable));
            ErrorDialogFragment.createInstance(isGooglePlayServicesAvailable).show(getSupportFragmentManager(), DIALOG_TAG);
        } else {
            Logger.error("Unrecoverable Google Play services error: %s", Integer.valueOf(isGooglePlayServicesAvailable));
            finish();
        }
    }

    public static class ErrorDialogFragment extends DialogFragment {
        private static final String DIALOG_ERROR = "dialog_error";

        public static ErrorDialogFragment createInstance(int i) {
            ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(DIALOG_ERROR, i);
            errorDialogFragment.setArguments(bundle);
            return errorDialogFragment;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            return GoogleApiAvailability.getInstance().getErrorDialog((Activity) getActivity(), getArguments() != null ? getArguments().getInt(DIALOG_ERROR) : 0, 1000);
        }

        public void onCancel(DialogInterface dialogInterface) {
            super.onCancel(dialogInterface);
            if (getActivity() != null) {
                getActivity().finish();
            }
        }
    }
}
