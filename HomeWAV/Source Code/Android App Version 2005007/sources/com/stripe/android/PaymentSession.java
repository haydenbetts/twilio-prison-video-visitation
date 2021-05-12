package com.stripe.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.stripe.android.CustomerSession;
import com.stripe.android.model.Customer;
import com.stripe.android.view.PaymentFlowActivity;
import com.stripe.android.view.PaymentMethodsActivityStarter;
import java.lang.ref.WeakReference;

public class PaymentSession {
    public static final String EXTRA_PAYMENT_SESSION_ACTIVE = "payment_session_active";
    static final int PAYMENT_METHOD_REQUEST = 3003;
    public static final String PAYMENT_SESSION_CONFIG = "payment_session_config";
    public static final String PAYMENT_SESSION_DATA_KEY = "payment_session_data";
    static final int PAYMENT_SHIPPING_DETAILS_REQUEST = 3004;
    public static final String TOKEN_PAYMENT_SESSION = "PaymentSession";
    /* access modifiers changed from: private */
    public final CustomerSession mCustomerSession = CustomerSession.getInstance();
    private final Activity mHostActivity;
    private final PaymentMethodsActivityStarter mPaymentMethodsActivityStarter;
    /* access modifiers changed from: private */
    public PaymentSessionConfig mPaymentSessionConfig;
    /* access modifiers changed from: private */
    public PaymentSessionData mPaymentSessionData;
    /* access modifiers changed from: private */
    public PaymentSessionListener mPaymentSessionListener;

    public interface PaymentSessionListener {
        void onCommunicatingStateChanged(boolean z);

        void onError(int i, String str);

        void onPaymentSessionDataChanged(PaymentSessionData paymentSessionData);
    }

    public PaymentSession(Activity activity) {
        this.mHostActivity = activity;
        this.mPaymentMethodsActivityStarter = new PaymentMethodsActivityStarter(activity);
        this.mPaymentSessionData = new PaymentSessionData();
    }

    public void completePayment(PaymentCompletionProvider paymentCompletionProvider) {
        paymentCompletionProvider.completePayment(this.mPaymentSessionData, new PaymentResultListener() {
            public void onPaymentResult(String str) {
                PaymentSession.this.mPaymentSessionData.setPaymentResult(str);
                PaymentSession.this.mCustomerSession.resetUsageTokens();
                if (PaymentSession.this.mPaymentSessionListener != null) {
                    PaymentSession.this.mPaymentSessionListener.onPaymentSessionDataChanged(PaymentSession.this.mPaymentSessionData);
                }
            }
        });
    }

    public boolean handlePaymentData(int i, int i2, Intent intent) {
        if (i2 == 0) {
            fetchCustomer();
            return false;
        }
        if (i2 == -1) {
            if (i == PAYMENT_METHOD_REQUEST) {
                fetchCustomer();
                return true;
            } else if (i == PAYMENT_SHIPPING_DETAILS_REQUEST) {
                PaymentSessionData paymentSessionData = (PaymentSessionData) intent.getParcelableExtra(PAYMENT_SESSION_DATA_KEY);
                paymentSessionData.updateIsPaymentReadyToCharge(this.mPaymentSessionConfig);
                this.mPaymentSessionData = paymentSessionData;
                PaymentSessionListener paymentSessionListener = this.mPaymentSessionListener;
                if (paymentSessionListener != null) {
                    paymentSessionListener.onPaymentSessionDataChanged(paymentSessionData);
                }
                return true;
            }
        }
        return false;
    }

    public boolean init(PaymentSessionListener paymentSessionListener, PaymentSessionConfig paymentSessionConfig) {
        return init(paymentSessionListener, paymentSessionConfig, (Bundle) null);
    }

    public boolean init(PaymentSessionListener paymentSessionListener, PaymentSessionConfig paymentSessionConfig, Bundle bundle) {
        PaymentSessionData paymentSessionData;
        if (bundle == null) {
            try {
                this.mCustomerSession.resetUsageTokens();
            } catch (IllegalStateException unused) {
                this.mPaymentSessionListener = null;
                return false;
            }
        }
        this.mCustomerSession.addProductUsageTokenIfValid(TOKEN_PAYMENT_SESSION);
        this.mPaymentSessionListener = paymentSessionListener;
        if (!(bundle == null || (paymentSessionData = (PaymentSessionData) bundle.getParcelable(PAYMENT_SESSION_DATA_KEY)) == null)) {
            this.mPaymentSessionData = paymentSessionData;
        }
        this.mPaymentSessionConfig = paymentSessionConfig;
        fetchCustomer();
        return true;
    }

    public void presentPaymentMethodSelection() {
        this.mHostActivity.startActivityForResult(this.mPaymentMethodsActivityStarter.newIntent().putExtra(EXTRA_PAYMENT_SESSION_ACTIVE, true), PAYMENT_METHOD_REQUEST);
    }

    public void savePaymentSessionInstanceState(Bundle bundle) {
        bundle.putParcelable(PAYMENT_SESSION_DATA_KEY, this.mPaymentSessionData);
    }

    public void setCartTotal(long j) {
        this.mPaymentSessionData.setCartTotal(j);
    }

    public void presentShippingFlow() {
        Intent intent = new Intent(this.mHostActivity, PaymentFlowActivity.class);
        intent.putExtra(PAYMENT_SESSION_CONFIG, this.mPaymentSessionConfig);
        intent.putExtra(PAYMENT_SESSION_DATA_KEY, this.mPaymentSessionData);
        intent.putExtra(EXTRA_PAYMENT_SESSION_ACTIVE, true);
        this.mHostActivity.startActivityForResult(intent, PAYMENT_SHIPPING_DETAILS_REQUEST);
    }

    public PaymentSessionData getPaymentSessionData() {
        return this.mPaymentSessionData;
    }

    public void onDestroy() {
        this.mPaymentSessionListener = null;
    }

    private void fetchCustomer() {
        PaymentSessionListener paymentSessionListener = this.mPaymentSessionListener;
        if (paymentSessionListener != null) {
            paymentSessionListener.onCommunicatingStateChanged(true);
        }
        this.mCustomerSession.retrieveCurrentCustomer(new CustomerSession.CustomerRetrievalListener() {
            public void onCustomerRetrieved(Customer customer) {
                PaymentSession.this.mPaymentSessionData.setSelectedPaymentMethodId(customer.getDefaultSource());
                PaymentSession.this.mPaymentSessionData.updateIsPaymentReadyToCharge(PaymentSession.this.mPaymentSessionConfig);
                if (PaymentSession.this.mPaymentSessionListener != null) {
                    PaymentSession.this.mPaymentSessionListener.onPaymentSessionDataChanged(PaymentSession.this.mPaymentSessionData);
                    PaymentSession.this.mPaymentSessionListener.onCommunicatingStateChanged(false);
                }
            }

            public void onError(int i, String str, StripeError stripeError) {
                if (PaymentSession.this.mPaymentSessionListener != null) {
                    PaymentSession.this.mPaymentSessionListener.onError(i, str);
                    PaymentSession.this.mPaymentSessionListener.onCommunicatingStateChanged(false);
                }
            }
        });
    }

    public static abstract class ActivityPaymentSessionListener<A extends Activity> implements PaymentSessionListener {
        private final WeakReference<A> mActivityRef;

        public ActivityPaymentSessionListener(A a) {
            this.mActivityRef = new WeakReference<>(a);
        }

        /* access modifiers changed from: protected */
        public A getListenerActivity() {
            return (Activity) this.mActivityRef.get();
        }
    }
}
