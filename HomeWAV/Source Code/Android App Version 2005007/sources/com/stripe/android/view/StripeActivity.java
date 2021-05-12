package com.stripe.android.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.stripe.android.CustomerSession;
import com.stripe.android.R;
import com.stripe.android.exception.StripeException;

abstract class StripeActivity extends AppCompatActivity {
    BroadcastReceiver mAlertBroadcastReceiver;
    AlertMessageListener mAlertMessageListener;
    boolean mCommunicating;
    ProgressBar mProgressBar;
    Toolbar mToolbar;
    ViewStub mViewStub;

    interface AlertMessageListener {
        void onAlertMessageDisplayed(String str);
    }

    /* access modifiers changed from: protected */
    public abstract void onActionSave();

    StripeActivity() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_stripe);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_as);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar_as);
        this.mViewStub = (ViewStub) findViewById(R.id.widget_viewstub_as);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setSupportActionBar(this.mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setCommunicatingProgress(false);
        this.mAlertBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                StripeActivity.this.showError(((StripeException) intent.getSerializableExtra(CustomerSession.EXTRA_EXCEPTION)).getLocalizedMessage());
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mAlertBroadcastReceiver);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mAlertBroadcastReceiver, new IntentFilter(CustomerSession.ACTION_API_EXCEPTION));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_source_menu, menu);
        menu.findItem(R.id.action_save).setEnabled(!this.mCommunicating);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_save) {
            onActionSave();
            return true;
        }
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        if (!onOptionsItemSelected) {
            onBackPressed();
        }
        return onOptionsItemSelected;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setIcon(ViewUtils.getTintedIconWithAttribute(this, getTheme(), R.attr.titleTextColor, R.drawable.ic_checkmark));
        return super.onPrepareOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void setCommunicatingProgress(boolean z) {
        this.mCommunicating = z;
        if (z) {
            this.mProgressBar.setVisibility(0);
        } else {
            this.mProgressBar.setVisibility(8);
        }
        supportInvalidateOptionsMenu();
    }

    /* access modifiers changed from: package-private */
    public void setAlertMessageListener(AlertMessageListener alertMessageListener) {
        this.mAlertMessageListener = alertMessageListener;
    }

    /* access modifiers changed from: package-private */
    public void showError(String str) {
        AlertMessageListener alertMessageListener = this.mAlertMessageListener;
        if (alertMessageListener != null) {
            alertMessageListener.onAlertMessageDisplayed(str);
        }
        new AlertDialog.Builder(this).setMessage((CharSequence) str).setCancelable(true).setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
}
