package com.urbanairship.push.notifications;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.RemoteInput;

public class LocalizableRemoteInput {
    private final boolean allowFreeFormInput;
    private final int[] choices;
    private final int choicesArray;
    private final Bundle extras;
    private final int labelId;
    private final String resultKey;

    private LocalizableRemoteInput(Builder builder) {
        this.resultKey = builder.resultKey;
        this.labelId = builder.labelId;
        this.choices = builder.choices;
        this.allowFreeFormInput = builder.allowFreeFormInput;
        this.extras = builder.extras;
        this.choicesArray = builder.choicesArray;
    }

    public String getResultKey() {
        return this.resultKey;
    }

    public int getLabel() {
        return this.labelId;
    }

    public int[] getChoices() {
        return this.choices;
    }

    public boolean getAllowFreeFormInput() {
        return this.allowFreeFormInput;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public RemoteInput createRemoteInput(Context context) {
        RemoteInput.Builder addExtras = new RemoteInput.Builder(this.resultKey).setAllowFreeFormInput(this.allowFreeFormInput).addExtras(this.extras);
        int[] iArr = this.choices;
        if (iArr != null) {
            CharSequence[] charSequenceArr = new CharSequence[iArr.length];
            int i = 0;
            while (true) {
                int[] iArr2 = this.choices;
                if (i >= iArr2.length) {
                    break;
                }
                charSequenceArr[i] = context.getText(iArr2[i]);
                i++;
            }
            addExtras.setChoices(charSequenceArr);
        }
        if (this.choicesArray != 0) {
            addExtras.setChoices(context.getResources().getStringArray(this.choicesArray));
        }
        int i2 = this.labelId;
        if (i2 != 0) {
            addExtras.setLabel(context.getText(i2));
        }
        return addExtras.build();
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean allowFreeFormInput = false;
        /* access modifiers changed from: private */
        public int[] choices;
        /* access modifiers changed from: private */
        public int choicesArray;
        /* access modifiers changed from: private */
        public final Bundle extras = new Bundle();
        /* access modifiers changed from: private */
        public int labelId;
        /* access modifiers changed from: private */
        public final String resultKey;

        public Builder(String str) {
            this.resultKey = str;
        }

        public Builder setLabel(int i) {
            this.labelId = i;
            return this;
        }

        public Builder setChoices(int i) {
            this.choices = null;
            this.choicesArray = i;
            return this;
        }

        public Builder setAllowFreeFormInput(boolean z) {
            this.allowFreeFormInput = z;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                this.extras.putAll(bundle);
            }
            return this;
        }

        public LocalizableRemoteInput build() {
            return new LocalizableRemoteInput(this);
        }
    }
}
