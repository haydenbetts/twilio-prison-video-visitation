package com.stripe.android.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.stripe.android.R;
import com.stripe.android.model.ShippingMethod;

class ShippingMethodView extends RelativeLayout {
    private TextView mAmount;
    private ImageView mCheckmark;
    private TextView mDetail;
    private TextView mLabel;
    int mSelectedColorInt;
    int mUnselectedTextColorPrimaryInt;
    int mUnselectedTextColorSecondaryInt;

    ShippingMethodView(Context context) {
        super(context);
        initView();
    }

    ShippingMethodView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    ShippingMethodView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    public void setSelected(boolean z) {
        if (z) {
            this.mLabel.setTextColor(this.mSelectedColorInt);
            this.mDetail.setTextColor(this.mSelectedColorInt);
            this.mAmount.setTextColor(this.mSelectedColorInt);
            this.mCheckmark.setVisibility(0);
            return;
        }
        this.mLabel.setTextColor(this.mUnselectedTextColorPrimaryInt);
        this.mDetail.setTextColor(this.mUnselectedTextColorSecondaryInt);
        this.mAmount.setTextColor(this.mUnselectedTextColorPrimaryInt);
        this.mCheckmark.setVisibility(4);
    }

    private void initView() {
        inflate(getContext(), R.layout.shipping_method_view, this);
        this.mLabel = (TextView) findViewById(R.id.tv_label_smv);
        this.mDetail = (TextView) findViewById(R.id.tv_detail_smv);
        this.mAmount = (TextView) findViewById(R.id.tv_amount_smv);
        this.mCheckmark = (ImageView) findViewById(R.id.iv_selected_icon);
        this.mSelectedColorInt = ViewUtils.getThemeAccentColor(getContext()).data;
        this.mUnselectedTextColorPrimaryInt = ViewUtils.getThemeTextColorPrimary(getContext()).data;
        this.mUnselectedTextColorSecondaryInt = ViewUtils.getThemeTextColorSecondary(getContext()).data;
        useDefaultColorsIfThemeColorsAreInvisible();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.addRule(16);
        }
        layoutParams.height = ViewUtils.getPxFromDp(getContext(), getResources().getDimensionPixelSize(R.dimen.shipping_method_view_height));
        setLayoutParams(layoutParams);
    }

    private void useDefaultColorsIfThemeColorsAreInvisible() {
        this.mSelectedColorInt = ViewUtils.isColorTransparent(this.mSelectedColorInt) ? ContextCompat.getColor(getContext(), R.color.accent_color_default) : this.mSelectedColorInt;
        this.mUnselectedTextColorPrimaryInt = ViewUtils.isColorTransparent(this.mUnselectedTextColorPrimaryInt) ? ContextCompat.getColor(getContext(), R.color.color_text_unselected_primary_default) : this.mUnselectedTextColorPrimaryInt;
        this.mUnselectedTextColorSecondaryInt = ViewUtils.isColorTransparent(this.mUnselectedTextColorSecondaryInt) ? ContextCompat.getColor(getContext(), R.color.color_text_unselected_secondary_default) : this.mUnselectedTextColorSecondaryInt;
    }

    /* access modifiers changed from: package-private */
    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.mLabel.setText(shippingMethod.getLabel());
        this.mDetail.setText(shippingMethod.getDetail());
        this.mAmount.setText(PaymentUtils.formatPriceStringUsingFree(shippingMethod.getAmount(), shippingMethod.getCurrency(), getContext().getString(R.string.price_free)));
    }
}
