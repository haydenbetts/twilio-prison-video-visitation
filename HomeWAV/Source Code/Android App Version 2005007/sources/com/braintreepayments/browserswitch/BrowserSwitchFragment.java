package com.braintreepayments.browserswitch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

public abstract class BrowserSwitchFragment extends Fragment implements BrowserSwitchListener {
    BrowserSwitchClient browserSwitchClient = null;
    private String returnUrlScheme;

    public abstract void onBrowserSwitchResult(int i, BrowserSwitchResult browserSwitchResult, Uri uri);

    public void onAttach(Context context) {
        super.onAttach(context);
        String packageName = context.getApplicationContext().getPackageName();
        this.returnUrlScheme = packageName.toLowerCase().replace("_", "") + ".browserswitch";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.browserSwitchClient = BrowserSwitchClient.newInstance(getReturnUrlScheme());
    }

    public void onResume() {
        super.onResume();
        this.browserSwitchClient.deliverResult((Fragment) this);
    }

    public String getReturnUrlScheme() {
        return this.returnUrlScheme;
    }

    public void browserSwitch(int i, String str) {
        this.browserSwitchClient.start(new BrowserSwitchOptions().requestCode(i).url(Uri.parse(str)), (Fragment) this);
    }

    public void browserSwitch(int i, Intent intent) {
        this.browserSwitchClient.start(new BrowserSwitchOptions().intent(intent).requestCode(i), (Fragment) this);
    }

    public void browserSwitch(BrowserSwitchOptions browserSwitchOptions) {
        this.browserSwitchClient.start(browserSwitchOptions, (Fragment) this);
    }
}
