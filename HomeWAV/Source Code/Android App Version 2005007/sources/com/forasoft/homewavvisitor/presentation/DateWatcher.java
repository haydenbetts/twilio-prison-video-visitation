package com.forasoft.homewavvisitor.presentation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0017J*\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\rH\u0016J*\u0010\u001b\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/DateWatcher;", "Landroid/text/TextWatcher;", "editDateOfBirth", "Landroid/widget/EditText;", "(Landroid/widget/EditText;)V", "deleting", "", "skip", "getSkip", "()Z", "setSkip", "(Z)V", "start", "", "getStart", "()I", "setStart", "(I)V", "afterTextChanged", "", "text", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "count", "after", "onTextChanged", "before", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DateWatcher.kt */
public final class DateWatcher implements TextWatcher {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean deleting;
    private final EditText editDateOfBirth;
    private boolean skip;
    private int start;

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public DateWatcher(EditText editText) {
        Intrinsics.checkParameterIsNotNull(editText, "editDateOfBirth");
        this.editDateOfBirth = editText;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/DateWatcher$Companion;", "", "()V", "formatDate", "", "numbers", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DateWatcher.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String formatDate(CharSequence charSequence) {
            Intrinsics.checkParameterIsNotNull(charSequence, "numbers");
            StringBuilder sb = new StringBuilder();
            int lastIndex = StringsKt.getLastIndex(charSequence);
            if (lastIndex >= 0) {
                int i = 0;
                while (true) {
                    if (i == 2 || i == 4) {
                        sb.append("/");
                    }
                    sb.append(charSequence.charAt(i));
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "formattedPhone.toString()");
            return sb2;
        }
    }

    public final boolean getSkip() {
        return this.skip;
    }

    public final void setSkip(boolean z) {
        this.skip = z;
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null) {
            CharSequence charSequence = editable;
            if (!(charSequence.length() == 0)) {
                if (this.skip) {
                    this.skip = false;
                    return;
                }
                Appendable sb = new StringBuilder();
                int length = charSequence.length();
                for (int i = 0; i < length; i++) {
                    char charAt = charSequence.charAt(i);
                    if ('0' <= charAt && '9' >= charAt) {
                        sb.append(charAt);
                    }
                }
                CharSequence charSequence2 = (CharSequence) sb;
                int i2 = this.start;
                if (!this.deleting) {
                    i2 = (i2 == 2 || i2 == 5) ? i2 + 2 : i2 + 1;
                } else if (i2 == 3 || i2 == 6) {
                    i2--;
                }
                String formatDate = Companion.formatDate(charSequence2);
                this.skip = true;
                this.editDateOfBirth.setText(CommonKt.toEditable(formatDate.toString()));
                this.editDateOfBirth.setSelection(i2);
            }
        }
    }

    public final int getStart() {
        return this.start;
    }

    public final void setStart(int i) {
        this.start = i;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.deleting = i3 < i2;
        this.start = i;
    }
}
