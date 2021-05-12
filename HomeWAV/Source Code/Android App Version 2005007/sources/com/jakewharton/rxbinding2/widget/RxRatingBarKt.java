package com.jakewharton.rxbinding2.widget;

import android.widget.RatingBar;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0015\u0010\u0004\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00050\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u0003H\b\u001a\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007*\u00020\u0003H\b¨\u0006\n"}, d2 = {"isIndicator", "Lio/reactivex/functions/Consumer;", "", "Landroid/widget/RatingBar;", "rating", "", "ratingChangeEvents", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "Lcom/jakewharton/rxbinding2/widget/RatingBarChangeEvent;", "ratingChanges", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxRatingBar.kt */
public final class RxRatingBarKt {
    public static final InitialValueObservable<Float> ratingChanges(RatingBar ratingBar) {
        Intrinsics.checkParameterIsNotNull(ratingBar, "$receiver");
        InitialValueObservable<Float> ratingChanges = RxRatingBar.ratingChanges(ratingBar);
        Intrinsics.checkExpressionValueIsNotNull(ratingChanges, "RxRatingBar.ratingChanges(this)");
        return ratingChanges;
    }

    public static final InitialValueObservable<RatingBarChangeEvent> ratingChangeEvents(RatingBar ratingBar) {
        Intrinsics.checkParameterIsNotNull(ratingBar, "$receiver");
        InitialValueObservable<RatingBarChangeEvent> ratingChangeEvents = RxRatingBar.ratingChangeEvents(ratingBar);
        Intrinsics.checkExpressionValueIsNotNull(ratingChangeEvents, "RxRatingBar.ratingChangeEvents(this)");
        return ratingChangeEvents;
    }

    public static final Consumer<? super Float> rating(RatingBar ratingBar) {
        Intrinsics.checkParameterIsNotNull(ratingBar, "$receiver");
        Consumer<? super Float> rating = RxRatingBar.rating(ratingBar);
        Intrinsics.checkExpressionValueIsNotNull(rating, "RxRatingBar.rating(this)");
        return rating;
    }

    public static final Consumer<? super Boolean> isIndicator(RatingBar ratingBar) {
        Intrinsics.checkParameterIsNotNull(ratingBar, "$receiver");
        Consumer<? super Boolean> isIndicator = RxRatingBar.isIndicator(ratingBar);
        Intrinsics.checkExpressionValueIsNotNull(isIndicator, "RxRatingBar.isIndicator(this)");
        return isIndicator;
    }
}
