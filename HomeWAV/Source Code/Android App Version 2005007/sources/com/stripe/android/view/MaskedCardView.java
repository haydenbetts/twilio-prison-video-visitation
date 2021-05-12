package com.stripe.android.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.stripe.android.R;
import com.stripe.android.model.Card;
import com.stripe.android.model.CustomerSource;
import com.stripe.android.model.Source;
import com.stripe.android.model.SourceCardData;
import java.util.HashMap;
import java.util.Map;

public class MaskedCardView extends LinearLayout {
    static final Map<String, Integer> TEMPLATE_RESOURCE_MAP;
    private String mCardBrand;
    private AppCompatImageView mCardIconImageView;
    private AppCompatTextView mCardInformationTextView;
    private AppCompatImageView mCheckMarkImageView;
    private boolean mIsSelected;
    private String mLast4;
    int mSelectedAlphaColorInt;
    int mSelectedColorInt;
    int mUnselectedColorInt;
    int mUnselectedTextAlphaColorInt;
    int mUnselectedTextColorInt;

    static {
        HashMap hashMap = new HashMap();
        TEMPLATE_RESOURCE_MAP = hashMap;
        hashMap.put(Card.AMERICAN_EXPRESS, Integer.valueOf(R.drawable.ic_amex_template_32));
        hashMap.put(Card.DINERS_CLUB, Integer.valueOf(R.drawable.ic_diners_template_32));
        hashMap.put(Card.DISCOVER, Integer.valueOf(R.drawable.ic_discover_template_32));
        hashMap.put(Card.JCB, Integer.valueOf(R.drawable.ic_jcb_template_32));
        hashMap.put(Card.MASTERCARD, Integer.valueOf(R.drawable.ic_mastercard_template_32));
        hashMap.put(Card.VISA, Integer.valueOf(R.drawable.ic_visa_template_32));
        hashMap.put(Card.UNIONPAY, Integer.valueOf(R.drawable.ic_unionpay_template_32));
        hashMap.put("Unknown", Integer.valueOf(R.drawable.ic_unknown));
    }

    public MaskedCardView(Context context) {
        super(context);
        init();
    }

    public MaskedCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MaskedCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public boolean isSelected() {
        return this.mIsSelected;
    }

    public void setSelected(boolean z) {
        this.mIsSelected = z;
        updateCheckMark();
        updateBrandIcon();
        updateCardInformation();
    }

    /* access modifiers changed from: package-private */
    public void setCard(Card card) {
        this.mCardBrand = card.getBrand();
        this.mLast4 = card.getLast4();
        updateBrandIcon();
        updateCardInformation();
    }

    /* access modifiers changed from: package-private */
    public void setSourceCardData(SourceCardData sourceCardData) {
        this.mCardBrand = sourceCardData.getBrand();
        this.mLast4 = sourceCardData.getLast4();
        updateBrandIcon();
        updateCardInformation();
    }

    /* access modifiers changed from: package-private */
    public void setCustomerSource(CustomerSource customerSource) {
        Source asSource = customerSource.asSource();
        if (asSource == null || asSource.getSourceTypeModel() == null || !"card".equals(asSource.getType()) || !(asSource.getSourceTypeModel() instanceof SourceCardData)) {
            Card asCard = customerSource.asCard();
            if (asCard != null) {
                setCard(asCard);
                return;
            }
            return;
        }
        setSourceCardData((SourceCardData) asSource.getSourceTypeModel());
    }

    /* access modifiers changed from: package-private */
    public void toggleSelected() {
        setSelected(!this.mIsSelected);
    }

    /* access modifiers changed from: package-private */
    public int[] getTextColorValues() {
        return new int[]{this.mSelectedColorInt, this.mSelectedAlphaColorInt, this.mUnselectedTextColorInt, this.mUnselectedTextAlphaColorInt};
    }

    /* access modifiers changed from: package-private */
    public String getCardBrand() {
        return this.mCardBrand;
    }

