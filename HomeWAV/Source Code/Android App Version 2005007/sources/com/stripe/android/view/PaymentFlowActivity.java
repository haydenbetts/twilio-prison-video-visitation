package com.stripe.android.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentSession;
import com.stripe.android.PaymentSessionConfig;
import com.stripe.android.PaymentSessionData;
import com.stripe.android.R;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.model.ShippingMethod;
import java.util.List;

public class PaymentFlowActivity extends StripeActivity {
    static final String TOKEN_PAYMENT_FLOW_ACTIVITY = "PaymentFlowActivity";
    /* access modifiers changed from: private */
    public ShippingMethod mDefaultShippingMethod;
    /* access modifiers changed from: private */
    public PaymentFlowPagerAdapter mPaymentFlowPagerAdapter;
    /* access modifiers changed from: private */
    public PaymentSessionData mPaymentSessionData;
    private BroadcastReceiver mShippingInfoSavedBroadcastReceiver;
    private BroadcastReceiver mShippingInfoSubmittedBroadcastReceiver;
    /* access modifiers changed from: private */
    public ShippingInformation mShippingInformationSubmitted;
    /* access modifiers changed from: private */
    public List<ShippingMethod> mValidShippingMethods;
    /* access modifiers changed from: private */
    public ViewPager mViewPager;

