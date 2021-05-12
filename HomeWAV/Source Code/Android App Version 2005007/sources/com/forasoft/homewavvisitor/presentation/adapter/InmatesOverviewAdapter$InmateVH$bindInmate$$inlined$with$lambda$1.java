package com.forasoft.homewavvisitor.presentation.adapter;

import android.view.View;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.adapter.InmatesOverviewAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke", "com/forasoft/homewavvisitor/presentation/adapter/InmatesOverviewAdapter$InmateVH$bindInmate$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmatesOverviewAdapter.kt */
final class InmatesOverviewAdapter$InmateVH$bindInmate$$inlined$with$lambda$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ Inmate $inmate$inlined;
    final /* synthetic */ InmatesOverviewAdapter.InmateVH this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InmatesOverviewAdapter$InmateVH$bindInmate$$inlined$with$lambda$1(InmatesOverviewAdapter.InmateVH inmateVH, Inmate inmate) {
        super(1);
        this.this$0 = inmateVH;
        this.$inmate$inlined = inmate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        this.this$0.this$0.itemClickListener.invoke(this.$inmate$inlined);
    }
}
