package com.stripe.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.stripe.android.EphemeralKeyManager;
import com.stripe.android.StripeApiHandler;
import com.stripe.android.exception.StripeException;
import com.stripe.android.model.Customer;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.model.Source;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomerSession implements EphemeralKeyManager.KeyManagerListener<CustomerEphemeralKey> {
    private static final String ACTION_ADD_SOURCE = "add_source";
    public static final String ACTION_API_EXCEPTION = "action_api_exception";
    private static final String ACTION_DELETE_SOURCE = "delete_source";
    private static final String ACTION_SET_CUSTOMER_SHIPPING_INFO = "set_shipping_info";
    private static final String ACTION_SET_DEFAULT_SOURCE = "default_source";
    private static final long CUSTOMER_CACHE_DURATION_MILLISECONDS = TimeUnit.MINUTES.toMillis(1);
    private static final int CUSTOMER_ERROR = 11;
    private static final int CUSTOMER_RETRIEVED = 7;
    private static final int CUSTOMER_SHIPPING_INFO_SAVED = 19;
    public static final String EVENT_SHIPPING_INFO_SAVED = "shipping_info_saved";
    public static final String EXTRA_EXCEPTION = "exception";
    private static final int KEEP_ALIVE_TIME = 2;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static final long KEY_REFRESH_BUFFER_IN_SECONDS = 30;
    private static final String KEY_SHIPPING_INFO = "shipping_info";
    private static final String KEY_SOURCE = "source";
    private static final String KEY_SOURCE_TYPE = "source_type";
    private static final int SOURCE_ERROR = 17;
    private static final int SOURCE_RETRIEVED = 13;
    private static final int THREAD_POOL_SIZE = 3;
    private static final String TOKEN_PAYMENT_SESSION = "PaymentSession";
    private static final Set<String> VALID_TOKENS = new HashSet(Arrays.asList(new String[]{"AddSourceActivity", "PaymentMethodsActivity", "PaymentFlowActivity", "PaymentSession", "ShippingInfoScreen", "ShippingMethodScreen"}));
    private static CustomerSession mInstance;
    private final StripeApiHandler mApiHandler;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public Customer mCustomer;
    /* access modifiers changed from: private */
    public long mCustomerCacheTime;
    private final Map<String, CustomerRetrievalListener> mCustomerRetrievalListeners;
    private final EphemeralKeyManager mEphemeralKeyManager;
    private final Set<String> mProductUsageTokens;
    private final Calendar mProxyNowCalendar;
    private final Map<String, SourceRetrievalListener> mSourceRetrievalListeners;
    private final ThreadPoolExecutor mThreadPoolExecutor;
    /* access modifiers changed from: private */
    public final Handler mUiThreadHandler;

    public interface CustomerRetrievalListener extends RetrievalListener {
        void onCustomerRetrieved(Customer customer);
    }

    interface RetrievalListener {
        void onError(int i, String str, StripeError stripeError);
    }

    public interface SourceRetrievalListener extends RetrievalListener {
        void onSourceRetrieved(Source source);
    }

    public static void initCustomerSession(EphemeralKeyProvider ephemeralKeyProvider) {
        setInstance(new CustomerSession(ephemeralKeyProvider));
    }

    public static CustomerSession getInstance() {
        CustomerSession customerSession = mInstance;
        if (customerSession != null) {
            return customerSession;
        }
        throw new IllegalStateException("Attempted to get instance of CustomerSession without initialization.");
    }

    static void setInstance(CustomerSession customerSession) {
        mInstance = customerSession;
    }

    public static void endCustomerSession() {
        clearInstance();
    }

    static void clearInstance() {
        CustomerSession customerSession = mInstance;
        if (customerSession != null) {
            customerSession.mCustomerRetrievalListeners.clear();
            mInstance.mSourceRetrievalListeners.clear();
        }
        cancelCallbacks();
        setInstance((CustomerSession) null);
    }

    public static void cancelCallbacks() {
        CustomerSession customerSession = mInstance;
        if (customerSession != null) {
            customerSession.mThreadPoolExecutor.shutdownNow();
        }
    }

    private CustomerSession(EphemeralKeyProvider ephemeralKeyProvider) {
        this(ephemeralKeyProvider, (Calendar) null, createThreadPoolExecutor(), new StripeApiHandler());
    }

    CustomerSession(EphemeralKeyProvider ephemeralKeyProvider, Calendar calendar, ThreadPoolExecutor threadPoolExecutor, StripeApiHandler stripeApiHandler) {
        this.mCustomerRetrievalListeners = new HashMap();
        this.mSourceRetrievalListeners = new HashMap();
        this.mThreadPoolExecutor = threadPoolExecutor;
        this.mProxyNowCalendar = calendar;
        this.mProductUsageTokens = new HashSet();
        this.mApiHandler = stripeApiHandler;
        this.mUiThreadHandler = createMainThreadHandler();
        this.mEphemeralKeyManager = new EphemeralKeyManager(ephemeralKeyProvider, this, KEY_REFRESH_BUFFER_IN_SECONDS, calendar, CustomerEphemeralKey.class);
    }

    public void addProductUsageTokenIfValid(String str) {
        if (VALID_TOKENS.contains(str)) {
            this.mProductUsageTokens.add(str);
        }
    }

    public void retrieveCurrentCustomer(CustomerRetrievalListener customerRetrievalListener) {
        Customer cachedCustomer = getCachedCustomer();
        if (cachedCustomer != null) {
            customerRetrievalListener.onCustomerRetrieved(cachedCustomer);
            return;
        }
        this.mCustomer = null;
        String uuid = UUID.randomUUID().toString();
        this.mCustomerRetrievalListeners.put(uuid, customerRetrievalListener);
        this.mEphemeralKeyManager.retrieveEphemeralKey(uuid, (String) null, (Map<String, Object>) null);
    }

    public void updateCurrentCustomer(CustomerRetrievalListener customerRetrievalListener) {
        this.mCustomer = null;
        String uuid = UUID.randomUUID().toString();
        this.mCustomerRetrievalListeners.put(uuid, customerRetrievalListener);
        this.mEphemeralKeyManager.retrieveEphemeralKey(uuid, (String) null, (Map<String, Object>) null);
    }

    public Customer getCachedCustomer() {
        if (canUseCachedCustomer()) {
            return this.mCustomer;
        }
        return null;
    }

    public void addCustomerSource(Context context, String str, String str2, SourceRetrievalListener sourceRetrievalListener) {
        this.mContext = context.getApplicationContext();
        HashMap hashMap = new HashMap();
        hashMap.put("source", str);
        hashMap.put(KEY_SOURCE_TYPE, str2);
        String uuid = UUID.randomUUID().toString();
        if (sourceRetrievalListener != null) {
            this.mSourceRetrievalListeners.put(uuid, sourceRetrievalListener);
        }
        this.mEphemeralKeyManager.retrieveEphemeralKey(uuid, ACTION_ADD_SOURCE, hashMap);
    }

    public void deleteCustomerSource(Context context, String str, SourceRetrievalListener sourceRetrievalListener) {
        this.mContext = context.getApplicationContext();
        HashMap hashMap = new HashMap();
        hashMap.put("source", str);
        String uuid = UUID.randomUUID().toString();
        if (sourceRetrievalListener != null) {
            this.mSourceRetrievalListeners.put(uuid, sourceRetrievalListener);
        }
        this.mEphemeralKeyManager.retrieveEphemeralKey(uuid, ACTION_DELETE_SOURCE, hashMap);
    }

    public void setCustomerShippingInformation(Context context, ShippingInformation shippingInformation) {
        this.mContext = context.getApplicationContext();
        HashMap hashMap = new HashMap();
        hashMap.put(KEY_SHIPPING_INFO, shippingInformation);
        this.mEphemeralKeyManager.retrieveEphemeralKey((String) null, ACTION_SET_CUSTOMER_SHIPPING_INFO, hashMap);
    }

    public void setCustomerDefaultSource(Context context, String str, String str2, CustomerRetrievalListener customerRetrievalListener) {
        this.mContext = context.getApplicationContext();
        HashMap hashMap = new HashMap();
        hashMap.put("source", str);
        hashMap.put(KEY_SOURCE_TYPE, str2);
        String uuid = UUID.randomUUID().toString();
        if (customerRetrievalListener != null) {
            this.mCustomerRetrievalListeners.put(uuid, customerRetrievalListener);
        }
        this.mEphemeralKeyManager.retrieveEphemeralKey(uuid, ACTION_SET_DEFAULT_SOURCE, hashMap);
    }

    /* access modifiers changed from: package-private */
    public void resetUsageTokens() {
        this.mProductUsageTokens.clear();
    }

    /* access modifiers changed from: package-private */
    public Customer getCustomer() {
        return this.mCustomer;
    }

    /* access modifiers changed from: package-private */
    public long getCustomerCacheTime() {
        return this.mCustomerCacheTime;
    }

    /* access modifiers changed from: package-private */
    public Set<String> getProductUsageTokens() {
        return this.mProductUsageTokens;
    }

    private void addCustomerSource(Context context, CustomerEphemeralKey customerEphemeralKey, String str, String str2, String str3) {
        final String str4 = str3;
        final Context context2 = context;
        final CustomerEphemeralKey customerEphemeralKey2 = customerEphemeralKey;
        final String str5 = str;
        final String str6 = str2;
        executeRunnable(new Runnable() {
            public void run() {
                try {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(13, new SourceMessage(str4, CustomerSession.this.addCustomerSourceWithKey(context2, customerEphemeralKey2, str5, str6))));
                } catch (StripeException e) {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(17, new ExceptionMessage(str4, e)));
                    CustomerSession.this.sendErrorIntent(e);
                }
            }
        });
    }

    private boolean canUseCachedCustomer() {
        return this.mCustomer != null && getCalendarInstance().getTimeInMillis() - this.mCustomerCacheTime < CUSTOMER_CACHE_DURATION_MILLISECONDS;
    }

    private void deleteCustomerSource(Context context, CustomerEphemeralKey customerEphemeralKey, String str, String str2) {
        final String str3 = str2;
        final Context context2 = context;
        final CustomerEphemeralKey customerEphemeralKey2 = customerEphemeralKey;
        final String str4 = str;
        executeRunnable(new Runnable() {
            public void run() {
                try {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(13, new SourceMessage(str3, CustomerSession.this.deleteCustomerSourceWithKey(context2, customerEphemeralKey2, str4))));
                } catch (StripeException e) {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(17, new ExceptionMessage(str3, e)));
                    CustomerSession.this.sendErrorIntent(e);
                }
            }
        });
    }

    private void setCustomerSourceDefault(Context context, CustomerEphemeralKey customerEphemeralKey, String str, String str2, String str3) {
        final String str4 = str3;
        final Context context2 = context;
        final CustomerEphemeralKey customerEphemeralKey2 = customerEphemeralKey;
        final String str5 = str;
        final String str6 = str2;
        executeRunnable(new Runnable() {
            public void run() {
                try {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(7, new CustomerMessage(str4, CustomerSession.this.setCustomerSourceDefaultWithKey(context2, customerEphemeralKey2, str5, str6))));
                } catch (StripeException e) {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(11, new ExceptionMessage(str4, e)));
                    CustomerSession.this.sendErrorIntent(e);
                }
            }
        });
    }

    private void setCustomerShippingInformation(Context context, CustomerEphemeralKey customerEphemeralKey, ShippingInformation shippingInformation, String str) {
        final String str2 = str;
        final Context context2 = context;
        final CustomerEphemeralKey customerEphemeralKey2 = customerEphemeralKey;
        final ShippingInformation shippingInformation2 = shippingInformation;
        executeRunnable(new Runnable() {
            public void run() {
                try {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(19, new CustomerMessage(str2, CustomerSession.this.setCustomerShippingInfoWithKey(context2, customerEphemeralKey2, shippingInformation2))));
                } catch (StripeException e) {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(11, new ExceptionMessage(str2, e)));
                    CustomerSession.this.sendErrorIntent(e);
                }
            }
        });
    }

    private void updateCustomer(final CustomerEphemeralKey customerEphemeralKey, final String str) {
        executeRunnable(new Runnable() {
            public void run() {
                try {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(7, new CustomerMessage(str, CustomerSession.this.retrieveCustomerWithKey(customerEphemeralKey))));
                } catch (StripeException e) {
                    CustomerSession.this.mUiThreadHandler.sendMessage(CustomerSession.this.mUiThreadHandler.obtainMessage(11, new ExceptionMessage(str, e)));
                }
            }
        });
    }

    private void executeRunnable(Runnable runnable) {
        this.mThreadPoolExecutor.execute(runnable);
    }

    public void onKeyUpdate(CustomerEphemeralKey customerEphemeralKey, String str, String str2, Map<String, Object> map) {
        if (str2 == null) {
            updateCustomer(customerEphemeralKey, str);
        } else if (map != null && this.mContext != null) {
            if (ACTION_ADD_SOURCE.equals(str2) && map.containsKey("source") && map.containsKey(KEY_SOURCE_TYPE)) {
                addCustomerSource(this.mContext, customerEphemeralKey, (String) map.get("source"), (String) map.get(KEY_SOURCE_TYPE), str);
                resetUsageTokens();
            } else if (ACTION_DELETE_SOURCE.equals(str2) && map.containsKey("source")) {
                deleteCustomerSource(this.mContext, customerEphemeralKey, (String) map.get("source"), str);
                resetUsageTokens();
            } else if (ACTION_SET_DEFAULT_SOURCE.equals(str2) && map.containsKey("source") && map.containsKey(KEY_SOURCE_TYPE)) {
                setCustomerSourceDefault(this.mContext, customerEphemeralKey, (String) map.get("source"), (String) map.get(KEY_SOURCE_TYPE), str);
                resetUsageTokens();
            } else if (ACTION_SET_CUSTOMER_SHIPPING_INFO.equals(str2) && map.containsKey(KEY_SHIPPING_INFO)) {
                setCustomerShippingInformation(this.mContext, customerEphemeralKey, (ShippingInformation) map.get(KEY_SHIPPING_INFO), str);
                resetUsageTokens();
            }
        }
    }

    public void onKeyError(String str, int i, String str2) {
        CustomerRetrievalListener customerRetrievalListener = getCustomerRetrievalListener(str);
        if (customerRetrievalListener != null) {
            customerRetrievalListener.onError(i, str2, (StripeError) null);
        }
        SourceRetrievalListener sourceRetrievalListener = getSourceRetrievalListener(str);
        if (sourceRetrievalListener != null) {
            sourceRetrievalListener.onError(i, str2, (StripeError) null);
        }
    }

    private Handler createMainThreadHandler() {
        return new Handler(Looper.getMainLooper()) {
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x006a, code lost:
                r4 = (com.stripe.android.CustomerSession.SourceMessage) r4.obj;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleMessage(android.os.Message r4) {
                /*
                    r3 = this;
                    super.handleMessage(r4)
                    int r0 = r4.what
                    r1 = 7
                    if (r0 == r1) goto L_0x00a6
                    r1 = 11
                    if (r0 == r1) goto L_0x008e
                    r1 = 13
                    if (r0 == r1) goto L_0x0064
                    r1 = 17
                    if (r0 == r1) goto L_0x004b
                    r1 = 19
                    if (r0 == r1) goto L_0x001a
                    goto L_0x00e3
                L_0x001a:
                    com.stripe.android.CustomerSession r0 = com.stripe.android.CustomerSession.this
                    android.content.Context r0 = r0.mContext
                    if (r0 == 0) goto L_0x00e3
                    java.lang.Object r0 = r4.obj
                    boolean r0 = r0 instanceof com.stripe.android.CustomerSession.CustomerMessage
                    if (r0 == 0) goto L_0x00e3
                    java.lang.Object r4 = r4.obj
                    com.stripe.android.CustomerSession$CustomerMessage r4 = (com.stripe.android.CustomerSession.CustomerMessage) r4
                    com.stripe.android.CustomerSession r0 = com.stripe.android.CustomerSession.this
                    com.stripe.android.model.Customer r4 = r4.customer
                    com.stripe.android.model.Customer unused = r0.mCustomer = r4
                    com.stripe.android.CustomerSession r4 = com.stripe.android.CustomerSession.this
                    android.content.Context r4 = r4.mContext
                    androidx.localbroadcastmanager.content.LocalBroadcastManager r4 = androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(r4)
                    android.content.Intent r0 = new android.content.Intent
                    java.lang.String r1 = "shipping_info_saved"
                    r0.<init>(r1)
                    r4.sendBroadcast(r0)
                    goto L_0x00e3
                L_0x004b:
                    java.lang.Object r0 = r4.obj
                    boolean r0 = r0 instanceof com.stripe.android.CustomerSession.ExceptionMessage
                    if (r0 == 0) goto L_0x00e3
                    java.lang.Object r4 = r4.obj
                    com.stripe.android.CustomerSession$ExceptionMessage r4 = (com.stripe.android.CustomerSession.ExceptionMessage) r4
                    com.stripe.android.CustomerSession r0 = com.stripe.android.CustomerSession.this
                    java.lang.String r2 = r4.operationId
                    com.stripe.android.exception.StripeException r4 = r4.exception
                    r0.handleRetrievalError(r2, r4, r1)
                    goto L_0x00e3
                L_0x0064:
                    java.lang.Object r0 = r4.obj
                    boolean r0 = r0 instanceof com.stripe.android.CustomerSession.SourceMessage
                    if (r0 == 0) goto L_0x0087
                    java.lang.Object r4 = r4.obj
                    com.stripe.android.CustomerSession$SourceMessage r4 = (com.stripe.android.CustomerSession.SourceMessage) r4
                    java.lang.String r0 = r4.operationId
                    com.stripe.android.CustomerSession r1 = com.stripe.android.CustomerSession.this
                    com.stripe.android.CustomerSession$SourceRetrievalListener r0 = r1.getSourceRetrievalListener(r0)
                    if (r0 == 0) goto L_0x0087
                    com.stripe.android.model.Source r1 = r4.source
                    if (r1 == 0) goto L_0x0087
                    com.stripe.android.model.Source r4 = r4.source
                    r0.onSourceRetrieved(r4)
                L_0x0087:
                    com.stripe.android.CustomerSession r4 = com.stripe.android.CustomerSession.this
                    r0 = 0
                    android.content.Context unused = r4.mContext = r0
                    goto L_0x00e3
                L_0x008e:
                    java.lang.Object r0 = r4.obj
                    boolean r0 = r0 instanceof com.stripe.android.CustomerSession.ExceptionMessage
                    if (r0 == 0) goto L_0x00e3
                    java.lang.Object r4 = r4.obj
                    com.stripe.android.CustomerSession$ExceptionMessage r4 = (com.stripe.android.CustomerSession.ExceptionMessage) r4
                    com.stripe.android.CustomerSession r0 = com.stripe.android.CustomerSession.this
                    java.lang.String r2 = r4.operationId
                    com.stripe.android.exception.StripeException r4 = r4.exception
                    r0.handleRetrievalError(r2, r4, r1)
                    goto L_0x00e3
                L_0x00a6:
                    java.lang.Object r0 = r4.obj
                    boolean r0 = r0 instanceof com.stripe.android.CustomerSession.CustomerMessage
                    if (r0 == 0) goto L_0x00e3
                    java.lang.Object r4 = r4.obj
                    com.stripe.android.CustomerSession$CustomerMessage r4 = (com.stripe.android.CustomerSession.CustomerMessage) r4
                    java.lang.String r0 = r4.operationId
                    com.stripe.android.CustomerSession r1 = com.stripe.android.CustomerSession.this
                    com.stripe.android.model.Customer r4 = r4.customer
                    com.stripe.android.model.Customer unused = r1.mCustomer = r4
                    com.stripe.android.CustomerSession r4 = com.stripe.android.CustomerSession.this
                    java.util.Calendar r1 = r4.getCalendarInstance()
                    long r1 = r1.getTimeInMillis()
                    long unused = r4.mCustomerCacheTime = r1
                    com.stripe.android.CustomerSession r4 = com.stripe.android.CustomerSession.this
                    com.stripe.android.CustomerSession$CustomerRetrievalListener r4 = r4.getCustomerRetrievalListener(r0)
                    if (r4 == 0) goto L_0x00e3
                    com.stripe.android.CustomerSession r0 = com.stripe.android.CustomerSession.this
                    com.stripe.android.model.Customer r0 = r0.mCustomer
                    if (r0 == 0) goto L_0x00e3
                    com.stripe.android.CustomerSession r0 = com.stripe.android.CustomerSession.this
                    com.stripe.android.model.Customer r0 = r0.mCustomer
                    r4.onCustomerRetrieved(r0)
                L_0x00e3:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.CustomerSession.AnonymousClass6.handleMessage(android.os.Message):void");
            }
        };
    }

    /* access modifiers changed from: private */
    public void handleRetrievalError(String str, StripeException stripeException, int i) {
        RetrievalListener retrievalListener;
        int i2;
        if (i == 17) {
            retrievalListener = this.mSourceRetrievalListeners.remove(str);
        } else {
            retrievalListener = i == 11 ? this.mCustomerRetrievalListeners.remove(str) : null;
        }
        if (retrievalListener != null) {
            if (stripeException.getStatusCode() == null) {
                i2 = 400;
            } else {
                i2 = stripeException.getStatusCode().intValue();
            }
            retrievalListener.onError(i2, stripeException.getLocalizedMessage(), stripeException.getStripeError());
        }
        resetUsageTokens();
    }

    private static ThreadPoolExecutor createThreadPoolExecutor() {
        return new ThreadPoolExecutor(3, 3, 2, KEEP_ALIVE_TIME_UNIT, new LinkedBlockingQueue());
    }

    /* access modifiers changed from: private */
    public Calendar getCalendarInstance() {
        Calendar calendar = this.mProxyNowCalendar;
        return calendar == null ? Calendar.getInstance() : calendar;
    }

    /* access modifiers changed from: private */
    public Source addCustomerSourceWithKey(Context context, CustomerEphemeralKey customerEphemeralKey, String str, String str2) throws StripeException {
        return this.mApiHandler.addCustomerSource(context, customerEphemeralKey.getCustomerId(), PaymentConfiguration.getInstance().getPublishableKey(), new ArrayList(this.mProductUsageTokens), str, str2, customerEphemeralKey.getSecret(), (StripeApiHandler.LoggingResponseListener) null);
    }

    /* access modifiers changed from: private */
    public Source deleteCustomerSourceWithKey(Context context, CustomerEphemeralKey customerEphemeralKey, String str) throws StripeException {
        return this.mApiHandler.deleteCustomerSource(context, customerEphemeralKey.getCustomerId(), PaymentConfiguration.getInstance().getPublishableKey(), new ArrayList(this.mProductUsageTokens), str, customerEphemeralKey.getSecret(), (StripeApiHandler.LoggingResponseListener) null);
    }

    /* access modifiers changed from: private */
    public Customer setCustomerShippingInfoWithKey(Context context, CustomerEphemeralKey customerEphemeralKey, ShippingInformation shippingInformation) throws StripeException {
        return this.mApiHandler.setCustomerShippingInfo(context, customerEphemeralKey.getCustomerId(), PaymentConfiguration.getInstance().getPublishableKey(), new ArrayList(this.mProductUsageTokens), shippingInformation, customerEphemeralKey.getSecret(), (StripeApiHandler.LoggingResponseListener) null);
    }

    /* access modifiers changed from: private */
    public Customer setCustomerSourceDefaultWithKey(Context context, CustomerEphemeralKey customerEphemeralKey, String str, String str2) throws StripeException {
        return this.mApiHandler.setDefaultCustomerSource(context, customerEphemeralKey.getCustomerId(), PaymentConfiguration.getInstance().getPublishableKey(), new ArrayList(this.mProductUsageTokens), str, str2, customerEphemeralKey.getSecret(), (StripeApiHandler.LoggingResponseListener) null);
    }

    /* access modifiers changed from: private */
    public Customer retrieveCustomerWithKey(CustomerEphemeralKey customerEphemeralKey) throws StripeException {
        return this.mApiHandler.retrieveCustomer(customerEphemeralKey.getCustomerId(), customerEphemeralKey.getSecret());
    }

    /* access modifiers changed from: private */
    public void sendErrorIntent(StripeException stripeException) {
        if (this.mContext != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRA_EXCEPTION, stripeException);
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(ACTION_API_EXCEPTION).putExtras(bundle));
        }
    }

    /* access modifiers changed from: private */
    public CustomerRetrievalListener getCustomerRetrievalListener(String str) {
        return this.mCustomerRetrievalListeners.remove(str);
    }

    /* access modifiers changed from: private */
    public SourceRetrievalListener getSourceRetrievalListener(String str) {
        return this.mSourceRetrievalListeners.remove(str);
    }

    public static abstract class ActivitySourceRetrievalListener<A extends Activity> implements SourceRetrievalListener {
        private final WeakReference<A> mActivityRef;

        public ActivitySourceRetrievalListener(A a) {
            this.mActivityRef = new WeakReference<>(a);
        }

        /* access modifiers changed from: protected */
        public A getActivity() {
            return (Activity) this.mActivityRef.get();
        }
    }

    private static class CustomerMessage {
        /* access modifiers changed from: private */
        public final Customer customer;
        /* access modifiers changed from: private */
        public final String operationId;

        private CustomerMessage(String str, Customer customer2) {
            this.operationId = str;
            this.customer = customer2;
        }
    }

    private static class SourceMessage {
        /* access modifiers changed from: private */
        public final String operationId;
        /* access modifiers changed from: private */
        public final Source source;

        private SourceMessage(String str, Source source2) {
            this.operationId = str;
            this.source = source2;
        }
    }

    private static class ExceptionMessage {
        /* access modifiers changed from: private */
        public final StripeException exception;
        /* access modifiers changed from: private */
        public final String operationId;

        private ExceptionMessage(String str, StripeException stripeException) {
            this.operationId = str;
            this.exception = stripeException;
        }
    }
}
