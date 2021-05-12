package com.forasoft.homewavvisitor.ui.views;

import air.HomeWAV.R;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "position", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddInmateView.kt */
final class AddInmateView$initStateSpinner$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ AddInmateView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddInmateView$initStateSpinner$1(AddInmateView addInmateView) {
        super(1);
        this.this$0 = addInmateView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        if (!Intrinsics.areEqual(this.this$0.getSpinnerState().getSelectedItem(), (Object) this.this$0.getContext().getString(R.string.placeholder_select_state))) {
            AddInmateView addInmateView = this.this$0;
            int i2 = 0;
            Iterator<String> it = addInmateView.getConstants().getStatesList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                } else if (Intrinsics.areEqual((Object) it.next(), this.this$0.getSpinnerState().getSelectedItem())) {
                    break;
                } else {
                    i2++;
                }
            }
            addInmateView.setSelectedState(i2);
            this.this$0.getConnection().setState(this.this$0.getSelectedState());
            this.this$0.getConnection().setStateName(this.this$0.getConstants().getStatesList().get(this.this$0.getSelectedState()));
            this.this$0.checkCompleteness();
            this.this$0.filterFacilities();
            this.this$0.clearEditInmate();
        }
    }
}
