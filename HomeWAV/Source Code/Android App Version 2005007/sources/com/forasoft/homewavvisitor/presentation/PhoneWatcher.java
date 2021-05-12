package com.forasoft.homewavvisitor.presentation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\bH\u0017J*\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0013H\u0016J*\u0010 \u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0013H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/PhoneWatcher;", "Landroid/text/TextWatcher;", "editPhone", "Landroid/widget/EditText;", "(Landroid/widget/EditText;)V", "deleting", "", "previousText", "Landroid/text/Editable;", "getPreviousText", "()Landroid/text/Editable;", "setPreviousText", "(Landroid/text/Editable;)V", "skip", "getSkip", "()Z", "setSkip", "(Z)V", "start", "", "getStart", "()I", "setStart", "(I)V", "afterTextChanged", "", "text", "beforeTextChanged", "s", "", "count", "after", "onTextChanged", "before", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PhoneWatcher.kt */
public final class PhoneWatcher implements TextWatcher {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean deleting;
    private final EditText editPhone;
    private Editable previousText;
    private boolean skip;
    private int start;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/PhoneWatcher$Companion;", "", "()V", "formatPhone", "", "numbers", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: PhoneWatcher.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String formatPhone(CharSequence charSequence) {
            Intrinsics.checkParameterIsNotNull(charSequence, "numbers");
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            int lastIndex = StringsKt.getLastIndex(charSequence);
            if (lastIndex >= 0) {
                int i = 0;
                while (true) {
                    if (i == 3) {
                        sb.append(") ");
                    } else if (i == 6) {
                        sb.append("-");
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

    public PhoneWatcher(EditText editText) {
        Intrinsics.checkParameterIsNotNull(editText, "editPhone");
        this.editPhone = editText;
    }

    public final boolean getSkip() {
        return this.skip;
    }

    public final void setSkip(boolean z) {
        this.skip = z;
    }

    public final Editable getPreviousText() {
        return this.previousText;
    }

    public final void setPreviousText(Editable editable) {
        this.previousText = editable;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0081, code lost:
        if (r3 != 10) goto L_0x009d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void afterTextChanged(android.text.Editable r9) {
        /*
            r8 = this;
            android.text.Editable r0 = r8.previousText
            r1 = 0
            if (r0 == 0) goto L_0x000a
            java.lang.String r0 = r0.toString()
            goto L_0x000b
        L_0x000a:
            r0 = r1
        L_0x000b:
            if (r9 == 0) goto L_0x0011
            java.lang.String r1 = r9.toString()
        L_0x0011:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            if (r0 == 0) goto L_0x0018
            return
        L_0x0018:
            r8.previousText = r9
            if (r9 == 0) goto L_0x00e5
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            int r0 = r9.length()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0028
            r0 = 1
            goto L_0x0029
        L_0x0028:
            r0 = 0
        L_0x0029:
            if (r0 == 0) goto L_0x002c
            return
        L_0x002c:
            boolean r0 = r8.skip
            if (r0 == 0) goto L_0x0033
            r8.skip = r2
            return
        L_0x0033:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.Appendable r0 = (java.lang.Appendable) r0
            int r3 = r9.length()
            r4 = 0
        L_0x003f:
            if (r4 >= r3) goto L_0x0059
            char r5 = r9.charAt(r4)
            r6 = 57
            r7 = 48
            if (r7 <= r5) goto L_0x004c
            goto L_0x0050
        L_0x004c:
            if (r6 < r5) goto L_0x0050
            r6 = 1
            goto L_0x0051
        L_0x0050:
            r6 = 0
        L_0x0051:
            if (r6 == 0) goto L_0x0056
            r0.append(r5)
        L_0x0056:
            int r4 = r4 + 1
            goto L_0x003f
        L_0x0059:
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r9 = r8.start
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "afterTextChanged: focus starts "
            r3.append(r4)
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            java.lang.Object[] r4 = new java.lang.Object[r2]
            timber.log.Timber.d(r3, r4)
            boolean r3 = r8.deleting
            if (r3 == 0) goto L_0x008a
            int r3 = r8.start
            r4 = 5
            if (r3 == r4) goto L_0x0087
            r4 = 6
            if (r3 == r4) goto L_0x0084
            r4 = 10
            if (r3 == r4) goto L_0x0087
            goto L_0x009d
        L_0x0084:
            int r9 = r9 + -2
            goto L_0x009d
        L_0x0087:
            int r9 = r9 + -1
            goto L_0x009d
        L_0x008a:
            int r3 = r8.start
            if (r3 == 0) goto L_0x009b
            r4 = 4
            if (r3 == r4) goto L_0x0098
            r4 = 9
            if (r3 == r4) goto L_0x009b
            int r9 = r9 + 1
            goto L_0x009d
        L_0x0098:
            int r9 = r9 + 3
            goto L_0x009d
        L_0x009b:
            int r9 = r9 + 2
        L_0x009d:
            com.forasoft.homewavvisitor.presentation.PhoneWatcher$Companion r3 = Companion
            java.lang.String r0 = r3.formatPhone(r0)
            r8.skip = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "afterTextChanged: final text: "
            r1.append(r3)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r3 = new java.lang.Object[r2]
            timber.log.Timber.d(r1, r3)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "afterTextChanged: final selection "
            r1.append(r3)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r2]
            timber.log.Timber.d(r1, r2)
            android.widget.EditText r1 = r8.editPhone
            java.lang.String r0 = r0.toString()
            android.text.Editable r0 = com.forasoft.homewavvisitor.extension.CommonKt.toEditable(r0)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r1.setText(r0)
            android.widget.EditText r0 = r8.editPhone
            r0.setSelection(r9)
        L_0x00e5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.PhoneWatcher.afterTextChanged(android.text.Editable):void");
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
        StringBuilder sb = new StringBuilder();
        sb.append("BEFORE: length: ");
        sb.append(charSequence != null ? Integer.valueOf(charSequence.length()) : null);
        sb.append("; start: ");
        sb.append(i);
        sb.append("; count: ");
        sb.append(i2);
        sb.append("; after: ");
        sb.append(i3);
        Timber.d(sb.toString(), new Object[0]);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append("ON:     length: ");
        sb.append(charSequence != null ? Integer.valueOf(charSequence.length()) : null);
        sb.append("; start: ");
        sb.append(i);
        sb.append("; count: ");
        sb.append(i3);
        sb.append("; before: ");
        sb.append(i2);
        Timber.d(sb.toString(), new Object[0]);
    }
}
