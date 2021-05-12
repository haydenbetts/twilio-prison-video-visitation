package com.jakewharton.rxbinding2.widget;

import android.widget.SeekBar;
import com.jakewharton.rxbinding2.InitialValueObservable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0003H\b¨\u0006\b"}, d2 = {"changeEvents", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "Lcom/jakewharton/rxbinding2/widget/SeekBarChangeEvent;", "Landroid/widget/SeekBar;", "changes", "", "systemChanges", "userChanges", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxSeekBar.kt */
public final class RxSeekBarKt {
    public static final InitialValueObservable<Integer> changes(SeekBar seekBar) {
        Intrinsics.checkParameterIsNotNull(seekBar, "$receiver");
        InitialValueObservable<Integer> changes = RxSeekBar.changes(seekBar);
        Intrinsics.checkExpressionValueIsNotNull(changes, "RxSeekBar.changes(this)");
        return changes;
    }

    public static final InitialValueObservable<Integer> userChanges(SeekBar seekBar) {
        Intrinsics.checkParameterIsNotNull(seekBar, "$receiver");
        InitialValueObservable<Integer> userChanges = RxSeekBar.userChanges(seekBar);
        Intrinsics.checkExpressionValueIsNotNull(userChanges, "RxSeekBar.userChanges(this)");
        return userChanges;
    }

    public static final InitialValueObservable<Integer> systemChanges(SeekBar seekBar) {
        Intrinsics.checkParameterIsNotNull(seekBar, "$receiver");
        InitialValueObservable<Integer> systemChanges = RxSeekBar.systemChanges(seekBar);
        Intrinsics.checkExpressionValueIsNotNull(systemChanges, "RxSeekBar.systemChanges(this)");
        return systemChanges;
    }

    public static final InitialValueObservable<SeekBarChangeEvent> changeEvents(SeekBar seekBar) {
        Intrinsics.checkParameterIsNotNull(seekBar, "$receiver");
        InitialValueObservable<SeekBarChangeEvent> changeEvents = RxSeekBar.changeEvents(seekBar);
        Intrinsics.checkExpressionValueIsNotNull(changeEvents, "RxSeekBar.changeEvents(this)");
        return changeEvents;
    }
}
