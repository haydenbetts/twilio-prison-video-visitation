package com.forasoft.homewavvisitor.presentation.adapter.account;

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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0010B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/InmatesWithBalanceAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "Lcom/forasoft/homewavvisitor/presentation/adapter/account/InmatesWithBalanceAdapter$InmateVH;", "itemClickListener", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "InmateVH", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmatesWithBalanceAdapter.kt */
public final class InmatesWithBalanceAdapter extends ListAdapter<Inmate, InmateVH> {
    /* access modifiers changed from: private */
    public final Function1<Inmate, Unit> itemClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InmatesWithBalanceAdapter(Function1<? super Inmate, Unit> function1) {
        super(InmatesAdapter.INMATE_COMPARATOR.INSTANCE);
        Intrinsics.checkParameterIsNotNull(function1, "itemClickListener");
        this.itemClickListener = function1;
    }

    public InmateVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new InmateVH(this, ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_inmate_transfer, false, 2, (Object) null));
    }

    public void onBindViewHolder(InmateVH inmateVH, int i) {
        Intrinsics.checkParameterIsNotNull(inmateVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        inmateVH.bindInmate((Inmate) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/InmatesWithBalanceAdapter$InmateVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/account/InmatesWithBalanceAdapter;Landroid/view/View;)V", "bindInmate", "", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: InmatesWithBalanceAdapter.kt */
    public final class InmateVH extends RecyclerView.ViewHolder {
        final /* synthetic */ InmatesWithBalanceAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InmateVH(InmatesWithBalanceAdapter inmatesWithBalanceAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = inmatesWithBalanceAdapter;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0074  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x00e2  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bindInmate(com.forasoft.homewavvisitor.model.data.Inmate r13) {
            /*
                r12 = this;
                java.lang.String r0 = "inmate"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r0)
                android.view.View r0 = r12.itemView
                java.lang.String r1 = r13.getStatus()
                r2 = 1
                r3 = 0
                if (r1 != 0) goto L_0x0010
                goto L_0x0033
            L_0x0010:
                int r4 = r1.hashCode()
                r5 = 112785(0x1b891, float:1.58045E-40)
                if (r4 == r5) goto L_0x0029
                r5 = 98619139(0x5e0cf03, float:2.1140903E-35)
                if (r4 == r5) goto L_0x001f
                goto L_0x0033
            L_0x001f:
                java.lang.String r4 = "green"
                boolean r1 = r1.equals(r4)
                if (r1 == 0) goto L_0x0033
                r1 = 2
                goto L_0x0034
            L_0x0029:
                java.lang.String r4 = "red"
                boolean r1 = r1.equals(r4)
                if (r1 == 0) goto L_0x0033
                r1 = 1
                goto L_0x0034
            L_0x0033:
                r1 = 0
            L_0x0034:
                java.lang.String r4 = r13.getFull_name()
                if (r4 != 0) goto L_0x003d
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L_0x003d:
                java.lang.String r6 = com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt.getAsInitials(r4)
                android.content.Context r5 = r0.getContext()
                java.lang.String r4 = "context"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r4)
                r7 = 0
                r8 = 0
                r9 = 0
                r10 = 14
                r11 = 0
                com.amulyakhare.textdrawable.TextDrawable r4 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r5, r6, r7, r8, r9, r10, r11)
                int r5 = com.forasoft.homewavvisitor.R.id.iv_avatar
                android.view.View r5 = r0.findViewById(r5)
                android.widget.ImageView r5 = (android.widget.ImageView) r5
                android.graphics.drawable.Drawable r4 = (android.graphics.drawable.Drawable) r4
                r5.setImageDrawable(r4)
                int r4 = com.forasoft.homewavvisitor.R.id.iv_status
                android.view.View r4 = r0.findViewById(r4)
                android.widget.ImageView r4 = (android.widget.ImageView) r4
                java.lang.String r5 = "iv_status"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
                android.graphics.drawable.Drawable r4 = r4.getBackground()
                if (r4 == 0) goto L_0x00e2
                android.graphics.drawable.LevelListDrawable r4 = (android.graphics.drawable.LevelListDrawable) r4
                r4.setLevel(r1)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_full_name
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r4 = "tv_full_name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
                java.lang.String r4 = r13.getFull_name()
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                r1.setText(r4)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_balance
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r4 = "tv_balance"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
                kotlin.jvm.internal.StringCompanionObject r4 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
                java.util.Locale r4 = java.util.Locale.ENGLISH
                java.lang.String r5 = "Locale.ENGLISH"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
                java.lang.Object[] r5 = new java.lang.Object[r2]
                java.lang.String r6 = r13.getCredit_balance()
                if (r6 != 0) goto L_0x00b2
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L_0x00b2:
                double r6 = java.lang.Double.parseDouble(r6)
                java.lang.Double r6 = java.lang.Double.valueOf(r6)
                r5[r3] = r6
                java.lang.Object[] r2 = java.util.Arrays.copyOf(r5, r2)
                java.lang.String r3 = "%.2f"
                java.lang.String r2 = java.lang.String.format(r4, r3, r2)
                java.lang.String r3 = "java.lang.String.format(locale, format, *args)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                com.forasoft.homewavvisitor.presentation.adapter.account.InmatesWithBalanceAdapter$InmateVH$bindInmate$$inlined$with$lambda$1 r1 = new com.forasoft.homewavvisitor.presentation.adapter.account.InmatesWithBalanceAdapter$InmateVH$bindInmate$$inlined$with$lambda$1
                r1.<init>(r12, r13)
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                com.forasoft.homewavvisitor.presentation.adapter.account.InmatesWithBalanceAdapter$InmateVH$inlined$sam$i$android_view_View_OnClickListener$0 r13 = new com.forasoft.homewavvisitor.presentation.adapter.account.InmatesWithBalanceAdapter$InmateVH$inlined$sam$i$android_view_View_OnClickListener$0
                r13.<init>(r1)
                android.view.View$OnClickListener r13 = (android.view.View.OnClickListener) r13
                r0.setOnClickListener(r13)
                return
            L_0x00e2:
                kotlin.TypeCastException r13 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
                r13.<init>(r0)
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.account.InmatesWithBalanceAdapter.InmateVH.bindInmate(com.forasoft.homewavvisitor.model.data.Inmate):void");
        }
    }
}
