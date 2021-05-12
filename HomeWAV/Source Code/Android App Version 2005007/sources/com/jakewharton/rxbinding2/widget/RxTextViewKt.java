package com.jakewharton.rxbinding2.widget;

import android.widget.TextView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0003H\b\u001a\u0015\u0010\u0006\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u0007*\u00020\u0003H\b\u001a\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u00020\u0003H\b\u001a#\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u00020\u00032\u000e\u0010\f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u000b0\rH\b\u001a\u0013\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\n*\u00020\u0003H\b\u001a#\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\n*\u00020\u00032\u000e\u0010\f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\rH\b\u001a\u0015\u0010\u000f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00100\u0007*\u00020\u0003H\b\u001a\u0015\u0010\u0011\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u0007*\u00020\u0003H\b\u001a\u0015\u0010\u0012\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00100\u0007*\u00020\u0003H\b\u001a\u0015\u0010\u0013\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u0007*\u00020\u0003H\b\u001a\u0015\u0010\u0014\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00100\u0007*\u00020\u0003H\b\u001a\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u0001*\u00020\u0003H\b\u001a\u0015\u0010\u0018\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u0007*\u00020\u0003H\b¨\u0006\u0019"}, d2 = {"afterTextChangeEvents", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "Lcom/jakewharton/rxbinding2/widget/TextViewAfterTextChangeEvent;", "Landroid/widget/TextView;", "beforeTextChangeEvents", "Lcom/jakewharton/rxbinding2/widget/TextViewBeforeTextChangeEvent;", "color", "Lio/reactivex/functions/Consumer;", "", "editorActionEvents", "Lio/reactivex/Observable;", "Lcom/jakewharton/rxbinding2/widget/TextViewEditorActionEvent;", "handled", "Lio/reactivex/functions/Predicate;", "editorActions", "error", "", "errorRes", "hint", "hintRes", "text", "textChangeEvents", "Lcom/jakewharton/rxbinding2/widget/TextViewTextChangeEvent;", "textChanges", "textRes", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxTextView.kt */
public final class RxTextViewKt {
    public static final Observable<Integer> editorActions(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Observable<Integer> editorActions = RxTextView.editorActions(textView);
        Intrinsics.checkExpressionValueIsNotNull(editorActions, "RxTextView.editorActions(this)");
        return editorActions;
    }

    public static final Observable<Integer> editorActions(TextView textView, Predicate<? super Integer> predicate) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<Integer> editorActions = RxTextView.editorActions(textView, predicate);
        Intrinsics.checkExpressionValueIsNotNull(editorActions, "RxTextView.editorActions(this, handled)");
        return editorActions;
    }

    public static final Observable<TextViewEditorActionEvent> editorActionEvents(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Observable<TextViewEditorActionEvent> editorActionEvents = RxTextView.editorActionEvents(textView);
        Intrinsics.checkExpressionValueIsNotNull(editorActionEvents, "RxTextView.editorActionEvents(this)");
        return editorActionEvents;
    }

    public static final Observable<TextViewEditorActionEvent> editorActionEvents(TextView textView, Predicate<? super TextViewEditorActionEvent> predicate) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<TextViewEditorActionEvent> editorActionEvents = RxTextView.editorActionEvents(textView, predicate);
        Intrinsics.checkExpressionValueIsNotNull(editorActionEvents, "RxTextView.editorActionEvents(this, handled)");
        return editorActionEvents;
    }

    public static final InitialValueObservable<CharSequence> textChanges(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        InitialValueObservable<CharSequence> textChanges = RxTextView.textChanges(textView);
        Intrinsics.checkExpressionValueIsNotNull(textChanges, "RxTextView.textChanges(this)");
        return textChanges;
    }

    public static final InitialValueObservable<TextViewTextChangeEvent> textChangeEvents(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        InitialValueObservable<TextViewTextChangeEvent> textChangeEvents = RxTextView.textChangeEvents(textView);
        Intrinsics.checkExpressionValueIsNotNull(textChangeEvents, "RxTextView.textChangeEvents(this)");
        return textChangeEvents;
    }

    public static final InitialValueObservable<TextViewBeforeTextChangeEvent> beforeTextChangeEvents(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        InitialValueObservable<TextViewBeforeTextChangeEvent> beforeTextChangeEvents = RxTextView.beforeTextChangeEvents(textView);
        Intrinsics.checkExpressionValueIsNotNull(beforeTextChangeEvents, "RxTextView.beforeTextChangeEvents(this)");
        return beforeTextChangeEvents;
    }

    public static final InitialValueObservable<TextViewAfterTextChangeEvent> afterTextChangeEvents(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        InitialValueObservable<TextViewAfterTextChangeEvent> afterTextChangeEvents = RxTextView.afterTextChangeEvents(textView);
        Intrinsics.checkExpressionValueIsNotNull(afterTextChangeEvents, "RxTextView.afterTextChangeEvents(this)");
        return afterTextChangeEvents;
    }

    public static final Consumer<? super CharSequence> text(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Consumer<? super CharSequence> text = RxTextView.text(textView);
        Intrinsics.checkExpressionValueIsNotNull(text, "RxTextView.text(this)");
        return text;
    }

    public static final Consumer<? super Integer> textRes(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Consumer<? super Integer> textRes = RxTextView.textRes(textView);
        Intrinsics.checkExpressionValueIsNotNull(textRes, "RxTextView.textRes(this)");
        return textRes;
    }

    public static final Consumer<? super CharSequence> error(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Consumer<? super CharSequence> error = RxTextView.error(textView);
        Intrinsics.checkExpressionValueIsNotNull(error, "RxTextView.error(this)");
        return error;
    }

    public static final Consumer<? super Integer> errorRes(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Consumer<? super Integer> errorRes = RxTextView.errorRes(textView);
        Intrinsics.checkExpressionValueIsNotNull(errorRes, "RxTextView.errorRes(this)");
        return errorRes;
    }

    public static final Consumer<? super CharSequence> hint(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Consumer<? super CharSequence> hint = RxTextView.hint(textView);
        Intrinsics.checkExpressionValueIsNotNull(hint, "RxTextView.hint(this)");
        return hint;
    }

    public static final Consumer<? super Integer> hintRes(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Consumer<? super Integer> hintRes = RxTextView.hintRes(textView);
        Intrinsics.checkExpressionValueIsNotNull(hintRes, "RxTextView.hintRes(this)");
        return hintRes;
    }

    public static final Consumer<? super Integer> color(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        Consumer<? super Integer> color = RxTextView.color(textView);
        Intrinsics.checkExpressionValueIsNotNull(color, "RxTextView.color(this)");
        return color;
    }
}
