package com.stripe.android.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentSession;
import com.stripe.android.R;
import com.stripe.android.StripeError;
import com.stripe.android.model.Customer;
import com.stripe.android.model.CustomerSource;
import com.stripe.android.view.i18n.TranslatorManager;
import java.util.List;

public class PaymentMethodsActivity extends AppCompatActivity {
    static final String EXTRA_PROXY_DELAY = "proxy_delay";
    public static final String EXTRA_SELECTED_PAYMENT = "selected_payment";
    static final String PAYMENT_METHODS_ACTIVITY = "PaymentMethodsActivity";
    static final int REQUEST_CODE_ADD_CARD = 700;
    private boolean mCommunicating;
    /* access modifiers changed from: private */
    public Customer mCustomer;
    private CustomerSession mCustomerSession;
    private MaskedCardAdapter mMaskedCardAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private boolean mRecyclerViewUpdated;
    /* access modifiers changed from: private */
    public boolean mStartedFromPaymentSession;

    @Deprecated
    public static Intent newIntent(Activity activity) {
        return new PaymentMethodsActivityStarter(activity).newIntent();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_payment_methods);
        this.mProgressBar = (ProgressBar) findViewById(R.id.payment_methods_progress_bar);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.payment_methods_recycler);
        View findViewById = findViewById(R.id.payment_methods_add_payment_container);
        this.mCustomerSession = CustomerSession.getInstance();
        this.mStartedFromPaymentSession = getIntent().hasExtra(PaymentSession.EXTRA_PAYMENT_SESSION_ACTIVE);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent newIntent = AddSourceActivity.newIntent(PaymentMethodsActivity.this, false, true);
                if (PaymentMethodsActivity.this.mStartedFromPaymentSession) {
                    newIntent.putExtra(PaymentSession.EXTRA_PAYMENT_SESSION_ACTIVE, true);
                }
                PaymentMethodsActivity.this.startActivityForResult(newIntent, 700);
            }
        });
        setSupportActionBar((Toolbar) findViewById(R.id.payment_methods_toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!getIntent().getBooleanExtra(EXTRA_PROXY_DELAY, false)) {
            initializeCustomerSourceData();
        }
        findViewById.requestFocusFromTouch();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 700 && i2 == -1) {
            setCommunicatingProgress(true);
            initLoggingTokens();
            this.mCustomerSession.updateCurrentCustomer(new CustomerSession.CustomerRetrievalListener() {
                public void onCustomerRetrieved(Customer customer) {
                    PaymentMethodsActivity.this.updateCustomerAndSetDefaultSourceIfNecessary(customer);
                }

                public void onError(int i, String str, StripeError stripeError) {
                    PaymentMethodsActivity.this.showError(TranslatorManager.getErrorMessageTranslator().translate(i, str, stripeError));
                    PaymentMethodsActivity.this.setCommunicatingProgress(false);
                }
            });
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setIcon(ViewUtils.getTintedIconWithAttribute(this, getTheme(), R.attr.titleTextColor, R.drawable.ic_checkmark));
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_source_menu, menu);
        menu.findItem(R.id.action_save).setEnabled(!this.mCommunicating);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_save) {
            setSelectionAndFinish();
            return true;
        }
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        if (!onOptionsItemSelected) {
            onBackPressed();
        }
        return onOptionsItemSelected;
    }

    /* access modifiers changed from: package-private */
    public void initializeCustomerSourceData() {
        Customer cachedCustomer = this.mCustomerSession.getCachedCustomer();
        if (cachedCustomer != null) {
            this.mCustomer = cachedCustomer;
            createListFromCustomerSources();
            return;
        }
        getCustomerFromSession();
    }

    private void initLoggingTokens() {
        if (this.mStartedFromPaymentSession) {
            this.mCustomerSession.addProductUsageTokenIfValid(PaymentSession.TOKEN_PAYMENT_SESSION);
        }
        this.mCustomerSession.addProductUsageTokenIfValid(PAYMENT_METHODS_ACTIVITY);
    }

    /* access modifiers changed from: private */
    public void updateCustomerAndSetDefaultSourceIfNecessary(Customer customer) {
        if (!TextUtils.isEmpty(customer.getDefaultSource()) || customer.getSources().size() != 1) {
            updateAdapterWithCustomer(customer);
            return;
        }
        AnonymousClass3 r0 = new CustomerSession.CustomerRetrievalListener() {
            public void onCustomerRetrieved(Customer customer) {
                PaymentMethodsActivity.this.updateAdapterWithCustomer(customer);
            }

            public void onError(int i, String str, StripeError stripeError) {
                PaymentMethodsActivity.this.showError(TranslatorManager.getErrorMessageTranslator().translate(i, str, stripeError));
                PaymentMethodsActivity.this.setCommunicatingProgress(false);
            }
        };
        CustomerSource customerSource = customer.getSources().get(0);
        if (customerSource == null || customerSource.getId() == null) {
            updateAdapterWithCustomer(customer);
        } else {
            this.mCustomerSession.setCustomerDefaultSource(this, customerSource.getId(), customerSource.getSourceType(), r0);
        }
    }

    /* access modifiers changed from: private */
    public void createListFromCustomerSources() {
        setCommunicatingProgress(false);
        Customer customer = this.mCustomer;
        if (customer != null) {
            List<CustomerSource> sources = customer.getSources();
            if (!this.mRecyclerViewUpdated) {
                this.mMaskedCardAdapter = new MaskedCardAdapter(sources);
                this.mRecyclerView.setHasFixedSize(false);
                this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                this.mRecyclerView.setAdapter(this.mMaskedCardAdapter);
                this.mRecyclerViewUpdated = true;
            } else {
                this.mMaskedCardAdapter.setCustomerSourceList(sources);
            }
            String defaultSource = this.mCustomer.getDefaultSource();
            if (defaultSource != null && !TextUtils.isEmpty(defaultSource)) {
                this.mMaskedCardAdapter.setSelectedSource(defaultSource);
            }
            this.mMaskedCardAdapter.notifyDataSetChanged();
        }
    }

    private void cancelAndFinish() {
        setResult(0);
        finish();
    }

    /* access modifiers changed from: private */
    public void finishWithSelection(String str) {
        CustomerSource sourceById = this.mCustomer.getSourceById(str);
        if (sourceById != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SELECTED_PAYMENT, sourceById.toJson().toString());
            setResult(-1, intent);
        } else {
            setResult(0);
        }
        finish();
    }

    private void getCustomerFromSession() {
        AnonymousClass4 r0 = new CustomerSession.CustomerRetrievalListener() {
            public void onCustomerRetrieved(Customer customer) {
                Customer unused = PaymentMethodsActivity.this.mCustomer = customer;
                PaymentMethodsActivity.this.createListFromCustomerSources();
            }

            public void onError(int i, String str, StripeError stripeError) {
                PaymentMethodsActivity.this.setCommunicatingProgress(false);
            }
        };
        setCommunicatingProgress(true);
        this.mCustomerSession.retrieveCurrentCustomer(r0);
    }

    /* access modifiers changed from: private */
    public void setCommunicatingProgress(boolean z) {
        this.mCommunicating = z;
        if (z) {
            this.mProgressBar.setVisibility(0);
        } else {
            this.mProgressBar.setVisibility(8);
        }
        supportInvalidateOptionsMenu();
    }

    private void setSelectionAndFinish() {
        MaskedCardAdapter maskedCardAdapter = this.mMaskedCardAdapter;
        if (maskedCardAdapter == null || maskedCardAdapter.getSelectedSource() == null) {
            cancelAndFinish();
            return;
        }
        CustomerSource selectedSource = this.mMaskedCardAdapter.getSelectedSource();
        AnonymousClass5 r1 = new CustomerSession.CustomerRetrievalListener() {
            public void onCustomerRetrieved(Customer customer) {
                Customer unused = PaymentMethodsActivity.this.mCustomer = customer;
                PaymentMethodsActivity.this.finishWithSelection(customer.getDefaultSource());
                PaymentMethodsActivity.this.setCommunicatingProgress(false);
            }

            public void onError(int i, String str, StripeError stripeError) {
                PaymentMethodsActivity.this.showError(TranslatorManager.getErrorMessageTranslator().translate(i, str, stripeError));
                PaymentMethodsActivity.this.setCommunicatingProgress(false);
            }
        };
        if (selectedSource != null && selectedSource.getId() != null) {
            this.mCustomerSession.setCustomerDefaultSource(this, selectedSource.getId(), selectedSource.getSourceType(), r1);
            setCommunicatingProgress(true);
        }
    }

    /* access modifiers changed from: private */
    public void showError(String str) {
        new AlertDialog.Builder(this).setMessage((CharSequence) str).setCancelable(true).setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    /* access modifiers changed from: private */
    public void updateAdapterWithCustomer(Customer customer) {
        if (this.mMaskedCardAdapter == null) {
            createListFromCustomerSources();
            if (this.mCustomer == null) {
                return;
            }
        }
        this.mMaskedCardAdapter.updateCustomer(customer);
        setCommunicatingProgress(false);
    }
}