    public /* bridge */ /* synthetic */ boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public /* bridge */ /* synthetic */ boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public /* bridge */ /* synthetic */ boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CustomerSession.getInstance().addProductUsageTokenIfValid(PaymentSession.TOKEN_PAYMENT_SESSION);
        CustomerSession.getInstance().addProductUsageTokenIfValid(TOKEN_PAYMENT_FLOW_ACTIVITY);
        this.mViewStub.setLayoutResource(R.layout.activity_shipping_flow);
        this.mViewStub.inflate();
        this.mViewPager = (ViewPager) findViewById(R.id.shipping_flow_viewpager);
        PaymentSessionConfig paymentSessionConfig = (PaymentSessionConfig) getIntent().getParcelableExtra(PaymentSession.PAYMENT_SESSION_CONFIG);
        PaymentSessionData paymentSessionData = (PaymentSessionData) getIntent().getParcelableExtra(PaymentSession.PAYMENT_SESSION_DATA_KEY);
        this.mPaymentSessionData = paymentSessionData;
        if (paymentSessionData != null) {
            PaymentFlowPagerAdapter paymentFlowPagerAdapter = new PaymentFlowPagerAdapter(this, paymentSessionConfig);
            this.mPaymentFlowPagerAdapter = paymentFlowPagerAdapter;
            this.mViewPager.setAdapter(paymentFlowPagerAdapter);
            this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    PaymentFlowActivity paymentFlowActivity = PaymentFlowActivity.this;
                    paymentFlowActivity.setTitle(paymentFlowActivity.mViewPager.getAdapter().getPageTitle(i));
                    if (PaymentFlowActivity.this.mPaymentFlowPagerAdapter.getPageAt(i) == PaymentFlowPagerEnum.SHIPPING_INFO) {
                        PaymentFlowActivity.this.mPaymentFlowPagerAdapter.hideShippingPage();
                    }
                }
            });
            this.mShippingInfoSubmittedBroadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent.getBooleanExtra(PaymentFlowExtras.EXTRA_IS_SHIPPING_INFO_VALID, false)) {
                        PaymentFlowActivity.this.onShippingInfoValidated();
                        List unused = PaymentFlowActivity.this.mValidShippingMethods = intent.getParcelableArrayListExtra(PaymentFlowExtras.EXTRA_VALID_SHIPPING_METHODS);
                        ShippingMethod unused2 = PaymentFlowActivity.this.mDefaultShippingMethod = (ShippingMethod) intent.getParcelableExtra(PaymentFlowExtras.EXTRA_DEFAULT_SHIPPING_METHOD);
                        return;
                    }
                    PaymentFlowActivity.this.setCommunicatingProgress(false);
                    String stringExtra = intent.getStringExtra(PaymentFlowExtras.EXTRA_SHIPPING_INFO_ERROR);
                    if (stringExtra == null || stringExtra.isEmpty()) {
                        PaymentFlowActivity paymentFlowActivity = PaymentFlowActivity.this;
                        paymentFlowActivity.showError(paymentFlowActivity.getString(R.string.invalid_shipping_information));
                    } else {
                        PaymentFlowActivity.this.showError(stringExtra);
                    }
                    ShippingInformation unused3 = PaymentFlowActivity.this.mShippingInformationSubmitted = null;
                }
            };
            this.mShippingInfoSavedBroadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    PaymentFlowActivity paymentFlowActivity = PaymentFlowActivity.this;
                    paymentFlowActivity.onShippingMethodsReady(paymentFlowActivity.mValidShippingMethods, PaymentFlowActivity.this.mDefaultShippingMethod);
                    PaymentFlowActivity.this.mPaymentSessionData.setShippingInformation(PaymentFlowActivity.this.mShippingInformationSubmitted);
                }
            };
            setTitle(this.mPaymentFlowPagerAdapter.getPageTitle(this.mViewPager.getCurrentItem()));
            return;
        }
        throw new IllegalArgumentException("PaymentFlowActivity launched without PaymentSessionData");
    }

    /* access modifiers changed from: protected */
    public void onActionSave() {
        if (PaymentFlowPagerEnum.SHIPPING_INFO.equals(this.mPaymentFlowPagerAdapter.getPageAt(this.mViewPager.getCurrentItem()))) {
            onShippingInfoSubmitted();
        } else {
            onShippingMethodSave();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mShippingInfoSubmittedBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mShippingInfoSavedBroadcastReceiver);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mShippingInfoSubmittedBroadcastReceiver, new IntentFilter(PaymentFlowExtras.EVENT_SHIPPING_INFO_PROCESSED));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mShippingInfoSavedBroadcastReceiver, new IntentFilter(CustomerSession.EVENT_SHIPPING_INFO_SAVED));
    }

    /* access modifiers changed from: private */
    public void onShippingInfoValidated() {
        CustomerSession.getInstance().setCustomerShippingInformation(this, this.mShippingInformationSubmitted);
    }

    /* access modifiers changed from: private */
    public void onShippingMethodsReady(List<ShippingMethod> list, ShippingMethod shippingMethod) {
        setCommunicatingProgress(false);
        this.mPaymentFlowPagerAdapter.setShippingMethods(list, shippingMethod);
        this.mPaymentFlowPagerAdapter.setShippingInfoSaved(true);
        if (hasNextPage()) {
            ViewPager viewPager = this.mViewPager;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            return;
        }
        this.mPaymentSessionData.setShippingInformation(this.mShippingInformationSubmitted);
        Intent intent = new Intent();
        intent.putExtra(PaymentSession.PAYMENT_SESSION_DATA_KEY, this.mPaymentSessionData);
        setResult(-1, intent);
        finish();
    }

    private void onShippingInfoSubmitted() {
        ShippingInformation shippingInformation = ((ShippingInfoWidget) findViewById(R.id.shipping_info_widget)).getShippingInformation();
        if (shippingInformation != null) {
            this.mShippingInformationSubmitted = shippingInformation;
            setCommunicatingProgress(true);
            broadcastShippingInfoSubmitted(shippingInformation);
        }
    }

    private void broadcastShippingInfoSubmitted(ShippingInformation shippingInformation) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(PaymentFlowExtras.EVENT_SHIPPING_INFO_SUBMITTED).putExtra(PaymentFlowExtras.EXTRA_SHIPPING_INFO_DATA, shippingInformation));
    }

    private boolean hasNextPage() {
        return this.mViewPager.getCurrentItem() + 1 < this.mPaymentFlowPagerAdapter.getCount();
    }

    private boolean hasPreviousPage() {
        return this.mViewPager.getCurrentItem() != 0;
    }

    private void onShippingMethodSave() {
        this.mPaymentSessionData.setShippingMethod(((SelectShippingMethodWidget) findViewById(R.id.select_shipping_method_widget)).getSelectedShippingMethod());
        Intent intent = new Intent();
        intent.putExtra(PaymentSession.PAYMENT_SESSION_DATA_KEY, this.mPaymentSessionData);
        setResult(-1, intent);
        finish();
    }

    public void onBackPressed() {
        if (hasPreviousPage()) {
            ViewPager viewPager = this.mViewPager;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            return;
        }
        super.onBackPressed();
    }
}
