package com.stripe.android.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import com.stripe.android.CardUtils;
import com.stripe.android.R;
import com.stripe.android.StripeTextUtils;
import com.stripe.android.model.Card;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CardNumberEditText extends StripeEditText {
    private static final int MAX_LENGTH_AMEX_DINERS = 17;
    private static final int MAX_LENGTH_COMMON = 19;
    private static final Integer[] SPACES_ARRAY_AMEX;
    private static final Integer[] SPACES_ARRAY_COMMON;
    private static final Set<Integer> SPACE_SET_AMEX;
    private static final Set<Integer> SPACE_SET_COMMON;
    String mCardBrand = "Unknown";
    private CardBrandChangeListener mCardBrandChangeListener;
    /* access modifiers changed from: private */
    public CardNumberCompleteListener mCardNumberCompleteListener;
    /* access modifiers changed from: private */
    public boolean mIgnoreChanges = false;
    /* access modifiers changed from: private */
    public boolean mIsCardNumberValid = false;
    /* access modifiers changed from: private */
    public int mLengthMax = 19;

    interface CardBrandChangeListener {
        void onCardBrandChanged(String str);
    }

    interface CardNumberCompleteListener {
        void onCardNumberComplete();
    }

    static {
        Integer[] numArr = {4, 9, 14};
        SPACES_ARRAY_COMMON = numArr;
        SPACE_SET_COMMON = new HashSet(Arrays.asList(numArr));
        Integer[] numArr2 = {4, 11};
        SPACES_ARRAY_AMEX = numArr2;
        SPACE_SET_AMEX = new HashSet(Arrays.asList(numArr2));
    }

    public CardNumberEditText(Context context) {
        super(context);
        listenForTextChanges();
    }

    public CardNumberEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        listenForTextChanges();
    }

    public CardNumberEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        listenForTextChanges();
    }

    public String getCardBrand() {
        return this.mCardBrand;
    }

    public String getCardNumber() {
        if (this.mIsCardNumberValid) {
            return StripeTextUtils.removeSpacesAndHyphens(getText().toString());
        }
        return null;
    }

    public int getLengthMax() {
        return this.mLengthMax;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setText(getResources().getString(R.string.acc_label_card_number_node, new Object[]{getText()}));
    }

    public boolean isCardNumberValid() {
        return this.mIsCardNumberValid;
    }

    /* access modifiers changed from: package-private */
    public void setCardNumberCompleteListener(CardNumberCompleteListener cardNumberCompleteListener) {
        this.mCardNumberCompleteListener = cardNumberCompleteListener;
    }

    /* access modifiers changed from: package-private */
    public void setCardBrandChangeListener(CardBrandChangeListener cardBrandChangeListener) {
        this.mCardBrandChangeListener = cardBrandChangeListener;
        cardBrandChangeListener.onCardBrandChanged(this.mCardBrand);
    }

    /* access modifiers changed from: package-private */
    public void updateLengthFilter() {
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.mLengthMax)});
    }

    /* access modifiers changed from: package-private */
    public int updateSelectionIndex(int i, int i2, int i3) {
        int i4 = 0;
        boolean z = false;
        for (Integer next : Card.AMERICAN_EXPRESS.equals(this.mCardBrand) ? SPACE_SET_AMEX : SPACE_SET_COMMON) {
            if (i2 <= next.intValue() && i2 + i3 > next.intValue()) {
                i4++;
            }
            if (i3 == 0 && i2 == next.intValue() + 1) {
                z = true;
            }
        }
        int i5 = i2 + i3 + i4;
        if (z && i5 > 0) {
            i5--;
        }
        return i5 <= i ? i5 : i;
    }

    private void listenForTextChanges() {
        addTextChangedListener(new TextWatcher() {
            int latestChangeStart;
            int latestInsertionSize;

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!CardNumberEditText.this.mIgnoreChanges) {
                    this.latestChangeStart = i;
                    this.latestInsertionSize = i3;
                }
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String removeSpacesAndHyphens;
                if (!CardNumberEditText.this.mIgnoreChanges) {
                    if (i < 4) {
                        CardNumberEditText.this.updateCardBrandFromNumber(charSequence.toString());
                    }
                    if (i <= 16 && (removeSpacesAndHyphens = StripeTextUtils.removeSpacesAndHyphens(charSequence.toString())) != null) {
                        String[] separateCardNumberGroups = ViewUtils.separateCardNumberGroups(removeSpacesAndHyphens, CardNumberEditText.this.mCardBrand);
                        StringBuilder sb = new StringBuilder();
                        int i4 = 0;
                        while (i4 < separateCardNumberGroups.length && separateCardNumberGroups[i4] != null) {
                            if (i4 != 0) {
                                sb.append(' ');
                            }
                            sb.append(separateCardNumberGroups[i4]);
                            i4++;
                        }
                        String sb2 = sb.toString();
                        int updateSelectionIndex = CardNumberEditText.this.updateSelectionIndex(sb2.length(), this.latestChangeStart, this.latestInsertionSize);
                        boolean unused = CardNumberEditText.this.mIgnoreChanges = true;
                        CardNumberEditText.this.setText(sb2);
                        CardNumberEditText.this.setSelection(updateSelectionIndex);
                        boolean unused2 = CardNumberEditText.this.mIgnoreChanges = false;
                    }
                }
            }

            public void afterTextChanged(Editable editable) {
                boolean z = true;
                if (editable.length() == CardNumberEditText.this.mLengthMax) {
                    boolean access$300 = CardNumberEditText.this.mIsCardNumberValid;
                    boolean unused = CardNumberEditText.this.mIsCardNumberValid = CardUtils.isValidCardNumber(editable.toString());
                    CardNumberEditText cardNumberEditText = CardNumberEditText.this;
                    cardNumberEditText.setShouldShowError(!cardNumberEditText.mIsCardNumberValid);
                    if (!access$300 && CardNumberEditText.this.mIsCardNumberValid && CardNumberEditText.this.mCardNumberCompleteListener != null) {
                        CardNumberEditText.this.mCardNumberCompleteListener.onCardNumberComplete();
                        return;
                    }
                    return;
                }
                CardNumberEditText cardNumberEditText2 = CardNumberEditText.this;
                if (cardNumberEditText2.getText() == null || !CardUtils.isValidCardNumber(CardNumberEditText.this.getText().toString())) {
                    z = false;
                }
                boolean unused2 = cardNumberEditText2.mIsCardNumberValid = z;
                CardNumberEditText.this.setShouldShowError(false);
            }
        });
    }

    private void updateCardBrand(String str) {
        if (!this.mCardBrand.equals(str)) {
            this.mCardBrand = str;
            CardBrandChangeListener cardBrandChangeListener = this.mCardBrandChangeListener;
            if (cardBrandChangeListener != null) {
                cardBrandChangeListener.onCardBrandChanged(str);
            }
            int i = this.mLengthMax;
            int lengthForBrand = getLengthForBrand(this.mCardBrand);
            this.mLengthMax = lengthForBrand;
            if (i != lengthForBrand) {
                updateLengthFilter();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateCardBrandFromNumber(String str) {
        updateCardBrand(CardUtils.getPossibleCardType(str));
    }

    private static int getLengthForBrand(String str) {
        return (Card.AMERICAN_EXPRESS.equals(str) || Card.DINERS_CLUB.equals(str)) ? 17 : 19;
    }
}
