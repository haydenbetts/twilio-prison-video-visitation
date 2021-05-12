package com.stripe.android.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import com.stripe.android.R;

public class ExpiryDateEditText extends StripeEditText {
    static final int INVALID_INPUT = -1;
    private static final int MAX_INPUT_LENGTH = 5;
    /* access modifiers changed from: private */
    public ExpiryDateEditListener mExpiryDateEditListener;
    /* access modifiers changed from: private */
    public boolean mIsDateValid;

    interface ExpiryDateEditListener {
        void onExpiryDateComplete();
    }

    public ExpiryDateEditText(Context context) {
        super(context);
        listenForTextChanges();
    }

    public ExpiryDateEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        listenForTextChanges();
    }

    public ExpiryDateEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        listenForTextChanges();
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setText(getResources().getString(R.string.acc_label_expiry_date_node, new Object[]{getText()}));
    }

    public boolean isDateValid() {
        return this.mIsDateValid;
    }

    public int[] getValidDateFields() {
        if (!this.mIsDateValid) {
            return null;
        }
        int[] iArr = new int[2];
        String[] separateDateStringParts = DateUtils.separateDateStringParts(getText().toString().replaceAll("/", ""));
        try {
            iArr[0] = Integer.parseInt(separateDateStringParts[0]);
            iArr[1] = DateUtils.convertTwoDigitYearToFour(Integer.parseInt(separateDateStringParts[1]));
            return iArr;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public void setExpiryDateEditListener(ExpiryDateEditListener expiryDateEditListener) {
        this.mExpiryDateEditListener = expiryDateEditListener;
    }

    private void listenForTextChanges() {
        addTextChangedListener(new TextWatcher() {
            boolean ignoreChanges = false;
            int latestChangeStart;
            int latestInsertionSize;
            String[] parts = new String[2];

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!this.ignoreChanges) {
                    this.latestChangeStart = i;
                    this.latestInsertionSize = i3;
                }
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!this.ignoreChanges) {
                    String replaceAll = charSequence.toString().replaceAll("/", "");
                    if (replaceAll.length() == 1 && this.latestChangeStart == 0 && this.latestInsertionSize == 1) {
                        char charAt = replaceAll.charAt(0);
                        if (!(charAt == '0' || charAt == '1')) {
                            replaceAll = "0" + replaceAll;
                            this.latestInsertionSize++;
                        }
                    } else if (replaceAll.length() == 2 && this.latestChangeStart == 2 && this.latestInsertionSize == 0) {
                        replaceAll = replaceAll.substring(0, 1);
                    }
                    String[] separateDateStringParts = DateUtils.separateDateStringParts(replaceAll);
                    this.parts = separateDateStringParts;
                    boolean z = !DateUtils.isValidMonth(separateDateStringParts[0]);
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.parts[0]);
                    if ((this.parts[0].length() == 2 && this.latestInsertionSize > 0 && !z) || replaceAll.length() > 2) {
                        sb.append("/");
                    }
                    sb.append(this.parts[1]);
                    String sb2 = sb.toString();
                    int updateSelectionIndex = ExpiryDateEditText.this.updateSelectionIndex(sb2.length(), this.latestChangeStart, this.latestInsertionSize, 5);
                    this.ignoreChanges = true;
                    ExpiryDateEditText.this.setText(sb2);
                    ExpiryDateEditText.this.setSelection(updateSelectionIndex);
                    this.ignoreChanges = false;
                }
            }

            public void afterTextChanged(Editable editable) {
                boolean z = this.parts[0].length() == 2 && !DateUtils.isValidMonth(this.parts[0]);
                if (this.parts[0].length() == 2 && this.parts[1].length() == 2) {
                    boolean access$000 = ExpiryDateEditText.this.mIsDateValid;
                    ExpiryDateEditText.this.updateInputValues(this.parts);
                    boolean z2 = !ExpiryDateEditText.this.mIsDateValid;
                    if (!access$000 && ExpiryDateEditText.this.mIsDateValid && ExpiryDateEditText.this.mExpiryDateEditListener != null) {
                        ExpiryDateEditText.this.mExpiryDateEditListener.onExpiryDateComplete();
                    }
                    z = z2;
                } else {
                    boolean unused = ExpiryDateEditText.this.mIsDateValid = false;
                }
                ExpiryDateEditText.this.setShouldShowError(z);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public int updateSelectionIndex(int i, int i2, int i3, int i4) {
        boolean z = true;
        int i5 = (i2 > 2 || i2 + i3 < 2) ? 0 : 1;
        if (!(i3 == 0 && i2 == 3)) {
            z = false;
        }
        int i6 = i2 + i3 + i5;
        if (z && i6 > 0) {
            i6--;
        }
        if (i6 <= i) {
            i = i6;
        }
        return Math.min(i4, i);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001d A[SYNTHETIC, Splitter:B:8:0x001d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateInputValues(java.lang.String[] r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = r6[r0]
            int r1 = r1.length()
            r2 = 2
            r3 = -1
            if (r1 == r2) goto L_0x000d
        L_0x000b:
            r0 = -1
            goto L_0x0013
        L_0x000d:
            r0 = r6[r0]     // Catch:{ NumberFormatException -> 0x000b }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x000b }
        L_0x0013:
            r1 = 1
            r4 = r6[r1]
            int r4 = r4.length()
            if (r4 == r2) goto L_0x001d
            goto L_0x0027
        L_0x001d:
            r6 = r6[r1]     // Catch:{ NumberFormatException -> 0x0027 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x0027 }
            int r3 = com.stripe.android.view.DateUtils.convertTwoDigitYearToFour(r6)     // Catch:{ NumberFormatException -> 0x0027 }
        L_0x0027:
            boolean r6 = com.stripe.android.view.DateUtils.isExpiryDataValid(r0, r3)
            r5.mIsDateValid = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.view.ExpiryDateEditText.updateInputValues(java.lang.String[]):void");
    }
}
