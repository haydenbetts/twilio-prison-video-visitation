package com.braintreepayments.browserswitch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import org.json.JSONObject;

public class BrowserSwitchClient {
    private final ActivityFinder activityFinder;
    private final BrowserSwitchConfig config;
    private final BrowserSwitchPersistentStore persistentStore;
    private final String returnUrlScheme;

    private boolean isValidRequestCode(int i) {
        return i != Integer.MIN_VALUE;
    }

    public static BrowserSwitchClient newInstance(String str) {
        return new BrowserSwitchClient(BrowserSwitchConfig.newInstance(), ActivityFinder.newInstance(), BrowserSwitchPersistentStore.getInstance(), str);
    }

    static BrowserSwitchClient newInstance(BrowserSwitchConfig browserSwitchConfig, ActivityFinder activityFinder2, BrowserSwitchPersistentStore browserSwitchPersistentStore, String str) {
        return new BrowserSwitchClient(browserSwitchConfig, activityFinder2, browserSwitchPersistentStore, str);
    }

    private BrowserSwitchClient(BrowserSwitchConfig browserSwitchConfig, ActivityFinder activityFinder2, BrowserSwitchPersistentStore browserSwitchPersistentStore, String str) {
        this.config = browserSwitchConfig;
        this.activityFinder = activityFinder2;
        this.persistentStore = browserSwitchPersistentStore;
        this.returnUrlScheme = str;
    }

    public void start(int i, Uri uri, Fragment fragment) {
        start(new BrowserSwitchOptions().requestCode(i).url(uri), fragment);
    }

    public void start(int i, Uri uri, Fragment fragment, BrowserSwitchListener browserSwitchListener) {
        start(new BrowserSwitchOptions().requestCode(i).url(uri), fragment, browserSwitchListener);
    }

    public void start(int i, Uri uri, FragmentActivity fragmentActivity) {
        start(new BrowserSwitchOptions().requestCode(i).url(uri), fragmentActivity);
    }

    public void start(int i, Uri uri, FragmentActivity fragmentActivity, BrowserSwitchListener browserSwitchListener) {
        start(new BrowserSwitchOptions().requestCode(i).url(uri), fragmentActivity, browserSwitchListener);
    }

    public void start(int i, Intent intent, Fragment fragment) {
        start(new BrowserSwitchOptions().intent(intent).requestCode(i), fragment);
    }

    public void start(int i, Intent intent, Fragment fragment, BrowserSwitchListener browserSwitchListener) {
        start(new BrowserSwitchOptions().intent(intent).requestCode(i), fragment, browserSwitchListener);
    }

    public void start(int i, Intent intent, FragmentActivity fragmentActivity) {
        start(new BrowserSwitchOptions().intent(intent).requestCode(i), fragmentActivity);
    }

    public void start(int i, Intent intent, FragmentActivity fragmentActivity, BrowserSwitchListener browserSwitchListener) {
        start(new BrowserSwitchOptions().intent(intent).requestCode(i), fragmentActivity, browserSwitchListener);
    }

    public void start(BrowserSwitchOptions browserSwitchOptions, Fragment fragment) {
        if (fragment instanceof BrowserSwitchListener) {
            start(browserSwitchOptions, fragment, (BrowserSwitchListener) fragment);
            return;
        }
        throw new IllegalArgumentException("Fragment must implement BrowserSwitchListener.");
    }

