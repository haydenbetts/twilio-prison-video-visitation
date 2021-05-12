package com.braintreepayments.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.ConfigurationException;
import com.braintreepayments.api.exceptions.GoogleApiClientException;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.AmericanExpressListener;
import com.braintreepayments.api.interfaces.BraintreeCancelListener;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeListener;
import com.braintreepayments.api.interfaces.BraintreePaymentResultListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceDeletedListener;
import com.braintreepayments.api.interfaces.PaymentMethodNoncesUpdatedListener;
import com.braintreepayments.api.interfaces.QueuedCallback;
import com.braintreepayments.api.interfaces.UnionPayListener;
import com.braintreepayments.api.internal.AnalyticsDatabase;
import com.braintreepayments.api.internal.AnalyticsEvent;
import com.braintreepayments.api.internal.AnalyticsIntentService;
import com.braintreepayments.api.internal.AnalyticsSender;
import com.braintreepayments.api.internal.BraintreeApiHttpClient;
import com.braintreepayments.api.internal.BraintreeGraphQLHttpClient;
import com.braintreepayments.api.internal.BraintreeHttpClient;
import com.braintreepayments.api.models.AmericanExpressRewardsBalance;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.BraintreePaymentResult;
import com.braintreepayments.api.models.BraintreeRequestCodes;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.TokenizationKey;
import com.braintreepayments.api.models.UnionPayCapabilities;
import com.braintreepayments.browserswitch.BrowserSwitchFragment;
import com.braintreepayments.browserswitch.BrowserSwitchResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.Wallet;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import org.json.JSONException;

public class BraintreeFragment extends BrowserSwitchFragment {
    private static final String EXTRA_AUTHORIZATION_TOKEN = "com.braintreepayments.api.EXTRA_AUTHORIZATION_TOKEN";
    static final String EXTRA_CACHED_PAYMENT_METHOD_NONCES = "com.braintreepayments.api.EXTRA_CACHED_PAYMENT_METHOD_NONCES";
    static final String EXTRA_CONFIGURATION = "com.braintreepayments.api.EXTRA_CONFIGURATION";
    static final String EXTRA_FETCHED_PAYMENT_METHOD_NONCES = "com.braintreepayments.api.EXTRA_FETCHED_PAYMENT_METHOD_NONCES";
    private static final String EXTRA_INTEGRATION_TYPE = "com.braintreepayments.api.EXTRA_INTEGRATION_TYPE";
    private static final String EXTRA_SESSION_ID = "com.braintreepayments.api.EXTRA_SESSION_ID";
    public static final String EXTRA_WAS_BROWSER_SWITCH_RESULT = "com.braintreepayments.api.WAS_BROWSER_SWITCH_RESULT";
    @Deprecated
    public static final String TAG = "com.braintreepayments.api.BraintreeFragment";
    private static final String TAG_PREFIX = "BraintreeFragment";
    /* access modifiers changed from: private */
    public AmericanExpressListener mAmericanExpressListener;
    /* access modifiers changed from: private */
    public AnalyticsDatabase mAnalyticsDatabase;
    private Authorization mAuthorization;
    protected BraintreeApiHttpClient mBraintreeApiClient;
    /* access modifiers changed from: private */
    public BraintreePaymentResultListener mBraintreePaymentResultListener;
    private final List<PaymentMethodNonce> mCachedPaymentMethodNonces = new ArrayList();
    private final Queue<QueuedCallback> mCallbackQueue = new ArrayDeque();
    /* access modifiers changed from: private */
    public BraintreeCancelListener mCancelListener;
    private Configuration mConfiguration;
    /* access modifiers changed from: private */
    public BraintreeResponseListener<Exception> mConfigurationErrorListener;
    /* access modifiers changed from: private */
    public ConfigurationListener mConfigurationListener;
    private int mConfigurationRequestAttempts = 0;
    protected Context mContext;
    private CrashReporter mCrashReporter;
    /* access modifiers changed from: private */
    public BraintreeErrorListener mErrorListener;
    protected GoogleApiClient mGoogleApiClient;
    protected BraintreeGraphQLHttpClient mGraphQLHttpClient;
    private boolean mHasFetchedPaymentMethodNonces = false;
    protected BraintreeHttpClient mHttpClient;
    private String mIntegrationType;
    private boolean mNewActivityNeedsConfiguration;
    /* access modifiers changed from: private */
    public PaymentMethodNonceCreatedListener mPaymentMethodNonceCreatedListener;
    /* access modifiers changed from: private */
    public PaymentMethodNonceDeletedListener mPaymentMethodNonceDeletedListener;
    /* access modifiers changed from: private */
    public PaymentMethodNoncesUpdatedListener mPaymentMethodNoncesUpdatedListener;
    private String mReturnUrlScheme;
    private String mSessionId;
    /* access modifiers changed from: private */
    public UnionPayListener mUnionPayListener;

