package com.forasoft.homewavvisitor.presentation.adapter.visits;

import android.view.View;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/forasoft/homewavvisitor/presentation/adapter/visits/VisitVH$bindVisit$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: VisitVH.kt */
final class VisitVH$bindVisit$$inlined$with$lambda$1 implements View.OnClickListener {
    final /* synthetic */ ScheduledVisit $visit$inlined;
    final /* synthetic */ VisitVH this$0;

    VisitVH$bindVisit$$inlined$with$lambda$1(VisitVH visitVH, ScheduledVisit scheduledVisit) {
        this.this$0 = visitVH;
        this.$visit$inlined = scheduledVisit;
    }

    public final void onClick(View view) {
        this.this$0.onClickListener.invoke(this.$visit$inlined);
    }
}
