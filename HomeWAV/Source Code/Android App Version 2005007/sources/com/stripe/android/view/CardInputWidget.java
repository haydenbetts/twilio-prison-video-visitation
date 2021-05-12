package com.stripe.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputFilter;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.stripe.android.R;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputListener;
import com.stripe.android.view.CardNumberEditText;
import com.stripe.android.view.ExpiryDateEditText;
import com.stripe.android.view.StripeEditText;
import java.util.Locale;

public class CardInputWidget extends LinearLayout {
    private static final long ANIMATION_LENGTH = 150;
    private static final String CVC_PLACEHOLDER_AMEX = "2345";
    private static final String CVC_PLACEHOLDER_COMMON = "CVC";
    private static final int DEFAULT_READER_ID = R.id.default_reader_id;
    private static final String EXTRA_CARD_VIEWED = "extra_card_viewed";
    private static final String EXTRA_SUPER_STATE = "extra_super_state";
    private static final String FULL_SIZING_CARD_TEXT = "4242 4242 4242 4242";
    private static final String FULL_SIZING_DATE_TEXT = "MM/MM";
    private static final String HIDDEN_TEXT_AMEX = "3434 343434 ";
    private static final String HIDDEN_TEXT_COMMON = "4242 4242 4242 ";
    static final String LOGGING_TOKEN = "CardInputView";
    private static final String PEEK_TEXT_AMEX = "34343";
    private static final String PEEK_TEXT_COMMON = "4242";
    private static final String PEEK_TEXT_DINERS = "88";
    private String mCardHintText;
    private ImageView mCardIconImageView;
    /* access modifiers changed from: private */
    public CardInputListener mCardInputListener;
    /* access modifiers changed from: private */
    public CardNumberEditText mCardNumberEditText;
    private boolean mCardNumberIsViewed = true;
    /* access modifiers changed from: private */
    public StripeEditText mCvcNumberEditText;
    private DimensionOverrideSettings mDimensionOverrides;
    private int mErrorColorInt;
    /* access modifiers changed from: private */
    public ExpiryDateEditText mExpiryDateEditText;
    private FrameLayout mFrameLayout;
    private boolean mInitFlag;
    /* access modifiers changed from: private */
    public boolean mIsAmEx;
    /* access modifiers changed from: private */
    public PlacementParameters mPlacementParameters;
    private int mTintColorInt;
    private int mTotalLengthInPixels;

    interface DimensionOverrideSettings {
        int getFrameWidth();

        int getPixelWidth(String str, EditText editText);
    }

    public CardInputWidget(Context context) {
        super(context);
        initView((AttributeSet) null);
    }

