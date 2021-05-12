package com.stripe.android.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.stripe.android.ActivitySourceCallback;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.PaymentSession;
import com.stripe.android.R;
import com.stripe.android.Stripe;
import com.stripe.android.StripeError;
import com.stripe.android.model.Card;
import com.stripe.android.model.Source;
import com.stripe.android.model.SourceParams;
import com.stripe.android.model.StripePaymentSource;

public class AddSourceActivity extends StripeActivity {
    static final String ADD_SOURCE_ACTIVITY = "AddSourceActivity";
    public static final String EXTRA_NEW_SOURCE = "new_source";
    static final String EXTRA_PROXY_DELAY = "proxy_delay";
    static final String EXTRA_SHOW_ZIP = "show_zip";
    static final String EXTRA_UPDATE_CUSTOMER = "update_customer";
    /* access modifiers changed from: private */
    public CardMultilineWidget mCardMultilineWidget;
    private CustomerSessionProxy mCustomerSessionProxy;
    private final TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 6) {
                return false;
            }
            if (AddSourceActivity.this.mCardMultilineWidget.getCard() != null) {
                ((InputMethodManager) AddSourceActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AddSourceActivity.this.mViewStub.getWindowToken(), 0);
            }
            AddSourceActivity.this.onActionSave();
            return true;
        }
    };
    private boolean mStartedFromPaymentSession;
    private StripeProvider mStripeProvider;
    private boolean mUpdatesCustomer;

    interface CustomerSessionProxy {
        void addCustomerSource(String str, CustomerSession.SourceRetrievalListener sourceRetrievalListener);

        void addProductUsageTokenIfValid(String str);
    }

    interface StripeProvider {
        Stripe getStripe(Context context);
    }

    public /* bridge */ /* synthetic */ boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public /* bridge */ /* synthetic */ boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public /* bridge */ /* synthetic */ boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public static Intent newIntent(Context context, boolean z, boolean z2) {
        return new Intent(context, AddSourceActivity.class).putExtra(EXTRA_SHOW_ZIP, z).putExtra(EXTRA_UPDATE_CUSTOMER, z2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mViewStub.setLayoutResource(R.layout.activity_add_source);
        this.mViewStub.inflate();
        this.mCardMultilineWidget = (CardMultilineWidget) findViewById(R.id.add_source_card_entry_widget);
        initEnterListeners();
        boolean booleanExtra = getIntent().getBooleanExtra(EXTRA_SHOW_ZIP, false);
        this.mUpdatesCustomer = getIntent().getBooleanExtra(EXTRA_UPDATE_CUSTOMER, false);
        this.mStartedFromPaymentSession = getIntent().getBooleanExtra(PaymentSession.EXTRA_PAYMENT_SESSION_ACTIVE, true);
        this.mCardMultilineWidget.setShouldShowPostalCode(booleanExtra);
        if (this.mUpdatesCustomer && !getIntent().getBooleanExtra(EXTRA_PROXY_DELAY, false)) {
            initCustomerSessionTokens();
        }
        setTitle(R.string.title_add_a_card);
    }

    private void initEnterListeners() {
        ((TextView) this.mCardMultilineWidget.findViewById(R.id.et_add_source_card_number_ml)).setOnEditorActionListener(this.mOnEditorActionListener);
        ((TextView) this.mCardMultilineWidget.findViewById(R.id.et_add_source_expiry_ml)).setOnEditorActionListener(this.mOnEditorActionListener);
        ((TextView) this.mCardMultilineWidget.findViewById(R.id.et_add_source_cvc_ml)).setOnEditorActionListener(this.mOnEditorActionListener);
        ((TextView) this.mCardMultilineWidget.findViewById(R.id.et_add_source_postal_ml)).setOnEditorActionListener(this.mOnEditorActionListener);
    }

    /* access modifiers changed from: package-private */
    public void initCustomerSessionTokens() {
        logToCustomerSessionIf(ADD_SOURCE_ACTIVITY, this.mUpdatesCustomer);
        logToCustomerSessionIf(PaymentSession.TOKEN_PAYMENT_SESSION, this.mStartedFromPaymentSession);
    }

    /* access modifiers changed from: protected */
    public void onActionSave() {
        CardMultilineWidget cardMultilineWidget = this.mCardMultilineWidget;
        Card card = cardMultilineWidget != null ? cardMultilineWidget.getCard() : null;
        if (card != null) {
            card.addLoggingToken(ADD_SOURCE_ACTIVITY);
            Stripe stripe = getStripe();
            stripe.setDefaultPublishableKey(PaymentConfiguration.getInstance().getPublishableKey());
            SourceParams createCardParams = SourceParams.createCardParams(card);
            setCommunicatingProgress(true);
            stripe.createSource(createCardParams, new SourceCallbackImpl(this.mUpdatesCustomer));
        }
    }

    /* access modifiers changed from: private */
    public void attachCardToCustomer(StripePaymentSource stripePaymentSource) {
        String str;
        SourceRetrievalListenerImpl sourceRetrievalListenerImpl = new SourceRetrievalListenerImpl();
        CustomerSessionProxy customerSessionProxy = this.mCustomerSessionProxy;
        if (customerSessionProxy == null) {
            if (stripePaymentSource instanceof Source) {
                str = ((Source) stripePaymentSource).getType();
            } else {
                str = stripePaymentSource instanceof Card ? "card" : "unknown";
            }
            CustomerSession.getInstance().addCustomerSource(this, stripePaymentSource.getId(), str, sourceRetrievalListenerImpl);
            return;
        }
        customerSessionProxy.addCustomerSource(stripePaymentSource.getId(), sourceRetrievalListenerImpl);
    }

    private void logToCustomerSessionIf(String str, boolean z) {
        if (this.mCustomerSessionProxy != null) {
            logToProxyIf(str, z);
        } else if (z) {
            CustomerSession.getInstance().addProductUsageTokenIfValid(str);
        }
    }

    private void logToProxyIf(String str, boolean z) {
        CustomerSessionProxy customerSessionProxy = this.mCustomerSessionProxy;
        if (customerSessionProxy != null && z) {
            customerSessionProxy.addProductUsageTokenIfValid(str);
        }
    }

    /* access modifiers changed from: private */
    public void finishWithSource(Source source) {
        setCommunicatingProgress(false);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NEW_SOURCE, source.toJson().toString());
        setResult(-1, intent);
        finish();
    }

    private Stripe getStripe() {
        StripeProvider stripeProvider = this.mStripeProvider;
        if (stripeProvider == null) {
            return new Stripe(getApplicationContext());
        }
        return stripeProvider.getStripe(this);
    }

    /* access modifiers changed from: protected */
    public void setCommunicatingProgress(boolean z) {
        super.setCommunicatingProgress(z);
        CardMultilineWidget cardMultilineWidget = this.mCardMultilineWidget;
        if (cardMultilineWidget != null) {
            cardMultilineWidget.setEnabled(!z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setCustomerSessionProxy(CustomerSessionProxy customerSessionProxy) {
        this.mCustomerSessionProxy = customerSessionProxy;
    }

    /* access modifiers changed from: package-private */
    public void setStripeProvider(StripeProvider stripeProvider) {
        this.mStripeProvider = stripeProvider;
    }

    private static final class SourceCallbackImpl extends ActivitySourceCallback<AddSourceActivity> {
        private final boolean mUpdatesCustomer;

        private SourceCallbackImpl(AddSourceActivity addSourceActivity, boolean z) {
            super(addSourceActivity);
            this.mUpdatesCustomer = z;
        }

        public void onError(Exception exc) {
            AddSourceActivity addSourceActivity = (AddSourceActivity) getActivity();
            if (addSourceActivity != null) {
                addSourceActivity.setCommunicatingProgress(false);
                addSourceActivity.showError(exc.getLocalizedMessage());
            }
        }

        public void onSuccess(Source source) {
            AddSourceActivity addSourceActivity = (AddSourceActivity) getActivity();
            if (addSourceActivity != null) {
                if (this.mUpdatesCustomer) {
                    addSourceActivity.attachCardToCustomer(source);
                } else {
                    addSourceActivity.finishWithSource(source);
                }
            }
        }
    }

    private static final class SourceRetrievalListenerImpl extends CustomerSession.ActivitySourceRetrievalListener<AddSourceActivity> {
        private SourceRetrievalListenerImpl(AddSourceActivity addSourceActivity) {
            super(addSourceActivity);
        }

        public void onSourceRetrieved(Source source) {
            AddSourceActivity addSourceActivity = (AddSourceActivity) getActivity();
            if (addSourceActivity != null) {
                addSourceActivity.finishWithSource(source);
            }
        }

        public void onError(int i, String str, StripeError stripeError) {
            AddSourceActivity addSourceActivity = (AddSourceActivity) getActivity();
            if (addSourceActivity != null) {
                addSourceActivity.setCommunicatingProgress(false);
            }
        }
    }
}
