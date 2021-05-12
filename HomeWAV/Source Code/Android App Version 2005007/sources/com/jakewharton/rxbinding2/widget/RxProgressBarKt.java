package com.jakewharton.rxbinding2.widget;

import android.widget.ProgressBar;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0015\u0010\u0004\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0015\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00060\u0001*\u00020\u0003H\b\u001a\u0015\u0010\u0007\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0015\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0015\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b¨\u0006\n"}, d2 = {"incrementProgressBy", "Lio/reactivex/functions/Consumer;", "", "Landroid/widget/ProgressBar;", "incrementSecondaryProgressBy", "indeterminate", "", "max", "progress", "secondaryProgress", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxProgressBar.kt */
public final class RxProgressBarKt {
    public static final Consumer<? super Integer> incrementProgressBy(ProgressBar progressBar) {
        Intrinsics.checkParameterIsNotNull(progressBar, "$receiver");
        Consumer<? super Integer> incrementProgressBy = RxProgressBar.incrementProgressBy(progressBar);
        Intrinsics.checkExpressionValueIsNotNull(incrementProgressBy, "RxProgressBar.incrementProgressBy(this)");
        return incrementProgressBy;
    }

    public static final Consumer<? super Integer> incrementSecondaryProgressBy(ProgressBar progressBar) {
        Intrinsics.checkParameterIsNotNull(progressBar, "$receiver");
        Consumer<? super Integer> incrementSecondaryProgressBy = RxProgressBar.incrementSecondaryProgressBy(progressBar);
        Intrinsics.checkExpressionValueIsNotNull(incrementSecondaryProgressBy, "RxProgressBar.incrementSecondaryProgressBy(this)");
        return incrementSecondaryProgressBy;
    }

    public static final Consumer<? super Boolean> indeterminate(ProgressBar progressBar) {
        Intrinsics.checkParameterIsNotNull(progressBar, "$receiver");
        Consumer<? super Boolean> indeterminate = RxProgressBar.indeterminate(progressBar);
        Intrinsics.checkExpressionValueIsNotNull(indeterminate, "RxProgressBar.indeterminate(this)");
        return indeterminate;
    }

    public static final Consumer<? super Integer> max(ProgressBar progressBar) {
        Intrinsics.checkParameterIsNotNull(progressBar, "$receiver");
        Consumer<? super Integer> max = RxProgressBar.max(progressBar);
        Intrinsics.checkExpressionValueIsNotNull(max, "RxProgressBar.max(this)");
        return max;
    }

    public static final Consumer<? super Integer> progress(ProgressBar progressBar) {
        Intrinsics.checkParameterIsNotNull(progressBar, "$receiver");
        Consumer<? super Integer> progress = RxProgressBar.progress(progressBar);
        Intrinsics.checkExpressionValueIsNotNull(progress, "RxProgressBar.progress(this)");
        return progress;
    }

    public static final Consumer<? super Integer> secondaryProgress(ProgressBar progressBar) {
        Intrinsics.checkParameterIsNotNull(progressBar, "$receiver");
        Consumer<? super Integer> secondaryProgress = RxProgressBar.secondaryProgress(progressBar);
        Intrinsics.checkExpressionValueIsNotNull(secondaryProgress, "RxProgressBar.secondaryProgress(this)");
        return secondaryProgress;
    }
}