    public CardInputWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(attributeSet);
    }

    public CardInputWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(attributeSet);
    }

    public Card getCard() {
        String cardNumber = this.mCardNumberEditText.getCardNumber();
        int[] validDateFields = this.mExpiryDateEditText.getValidDateFields();
        if (cardNumber == null || validDateFields == null || validDateFields.length != 2) {
            return null;
        }
        String trim = this.mCvcNumberEditText.getText().toString().trim();
        if (!isCvcLengthValid()) {
            return null;
        }
        return new Card(cardNumber, Integer.valueOf(validDateFields[0]), Integer.valueOf(validDateFields[1]), trim).addLoggingToken(LOGGING_TOKEN);
    }

    public void setCardInputListener(CardInputListener cardInputListener) {
        this.mCardInputListener = cardInputListener;
    }

    public void setCardNumber(String str) {
        this.mCardNumberEditText.setText(str);
        setCardNumberIsViewed(!this.mCardNumberEditText.isCardNumberValid());
    }

    public void setExpiryDate(int i, int i2) {
        this.mExpiryDateEditText.setText(DateUtils.createDateStringFromIntegerInput(i, i2));
    }

    public void setCvcCode(String str) {
        this.mCvcNumberEditText.setText(str);
    }

    public void clear() {
        if (this.mCardNumberEditText.hasFocus() || this.mExpiryDateEditText.hasFocus() || this.mCvcNumberEditText.hasFocus() || hasFocus()) {
            this.mCardNumberEditText.requestFocus();
        }
        this.mCvcNumberEditText.setText("");
        this.mExpiryDateEditText.setText("");
        this.mCardNumberEditText.setText("");
    }

    public void setEnabled(boolean z) {
        this.mCardNumberEditText.setEnabled(z);
        this.mExpiryDateEditText.setEnabled(z);
        this.mCvcNumberEditText.setEnabled(z);
    }

    public void setCardNumberTextWatcher(TextWatcher textWatcher) {
        this.mCardNumberEditText.addTextChangedListener(textWatcher);
    }

    public void setExpiryDateTextWatcher(TextWatcher textWatcher) {
        this.mExpiryDateEditText.addTextChangedListener(textWatcher);
    }

    public void setCvcNumberTextWatcher(TextWatcher textWatcher) {
        this.mCvcNumberEditText.addTextChangedListener(textWatcher);
    }

    public boolean isEnabled() {
        return this.mCardNumberEditText.isEnabled() && this.mExpiryDateEditText.isEnabled() && this.mCvcNumberEditText.isEnabled();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        StripeEditText focusRequestOnTouch = getFocusRequestOnTouch((int) motionEvent.getX());
        if (focusRequestOnTouch == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        focusRequestOnTouch.requestFocus();
        return true;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SUPER_STATE, super.onSaveInstanceState());
        bundle.putBoolean(EXTRA_CARD_VIEWED, this.mCardNumberIsViewed);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        int i2;
        int i3;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            boolean z = bundle.getBoolean(EXTRA_CARD_VIEWED, true);
            this.mCardNumberIsViewed = z;
            updateSpaceSizes(z);
            this.mTotalLengthInPixels = getFrameWidth();
            if (this.mCardNumberIsViewed) {
                i3 = 0;
                i2 = this.mPlacementParameters.cardWidth + this.mPlacementParameters.cardDateSeparation;
                i = this.mTotalLengthInPixels;
            } else {
                i3 = this.mPlacementParameters.hiddenCardWidth * -1;
                i2 = this.mPlacementParameters.peekCardWidth + this.mPlacementParameters.cardDateSeparation;
                i = this.mPlacementParameters.dateWidth + i2 + this.mPlacementParameters.dateCvcSeparation;
            }
            setLayoutValues(this.mPlacementParameters.cardWidth, i3, this.mCardNumberEditText);
            setLayoutValues(this.mPlacementParameters.dateWidth, i2, this.mExpiryDateEditText);
            setLayoutValues(this.mPlacementParameters.cvcWidth, i, this.mCvcNumberEditText);
            super.onRestoreInstanceState(bundle.getParcelable(EXTRA_SUPER_STATE));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: package-private */
    public StripeEditText getFocusRequestOnTouch(int i) {
        int left = this.mFrameLayout.getLeft();
        if (this.mCardNumberIsViewed) {
            if (i < left + this.mPlacementParameters.cardWidth) {
                return null;
            }
            if (i < this.mPlacementParameters.cardTouchBufferLimit) {
                return this.mCardNumberEditText;
            }
            if (i < this.mPlacementParameters.dateStartPosition) {
                return this.mExpiryDateEditText;
            }
            return null;
        } else if (i < left + this.mPlacementParameters.peekCardWidth) {
            return null;
        } else {
            if (i < this.mPlacementParameters.cardTouchBufferLimit) {
                return this.mCardNumberEditText;
            }
            if (i < this.mPlacementParameters.dateStartPosition) {
                return this.mExpiryDateEditText;
            }
            if (i < this.mPlacementParameters.dateStartPosition + this.mPlacementParameters.dateWidth) {
                return null;
            }
            if (i < this.mPlacementParameters.dateRightTouchBufferLimit) {
                return this.mExpiryDateEditText;
            }
            if (i < this.mPlacementParameters.cvcStartPosition) {
                return this.mCvcNumberEditText;
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setDimensionOverrideSettings(DimensionOverrideSettings dimensionOverrideSettings) {
        this.mDimensionOverrides = dimensionOverrideSettings;
    }

    /* access modifiers changed from: package-private */
    public void setCardNumberIsViewed(boolean z) {
        this.mCardNumberIsViewed = z;
    }

    /* access modifiers changed from: package-private */
    public PlacementParameters getPlacementParameters() {
        return this.mPlacementParameters;
    }

    /* access modifiers changed from: package-private */
    public void updateSpaceSizes(boolean z) {
        int frameWidth = getFrameWidth();
        int left = this.mFrameLayout.getLeft();
        if (frameWidth != 0) {
            this.mPlacementParameters.cardWidth = getDesiredWidthInPixels(FULL_SIZING_CARD_TEXT, this.mCardNumberEditText);
            this.mPlacementParameters.dateWidth = getDesiredWidthInPixels(FULL_SIZING_DATE_TEXT, this.mExpiryDateEditText);
            String cardBrand = this.mCardNumberEditText.getCardBrand();
            this.mPlacementParameters.hiddenCardWidth = getDesiredWidthInPixels(getHiddenTextForBrand(cardBrand), this.mCardNumberEditText);
            this.mPlacementParameters.cvcWidth = getDesiredWidthInPixels(getCvcPlaceHolderForBrand(cardBrand), this.mCvcNumberEditText);
            this.mPlacementParameters.peekCardWidth = getDesiredWidthInPixels(getPeekCardTextForBrand(cardBrand), this.mCardNumberEditText);
            if (z) {
                PlacementParameters placementParameters = this.mPlacementParameters;
                placementParameters.cardDateSeparation = (frameWidth - placementParameters.cardWidth) - this.mPlacementParameters.dateWidth;
                PlacementParameters placementParameters2 = this.mPlacementParameters;
                placementParameters2.cardTouchBufferLimit = placementParameters2.cardWidth + left + (this.mPlacementParameters.cardDateSeparation / 2);
                PlacementParameters placementParameters3 = this.mPlacementParameters;
                placementParameters3.dateStartPosition = left + placementParameters3.cardWidth + this.mPlacementParameters.cardDateSeparation;
                return;
            }
            PlacementParameters placementParameters4 = this.mPlacementParameters;
            placementParameters4.cardDateSeparation = ((frameWidth / 2) - placementParameters4.peekCardWidth) - (this.mPlacementParameters.dateWidth / 2);
            PlacementParameters placementParameters5 = this.mPlacementParameters;
            placementParameters5.dateCvcSeparation = (((frameWidth - placementParameters5.peekCardWidth) - this.mPlacementParameters.cardDateSeparation) - this.mPlacementParameters.dateWidth) - this.mPlacementParameters.cvcWidth;
            PlacementParameters placementParameters6 = this.mPlacementParameters;
            placementParameters6.cardTouchBufferLimit = placementParameters6.peekCardWidth + left + (this.mPlacementParameters.cardDateSeparation / 2);
            PlacementParameters placementParameters7 = this.mPlacementParameters;
            placementParameters7.dateStartPosition = left + placementParameters7.peekCardWidth + this.mPlacementParameters.cardDateSeparation;
            PlacementParameters placementParameters8 = this.mPlacementParameters;
            placementParameters8.dateRightTouchBufferLimit = placementParameters8.dateStartPosition + this.mPlacementParameters.dateWidth + (this.mPlacementParameters.dateCvcSeparation / 2);
            PlacementParameters placementParameters9 = this.mPlacementParameters;
            placementParameters9.cvcStartPosition = placementParameters9.dateStartPosition + this.mPlacementParameters.dateWidth + this.mPlacementParameters.dateCvcSeparation;
        }
    }

    private boolean isCvcLengthValid() {
        int length = this.mCvcNumberEditText.getText().toString().trim().length();
        return (this.mIsAmEx && length == 4) || length == 3;
    }

    private void setLayoutValues(int i, int i2, StripeEditText stripeEditText) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) stripeEditText.getLayoutParams();
        layoutParams.width = i;
        layoutParams.leftMargin = i2;
        stripeEditText.setLayoutParams(layoutParams);
    }

    private int getDesiredWidthInPixels(String str, StripeEditText stripeEditText) {
        DimensionOverrideSettings dimensionOverrideSettings = this.mDimensionOverrides;
        if (dimensionOverrideSettings == null) {
            return (int) Layout.getDesiredWidth(str, stripeEditText.getPaint());
        }
        return dimensionOverrideSettings.getPixelWidth(str, stripeEditText);
    }

    private int getFrameWidth() {
        DimensionOverrideSettings dimensionOverrideSettings = this.mDimensionOverrides;
        if (dimensionOverrideSettings == null) {
            return this.mFrameLayout.getWidth();
        }
        return dimensionOverrideSettings.getFrameWidth();
    }

    private void initView(AttributeSet attributeSet) {
        inflate(getContext(), R.layout.card_input_widget, this);
        if (getId() == -1) {
            setId(DEFAULT_READER_ID);
        }
        setOrientation(0);
        setMinimumWidth(getResources().getDimensionPixelSize(R.dimen.card_widget_min_width));
        this.mPlacementParameters = new PlacementParameters();
        this.mCardIconImageView = (ImageView) findViewById(R.id.iv_card_icon);
        this.mCardNumberEditText = (CardNumberEditText) findViewById(R.id.et_card_number);
        this.mExpiryDateEditText = (ExpiryDateEditText) findViewById(R.id.et_expiry_date);
        StripeEditText stripeEditText = (StripeEditText) findViewById(R.id.et_cvc_number);
        this.mCvcNumberEditText = stripeEditText;
        ViewCompat.setAccessibilityDelegate(stripeEditText, new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setText(CardInputWidget.this.getResources().getString(R.string.acc_label_cvc_node, new Object[]{CardInputWidget.this.mCvcNumberEditText.getText()}));
            }
        });
        this.mCardNumberIsViewed = true;
        this.mFrameLayout = (FrameLayout) findViewById(R.id.frame_container);
        this.mErrorColorInt = this.mCardNumberEditText.getDefaultErrorColorInt();
        this.mTintColorInt = this.mCardNumberEditText.getHintTextColors().getDefaultColor();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.CardInputView, 0, 0);
            try {
                this.mErrorColorInt = obtainStyledAttributes.getColor(R.styleable.CardInputView_cardTextErrorColor, this.mErrorColorInt);
                this.mTintColorInt = obtainStyledAttributes.getColor(R.styleable.CardInputView_cardTint, this.mTintColorInt);
                this.mCardHintText = obtainStyledAttributes.getString(R.styleable.CardInputView_cardHintText);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        String str = this.mCardHintText;
        if (str != null) {
            this.mCardNumberEditText.setHint(str);
        }
        this.mCardNumberEditText.setErrorColor(this.mErrorColorInt);
        this.mExpiryDateEditText.setErrorColor(this.mErrorColorInt);
        this.mCvcNumberEditText.setErrorColor(this.mErrorColorInt);
        this.mCardNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardInputWidget.this.scrollLeft();
                    if (CardInputWidget.this.mCardInputListener != null) {
                        CardInputWidget.this.mCardInputListener.onFocusChange(CardInputListener.FocusField.FOCUS_CARD);
                    }
                }
            }
        });
        this.mExpiryDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardInputWidget.this.scrollRight();
                    if (CardInputWidget.this.mCardInputListener != null) {
                        CardInputWidget.this.mCardInputListener.onFocusChange(CardInputListener.FocusField.FOCUS_EXPIRY);
                    }
                }
            }
        });
        this.mExpiryDateEditText.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.mCardNumberEditText));
        this.mCvcNumberEditText.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.mExpiryDateEditText));
        this.mCvcNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardInputWidget.this.scrollRight();
                    if (CardInputWidget.this.mCardInputListener != null) {
                        CardInputWidget.this.mCardInputListener.onFocusChange(CardInputListener.FocusField.FOCUS_CVC);
                    }
                }
                CardInputWidget cardInputWidget = CardInputWidget.this;
                cardInputWidget.updateIconCvc(cardInputWidget.mCardNumberEditText.getCardBrand(), z, CardInputWidget.this.mCvcNumberEditText.getText().toString());
            }
        });
        this.mCvcNumberEditText.setAfterTextChangedListener(new StripeEditText.AfterTextChangedListener() {
            public void onTextChanged(String str) {
                if (CardInputWidget.this.mCardInputListener != null && ViewUtils.isCvcMaximalLength(CardInputWidget.this.mCardNumberEditText.getCardBrand(), str)) {
                    CardInputWidget.this.mCardInputListener.onCvcComplete();
                }
                CardInputWidget cardInputWidget = CardInputWidget.this;
                cardInputWidget.updateIconCvc(cardInputWidget.mCardNumberEditText.getCardBrand(), CardInputWidget.this.mCvcNumberEditText.hasFocus(), str);
            }
        });
        this.mCardNumberEditText.setCardNumberCompleteListener(new CardNumberEditText.CardNumberCompleteListener() {
            public void onCardNumberComplete() {
                CardInputWidget.this.scrollRight();
                if (CardInputWidget.this.mCardInputListener != null) {
                    CardInputWidget.this.mCardInputListener.onCardComplete();
                }
            }
        });
        this.mCardNumberEditText.setCardBrandChangeListener(new CardNumberEditText.CardBrandChangeListener() {
            public void onCardBrandChanged(String str) {
                boolean unused = CardInputWidget.this.mIsAmEx = Card.AMERICAN_EXPRESS.equals(str);
                CardInputWidget.this.updateIcon(str);
                CardInputWidget.this.updateCvc(str);
            }
        });
        this.mExpiryDateEditText.setExpiryDateEditListener(new ExpiryDateEditText.ExpiryDateEditListener() {
            public void onExpiryDateComplete() {
                CardInputWidget.this.mCvcNumberEditText.requestFocus();
                if (CardInputWidget.this.mCardInputListener != null) {
                    CardInputWidget.this.mCardInputListener.onExpirationComplete();
                }
            }
        });
        this.mCardNumberEditText.requestFocus();
    }

    /* access modifiers changed from: private */
    public void scrollLeft() {
        if (!this.mCardNumberIsViewed && this.mInitFlag) {
            final int i = this.mPlacementParameters.peekCardWidth + this.mPlacementParameters.cardDateSeparation;
            final int i2 = this.mPlacementParameters.dateWidth + i + this.mPlacementParameters.dateCvcSeparation;
            updateSpaceSizes(true);
            final int i3 = ((FrameLayout.LayoutParams) this.mCardNumberEditText.getLayoutParams()).leftMargin;
            AnonymousClass9 r4 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.mCardNumberEditText.getLayoutParams();
                    layoutParams.leftMargin = (int) (((float) i3) * (1.0f - f));
                    CardInputWidget.this.mCardNumberEditText.setLayoutParams(layoutParams);
                }
            };
            final int i4 = this.mPlacementParameters.cardWidth + this.mPlacementParameters.cardDateSeparation;
            AnonymousClass10 r5 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    int i = (int) ((((float) i4) * f) + ((1.0f - f) * ((float) i)));
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.mExpiryDateEditText.getLayoutParams();
                    layoutParams.leftMargin = i;
                    CardInputWidget.this.mExpiryDateEditText.setLayoutParams(layoutParams);
                }
            };
            final int i5 = (i4 - i) + i2;
            AnonymousClass11 r0 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    int i = (int) ((((float) i5) * f) + ((1.0f - f) * ((float) i2)));
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.mCvcNumberEditText.getLayoutParams();
                    layoutParams.leftMargin = i;
                    layoutParams.rightMargin = 0;
                    layoutParams.width = CardInputWidget.this.mPlacementParameters.cvcWidth;
                    CardInputWidget.this.mCvcNumberEditText.setLayoutParams(layoutParams);
                }
            };
            r4.setAnimationListener(new AnimationEndListener() {
                public void onAnimationEnd(Animation animation) {
                    CardInputWidget.this.mCardNumberEditText.requestFocus();
                }
            });
            r4.setDuration(150);
            r5.setDuration(150);
            r0.setDuration(150);
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(r4);
            animationSet.addAnimation(r5);
            animationSet.addAnimation(r0);
            this.mFrameLayout.startAnimation(animationSet);
            this.mCardNumberIsViewed = true;
        }
    }

    /* access modifiers changed from: private */
    public void scrollRight() {
        if (this.mCardNumberIsViewed && this.mInitFlag) {
            final int i = this.mPlacementParameters.cardWidth + this.mPlacementParameters.cardDateSeparation;
            updateSpaceSizes(false);
            AnonymousClass13 r2 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.mCardNumberEditText.getLayoutParams();
                    layoutParams.leftMargin = (int) (((float) (CardInputWidget.this.mPlacementParameters.hiddenCardWidth * -1)) * f);
                    CardInputWidget.this.mCardNumberEditText.setLayoutParams(layoutParams);
                }
            };
            final int i2 = this.mPlacementParameters.peekCardWidth + this.mPlacementParameters.cardDateSeparation;
            AnonymousClass14 r4 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    int i = (int) ((((float) i2) * f) + ((1.0f - f) * ((float) i)));
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.mExpiryDateEditText.getLayoutParams();
                    layoutParams.leftMargin = i;
                    CardInputWidget.this.mExpiryDateEditText.setLayoutParams(layoutParams);
                }
            };
            final int i3 = this.mPlacementParameters.peekCardWidth + this.mPlacementParameters.cardDateSeparation + this.mPlacementParameters.dateWidth + this.mPlacementParameters.dateCvcSeparation;
            final int i4 = (i - i2) + i3;
            AnonymousClass15 r3 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    int i = (int) ((((float) i3) * f) + ((1.0f - f) * ((float) i4)));
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.mCvcNumberEditText.getLayoutParams();
                    layoutParams.leftMargin = i;
                    layoutParams.rightMargin = 0;
                    layoutParams.width = CardInputWidget.this.mPlacementParameters.cvcWidth;
                    CardInputWidget.this.mCvcNumberEditText.setLayoutParams(layoutParams);
                }
            };
            r2.setDuration(150);
            r4.setDuration(150);
            r3.setDuration(150);
            r2.setAnimationListener(new AnimationEndListener() {
                public void onAnimationEnd(Animation animation) {
                    CardInputWidget.this.mExpiryDateEditText.requestFocus();
                }
            });
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(r2);
            animationSet.addAnimation(r4);
            animationSet.addAnimation(r3);
            this.mFrameLayout.startAnimation(animationSet);
            this.mCardNumberIsViewed = false;
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            applyTint(false);
        }
    }

    static boolean shouldIconShowBrand(String str, boolean z, String str2) {
        if (!z) {
            return true;
        }
        return ViewUtils.isCvcMaximalLength(str, str2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!this.mInitFlag && getWidth() != 0) {
            this.mInitFlag = true;
            this.mTotalLengthInPixels = getFrameWidth();
            updateSpaceSizes(this.mCardNumberIsViewed);
            setLayoutValues(this.mPlacementParameters.cardWidth, this.mCardNumberIsViewed ? 0 : this.mPlacementParameters.hiddenCardWidth * -1, this.mCardNumberEditText);
            setLayoutValues(this.mPlacementParameters.dateWidth, (this.mCardNumberIsViewed ? this.mPlacementParameters.cardWidth : this.mPlacementParameters.peekCardWidth) + this.mPlacementParameters.cardDateSeparation, this.mExpiryDateEditText);
            setLayoutValues(this.mPlacementParameters.cvcWidth, this.mCardNumberIsViewed ? this.mTotalLengthInPixels : this.mPlacementParameters.peekCardWidth + this.mPlacementParameters.cardDateSeparation + this.mPlacementParameters.dateWidth + this.mPlacementParameters.dateCvcSeparation, this.mCvcNumberEditText);
        }
    }

    private String getHiddenTextForBrand(String str) {
        return Card.AMERICAN_EXPRESS.equals(str) ? HIDDEN_TEXT_AMEX : HIDDEN_TEXT_COMMON;
    }

    private String getCvcPlaceHolderForBrand(String str) {
        return Card.AMERICAN_EXPRESS.equals(str) ? CVC_PLACEHOLDER_AMEX : CVC_PLACEHOLDER_COMMON;
    }

    private String getPeekCardTextForBrand(String str) {
        str.hashCode();
        if (!str.equals(Card.DINERS_CLUB)) {
            return !str.equals(Card.AMERICAN_EXPRESS) ? PEEK_TEXT_COMMON : PEEK_TEXT_AMEX;
        }
        return PEEK_TEXT_DINERS;
    }

    private void applyTint(boolean z) {
        if (z || "Unknown".equals(this.mCardNumberEditText.getCardBrand())) {
            Drawable wrap = DrawableCompat.wrap(this.mCardIconImageView.getDrawable());
            DrawableCompat.setTint(wrap.mutate(), this.mTintColorInt);
            this.mCardIconImageView.setImageDrawable(DrawableCompat.unwrap(wrap));
        }
    }

    /* access modifiers changed from: private */
    public void updateCvc(String str) {
        if (Card.AMERICAN_EXPRESS.equals(str)) {
            this.mCvcNumberEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
            this.mCvcNumberEditText.setHint(R.string.cvc_amex_hint);
            return;
        }
        this.mCvcNumberEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        this.mCvcNumberEditText.setHint(R.string.cvc_number_hint);
    }

    /* access modifiers changed from: private */
    public void updateIcon(String str) {
        if ("Unknown".equals(str)) {
            this.mCardIconImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_unknown));
            applyTint(false);
            return;
        }
        this.mCardIconImageView.setImageResource(Card.BRAND_RESOURCE_MAP.get(str).intValue());
    }

    /* access modifiers changed from: private */
    public void updateIconCvc(String str, boolean z, String str2) {
        if (shouldIconShowBrand(str, z, str2)) {
            updateIcon(str);
        } else {
            updateIconForCvcEntry(Card.AMERICAN_EXPRESS.equals(str));
        }
    }

    private void updateIconForCvcEntry(boolean z) {
        if (z) {
            this.mCardIconImageView.setImageResource(R.drawable.ic_cvc_amex);
        } else {
            this.mCardIconImageView.setImageResource(R.drawable.ic_cvc);
        }
        applyTint(true);
    }

    static class PlacementParameters {
        int cardDateSeparation;
        int cardTouchBufferLimit;
        int cardWidth;
        int cvcStartPosition;
        int cvcWidth;
        int dateCvcSeparation;
        int dateRightTouchBufferLimit;
        int dateStartPosition;
        int dateWidth;
        int hiddenCardWidth;
        int peekCardWidth;

        PlacementParameters() {
        }

        public String toString() {
            String format = String.format(Locale.ENGLISH, "Touch Buffer Data:\nCardTouchBufferLimit = %d\nDateStartPosition = %d\nDateRightTouchBufferLimit = %d\nCvcStartPosition = %d", new Object[]{Integer.valueOf(this.cardTouchBufferLimit), Integer.valueOf(this.dateStartPosition), Integer.valueOf(this.dateRightTouchBufferLimit), Integer.valueOf(this.cvcStartPosition)});
            String format2 = String.format(Locale.ENGLISH, "CardWidth = %d\nHiddenCardWidth = %d\nPeekCardWidth = %d\nCardDateSeparation = %d\nDateWidth = %d\nDateCvcSeparation = %d\nCvcWidth = %d\n", new Object[]{Integer.valueOf(this.cardWidth), Integer.valueOf(this.hiddenCardWidth), Integer.valueOf(this.peekCardWidth), Integer.valueOf(this.cardDateSeparation), Integer.valueOf(this.dateWidth), Integer.valueOf(this.dateCvcSeparation), Integer.valueOf(this.cvcWidth)});
            return format2 + format;
        }
    }

    private abstract class AnimationEndListener implements Animation.AnimationListener {
        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        private AnimationEndListener() {
        }
    }
}
