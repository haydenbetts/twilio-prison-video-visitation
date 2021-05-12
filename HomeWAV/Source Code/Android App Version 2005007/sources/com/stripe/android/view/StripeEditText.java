package com.stripe.android.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

public class StripeEditText extends AppCompatEditText {
    /* access modifiers changed from: private */
    public AfterTextChangedListener mAfterTextChangedListener;
    private ColorStateList mCachedColorStateList;
    private int mDefaultErrorColorResId;
    /* access modifiers changed from: private */
    public DeleteEmptyListener mDeleteEmptyListener;
    private int mErrorColor;
    private String mErrorMessage;
    private ErrorMessageListener mErrorMessageListener;
    private final Handler mHandler;
    private boolean mShouldShowError;

    interface AfterTextChangedListener {
        void onTextChanged(String str);
    }

    interface DeleteEmptyListener {
        void onDeleteEmpty();
    }

    interface ErrorMessageListener {
        void displayErrorMessage(String str);
    }

    public StripeEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public StripeEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    public StripeEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHandler = new Handler();
        initView();
    }

    public ColorStateList getCachedColorStateList() {
        return this.mCachedColorStateList;
    }

    public boolean getShouldShowError() {
        return this.mShouldShowError;
    }

    public int getDefaultErrorColorInt() {
        determineDefaultErrorColor();
        return ContextCompat.getColor(getContext(), this.mDefaultErrorColorResId);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (super.onCreateInputConnection(editorInfo) == null) {
            return null;
        }
        return new SoftDeleteInputConnection(super.onCreateInputConnection(editorInfo), true);
    }

    /* access modifiers changed from: package-private */
    public void setAfterTextChangedListener(AfterTextChangedListener afterTextChangedListener) {
        this.mAfterTextChangedListener = afterTextChangedListener;
    }

    /* access modifiers changed from: package-private */
    public void setDeleteEmptyListener(DeleteEmptyListener deleteEmptyListener) {
        this.mDeleteEmptyListener = deleteEmptyListener;
    }

    /* access modifiers changed from: package-private */
    public void setErrorMessageListener(ErrorMessageListener errorMessageListener) {
        this.mErrorMessageListener = errorMessageListener;
    }

    /* access modifiers changed from: package-private */
    public void setErrorMessage(String str) {
        this.mErrorMessage = str;
    }

    public void setErrorColor(int i) {
        this.mErrorColor = i;
    }

    public void setHintDelayed(final int i, long j) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                StripeEditText.this.setHint(i);
            }
        }, j);
    }

    public void setShouldShowError(boolean z) {
        ErrorMessageListener errorMessageListener;
        String str = this.mErrorMessage;
        if (str == null || (errorMessageListener = this.mErrorMessageListener) == null) {
            this.mShouldShowError = z;
            if (z) {
                setTextColor(this.mErrorColor);
            } else {
                setTextColor(this.mCachedColorStateList);
            }
            refreshDrawableState();
            return;
        }
        if (!z) {
            str = null;
        }
        errorMessageListener.displayErrorMessage(str);
        this.mShouldShowError = z;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    private void initView() {
        listenForTextChanges();
        listenForDeleteEmpty();
        determineDefaultErrorColor();
        this.mCachedColorStateList = getTextColors();
    }

    private void determineDefaultErrorColor() {
        ColorStateList textColors = getTextColors();
        this.mCachedColorStateList = textColors;
        if (ViewUtils.isColorDark(textColors.getDefaultColor())) {
            this.mDefaultErrorColorResId = com.stripe.android.R.color.error_text_light_theme;
        } else {
            this.mDefaultErrorColorResId = com.stripe.android.R.color.error_text_dark_theme;
        }
    }

    private void listenForTextChanges() {
        addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (StripeEditText.this.mAfterTextChangedListener != null) {
                    StripeEditText.this.mAfterTextChangedListener.onTextChanged(editable.toString());
                }
            }
        });
    }

    private void listenForDeleteEmpty() {
        setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 67 || keyEvent.getAction() != 0 || StripeEditText.this.mDeleteEmptyListener == null || StripeEditText.this.length() != 0) {
                    return false;
                }
                StripeEditText.this.mDeleteEmptyListener.onDeleteEmpty();
                return false;
            }
        });
    }

    private class SoftDeleteInputConnection extends InputConnectionWrapper {
        private SoftDeleteInputConnection(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }

        public boolean deleteSurroundingText(int i, int i2) {
            if (getTextBeforeCursor(1, 0).length() == 0 && StripeEditText.this.mDeleteEmptyListener != null) {
                StripeEditText.this.mDeleteEmptyListener.onDeleteEmpty();
            }
            return super.deleteSurroundingText(i, i2);
        }
    }
}
