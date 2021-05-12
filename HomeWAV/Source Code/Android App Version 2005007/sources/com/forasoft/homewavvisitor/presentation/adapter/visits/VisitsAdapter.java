package com.forasoft.homewavvisitor.presentation.adapter.visits;

import air.HomeWAV.R;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0010B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/visits/VisitsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "Lcom/forasoft/homewavvisitor/presentation/adapter/visits/VisitVH;", "onClickListener", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitsAdapter.kt */
public final class VisitsAdapter extends ListAdapter<ScheduledVisit, VisitVH> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final DiffUtil.ItemCallback<ScheduledVisit> VISIT_COMPARATOR = new VisitsAdapter$Companion$VISIT_COMPARATOR$1();
    private final Function1<ScheduledVisit, Unit> onClickListener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/visits/VisitsAdapter$Companion;", "", "()V", "VISIT_COMPARATOR", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "getVISIT_COMPARATOR", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: VisitsAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DiffUtil.ItemCallback<ScheduledVisit> getVISIT_COMPARATOR() {
            return VisitsAdapter.VISIT_COMPARATOR;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VisitsAdapter(Function1<? super ScheduledVisit, Unit> function1) {
        super(VISIT_COMPARATOR);
        Intrinsics.checkParameterIsNotNull(function1, "onClickListener");
        this.onClickListener = function1;
    }

    public VisitVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new VisitVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_visit, false, 2, (Object) null), this.onClickListener);
    }

    public void onBindViewHolder(VisitVH visitVH, int i) {
        Intrinsics.checkParameterIsNotNull(visitVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        visitVH.bindVisit((ScheduledVisit) item);
    }
}
