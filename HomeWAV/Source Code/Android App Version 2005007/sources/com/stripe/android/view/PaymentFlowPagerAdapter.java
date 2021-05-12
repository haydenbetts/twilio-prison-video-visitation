package com.stripe.android.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentSessionConfig;
import com.stripe.android.R;
import com.stripe.android.model.ShippingMethod;
import java.util.ArrayList;
import java.util.List;

class PaymentFlowPagerAdapter extends PagerAdapter {
    private static final String TOKEN_SHIPPING_INFO_SCREEN = "ShippingInfoScreen";
    private static final String TOKEN_SHIPPING_METHOD_SCREEN = "ShippingMethodScreen";
    private Context mContext;
    private ShippingMethod mDefaultShippingMethod;
    private List<PaymentFlowPagerEnum> mPages;
    private PaymentSessionConfig mPaymentSessionConfig;
    private boolean mShippingInfoSaved;
    private List<ShippingMethod> mValidShippingMethods = new ArrayList();

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    PaymentFlowPagerAdapter(Context context, PaymentSessionConfig paymentSessionConfig) {
        this.mContext = context;
        this.mPaymentSessionConfig = paymentSessionConfig;
        this.mPages = new ArrayList();
        if (this.mPaymentSessionConfig.isShippingInfoRequired()) {
            this.mPages.add(PaymentFlowPagerEnum.SHIPPING_INFO);
        }
        if (shouldAddShippingScreen()) {
            this.mPages.add(PaymentFlowPagerEnum.SHIPPING_METHOD);
        }
    }

    private boolean shouldAddShippingScreen() {
        return this.mPaymentSessionConfig.isShippingMethodRequired() && (!this.mPaymentSessionConfig.isShippingInfoRequired() || this.mShippingInfoSaved) && !this.mPages.contains(PaymentFlowPagerEnum.SHIPPING_METHOD);
    }

    /* access modifiers changed from: package-private */
    public void setShippingInfoSaved(boolean z) {
        this.mShippingInfoSaved = z;
        if (shouldAddShippingScreen()) {
            this.mPages.add(PaymentFlowPagerEnum.SHIPPING_METHOD);
        }
        notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    public void setShippingMethods(List<ShippingMethod> list, ShippingMethod shippingMethod) {
        this.mValidShippingMethods = list;
        this.mDefaultShippingMethod = shippingMethod;
    }

    /* access modifiers changed from: package-private */
    public void hideShippingPage() {
        this.mPages.remove(PaymentFlowPagerEnum.SHIPPING_METHOD);
        notifyDataSetChanged();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        PaymentFlowPagerEnum paymentFlowPagerEnum = this.mPages.get(i);
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this.mContext).inflate(paymentFlowPagerEnum.getLayoutResId(), viewGroup, false);
        if (paymentFlowPagerEnum.equals(PaymentFlowPagerEnum.SHIPPING_METHOD)) {
            CustomerSession.getInstance().addProductUsageTokenIfValid(TOKEN_SHIPPING_METHOD_SCREEN);
            ((SelectShippingMethodWidget) viewGroup2.findViewById(R.id.select_shipping_method_widget)).setShippingMethods(this.mValidShippingMethods, this.mDefaultShippingMethod);
        }
        if (paymentFlowPagerEnum.equals(PaymentFlowPagerEnum.SHIPPING_INFO)) {
            CustomerSession.getInstance().addProductUsageTokenIfValid(TOKEN_SHIPPING_INFO_SCREEN);
            ShippingInfoWidget shippingInfoWidget = (ShippingInfoWidget) viewGroup2.findViewById(R.id.shipping_info_widget);
            shippingInfoWidget.setHiddenFields(this.mPaymentSessionConfig.getHiddenShippingInfoFields());
            shippingInfoWidget.setOptionalFields(this.mPaymentSessionConfig.getOptionalShippingInfoFields());
            shippingInfoWidget.populateShippingInfo(this.mPaymentSessionConfig.getPrepopulatedShippingInfo());
        }
        viewGroup.addView(viewGroup2);
        return viewGroup2;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return this.mPages.size();
    }

    /* access modifiers changed from: package-private */
    public PaymentFlowPagerEnum getPageAt(int i) {
        if (i < this.mPages.size()) {
            return this.mPages.get(i);
        }
        return null;
    }

    public CharSequence getPageTitle(int i) {
        return this.mContext.getString(this.mPages.get(i).getTitleResId());
    }
}
