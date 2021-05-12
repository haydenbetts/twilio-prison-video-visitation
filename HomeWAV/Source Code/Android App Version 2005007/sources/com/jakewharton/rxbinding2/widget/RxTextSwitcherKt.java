package com.jakewharton.rxbinding2.widget;

import android.widget.TextSwitcher;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0015\u0010\u0004\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b¨\u0006\u0005"}, d2 = {"currentText", "Lio/reactivex/functions/Consumer;", "", "Landroid/widget/TextSwitcher;", "text", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxTextSwitcher.kt */
public final class RxTextSwitcherKt {
    public static final Consumer<? super CharSequence> text(TextSwitcher textSwitcher) {
        Intrinsics.checkParameterIsNotNull(textSwitcher, "$receiver");
        Consumer<? super CharSequence> text = RxTextSwitcher.text(textSwitcher);
        Intrinsics.checkExpressionValueIsNotNull(text, "RxTextSwitcher.text(this)");
        return text;
    }

    public static final Consumer<? super CharSequence> currentText(TextSwitcher textSwitcher) {
        Intrinsics.checkParameterIsNotNull(textSwitcher, "$receiver");
        Consumer<? super CharSequence> currentText = RxTextSwitcher.currentText(textSwitcher);
        Intrinsics.checkExpressionValueIsNotNull(currentText, "RxTextSwitcher.currentText(this)");
        return currentText;
    }
}
