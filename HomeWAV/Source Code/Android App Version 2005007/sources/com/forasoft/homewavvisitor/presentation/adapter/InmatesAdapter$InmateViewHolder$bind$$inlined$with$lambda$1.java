package com.forasoft.homewavvisitor.presentation.adapter;

import android.view.View;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/forasoft/homewavvisitor/presentation/adapter/InmatesAdapter$InmateViewHolder$bind$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmatesAdapter.kt */
final class InmatesAdapter$InmateViewHolder$bind$$inlined$with$lambda$1 implements View.OnClickListener {
    final /* synthetic */ Inmate $inmate$inlined;
    final /* synthetic */ InmatesAdapter.InmateViewHolder this$0;

    InmatesAdapter$InmateViewHolder$bind$$inlined$with$lambda$1(InmatesAdapter.InmateViewHolder inmateViewHolder, Inmate inmate) {
        this.this$0 = inmateViewHolder;
        this.$inmate$inlined = inmate;
    }

    public final void onClick(View view) {
        this.this$0.onMessageClickListener.invoke(this.$inmate$inlined);
    }
}
