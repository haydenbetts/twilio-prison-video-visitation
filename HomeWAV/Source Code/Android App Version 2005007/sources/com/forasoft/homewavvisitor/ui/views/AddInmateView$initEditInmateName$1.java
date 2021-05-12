package com.forasoft.homewavvisitor.ui.views;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "hasFocus", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddInmateView.kt */
final class AddInmateView$initEditInmateName$1 extends Lambda implements Function2<View, Boolean, Unit> {
    final /* synthetic */ AddInmateView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddInmateView$initEditInmateName$1(AddInmateView addInmateView) {
        super(2);
        this.this$0 = addInmateView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((View) obj, ((Boolean) obj2).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 0>");
        if (!z) {
            this.this$0.getConnection().setName(this.this$0.getEditInmate().getText().toString());
        }
        this.this$0.getEditInmate().showDropDownIfFocused();
        this.this$0.checkCompleteness();
    }
}