    /* access modifiers changed from: package-private */
    public String getLast4() {
        return this.mLast4;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        inflate(getContext(), R.layout.masked_card_view, this);
        setOrientation(0);
        setMinimumWidth(getResources().getDimensionPixelSize(R.dimen.card_widget_min_width));
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.masked_card_vertical_padding);
        setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
        this.mCardIconImageView = (AppCompatImageView) findViewById(R.id.masked_icon_view);
        this.mCardInformationTextView = (AppCompatTextView) findViewById(R.id.masked_card_info_view);
        this.mCheckMarkImageView = (AppCompatImageView) findViewById(R.id.masked_check_icon);
        this.mSelectedColorInt = ViewUtils.getThemeAccentColor(getContext()).data;
        this.mUnselectedColorInt = ViewUtils.getThemeColorControlNormal(getContext()).data;
        this.mUnselectedTextColorInt = ViewUtils.getThemeTextColorSecondary(getContext()).data;
        useDefaultColorsIfThemeColorsAreInvisible();
        setLightTextColorValues();
        initializeCheckMark();
        updateCheckMark();
    }

    private void initializeCheckMark() {
        updateDrawable(R.drawable.ic_checkmark, this.mCheckMarkImageView, true);
    }

    private void updateBrandIcon() {
        String str = this.mCardBrand;
        if (str != null) {
            Map<String, Integer> map = TEMPLATE_RESOURCE_MAP;
            if (map.containsKey(str)) {
                updateDrawable(map.get(this.mCardBrand).intValue(), this.mCardIconImageView, false);
            }
        }
    }

    private void updateDrawable(int i, ImageView imageView, boolean z) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), i);
        int i2 = (this.mIsSelected || z) ? this.mSelectedColorInt : this.mUnselectedColorInt;
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap.mutate(), i2);
        imageView.setImageDrawable(wrap);
    }

    private void updateCardInformation() {
        String string = Card.AMERICAN_EXPRESS.equals(this.mCardBrand) ? getResources().getString(R.string.amex_short) : this.mCardBrand;
        String string2 = getResources().getString(R.string.ending_in);
        int length = string.length();
        int length2 = string2.length();
        int length3 = this.mLast4.length();
        boolean z = this.mIsSelected;
        int i = z ? this.mSelectedColorInt : this.mUnselectedTextColorInt;
        int i2 = z ? this.mSelectedAlphaColorInt : this.mUnselectedTextAlphaColorInt;
        SpannableString spannableString = new SpannableString(string + string2 + this.mLast4);
        spannableString.setSpan(new TypefaceSpan("sans-serif-medium"), 0, length, 33);
        spannableString.setSpan(new ForegroundColorSpan(i), 0, length, 33);
        int i3 = length2 + length;
        spannableString.setSpan(new ForegroundColorSpan(i2), length, i3, 33);
        int i4 = length3 + i3;
        spannableString.setSpan(new TypefaceSpan("sans-serif-medium"), i3, i4, 33);
        spannableString.setSpan(new ForegroundColorSpan(i), i3, i4, 33);
        this.mCardInformationTextView.setText(spannableString);
    }

    private void updateCheckMark() {
        if (this.mIsSelected) {
            this.mCheckMarkImageView.setVisibility(0);
        } else {
            this.mCheckMarkImageView.setVisibility(4);
        }
    }

    private void useDefaultColorsIfThemeColorsAreInvisible() {
        this.mSelectedColorInt = ViewUtils.isColorTransparent(this.mSelectedColorInt) ? ContextCompat.getColor(getContext(), R.color.accent_color_default) : this.mSelectedColorInt;
        this.mUnselectedColorInt = ViewUtils.isColorTransparent(this.mUnselectedColorInt) ? ContextCompat.getColor(getContext(), R.color.control_normal_color_default) : this.mUnselectedColorInt;
        this.mUnselectedTextColorInt = ViewUtils.isColorTransparent(this.mUnselectedTextColorInt) ? ContextCompat.getColor(getContext(), R.color.color_text_secondary_default) : this.mUnselectedTextColorInt;
    }

    private void setLightTextColorValues() {
        this.mSelectedAlphaColorInt = ColorUtils.setAlphaComponent(this.mSelectedColorInt, getResources().getInteger(R.integer.light_text_alpha_hex));
        this.mUnselectedTextAlphaColorInt = ColorUtils.setAlphaComponent(this.mUnselectedTextColorInt, getResources().getInteger(R.integer.light_text_alpha_hex));
    }
}
