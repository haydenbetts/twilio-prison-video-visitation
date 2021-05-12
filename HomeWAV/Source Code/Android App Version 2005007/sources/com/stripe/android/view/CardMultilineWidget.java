package com.stripe.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.textfield.TextInputLayout;
import com.stripe.android.CardUtils;
import com.stripe.android.R;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputListener;
import com.stripe.android.view.CardNumberEditText;
import com.stripe.android.view.ExpiryDateEditText;
import com.stripe.android.view.StripeEditText;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CardMultilineWidget extends LinearLayout {
    static final String CARD_MULTILINE_TOKEN = "CardMultilineView";
    static final long CARD_NUMBER_HINT_DELAY = 120;
    static final long COMMON_HINT_DELAY = 90;
    /* access modifiers changed from: private */
    public String mCardBrand;
    /* access modifiers changed from: private */
    public CardInputListener mCardInputListener;
    /* access modifiers changed from: private */
    public CardNumberEditText mCardNumberEditText;
    private TextInputLayout mCardNumberTextInputLayout;
    private String mCustomCvcLabel;
    /* access modifiers changed from: private */
    public StripeEditText mCvcEditText;
    private TextInputLayout mCvcTextInputLayout;
    /* access modifiers changed from: private */
    public ExpiryDateEditText mExpiryDateEditText;
    private TextInputLayout mExpiryTextInputLayout;
    private boolean mHasAdjustedDrawable;
    private boolean mIsEnabled;
    /* access modifiers changed from: private */
    public StripeEditText mPostalCodeEditText;
    private TextInputLayout mPostalInputLayout;
    /* access modifiers changed from: private */
    public boolean mShouldShowPostalCode;
    private int mTintColorInt;

    public CardMultilineWidget(Context context) {
        this(context, (AttributeSet) null);
    }

    public CardMultilineWidget(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardMultilineWidget(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, false);
    }

    CardMultilineWidget(Context context, boolean z) {
        this(context, (AttributeSet) null, 0, z);
    }

    private CardMultilineWidget(Context context, AttributeSet attributeSet, int i, boolean z) {
        super(context, attributeSet, i);
        initView(attributeSet, z);
    }

    public void clear() {
        this.mCardNumberEditText.setText("");
        this.mExpiryDateEditText.setText("");
        this.mCvcEditText.setText("");
        this.mPostalCodeEditText.setText("");
        this.mCardNumberEditText.setShouldShowError(false);
        this.mExpiryDateEditText.setShouldShowError(false);
        this.mCvcEditText.setShouldShowError(false);
        this.mPostalCodeEditText.setShouldShowError(false);
        updateBrand("Unknown");
    }

    public void setCardInputListener(CardInputListener cardInputListener) {
        this.mCardInputListener = cardInputListener;
    }

    public Card getCard() {
        if (!validateAllFields()) {
            return null;
        }
        String cardNumber = this.mCardNumberEditText.getCardNumber();
        int[] validDateFields = this.mExpiryDateEditText.getValidDateFields();
        Card card = new Card(cardNumber, Integer.valueOf(validDateFields[0]), Integer.valueOf(validDateFields[1]), this.mCvcEditText.getText().toString());
        if (this.mShouldShowPostalCode) {
            card.setAddressZip(this.mPostalCodeEditText.getText().toString());
        }
        return card.addLoggingToken(CARD_MULTILINE_TOKEN);
    }

    public boolean validateAllFields() {
        boolean z;
        boolean isValidCardNumber = CardUtils.isValidCardNumber(this.mCardNumberEditText.getCardNumber());
        boolean z2 = this.mExpiryDateEditText.getValidDateFields() != null && this.mExpiryDateEditText.isDateValid();
        boolean isCvcLengthValid = isCvcLengthValid();
        this.mCardNumberEditText.setShouldShowError(!isValidCardNumber);
        this.mExpiryDateEditText.setShouldShowError(!z2);
        this.mCvcEditText.setShouldShowError(!isCvcLengthValid);
        if (this.mShouldShowPostalCode) {
            z = isPostalCodeMaximalLength(true, this.mPostalCodeEditText.getText().toString());
            this.mPostalCodeEditText.setShouldShowError(!z);
        } else {
            z = true;
        }
        if (!isValidCardNumber || !z2 || !isCvcLengthValid || !z) {
            return false;
        }
        return true;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            updateBrand(this.mCardBrand);
        }
    }

    public void setCvcLabel(String str) {
        this.mCustomCvcLabel = str;
        updateCvc();
    }

    public void setShouldShowPostalCode(boolean z) {
        this.mShouldShowPostalCode = z;
        adjustViewForPostalCodeAttribute();
    }

    public void setCardNumber(String str) {
        this.mCardNumberEditText.setText(str);
    }

    public boolean validateCardNumber() {
        boolean isValidCardNumber = CardUtils.isValidCardNumber(this.mCardNumberEditText.getCardNumber());
        this.mCardNumberEditText.setShouldShowError(!isValidCardNumber);
        return isValidCardNumber;
    }

    public void setCardNumberTextWatcher(TextWatcher textWatcher) {
        this.mCardNumberEditText.addTextChangedListener(textWatcher);
    }

    public void setExpiryDateTextWatcher(TextWatcher textWatcher) {
        this.mExpiryDateEditText.addTextChangedListener(textWatcher);
    }

    public void setCvcNumberTextWatcher(TextWatcher textWatcher) {
        this.mCvcEditText.addTextChangedListener(textWatcher);
    }

    public void setPostalCodeTextWatcher(TextWatcher textWatcher) {
        this.mPostalCodeEditText.addTextChangedListener(textWatcher);
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void setEnabled(boolean z) {
        this.mExpiryTextInputLayout.setEnabled(z);
        this.mCardNumberTextInputLayout.setEnabled(z);
        this.mCvcTextInputLayout.setEnabled(z);
        this.mPostalInputLayout.setEnabled(z);
        this.mIsEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public void adjustViewForPostalCodeAttribute() {
        this.mExpiryTextInputLayout.setHint(getResources().getString(this.mShouldShowPostalCode ? R.string.expiry_label_short : R.string.acc_label_expiry_date));
        int i = this.mShouldShowPostalCode ? R.id.et_add_source_postal_ml : -1;
        this.mCvcEditText.setNextFocusForwardId(i);
        this.mCvcEditText.setNextFocusDownId(i);
        int i2 = this.mShouldShowPostalCode ? 0 : 8;
        this.mPostalInputLayout.setVisibility(i2);
        this.mCvcEditText.setImeOptions(i2 == 8 ? 6 : 5);
        int dimensionPixelSize = this.mShouldShowPostalCode ? getResources().getDimensionPixelSize(R.dimen.add_card_expiry_middle_margin) : 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mCvcTextInputLayout.getLayoutParams();
        layoutParams.setMargins(0, 0, dimensionPixelSize, 0);
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.setMarginEnd(dimensionPixelSize);
        }
        this.mCvcTextInputLayout.setLayoutParams(layoutParams);
    }

    static boolean isPostalCodeMaximalLength(boolean z, String str) {
        return z && str != null && str.length() == 5;
    }

    private boolean isCvcLengthValid() {
        int length = this.mCvcEditText.getText().toString().trim().length();
        return (TextUtils.equals(Card.AMERICAN_EXPRESS, this.mCardBrand) && length == 4) || length == 3;
    }

    private void checkAttributeSet(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.CardMultilineWidget, 0, 0);
            try {
                this.mShouldShowPostalCode = obtainStyledAttributes.getBoolean(R.styleable.CardMultilineWidget_shouldShowPostalCode, false);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    /* access modifiers changed from: private */
    public void flipToCvcIconIfNotFinished() {
        if (!ViewUtils.isCvcMaximalLength(this.mCardBrand, this.mCvcEditText.getText().toString())) {
            updateDrawable(Card.AMERICAN_EXPRESS.equals(this.mCardBrand) ? R.drawable.ic_cvc_amex : R.drawable.ic_cvc, true);
        }
    }

    /* access modifiers changed from: private */
    public int getCvcHelperText() {
        return Card.AMERICAN_EXPRESS.equals(this.mCardBrand) ? R.string.cvc_multiline_helper_amex : R.string.cvc_multiline_helper;
    }

    private int getDynamicBufferInPixels() {
        return new BigDecimal((double) getResources().getDimension(R.dimen.card_icon_multiline_padding_bottom)).setScale(0, RoundingMode.HALF_DOWN).intValue();
    }

    private void initView(AttributeSet attributeSet, boolean z) {
        this.mShouldShowPostalCode = z;
        setOrientation(1);
        inflate(getContext(), R.layout.card_multiline_widget, this);
        this.mCardNumberEditText = (CardNumberEditText) findViewById(R.id.et_add_source_card_number_ml);
        this.mExpiryDateEditText = (ExpiryDateEditText) findViewById(R.id.et_add_source_expiry_ml);
        this.mCvcEditText = (StripeEditText) findViewById(R.id.et_add_source_cvc_ml);
        this.mPostalCodeEditText = (StripeEditText) findViewById(R.id.et_add_source_postal_ml);
        this.mTintColorInt = this.mCardNumberEditText.getHintTextColors().getDefaultColor();
        this.mCardBrand = "Unknown";
        checkAttributeSet(attributeSet);
        this.mCardNumberTextInputLayout = (TextInputLayout) findViewById(R.id.tl_add_source_card_number_ml);
        this.mExpiryTextInputLayout = (TextInputLayout) findViewById(R.id.tl_add_source_expiry_ml);
        this.mCvcTextInputLayout = (TextInputLayout) findViewById(R.id.tl_add_source_cvc_ml);
        this.mPostalInputLayout = (TextInputLayout) findViewById(R.id.tl_add_source_postal_ml);
        if (this.mShouldShowPostalCode) {
            this.mExpiryTextInputLayout.setHint(getResources().getString(R.string.expiry_label_short));
        }
        initTextInputLayoutErrorHandlers(this.mCardNumberTextInputLayout, this.mExpiryTextInputLayout, this.mCvcTextInputLayout, this.mPostalInputLayout);
        initErrorMessages();
        initFocusChangeListeners();
        initDeleteEmptyListeners();
        this.mCardNumberEditText.setCardBrandChangeListener(new CardNumberEditText.CardBrandChangeListener() {
            public void onCardBrandChanged(String str) {
                CardMultilineWidget.this.updateBrand(str);
            }
        });
        this.mCardNumberEditText.setCardNumberCompleteListener(new CardNumberEditText.CardNumberCompleteListener() {
            public void onCardNumberComplete() {
                CardMultilineWidget.this.mExpiryDateEditText.requestFocus();
                if (CardMultilineWidget.this.mCardInputListener != null) {
                    CardMultilineWidget.this.mCardInputListener.onCardComplete();
                }
            }
        });
        this.mExpiryDateEditText.setExpiryDateEditListener(new ExpiryDateEditText.ExpiryDateEditListener() {
            public void onExpiryDateComplete() {
                CardMultilineWidget.this.mCvcEditText.requestFocus();
                if (CardMultilineWidget.this.mCardInputListener != null) {
                    CardMultilineWidget.this.mCardInputListener.onExpirationComplete();
                }
            }
        });
        this.mCvcEditText.setAfterTextChangedListener(new StripeEditText.AfterTextChangedListener() {
            public void onTextChanged(String str) {
                if (ViewUtils.isCvcMaximalLength(CardMultilineWidget.this.mCardBrand, str)) {
                    CardMultilineWidget cardMultilineWidget = CardMultilineWidget.this;
                    cardMultilineWidget.updateBrand(cardMultilineWidget.mCardBrand);
                    if (CardMultilineWidget.this.mShouldShowPostalCode) {
                        CardMultilineWidget.this.mPostalCodeEditText.requestFocus();
                    }
                    if (CardMultilineWidget.this.mCardInputListener != null) {
                        CardMultilineWidget.this.mCardInputListener.onCvcComplete();
                    }
                } else {
                    CardMultilineWidget.this.flipToCvcIconIfNotFinished();
                }
                CardMultilineWidget.this.mCvcEditText.setShouldShowError(false);
            }
        });
        adjustViewForPostalCodeAttribute();
        this.mPostalCodeEditText.setAfterTextChangedListener(new StripeEditText.AfterTextChangedListener() {
            public void onTextChanged(String str) {
                if (CardMultilineWidget.isPostalCodeMaximalLength(true, str) && CardMultilineWidget.this.mCardInputListener != null) {
                    CardMultilineWidget.this.mCardInputListener.onPostalCodeComplete();
                }
                CardMultilineWidget.this.mPostalCodeEditText.setShouldShowError(false);
            }
        });
        this.mCardNumberEditText.updateLengthFilter();
        updateBrand("Unknown");
        setEnabled(true);
    }

    private void initDeleteEmptyListeners() {
        this.mExpiryDateEditText.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.mCardNumberEditText));
        this.mCvcEditText.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.mExpiryDateEditText));
        StripeEditText stripeEditText = this.mPostalCodeEditText;
        if (stripeEditText != null) {
            stripeEditText.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.mCvcEditText));
        }
    }

    private void initErrorMessages() {
        this.mCardNumberEditText.setErrorMessage(getContext().getString(R.string.invalid_card_number));
        this.mExpiryDateEditText.setErrorMessage(getContext().getString(R.string.invalid_expiry_year));
        this.mCvcEditText.setErrorMessage(getContext().getString(R.string.invalid_cvc));
        this.mPostalCodeEditText.setErrorMessage(getContext().getString(R.string.invalid_zip));
    }

    private void initFocusChangeListeners() {
        this.mCardNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardMultilineWidget.this.mCardNumberEditText.setHintDelayed(R.string.card_number_hint, CardMultilineWidget.CARD_NUMBER_HINT_DELAY);
                    if (CardMultilineWidget.this.mCardInputListener != null) {
                        CardMultilineWidget.this.mCardInputListener.onFocusChange(CardInputListener.FocusField.FOCUS_CARD);
                        return;
                    }
                    return;
                }
                CardMultilineWidget.this.mCardNumberEditText.setHint("");
            }
        });
        this.mExpiryDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardMultilineWidget.this.mExpiryDateEditText.setHintDelayed(R.string.expiry_date_hint, CardMultilineWidget.COMMON_HINT_DELAY);
                    if (CardMultilineWidget.this.mCardInputListener != null) {
                        CardMultilineWidget.this.mCardInputListener.onFocusChange(CardInputListener.FocusField.FOCUS_EXPIRY);
                        return;
                    }
                    return;
                }
                CardMultilineWidget.this.mExpiryDateEditText.setHint("");
            }
        });
        this.mCvcEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardMultilineWidget.this.flipToCvcIconIfNotFinished();
                    CardMultilineWidget.this.mCvcEditText.setHintDelayed(CardMultilineWidget.this.getCvcHelperText(), CardMultilineWidget.COMMON_HINT_DELAY);
                    if (CardMultilineWidget.this.mCardInputListener != null) {
                        CardMultilineWidget.this.mCardInputListener.onFocusChange(CardInputListener.FocusField.FOCUS_CVC);
                        return;
                    }
                    return;
                }
                CardMultilineWidget cardMultilineWidget = CardMultilineWidget.this;
                cardMultilineWidget.updateBrand(cardMultilineWidget.mCardBrand);
                CardMultilineWidget.this.mCvcEditText.setHint("");
            }
        });
        StripeEditText stripeEditText = this.mPostalCodeEditText;
        if (stripeEditText != null) {
            stripeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (CardMultilineWidget.this.mShouldShowPostalCode) {
                        if (z) {
                            CardMultilineWidget.this.mPostalCodeEditText.setHintDelayed(R.string.zip_helper, CardMultilineWidget.COMMON_HINT_DELAY);
                            if (CardMultilineWidget.this.mCardInputListener != null) {
                                CardMultilineWidget.this.mCardInputListener.onFocusChange(CardInputListener.FocusField.FOCUS_POSTAL);
                                return;
                            }
                            return;
                        }
                        CardMultilineWidget.this.mPostalCodeEditText.setHint("");
                    }
                }
            });
        }
    }

    private void initTextInputLayoutErrorHandlers(TextInputLayout textInputLayout, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, TextInputLayout textInputLayout4) {
        this.mCardNumberEditText.setErrorMessageListener(new ErrorListener(textInputLayout));
        this.mExpiryDateEditText.setErrorMessageListener(new ErrorListener(textInputLayout2));
        this.mCvcEditText.setErrorMessageListener(new ErrorListener(textInputLayout3));
        StripeEditText stripeEditText = this.mPostalCodeEditText;
        if (stripeEditText != null) {
            stripeEditText.setErrorMessageListener(new ErrorListener(textInputLayout4));
        }
    }

    /* access modifiers changed from: private */
    public void updateBrand(String str) {
        this.mCardBrand = str;
        updateCvc();
        updateDrawable(Card.BRAND_RESOURCE_MAP.get(str).intValue(), "Unknown".equals(str));
    }

    private void updateCvc() {
        this.mCvcEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Card.AMERICAN_EXPRESS.equals(this.mCardBrand) ? 4 : 3)});
        this.mCvcTextInputLayout.setHint(getCvcLabel());
    }

    private String getCvcLabel() {
        String str = this.mCustomCvcLabel;
        if (str != null) {
            return str;
        }
        return getResources().getString(Card.AMERICAN_EXPRESS.equals(this.mCardBrand) ? R.string.cvc_amex_hint : R.string.cvc_number_hint);
    }

    private void updateDrawable(int i, boolean z) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), i);
        Drawable drawable2 = this.mCardNumberEditText.getCompoundDrawables()[0];
        if (drawable2 != null) {
            Rect rect = new Rect();
            drawable2.copyBounds(rect);
            int compoundDrawablePadding = this.mCardNumberEditText.getCompoundDrawablePadding();
            if (!this.mHasAdjustedDrawable) {
                rect.top -= getDynamicBufferInPixels();
                rect.bottom -= getDynamicBufferInPixels();
                this.mHasAdjustedDrawable = true;
            }
            drawable.setBounds(rect);
            Drawable wrap = DrawableCompat.wrap(drawable);
            if (z) {
                DrawableCompat.setTint(wrap.mutate(), this.mTintColorInt);
            }
            this.mCardNumberEditText.setCompoundDrawablePadding(compoundDrawablePadding);
            this.mCardNumberEditText.setCompoundDrawables(wrap, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }
}