    public void start(BrowserSwitchOptions browserSwitchOptions, Fragment fragment, BrowserSwitchListener browserSwitchListener) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            start(browserSwitchOptions, activity, browserSwitchListener);
            return;
        }
        throw new IllegalStateException("Fragment must be attached to an activity.");
    }

    public void start(BrowserSwitchOptions browserSwitchOptions, FragmentActivity fragmentActivity) {
        if (fragmentActivity instanceof BrowserSwitchListener) {
            start(browserSwitchOptions, fragmentActivity, (BrowserSwitchListener) fragmentActivity);
            return;
        }
        throw new IllegalArgumentException("Activity must implement BrowserSwitchListener.");
    }

    public void start(BrowserSwitchOptions browserSwitchOptions, FragmentActivity fragmentActivity, BrowserSwitchListener browserSwitchListener) {
        Intent intent;
        Context applicationContext = fragmentActivity.getApplicationContext();
        if (browserSwitchOptions.getIntent() != null) {
            intent = browserSwitchOptions.getIntent();
        } else {
            intent = this.config.createIntentToLaunchUriInBrowser(applicationContext, browserSwitchOptions.getUrl());
        }
        int requestCode = browserSwitchOptions.getRequestCode();
        String assertCanPerformBrowserSwitch = assertCanPerformBrowserSwitch(requestCode, applicationContext, intent);
        if (assertCanPerformBrowserSwitch == null) {
            this.persistentStore.putActiveRequest(new BrowserSwitchRequest(requestCode, intent.getData(), "PENDING", browserSwitchOptions.getMetadata()), applicationContext);
            applicationContext.startActivity(intent);
            return;
        }
        browserSwitchListener.onBrowserSwitchResult(requestCode, new BrowserSwitchResult(3, assertCanPerformBrowserSwitch), (Uri) null);
    }

    private String assertCanPerformBrowserSwitch(int i, Context context, Intent intent) {
        if (!isValidRequestCode(i)) {
            return "Request code cannot be Integer.MIN_VALUE";
        }
        if (!isConfiguredForBrowserSwitch(context)) {
            return "The return url scheme was not set up, incorrectly set up, or more than one Activity on this device defines the same url scheme in it's Android Manifest. See https://github.com/braintree/browser-switch-android for more information on setting up a return url scheme.";
        }
        if (canOpenUrl(context, intent)) {
            return null;
        }
        StringBuilder sb = new StringBuilder("No installed activities can open this URL");
        Uri data = intent.getData();
        if (data != null) {
            sb.append(String.format(": %s", new Object[]{data.toString()}));
        }
        return sb.toString();
    }

    private boolean isConfiguredForBrowserSwitch(Context context) {
        return this.activityFinder.canResolveActivityForIntent(context, this.config.createIntentForBrowserSwitchActivityQuery(this.returnUrlScheme));
    }

    private boolean canOpenUrl(Context context, Intent intent) {
        return this.activityFinder.canResolveActivityForIntent(context, intent);
    }

    public void deliverResult(Fragment fragment) {
        if (fragment instanceof BrowserSwitchListener) {
            deliverResult(fragment, (BrowserSwitchListener) fragment);
            return;
        }
        throw new IllegalArgumentException("Fragment must implement BrowserSwitchListener.");
    }

    public void deliverResult(Fragment fragment, BrowserSwitchListener browserSwitchListener) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            deliverResult(activity, browserSwitchListener);
            return;
        }
        throw new IllegalStateException("Fragment must be attached to an activity.");
    }

    public void deliverResult(FragmentActivity fragmentActivity) {
        if (fragmentActivity instanceof BrowserSwitchListener) {
            deliverResult(fragmentActivity, (BrowserSwitchListener) fragmentActivity);
            return;
        }
        throw new IllegalArgumentException("Activity must implement BrowserSwitchListener.");
    }

    public void deliverResult(FragmentActivity fragmentActivity, BrowserSwitchListener browserSwitchListener) {
        BrowserSwitchResult browserSwitchResult;
        Context applicationContext = fragmentActivity.getApplicationContext();
        BrowserSwitchRequest activeRequest = this.persistentStore.getActiveRequest(applicationContext);
        if (activeRequest != null) {
            this.persistentStore.clearActiveRequest(applicationContext);
            int requestCode = activeRequest.getRequestCode();
            JSONObject metadata = activeRequest.getMetadata();
            Uri uri = null;
            if (activeRequest.getState().equalsIgnoreCase("SUCCESS")) {
                Uri uri2 = activeRequest.getUri();
                browserSwitchResult = new BrowserSwitchResult(1, (String) null, metadata);
                uri = uri2;
            } else {
                browserSwitchResult = new BrowserSwitchResult(2, (String) null, metadata);
            }
            browserSwitchListener.onBrowserSwitchResult(requestCode, browserSwitchResult, uri);
        }
    }

    /* access modifiers changed from: package-private */
    public void captureResult(Intent intent, Context context) {
        if (intent != null) {
            Uri data = intent.getData();
            BrowserSwitchRequest activeRequest = this.persistentStore.getActiveRequest(context);
            if (activeRequest != null && data != null) {
                activeRequest.setUri(data);
                activeRequest.setState("SUCCESS");
                this.persistentStore.putActiveRequest(activeRequest, context);
            }
        }
    }
}