    public static BraintreeFragment newInstance(AppCompatActivity appCompatActivity, String str) throws InvalidArgumentException {
        if (appCompatActivity != null) {
            return newInstance(appCompatActivity, appCompatActivity.getSupportFragmentManager(), str);
        }
        throw new InvalidArgumentException("Activity is null");
    }

    public static BraintreeFragment newInstance(FragmentActivity fragmentActivity, String str) throws InvalidArgumentException {
        if (fragmentActivity != null) {
            return newInstance(fragmentActivity, fragmentActivity.getSupportFragmentManager(), str);
        }
        throw new InvalidArgumentException("Activity is null");
    }

    public static BraintreeFragment newInstance(Fragment fragment, String str) throws InvalidArgumentException {
        if (fragment != null) {
            return newInstance(fragment.getContext(), fragment.getChildFragmentManager(), str);
        }
        throw new InvalidArgumentException("Fragment is null");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:15|16|17|18|(2:19|20)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0066 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.braintreepayments.api.BraintreeFragment newInstance(android.content.Context r4, androidx.fragment.app.FragmentManager r5, java.lang.String r6) throws com.braintreepayments.api.exceptions.InvalidArgumentException {
        /*
            if (r4 == 0) goto L_0x00ad
            if (r5 == 0) goto L_0x00a5
            if (r6 == 0) goto L_0x009d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "BraintreeFragment."
            r0.append(r1)
            byte[] r1 = r6.getBytes()
            java.util.UUID r1 = java.util.UUID.nameUUIDFromBytes(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            androidx.fragment.app.Fragment r1 = r5.findFragmentByTag(r0)
            if (r1 == 0) goto L_0x002c
            androidx.fragment.app.Fragment r4 = r5.findFragmentByTag(r0)
            com.braintreepayments.api.BraintreeFragment r4 = (com.braintreepayments.api.BraintreeFragment) r4
            return r4
        L_0x002c:
            com.braintreepayments.api.BraintreeFragment r1 = new com.braintreepayments.api.BraintreeFragment
            r1.<init>()
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            com.braintreepayments.api.models.Authorization r6 = com.braintreepayments.api.models.Authorization.fromString(r6)     // Catch:{ InvalidArgumentException -> 0x0095 }
            java.lang.String r3 = "com.braintreepayments.api.EXTRA_AUTHORIZATION_TOKEN"
            r2.putParcelable(r3, r6)     // Catch:{ InvalidArgumentException -> 0x0095 }
            java.lang.String r6 = com.braintreepayments.api.internal.UUIDHelper.getFormattedUUID()
            java.lang.String r3 = "com.braintreepayments.api.EXTRA_SESSION_ID"
            r2.putString(r3, r6)
            java.lang.String r6 = com.braintreepayments.api.internal.IntegrationType.get(r4)
            java.lang.String r3 = "com.braintreepayments.api.EXTRA_INTEGRATION_TYPE"
            r2.putString(r3, r6)
            r1.setArguments(r2)
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ IllegalStateException -> 0x008a }
            r2 = 24
            if (r6 < r2) goto L_0x0075
            androidx.fragment.app.FragmentTransaction r6 = r5.beginTransaction()     // Catch:{ IllegalStateException | NullPointerException -> 0x0066 }
            androidx.fragment.app.FragmentTransaction r6 = r6.add((androidx.fragment.app.Fragment) r1, (java.lang.String) r0)     // Catch:{ IllegalStateException | NullPointerException -> 0x0066 }
            r6.commitNow()     // Catch:{ IllegalStateException | NullPointerException -> 0x0066 }
            goto L_0x0083
        L_0x0066:
            androidx.fragment.app.FragmentTransaction r6 = r5.beginTransaction()     // Catch:{ IllegalStateException -> 0x008a }
            androidx.fragment.app.FragmentTransaction r6 = r6.add((androidx.fragment.app.Fragment) r1, (java.lang.String) r0)     // Catch:{ IllegalStateException -> 0x008a }
            r6.commit()     // Catch:{ IllegalStateException -> 0x008a }
            r5.executePendingTransactions()     // Catch:{ IllegalStateException -> 0x0083 }
            goto L_0x0083
        L_0x0075:
            androidx.fragment.app.FragmentTransaction r6 = r5.beginTransaction()     // Catch:{ IllegalStateException -> 0x008a }
            androidx.fragment.app.FragmentTransaction r6 = r6.add((androidx.fragment.app.Fragment) r1, (java.lang.String) r0)     // Catch:{ IllegalStateException -> 0x008a }
            r6.commit()     // Catch:{ IllegalStateException -> 0x008a }
            r5.executePendingTransactions()     // Catch:{ IllegalStateException -> 0x0083 }
        L_0x0083:
            android.content.Context r4 = r4.getApplicationContext()
            r1.mContext = r4
            return r1
        L_0x008a:
            r4 = move-exception
            com.braintreepayments.api.exceptions.InvalidArgumentException r5 = new com.braintreepayments.api.exceptions.InvalidArgumentException
            java.lang.String r4 = r4.getMessage()
            r5.<init>(r4)
            throw r5
        L_0x0095:
            com.braintreepayments.api.exceptions.InvalidArgumentException r4 = new com.braintreepayments.api.exceptions.InvalidArgumentException
            java.lang.String r5 = "Tokenization Key or client token was invalid."
            r4.<init>(r5)
            throw r4
        L_0x009d:
            com.braintreepayments.api.exceptions.InvalidArgumentException r4 = new com.braintreepayments.api.exceptions.InvalidArgumentException
            java.lang.String r5 = "Tokenization Key or Client Token is null."
            r4.<init>(r5)
            throw r4
        L_0x00a5:
            com.braintreepayments.api.exceptions.InvalidArgumentException r4 = new com.braintreepayments.api.exceptions.InvalidArgumentException
            java.lang.String r5 = "FragmentManager is null"
            r4.<init>(r5)
            throw r4
        L_0x00ad:
            com.braintreepayments.api.exceptions.InvalidArgumentException r4 = new com.braintreepayments.api.exceptions.InvalidArgumentException
            java.lang.String r5 = "Context is null"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.BraintreeFragment.newInstance(android.content.Context, androidx.fragment.app.FragmentManager, java.lang.String):com.braintreepayments.api.BraintreeFragment");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.mNewActivityNeedsConfiguration = false;
        this.mCrashReporter = CrashReporter.setup(this);
        this.mSessionId = getArguments().getString(EXTRA_SESSION_ID);
        this.mIntegrationType = getArguments().getString(EXTRA_INTEGRATION_TYPE);
        this.mAuthorization = (Authorization) getArguments().getParcelable(EXTRA_AUTHORIZATION_TOKEN);
        this.mAnalyticsDatabase = AnalyticsDatabase.getInstance(getApplicationContext());
        if (this.mHttpClient == null) {
            this.mHttpClient = new BraintreeHttpClient(this.mAuthorization);
        }
        if (bundle != null) {
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(EXTRA_CACHED_PAYMENT_METHOD_NONCES);
            if (parcelableArrayList != null) {
                this.mCachedPaymentMethodNonces.addAll(parcelableArrayList);
            }
            this.mHasFetchedPaymentMethodNonces = bundle.getBoolean(EXTRA_FETCHED_PAYMENT_METHOD_NONCES);
            try {
                setConfiguration(Configuration.fromJson(bundle.getString(EXTRA_CONFIGURATION)));
            } catch (JSONException unused) {
            }
        } else if (this.mAuthorization instanceof TokenizationKey) {
            sendAnalyticsEvent("started.client-key");
        } else {
            sendAnalyticsEvent("started.client-token");
        }
        fetchConfiguration();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        onAttach((Activity) getActivity());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mNewActivityNeedsConfiguration = true;
        if (this.mContext == null) {
            this.mContext = activity.getApplicationContext();
        }
        this.mReturnUrlScheme = this.mContext.getPackageName().toLowerCase(Locale.ROOT).replace("_", "") + ".braintree";
    }

    public void onResume() {
        super.onResume();
        if (getActivity() instanceof BraintreeListener) {
            addListener((BraintreeListener) getActivity());
            if (this.mNewActivityNeedsConfiguration && getConfiguration() != null) {
                this.mNewActivityNeedsConfiguration = false;
                postConfigurationCallback();
            }
        }
        flushCallbacks();
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        if (googleApiClient != null && !googleApiClient.isConnected() && !this.mGoogleApiClient.isConnecting()) {
            this.mGoogleApiClient.connect();
        }
    }

    public void onPause() {
        super.onPause();
        if (getActivity() instanceof BraintreeListener) {
            removeListener((BraintreeListener) getActivity());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList(EXTRA_CACHED_PAYMENT_METHOD_NONCES, (ArrayList) this.mCachedPaymentMethodNonces);
        bundle.putBoolean(EXTRA_FETCHED_PAYMENT_METHOD_NONCES, this.mHasFetchedPaymentMethodNonces);
        Configuration configuration = this.mConfiguration;
        if (configuration != null) {
            bundle.putString(EXTRA_CONFIGURATION, configuration.toJson());
        }
    }

    public void onStop() {
        super.onStop();
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
        flushAnalyticsEvents();
    }

    public void onDetach() {
        super.onDetach();
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
            this.mGoogleApiClient = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCrashReporter.tearDown();
    }

    public void startActivityForResult(Intent intent, int i) {
        if (!isAdded()) {
            postCallback((Exception) new BraintreeException("BraintreeFragment is not attached to an Activity. Please ensure it is attached and try again."));
        } else {
            super.startActivityForResult(intent, i);
        }
    }

    public String getReturnUrlScheme() {
        return this.mReturnUrlScheme;
    }

    public void onBrowserSwitchResult(int i, BrowserSwitchResult browserSwitchResult, Uri uri) {
        int i2 = 1;
        Intent putExtra = new Intent().putExtra(EXTRA_WAS_BROWSER_SWITCH_RESULT, true);
        String str = i != 13487 ? i != 13591 ? i != 13596 ? "" : "local-payment" : "paypal" : "three-d-secure";
        if (browserSwitchResult.getStatus() == 1) {
            i2 = -1;
            sendAnalyticsEvent(str + ".browser-switch.succeeded");
        } else if (browserSwitchResult.getStatus() == 2) {
            i2 = 0;
            sendAnalyticsEvent(str + ".browser-switch.canceled");
        } else if (browserSwitchResult.getStatus() == 3) {
            String errorMessage = browserSwitchResult.getErrorMessage();
            if (errorMessage == null || !errorMessage.startsWith("No installed activities")) {
                sendAnalyticsEvent(str + ".browser-switch.failed.not-setup");
            } else {
                sendAnalyticsEvent(str + ".browser-switch.failed.no-browser-installed");
            }
        }
        onActivityResult(i, i2, putExtra.setData(uri));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 13487) {
            ThreeDSecure.onActivityResult(this, i2, intent);
        } else if (i == 13488) {
            Venmo.onActivityResult(this, i2, intent);
        } else if (i == 13596) {
            LocalPayment.onActivityResult(this, i2, intent);
        } else if (i != 13597) {
            switch (i) {
                case BraintreeRequestCodes.PAYPAL /*13591*/:
                    PayPal.onActivityResult(this, i2, intent);
                    break;
                case BraintreeRequestCodes.VISA_CHECKOUT /*13592*/:
                    VisaCheckoutFacade.onActivityResult(this, i2, intent);
                    break;
                case BraintreeRequestCodes.GOOGLE_PAYMENT /*13593*/:
                    GooglePayment.onActivityResult(this, i2, intent);
                    break;
            }
        } else {
            PayPalTwoFactorAuth.onActivityResult(this, i2, intent);
        }
        if (i2 == 0) {
            postCancelCallback(i);
        }
    }

    public <T extends BraintreeListener> void addListener(T t) {
        if (t instanceof ConfigurationListener) {
            this.mConfigurationListener = (ConfigurationListener) t;
        }
        if (t instanceof BraintreeCancelListener) {
            this.mCancelListener = (BraintreeCancelListener) t;
        }
        if (t instanceof PaymentMethodNoncesUpdatedListener) {
            this.mPaymentMethodNoncesUpdatedListener = (PaymentMethodNoncesUpdatedListener) t;
        }
        if (t instanceof PaymentMethodNonceCreatedListener) {
            this.mPaymentMethodNonceCreatedListener = (PaymentMethodNonceCreatedListener) t;
        }
        if (t instanceof PaymentMethodNonceDeletedListener) {
            this.mPaymentMethodNonceDeletedListener = (PaymentMethodNonceDeletedListener) t;
        }
        if (t instanceof BraintreePaymentResultListener) {
            this.mBraintreePaymentResultListener = (BraintreePaymentResultListener) t;
        }
        if (t instanceof BraintreeErrorListener) {
            this.mErrorListener = (BraintreeErrorListener) t;
        }
        if (t instanceof UnionPayListener) {
            this.mUnionPayListener = (UnionPayListener) t;
        }
        if (t instanceof AmericanExpressListener) {
            this.mAmericanExpressListener = (AmericanExpressListener) t;
        }
        flushCallbacks();
    }

    public <T extends BraintreeListener> void removeListener(T t) {
        if (t instanceof ConfigurationListener) {
            this.mConfigurationListener = null;
        }
        if (t instanceof BraintreeCancelListener) {
            this.mCancelListener = null;
        }
        if (t instanceof PaymentMethodNoncesUpdatedListener) {
            this.mPaymentMethodNoncesUpdatedListener = null;
        }
        if (t instanceof PaymentMethodNonceCreatedListener) {
            this.mPaymentMethodNonceCreatedListener = null;
        }
        if (t instanceof PaymentMethodNonceDeletedListener) {
            this.mPaymentMethodNonceDeletedListener = null;
        }
        if (t instanceof BraintreePaymentResultListener) {
            this.mBraintreePaymentResultListener = null;
        }
        if (t instanceof BraintreeErrorListener) {
            this.mErrorListener = null;
        }
        if (t instanceof UnionPayListener) {
            this.mUnionPayListener = null;
        }
        if (t instanceof AmericanExpressListener) {
            this.mAmericanExpressListener = null;
        }
    }

    public List<BraintreeListener> getListeners() {
        ArrayList arrayList = new ArrayList();
        ConfigurationListener configurationListener = this.mConfigurationListener;
        if (configurationListener != null) {
            arrayList.add(configurationListener);
        }
        BraintreeCancelListener braintreeCancelListener = this.mCancelListener;
        if (braintreeCancelListener != null) {
            arrayList.add(braintreeCancelListener);
        }
        PaymentMethodNoncesUpdatedListener paymentMethodNoncesUpdatedListener = this.mPaymentMethodNoncesUpdatedListener;
        if (paymentMethodNoncesUpdatedListener != null) {
            arrayList.add(paymentMethodNoncesUpdatedListener);
        }
        PaymentMethodNonceCreatedListener paymentMethodNonceCreatedListener = this.mPaymentMethodNonceCreatedListener;
        if (paymentMethodNonceCreatedListener != null) {
            arrayList.add(paymentMethodNonceCreatedListener);
        }
        PaymentMethodNonceDeletedListener paymentMethodNonceDeletedListener = this.mPaymentMethodNonceDeletedListener;
        if (paymentMethodNonceDeletedListener != null) {
            arrayList.add(paymentMethodNonceDeletedListener);
        }
        BraintreePaymentResultListener braintreePaymentResultListener = this.mBraintreePaymentResultListener;
        if (braintreePaymentResultListener != null) {
            arrayList.add(braintreePaymentResultListener);
        }
        BraintreeErrorListener braintreeErrorListener = this.mErrorListener;
        if (braintreeErrorListener != null) {
            arrayList.add(braintreeErrorListener);
        }
        UnionPayListener unionPayListener = this.mUnionPayListener;
        if (unionPayListener != null) {
            arrayList.add(unionPayListener);
        }
        AmericanExpressListener americanExpressListener = this.mAmericanExpressListener;
        if (americanExpressListener != null) {
            arrayList.add(americanExpressListener);
        }
        return arrayList;
    }

    public boolean hasFetchedPaymentMethodNonces() {
        return this.mHasFetchedPaymentMethodNonces;
    }

    public List<PaymentMethodNonce> getCachedPaymentMethodNonces() {
        return Collections.unmodifiableList(this.mCachedPaymentMethodNonces);
    }

    public boolean isActive() {
        return isAdded();
    }

    public void sendAnalyticsEvent(String str) {
        final AnalyticsEvent analyticsEvent = new AnalyticsEvent(this.mContext, getSessionId(), this.mIntegrationType, str);
        waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (configuration.getAnalytics().isEnabled()) {
                    BraintreeFragment.this.mAnalyticsDatabase.addEvent(analyticsEvent);
                }
            }
        });
    }

    private void flushAnalyticsEvents() {
        if (getConfiguration() != null && getConfiguration().toJson() != null && getConfiguration().getAnalytics().isEnabled()) {
            try {
                getApplicationContext().startService(new Intent(this.mContext, AnalyticsIntentService.class).putExtra(AnalyticsIntentService.EXTRA_AUTHORIZATION, getAuthorization().toString()).putExtra(AnalyticsIntentService.EXTRA_CONFIGURATION, getConfiguration().toJson()));
            } catch (RuntimeException unused) {
                AnalyticsSender.send(getApplicationContext(), this.mAuthorization, getHttpClient(), getConfiguration().getAnalytics().getUrl(), false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void postConfigurationCallback() {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mConfigurationListener != null;
            }

            public void run() {
                BraintreeFragment.this.mConfigurationListener.onConfigurationFetched(BraintreeFragment.this.getConfiguration());
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCancelCallback(final int i) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mCancelListener != null;
            }

            public void run() {
                BraintreeFragment.this.mCancelListener.onCancel(i);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final PaymentMethodNonce paymentMethodNonce) {
        this.mCachedPaymentMethodNonces.add(0, paymentMethodNonce);
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mPaymentMethodNonceCreatedListener != null;
            }

            public void run() {
                BraintreeFragment.this.mPaymentMethodNonceCreatedListener.onPaymentMethodNonceCreated(paymentMethodNonce);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final UnionPayCapabilities unionPayCapabilities) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mUnionPayListener != null;
            }

            public void run() {
                BraintreeFragment.this.mUnionPayListener.onCapabilitiesFetched(unionPayCapabilities);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postUnionPayCallback(final String str, final boolean z) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mUnionPayListener != null;
            }

            public void run() {
                BraintreeFragment.this.mUnionPayListener.onSmsCodeSent(str, z);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final BraintreePaymentResult braintreePaymentResult) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mBraintreePaymentResultListener != null;
            }

            public void run() {
                BraintreeFragment.this.mBraintreePaymentResultListener.onBraintreePaymentResult(braintreePaymentResult);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postAmericanExpressCallback(final AmericanExpressRewardsBalance americanExpressRewardsBalance) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mAmericanExpressListener != null;
            }

            public void run() {
                BraintreeFragment.this.mAmericanExpressListener.onRewardsBalanceFetched(americanExpressRewardsBalance);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final List<PaymentMethodNonce> list) {
        this.mCachedPaymentMethodNonces.clear();
        this.mCachedPaymentMethodNonces.addAll(list);
        this.mHasFetchedPaymentMethodNonces = true;
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mPaymentMethodNoncesUpdatedListener != null;
            }

            public void run() {
                BraintreeFragment.this.mPaymentMethodNoncesUpdatedListener.onPaymentMethodNoncesUpdated(list);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postPaymentMethodDeletedCallback(final PaymentMethodNonce paymentMethodNonce) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mPaymentMethodNonceDeletedListener != null;
            }

            public void run() {
                BraintreeFragment.this.mPaymentMethodNonceDeletedListener.onPaymentMethodNonceDeleted(paymentMethodNonce);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final Exception exc) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mErrorListener != null;
            }

            public void run() {
                BraintreeFragment.this.mErrorListener.onError(exc);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postOrQueueCallback(QueuedCallback queuedCallback) {
        if (!queuedCallback.shouldRun()) {
            synchronized (this.mCallbackQueue) {
                this.mCallbackQueue.add(queuedCallback);
            }
            return;
        }
        queuedCallback.run();
    }

    /* access modifiers changed from: protected */
    public void flushCallbacks() {
        synchronized (this.mCallbackQueue) {
            for (QueuedCallback queuedCallback : new ArrayDeque(this.mCallbackQueue)) {
                if (queuedCallback.shouldRun()) {
                    queuedCallback.run();
                    this.mCallbackQueue.remove(queuedCallback);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void fetchConfiguration() {
        if (getConfiguration() == null && !ConfigurationManager.isFetchingConfiguration() && this.mAuthorization != null && this.mHttpClient != null) {
            int i = this.mConfigurationRequestAttempts;
            if (i >= 3) {
                postCallback((Exception) new ConfigurationException("Configuration retry limit has been exceeded. Create a new BraintreeFragment and try again."));
                return;
            }
            this.mConfigurationRequestAttempts = i + 1;
            ConfigurationManager.getConfiguration(this, new ConfigurationListener() {
                public void onConfigurationFetched(Configuration configuration) {
                    BraintreeFragment.this.setConfiguration(configuration);
                    BraintreeFragment.this.postConfigurationCallback();
                    BraintreeFragment.this.flushCallbacks();
                }
            }, new BraintreeResponseListener<Exception>() {
                public void onResponse(Exception exc) {
                    final ConfigurationException configurationException = new ConfigurationException("Request for configuration has failed: " + exc.getMessage() + ". Future requests will retry up to 3 times", exc);
                    BraintreeFragment.this.postCallback((Exception) configurationException);
                    BraintreeFragment.this.postOrQueueCallback(new QueuedCallback() {
                        public boolean shouldRun() {
                            return BraintreeFragment.this.mConfigurationErrorListener != null;
                        }

                        public void run() {
                            BraintreeFragment.this.mConfigurationErrorListener.onResponse(configurationException);
                        }
                    });
                    BraintreeFragment.this.flushCallbacks();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void setConfigurationErrorListener(BraintreeResponseListener<Exception> braintreeResponseListener) {
        this.mConfigurationErrorListener = braintreeResponseListener;
    }

    /* access modifiers changed from: protected */
    public void waitForConfiguration(final ConfigurationListener configurationListener) {
        fetchConfiguration();
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.getConfiguration() != null && BraintreeFragment.this.isAdded();
            }

            public void run() {
                configurationListener.onConfigurationFetched(BraintreeFragment.this.getConfiguration());
            }
        });
    }

    /* access modifiers changed from: protected */
    public Authorization getAuthorization() {
        return this.mAuthorization;
    }

    /* access modifiers changed from: protected */
    public Context getApplicationContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    /* access modifiers changed from: protected */
    public void setConfiguration(Configuration configuration) {
        this.mConfiguration = configuration;
        getHttpClient().setBaseUrl(configuration.getClientApiUrl());
        if (configuration.getGraphQL().isEnabled()) {
            this.mGraphQLHttpClient = new BraintreeGraphQLHttpClient(configuration.getGraphQL().getUrl(), this.mAuthorization.getBearer());
        }
    }

    /* access modifiers changed from: protected */
    public BraintreeHttpClient getHttpClient() {
        return this.mHttpClient;
    }

    /* access modifiers changed from: protected */
    public BraintreeApiHttpClient getBraintreeApiHttpClient() {
        if (this.mBraintreeApiClient == null && getConfiguration() != null && getConfiguration().getBraintreeApiConfiguration().isEnabled()) {
            this.mBraintreeApiClient = new BraintreeApiHttpClient(getConfiguration().getBraintreeApiConfiguration().getUrl(), getConfiguration().getBraintreeApiConfiguration().getAccessToken());
        }
        return this.mBraintreeApiClient;
    }

    /* access modifiers changed from: protected */
    public BraintreeGraphQLHttpClient getGraphQLHttpClient() {
        return this.mGraphQLHttpClient;
    }

    /* access modifiers changed from: protected */
    public String getSessionId() {
        return this.mSessionId;
    }

    /* access modifiers changed from: protected */
    public String getIntegrationType() {
        return this.mIntegrationType;
    }

    public void getGoogleApiClient(final BraintreeResponseListener<GoogleApiClient> braintreeResponseListener) {
        waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                GoogleApiClient googleApiClient = BraintreeFragment.this.getGoogleApiClient();
                if (googleApiClient != null) {
                    braintreeResponseListener.onResponse(googleApiClient);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public GoogleApiClient getGoogleApiClient() {
        if (getActivity() == null) {
            postCallback((Exception) new GoogleApiClientException(GoogleApiClientException.ErrorType.NotAttachedToActivity, 1));
            return null;
        }
        if (this.mGoogleApiClient == null) {
            this.mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).addApi(Wallet.API, new Wallet.WalletOptions.Builder().setEnvironment(GooglePayment.getEnvironment(getConfiguration().getGooglePayment())).setTheme(1).build()).build();
        }
        if (!this.mGoogleApiClient.isConnected() && !this.mGoogleApiClient.isConnecting()) {
            this.mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                public void onConnected(Bundle bundle) {
                }

                public void onConnectionSuspended(int i) {
                    BraintreeFragment.this.postCallback((Exception) new GoogleApiClientException(GoogleApiClientException.ErrorType.ConnectionSuspended, i));
                }
            });
            this.mGoogleApiClient.registerConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                public void onConnectionFailed(ConnectionResult connectionResult) {
                    BraintreeFragment.this.postCallback((Exception) new GoogleApiClientException(GoogleApiClientException.ErrorType.ConnectionFailed, connectionResult.getErrorCode()));
                }
            });
            this.mGoogleApiClient.connect();
        }
        return this.mGoogleApiClient;
    }
}
