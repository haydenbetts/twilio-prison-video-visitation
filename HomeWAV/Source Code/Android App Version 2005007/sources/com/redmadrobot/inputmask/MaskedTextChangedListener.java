package com.redmadrobot.inputmask;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy;
import com.redmadrobot.inputmask.helper.Mask;
import com.redmadrobot.inputmask.helper.RTLMask;
import com.redmadrobot.inputmask.model.CaretString;
import com.redmadrobot.inputmask.model.Notation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 `2\u00020\u00012\u00020\u0002:\u0002`aB\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB+\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\fB3\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000fB%\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013B/\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0014B9\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0015BA\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0016BI\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0019Bk\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0012\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u000e¢\u0006\u0002\u0010\u001dJ\u0006\u0010B\u001a\u00020,J\u0006\u0010C\u001a\u00020,J\u0012\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J*\u0010H\u001a\u00020E2\b\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010K\u001a\u00020,2\u0006\u0010L\u001a\u00020,2\u0006\u0010M\u001a\u00020,H\u0016J \u0010N\u001a\u00020,2\u0006\u0010O\u001a\u0002092\u0006\u0010P\u001a\u00020Q2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001e\u0010R\u001a\u0002092\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0012H\u0002J\u001a\u0010S\u001a\u00020E2\b\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010V\u001a\u00020\u000eH\u0016J(\u0010W\u001a\u00020E2\u0006\u0010P\u001a\u00020J2\u0006\u0010X\u001a\u00020,2\u0006\u0010Y\u001a\u00020,2\u0006\u0010L\u001a\u00020,H\u0016J\u0018\u0010Z\u001a\u0002092\u0006\u0010P\u001a\u00020Q2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010[\u001a\u00020\u0004J\u0012\u0010\\\u001a\u0004\u0018\u00010]2\u0006\u0010P\u001a\u00020\u0004H\u0016J\u0018\u0010\\\u001a\u00020]2\u0006\u0010P\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0006\u0010^\u001a\u00020,J\u0006\u0010_\u001a\u00020,R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u000e¢\u0006\u0002\n\u0000R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001f\"\u0004\b.\u0010!R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060/X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u0010\u0010\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0014\u00108\u001a\u0002098BX\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u001a\u0010\u001c\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010(\"\u0004\b=\u0010*R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A¨\u0006b"}, d2 = {"Lcom/redmadrobot/inputmask/MaskedTextChangedListener;", "Landroid/text/TextWatcher;", "Landroid/view/View$OnFocusChangeListener;", "format", "", "field", "Landroid/widget/EditText;", "(Ljava/lang/String;Landroid/widget/EditText;)V", "valueListener", "Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;", "(Ljava/lang/String;Landroid/widget/EditText;Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;)V", "listener", "(Ljava/lang/String;Landroid/widget/EditText;Landroid/text/TextWatcher;Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;)V", "autocomplete", "", "(Ljava/lang/String;ZLandroid/widget/EditText;Landroid/text/TextWatcher;Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;)V", "primaryFormat", "affineFormats", "", "(Ljava/lang/String;Ljava/util/List;Landroid/widget/EditText;)V", "(Ljava/lang/String;Ljava/util/List;Landroid/widget/EditText;Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;)V", "(Ljava/lang/String;Ljava/util/List;Landroid/widget/EditText;Landroid/text/TextWatcher;Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;)V", "(Ljava/lang/String;Ljava/util/List;ZLandroid/widget/EditText;Landroid/text/TextWatcher;Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;)V", "affinityCalculationStrategy", "Lcom/redmadrobot/inputmask/helper/AffinityCalculationStrategy;", "(Ljava/lang/String;Ljava/util/List;Lcom/redmadrobot/inputmask/helper/AffinityCalculationStrategy;ZLandroid/widget/EditText;Landroid/text/TextWatcher;Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;)V", "customNotations", "Lcom/redmadrobot/inputmask/model/Notation;", "rightToLeft", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/redmadrobot/inputmask/helper/AffinityCalculationStrategy;ZLandroid/widget/EditText;Landroid/text/TextWatcher;Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;Z)V", "getAffineFormats", "()Ljava/util/List;", "setAffineFormats", "(Ljava/util/List;)V", "getAffinityCalculationStrategy", "()Lcom/redmadrobot/inputmask/helper/AffinityCalculationStrategy;", "setAffinityCalculationStrategy", "(Lcom/redmadrobot/inputmask/helper/AffinityCalculationStrategy;)V", "afterText", "getAutocomplete", "()Z", "setAutocomplete", "(Z)V", "caretPosition", "", "getCustomNotations", "setCustomNotations", "Ljava/lang/ref/WeakReference;", "getListener", "()Landroid/text/TextWatcher;", "setListener", "(Landroid/text/TextWatcher;)V", "getPrimaryFormat", "()Ljava/lang/String;", "setPrimaryFormat", "(Ljava/lang/String;)V", "primaryMask", "Lcom/redmadrobot/inputmask/helper/Mask;", "getPrimaryMask", "()Lcom/redmadrobot/inputmask/helper/Mask;", "getRightToLeft", "setRightToLeft", "getValueListener", "()Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;", "setValueListener", "(Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;)V", "acceptableTextLength", "acceptableValueLength", "afterTextChanged", "", "edit", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "count", "after", "calculateAffinity", "mask", "text", "Lcom/redmadrobot/inputmask/model/CaretString;", "maskGetOrCreate", "onFocusChange", "view", "Landroid/view/View;", "hasFocus", "onTextChanged", "cursorPosition", "before", "pickMask", "placeholder", "setText", "Lcom/redmadrobot/inputmask/helper/Mask$Result;", "totalTextLength", "totalValueLength", "Companion", "ValueListener", "input-mask-android_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: MaskedTextChangedListener.kt */
public class MaskedTextChangedListener implements TextWatcher, View.OnFocusChangeListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private List<String> affineFormats;
    private AffinityCalculationStrategy affinityCalculationStrategy;
    private String afterText;
    private boolean autocomplete;
    private int caretPosition;
    private List<Notation> customNotations;
    private final WeakReference<EditText> field;
    private TextWatcher listener;
    private String primaryFormat;
    private boolean rightToLeft;
    private ValueListener valueListener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;", "", "onTextChanged", "", "maskFilled", "", "extractedValue", "", "formattedValue", "input-mask-android_release"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MaskedTextChangedListener.kt */
    public interface ValueListener {
        void onTextChanged(boolean z, String str, String str2);
    }

    public MaskedTextChangedListener(String str, List<String> list, List<Notation> list2, AffinityCalculationStrategy affinityCalculationStrategy2, boolean z, EditText editText, TextWatcher textWatcher, ValueListener valueListener2, boolean z2) {
        Intrinsics.checkParameterIsNotNull(str, "primaryFormat");
        Intrinsics.checkParameterIsNotNull(list, "affineFormats");
        Intrinsics.checkParameterIsNotNull(list2, "customNotations");
        Intrinsics.checkParameterIsNotNull(affinityCalculationStrategy2, "affinityCalculationStrategy");
        Intrinsics.checkParameterIsNotNull(editText, "field");
        this.primaryFormat = str;
        this.affineFormats = list;
        this.customNotations = list2;
        this.affinityCalculationStrategy = affinityCalculationStrategy2;
        this.autocomplete = z;
        this.listener = textWatcher;
        this.valueListener = valueListener2;
        this.rightToLeft = z2;
        this.afterText = "";
        this.field = new WeakReference<>(editText);
    }

    public final String getPrimaryFormat() {
        return this.primaryFormat;
    }

    public final void setPrimaryFormat(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.primaryFormat = str;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ MaskedTextChangedListener(java.lang.String r13, java.util.List r14, java.util.List r15, com.redmadrobot.inputmask.helper.AffinityCalculationStrategy r16, boolean r17, android.widget.EditText r18, android.text.TextWatcher r19, com.redmadrobot.inputmask.MaskedTextChangedListener.ValueListener r20, boolean r21, int r22, kotlin.jvm.internal.DefaultConstructorMarker r23) {
        /*
            r12 = this;
            r0 = r22
            r1 = r0 & 2
            if (r1 == 0) goto L_0x000c
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r4 = r1
            goto L_0x000d
        L_0x000c:
            r4 = r14
        L_0x000d:
            r1 = r0 & 4
            if (r1 == 0) goto L_0x0017
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r5 = r1
            goto L_0x0018
        L_0x0017:
            r5 = r15
        L_0x0018:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0020
            com.redmadrobot.inputmask.helper.AffinityCalculationStrategy r1 = com.redmadrobot.inputmask.helper.AffinityCalculationStrategy.WHOLE_STRING
            r6 = r1
            goto L_0x0022
        L_0x0020:
            r6 = r16
        L_0x0022:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0029
            r1 = 1
            r7 = 1
            goto L_0x002b
        L_0x0029:
            r7 = r17
        L_0x002b:
            r1 = r0 & 64
            r2 = 0
            if (r1 == 0) goto L_0x0035
            r1 = r2
            android.text.TextWatcher r1 = (android.text.TextWatcher) r1
            r9 = r1
            goto L_0x0037
        L_0x0035:
            r9 = r19
        L_0x0037:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0040
            r1 = r2
            com.redmadrobot.inputmask.MaskedTextChangedListener$ValueListener r1 = (com.redmadrobot.inputmask.MaskedTextChangedListener.ValueListener) r1
            r10 = r1
            goto L_0x0042
        L_0x0040:
            r10 = r20
        L_0x0042:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x0049
            r0 = 0
            r11 = 0
            goto L_0x004b
        L_0x0049:
            r11 = r21
        L_0x004b:
            r2 = r12
            r3 = r13
            r8 = r18
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redmadrobot.inputmask.MaskedTextChangedListener.<init>(java.lang.String, java.util.List, java.util.List, com.redmadrobot.inputmask.helper.AffinityCalculationStrategy, boolean, android.widget.EditText, android.text.TextWatcher, com.redmadrobot.inputmask.MaskedTextChangedListener$ValueListener, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final List<String> getAffineFormats() {
        return this.affineFormats;
    }

    public final void setAffineFormats(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.affineFormats = list;
    }

    public final List<Notation> getCustomNotations() {
        return this.customNotations;
    }

    public final void setCustomNotations(List<Notation> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.customNotations = list;
    }

    public final AffinityCalculationStrategy getAffinityCalculationStrategy() {
        return this.affinityCalculationStrategy;
    }

    public final void setAffinityCalculationStrategy(AffinityCalculationStrategy affinityCalculationStrategy2) {
        Intrinsics.checkParameterIsNotNull(affinityCalculationStrategy2, "<set-?>");
        this.affinityCalculationStrategy = affinityCalculationStrategy2;
    }

    public final boolean getAutocomplete() {
        return this.autocomplete;
    }

    public final void setAutocomplete(boolean z) {
        this.autocomplete = z;
    }

    public final TextWatcher getListener() {
        return this.listener;
    }

    public final void setListener(TextWatcher textWatcher) {
        this.listener = textWatcher;
    }

    public final ValueListener getValueListener() {
        return this.valueListener;
    }

    public final void setValueListener(ValueListener valueListener2) {
        this.valueListener = valueListener2;
    }

    public final boolean getRightToLeft() {
        return this.rightToLeft;
    }

    public final void setRightToLeft(boolean z) {
        this.rightToLeft = z;
    }

    private final Mask getPrimaryMask() {
        return maskGetOrCreate(this.primaryFormat, this.customNotations);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedTextChangedListener(String str, EditText editText) {
        this(str, editText, (ValueListener) null);
        Intrinsics.checkParameterIsNotNull(str, "format");
        Intrinsics.checkParameterIsNotNull(editText, "field");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedTextChangedListener(String str, EditText editText, ValueListener valueListener2) {
        this(str, editText, (TextWatcher) null, valueListener2);
        Intrinsics.checkParameterIsNotNull(str, "format");
        Intrinsics.checkParameterIsNotNull(editText, "field");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedTextChangedListener(String str, EditText editText, TextWatcher textWatcher, ValueListener valueListener2) {
        this(str, true, editText, textWatcher, valueListener2);
        Intrinsics.checkParameterIsNotNull(str, "format");
        Intrinsics.checkParameterIsNotNull(editText, "field");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaskedTextChangedListener(java.lang.String r14, boolean r15, android.widget.EditText r16, android.text.TextWatcher r17, com.redmadrobot.inputmask.MaskedTextChangedListener.ValueListener r18) {
        /*
            r13 = this;
            java.lang.String r0 = "format"
            r2 = r14
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
            java.lang.String r0 = "field"
            r7 = r16
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
            com.redmadrobot.inputmask.helper.AffinityCalculationStrategy r5 = com.redmadrobot.inputmask.helper.AffinityCalculationStrategy.WHOLE_STRING
            r10 = 0
            r11 = 256(0x100, float:3.59E-43)
            r12 = 0
            r1 = r13
            r6 = r15
            r8 = r17
            r9 = r18
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redmadrobot.inputmask.MaskedTextChangedListener.<init>(java.lang.String, boolean, android.widget.EditText, android.text.TextWatcher, com.redmadrobot.inputmask.MaskedTextChangedListener$ValueListener):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedTextChangedListener(String str, List<String> list, EditText editText) {
        this(str, list, editText, (ValueListener) null);
        Intrinsics.checkParameterIsNotNull(str, "primaryFormat");
        Intrinsics.checkParameterIsNotNull(list, "affineFormats");
        Intrinsics.checkParameterIsNotNull(editText, "field");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedTextChangedListener(String str, List<String> list, EditText editText, ValueListener valueListener2) {
        this(str, list, editText, (TextWatcher) null, valueListener2);
        Intrinsics.checkParameterIsNotNull(str, "primaryFormat");
        Intrinsics.checkParameterIsNotNull(list, "affineFormats");
        Intrinsics.checkParameterIsNotNull(editText, "field");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedTextChangedListener(String str, List<String> list, EditText editText, TextWatcher textWatcher, ValueListener valueListener2) {
        this(str, list, true, editText, textWatcher, valueListener2);
        Intrinsics.checkParameterIsNotNull(str, "primaryFormat");
        Intrinsics.checkParameterIsNotNull(list, "affineFormats");
        Intrinsics.checkParameterIsNotNull(editText, "field");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedTextChangedListener(String str, List<String> list, boolean z, EditText editText, TextWatcher textWatcher, ValueListener valueListener2) {
        this(str, list, AffinityCalculationStrategy.WHOLE_STRING, z, editText, textWatcher, valueListener2);
        Intrinsics.checkParameterIsNotNull(str, "primaryFormat");
        Intrinsics.checkParameterIsNotNull(list, "affineFormats");
        Intrinsics.checkParameterIsNotNull(editText, "field");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaskedTextChangedListener(java.lang.String r14, java.util.List<java.lang.String> r15, com.redmadrobot.inputmask.helper.AffinityCalculationStrategy r16, boolean r17, android.widget.EditText r18, android.text.TextWatcher r19, com.redmadrobot.inputmask.MaskedTextChangedListener.ValueListener r20) {
        /*
            r13 = this;
            java.lang.String r0 = "primaryFormat"
            r2 = r14
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
            java.lang.String r0 = "affineFormats"
            r3 = r15
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r0)
            java.lang.String r0 = "affinityCalculationStrategy"
            r5 = r16
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "field"
            r7 = r18
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
            r10 = 0
            r11 = 256(0x100, float:3.59E-43)
            r12 = 0
            r1 = r13
            r6 = r17
            r8 = r19
            r9 = r20
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redmadrobot.inputmask.MaskedTextChangedListener.<init>(java.lang.String, java.util.List, com.redmadrobot.inputmask.helper.AffinityCalculationStrategy, boolean, android.widget.EditText, android.text.TextWatcher, com.redmadrobot.inputmask.MaskedTextChangedListener$ValueListener):void");
    }

    public Mask.Result setText(String str) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        EditText editText = (EditText) this.field.get();
        if (editText == null) {
            return null;
        }
        Intrinsics.checkExpressionValueIsNotNull(editText, "it");
        Mask.Result text = setText(str, editText);
        this.afterText = text.getFormattedText().getString();
        this.caretPosition = text.getFormattedText().getCaretPosition();
        ValueListener valueListener2 = this.valueListener;
        if (valueListener2 != null) {
            valueListener2.onTextChanged(text.getComplete(), text.getExtractedValue(), this.afterText);
        }
        return text;
    }

    public Mask.Result setText(String str, EditText editText) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        Intrinsics.checkParameterIsNotNull(editText, "field");
        CaretString caretString = new CaretString(str, str.length(), CaretString.CaretGravity.FORWARD);
        Mask.Result apply = pickMask(caretString, this.autocomplete).apply(caretString, this.autocomplete);
        editText.setText(apply.getFormattedText().getString());
        editText.setSelection(apply.getFormattedText().getCaretPosition());
        return apply;
    }

    public final String placeholder() {
        return getPrimaryMask().placeholder();
    }

    public final int acceptableTextLength() {
        return getPrimaryMask().acceptableTextLength();
    }

    public final int totalTextLength() {
        return getPrimaryMask().totalTextLength();
    }

    public final int acceptableValueLength() {
        return getPrimaryMask().acceptableValueLength();
    }

    public final int totalValueLength() {
        return getPrimaryMask().totalValueLength();
    }

    public void afterTextChanged(Editable editable) {
        EditText editText = (EditText) this.field.get();
        if (editText != null) {
            editText.removeTextChangedListener(this);
        }
        if (editable != null) {
            editable.replace(0, editable.length(), this.afterText);
        }
        EditText editText2 = (EditText) this.field.get();
        if (editText2 != null) {
            editText2.setSelection(this.caretPosition);
        }
        EditText editText3 = (EditText) this.field.get();
        if (editText3 != null) {
            editText3.addTextChangedListener(this);
        }
        TextWatcher textWatcher = this.listener;
        if (textWatcher != null) {
            textWatcher.afterTextChanged(editable);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        TextWatcher textWatcher = this.listener;
        if (textWatcher != null) {
            textWatcher.beforeTextChanged(charSequence, i, i2, i3);
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "text");
        boolean z = false;
        boolean z2 = i2 > 0 && i3 == 0;
        if (!z2) {
            i += i3;
        }
        CaretString caretString = new CaretString(charSequence.toString(), i, z2 ? CaretString.CaretGravity.BACKWARD : CaretString.CaretGravity.FORWARD);
        if (!z2) {
            z = this.autocomplete;
        }
        Mask.Result apply = pickMask(caretString, z).apply(caretString, z);
        this.afterText = apply.getFormattedText().getString();
        this.caretPosition = apply.getFormattedText().getCaretPosition();
        ValueListener valueListener2 = this.valueListener;
        if (valueListener2 != null) {
            valueListener2.onTextChanged(apply.getComplete(), apply.getExtractedValue(), this.afterText);
        }
    }

    public void onFocusChange(View view, boolean z) {
        String str;
        if (this.autocomplete && z) {
            EditText editText = (EditText) this.field.get();
            Editable editable = null;
            Editable text = editText != null ? editText.getText() : null;
            if (text == null) {
                Intrinsics.throwNpe();
            }
            if (text.length() == 0) {
                str = "";
            } else {
                EditText editText2 = (EditText) this.field.get();
                if (editText2 != null) {
                    editable = editText2.getText();
                }
                str = String.valueOf(editable);
            }
            CaretString caretString = new CaretString(str, str.length(), CaretString.CaretGravity.FORWARD);
            Mask.Result apply = pickMask(caretString, this.autocomplete).apply(caretString, this.autocomplete);
            this.afterText = apply.getFormattedText().getString();
            this.caretPosition = apply.getFormattedText().getCaretPosition();
            EditText editText3 = (EditText) this.field.get();
            if (editText3 != null) {
                editText3.setText(this.afterText);
            }
            EditText editText4 = (EditText) this.field.get();
            if (editText4 != null) {
                editText4.setSelection(apply.getFormattedText().getCaretPosition());
            }
            ValueListener valueListener2 = this.valueListener;
            if (valueListener2 != null) {
                valueListener2.onTextChanged(apply.getComplete(), apply.getExtractedValue(), this.afterText);
            }
        }
    }

    private final Mask pickMask(CaretString caretString, boolean z) {
        if (this.affineFormats.isEmpty()) {
            return getPrimaryMask();
        }
        int calculateAffinity = calculateAffinity(getPrimaryMask(), caretString, z);
        List arrayList = new ArrayList();
        for (String maskGetOrCreate : this.affineFormats) {
            Mask maskGetOrCreate2 = maskGetOrCreate(maskGetOrCreate, this.customNotations);
            arrayList.add(new MaskedTextChangedListener$pickMask$MaskAffinity(maskGetOrCreate2, calculateAffinity(maskGetOrCreate2, caretString, z)));
        }
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new MaskedTextChangedListener$pickMask$$inlined$sortByDescending$1());
        }
        int i = -1;
        int i2 = 0;
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (calculateAffinity >= ((MaskedTextChangedListener$pickMask$MaskAffinity) it.next()).getAffinity()) {
                i = i2;
                break;
            } else {
                i2++;
            }
        }
        if (i >= 0) {
            arrayList.add(i, new MaskedTextChangedListener$pickMask$MaskAffinity(getPrimaryMask(), calculateAffinity));
        } else {
            arrayList.add(new MaskedTextChangedListener$pickMask$MaskAffinity(getPrimaryMask(), calculateAffinity));
        }
        return ((MaskedTextChangedListener$pickMask$MaskAffinity) CollectionsKt.first(arrayList)).getMask();
    }

    private final Mask maskGetOrCreate(String str, List<Notation> list) {
        if (this.rightToLeft) {
            return RTLMask.Factory.getOrCreate(str, list);
        }
        return Mask.Factory.getOrCreate(str, list);
    }

    private final int calculateAffinity(Mask mask, CaretString caretString, boolean z) {
        return this.affinityCalculationStrategy.calculateAffinityOfMask(mask, caretString, z);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nJ<\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nJb\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u0015"}, d2 = {"Lcom/redmadrobot/inputmask/MaskedTextChangedListener$Companion;", "", "()V", "installOn", "Lcom/redmadrobot/inputmask/MaskedTextChangedListener;", "editText", "Landroid/widget/EditText;", "primaryFormat", "", "valueListener", "Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;", "affineFormats", "", "affinityCalculationStrategy", "Lcom/redmadrobot/inputmask/helper/AffinityCalculationStrategy;", "customNotations", "Lcom/redmadrobot/inputmask/model/Notation;", "autocomplete", "", "listener", "Landroid/text/TextWatcher;", "input-mask-android_release"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MaskedTextChangedListener.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ MaskedTextChangedListener installOn$default(Companion companion, EditText editText, String str, ValueListener valueListener, int i, Object obj) {
            if ((i & 4) != 0) {
                valueListener = null;
            }
            return companion.installOn(editText, str, valueListener);
        }

        public final MaskedTextChangedListener installOn(EditText editText, String str, ValueListener valueListener) {
            Intrinsics.checkParameterIsNotNull(editText, "editText");
            Intrinsics.checkParameterIsNotNull(str, "primaryFormat");
            return installOn(editText, str, CollectionsKt.emptyList(), AffinityCalculationStrategy.WHOLE_STRING, valueListener);
        }

        public static /* synthetic */ MaskedTextChangedListener installOn$default(Companion companion, EditText editText, String str, List list, AffinityCalculationStrategy affinityCalculationStrategy, ValueListener valueListener, int i, Object obj) {
            if ((i & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            List list2 = list;
            if ((i & 8) != 0) {
                affinityCalculationStrategy = AffinityCalculationStrategy.WHOLE_STRING;
            }
            AffinityCalculationStrategy affinityCalculationStrategy2 = affinityCalculationStrategy;
            if ((i & 16) != 0) {
                valueListener = null;
            }
            return companion.installOn(editText, str, list2, affinityCalculationStrategy2, valueListener);
        }

        public final MaskedTextChangedListener installOn(EditText editText, String str, List<String> list, AffinityCalculationStrategy affinityCalculationStrategy, ValueListener valueListener) {
            Intrinsics.checkParameterIsNotNull(editText, "editText");
            Intrinsics.checkParameterIsNotNull(str, "primaryFormat");
            Intrinsics.checkParameterIsNotNull(list, "affineFormats");
            Intrinsics.checkParameterIsNotNull(affinityCalculationStrategy, "affinityCalculationStrategy");
            return installOn(editText, str, list, CollectionsKt.emptyList(), affinityCalculationStrategy, true, (TextWatcher) null, valueListener);
        }

        public static /* synthetic */ MaskedTextChangedListener installOn$default(Companion companion, EditText editText, String str, List list, List list2, AffinityCalculationStrategy affinityCalculationStrategy, boolean z, TextWatcher textWatcher, ValueListener valueListener, int i, Object obj) {
            int i2 = i;
            return companion.installOn(editText, str, (i2 & 4) != 0 ? CollectionsKt.emptyList() : list, (i2 & 8) != 0 ? CollectionsKt.emptyList() : list2, (i2 & 16) != 0 ? AffinityCalculationStrategy.WHOLE_STRING : affinityCalculationStrategy, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? null : textWatcher, (i2 & 128) != 0 ? null : valueListener);
        }

        public final MaskedTextChangedListener installOn(EditText editText, String str, List<String> list, List<Notation> list2, AffinityCalculationStrategy affinityCalculationStrategy, boolean z, TextWatcher textWatcher, ValueListener valueListener) {
            EditText editText2 = editText;
            Intrinsics.checkParameterIsNotNull(editText, "editText");
            String str2 = str;
            Intrinsics.checkParameterIsNotNull(str2, "primaryFormat");
            List<String> list3 = list;
            Intrinsics.checkParameterIsNotNull(list3, "affineFormats");
            List<Notation> list4 = list2;
            Intrinsics.checkParameterIsNotNull(list4, "customNotations");
            AffinityCalculationStrategy affinityCalculationStrategy2 = affinityCalculationStrategy;
            Intrinsics.checkParameterIsNotNull(affinityCalculationStrategy2, "affinityCalculationStrategy");
            MaskedTextChangedListener maskedTextChangedListener = new MaskedTextChangedListener(str2, list3, list4, affinityCalculationStrategy2, z, editText, textWatcher, valueListener, false, 256, (DefaultConstructorMarker) null);
            editText.addTextChangedListener(maskedTextChangedListener);
            editText.setOnFocusChangeListener(maskedTextChangedListener);
            return maskedTextChangedListener;
        }
    }
}
