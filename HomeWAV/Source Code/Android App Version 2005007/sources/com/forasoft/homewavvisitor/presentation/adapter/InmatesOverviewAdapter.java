package com.forasoft.homewavvisitor.presentation.adapter;

import air.HomeWAV.R;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0010B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesOverviewAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesOverviewAdapter$InmateVH;", "itemClickListener", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "InmateVH", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmatesOverviewAdapter.kt */
public final class InmatesOverviewAdapter extends ListAdapter<Inmate, InmateVH> {
    /* access modifiers changed from: private */
    public final Function1<Inmate, Unit> itemClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InmatesOverviewAdapter(Function1<? super Inmate, Unit> function1) {
        super(InmatesAdapter.INMATE_COMPARATOR.INSTANCE);
        Intrinsics.checkParameterIsNotNull(function1, "itemClickListener");
        this.itemClickListener = function1;
    }

    public InmateVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new InmateVH(this, ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_inmate_overview, false, 2, (Object) null));
    }

    public void onBindViewHolder(InmateVH inmateVH, int i) {
        Intrinsics.checkParameterIsNotNull(inmateVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        inmateVH.bindInmate((Inmate) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesOverviewAdapter$InmateVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesOverviewAdapter;Landroid/view/View;)V", "bindInmate", "", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: InmatesOverviewAdapter.kt */
    public final class InmateVH extends RecyclerView.ViewHolder {
        final /* synthetic */ InmatesOverviewAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InmateVH(InmatesOverviewAdapter inmatesOverviewAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = inmatesOverviewAdapter;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00a0  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bindInmate(com.forasoft.homewavvisitor.model.data.Inmate r11) {
            /*
                r10 = this;
                java.lang.String r0 = "inmate"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
                android.view.View r0 = r10.itemView
                java.lang.String r1 = r11.getStatus()
                if (r1 != 0) goto L_0x000e
                goto L_0x0031
            L_0x000e:
                int r2 = r1.hashCode()
                r3 = 112785(0x1b891, float:1.58045E-40)
                if (r2 == r3) goto L_0x0027
                r3 = 98619139(0x5e0cf03, float:2.1140903E-35)
                if (r2 == r3) goto L_0x001d
                goto L_0x0031
            L_0x001d:
                java.lang.String r2 = "green"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0031
                r1 = 2
                goto L_0x0032
            L_0x0027:
                java.lang.String r2 = "red"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0031
                r1 = 1
                goto L_0x0032
            L_0x0031:
                r1 = 0
            L_0x0032:
                java.lang.String r2 = r11.getFull_name()
                if (r2 != 0) goto L_0x003b
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L_0x003b:
                java.lang.String r4 = com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt.getAsInitials(r2)
                android.content.Context r3 = r0.getContext()
                java.lang.String r2 = "context"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r2)
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 14
                r9 = 0
                com.amulyakhare.textdrawable.TextDrawable r2 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r3, r4, r5, r6, r7, r8, r9)
                int r3 = com.forasoft.homewavvisitor.R.id.iv_avatar
                android.view.View r3 = r0.findViewById(r3)
                android.widget.ImageView r3 = (android.widget.ImageView) r3
                android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
                r3.setImageDrawable(r2)
                int r2 = com.forasoft.homewavvisitor.R.id.iv_status
                android.view.View r2 = r0.findViewById(r2)
                android.widget.ImageView r2 = (android.widget.ImageView) r2
                java.lang.String r3 = "iv_status"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                android.graphics.drawable.Drawable r2 = r2.getBackground()
                if (r2 == 0) goto L_0x00a0
                android.graphics.drawable.LevelListDrawable r2 = (android.graphics.drawable.LevelListDrawable) r2
                r2.setLevel(r1)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_full_name
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r2 = "tv_full_name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                java.lang.String r2 = r11.getFull_name()
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                com.forasoft.homewavvisitor.presentation.adapter.InmatesOverviewAdapter$InmateVH$bindInmate$$inlined$with$lambda$1 r1 = new com.forasoft.homewavvisitor.presentation.adapter.InmatesOverviewAdapter$InmateVH$bindInmate$$inlined$with$lambda$1
                r1.<init>(r10, r11)
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                com.forasoft.homewavvisitor.presentation.adapter.InmatesOverviewAdapter$InmateVH$inlined$sam$i$android_view_View_OnClickListener$0 r11 = new com.forasoft.homewavvisitor.presentation.adapter.InmatesOverviewAdapter$InmateVH$inlined$sam$i$android_view_View_OnClickListener$0
                r11.<init>(r1)
                android.view.View$OnClickListener r11 = (android.view.View.OnClickListener) r11
                r0.setOnClickListener(r11)
                return
            L_0x00a0:
                kotlin.TypeCastException r11 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
                r11.<init>(r0)
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.InmatesOverviewAdapter.InmateVH.bindInmate(com.forasoft.homewavvisitor.model.data.Inmate):void");
        }
    }
}
