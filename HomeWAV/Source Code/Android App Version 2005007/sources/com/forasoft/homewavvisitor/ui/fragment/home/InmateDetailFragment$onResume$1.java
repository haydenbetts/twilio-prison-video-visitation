package com.forasoft.homewavvisitor.ui.fragment.home;

import android.widget.CompoundButton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "isChecked", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateDetailFragment.kt */
final class InmateDetailFragment$onResume$1 extends Lambda implements Function2<CompoundButton, Boolean, Unit> {
    final /* synthetic */ InmateDetailFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InmateDetailFragment$onResume$1(InmateDetailFragment inmateDetailFragment) {
        super(2);
        this.this$0 = inmateDetailFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((CompoundButton) obj, ((Boolean) obj2).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(CompoundButton compoundButton, boolean z) {
        this.this$0.getPresenter().onAllowCallsClicked(z);
    }
}
