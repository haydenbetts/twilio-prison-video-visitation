package com.forasoft.homewavvisitor.ui.views;

import android.text.Editable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.anko.sdk15.listeners.__TextWatcher;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lorg/jetbrains/anko/sdk15/listeners/__TextWatcher;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddInmateView.kt */
final class AddInmateView$initAddInmateViews$4 extends Lambda implements Function1<__TextWatcher, Unit> {
    final /* synthetic */ AddInmateView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddInmateView$initAddInmateViews$4(AddInmateView addInmateView) {
        super(1);
        this.this$0 = addInmateView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((__TextWatcher) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(__TextWatcher __textwatcher) {
        Intrinsics.checkParameterIsNotNull(__textwatcher, "$receiver");
        __textwatcher.afterTextChanged((Function1<? super Editable, Unit>) new Function1<Editable, Unit>(this) {
            final /* synthetic */ AddInmateView$initAddInmateViews$4 this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Editable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Editable editable) {
                this.this$0.this$0.getConnection().setDateOfBirth(String.valueOf(editable));
                this.this$0.this$0.checkCompleteness();
            }
        });
    }
}
