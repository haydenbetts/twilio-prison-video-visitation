package com.forasoft.homewavvisitor.presentation.adapter;

import air.HomeWAV.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.Inmate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0011\u0012B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesAdapter$InmateViewHolder;", "onMessageClickListener", "Lkotlin/Function1;", "", "onClickListener", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "INMATE_COMPARATOR", "InmateViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmatesAdapter.kt */
public final class InmatesAdapter extends ListAdapter<Inmate, InmateViewHolder> {
    private final Function1<Inmate, Unit> onClickListener;
    private final Function1<Inmate, Unit> onMessageClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InmatesAdapter(Function1<? super Inmate, Unit> function1, Function1<? super Inmate, Unit> function12) {
        super(INMATE_COMPARATOR.INSTANCE);
        Intrinsics.checkParameterIsNotNull(function1, "onMessageClickListener");
        Intrinsics.checkParameterIsNotNull(function12, "onClickListener");
        this.onMessageClickListener = function1;
        this.onClickListener = function12;
    }

    public InmateViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return InmateViewHolder.Companion.create(viewGroup, this.onMessageClickListener, this.onClickListener);
    }

    public void onBindViewHolder(InmateViewHolder inmateViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(inmateViewHolder, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        inmateViewHolder.bind((Inmate) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \f2\u00020\u0001:\u0001\fB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0006R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesAdapter$InmateViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "onMessageClickListener", "Lkotlin/Function1;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "", "onClickListener", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "bind", "inmate", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: InmatesAdapter.kt */
    public static final class InmateViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public final Function1<Inmate, Unit> onClickListener;
        /* access modifiers changed from: private */
        public final Function1<Inmate, Unit> onMessageClickListener;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesAdapter$InmateViewHolder$Companion;", "", "()V", "create", "Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesAdapter$InmateViewHolder;", "parent", "Landroid/view/ViewGroup;", "onMessageClickListener", "Lkotlin/Function1;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "", "onClickListener", "app_release"}, k = 1, mv = {1, 1, 16})
        /* compiled from: InmatesAdapter.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final InmateViewHolder create(ViewGroup viewGroup, Function1<? super Inmate, Unit> function1, Function1<? super Inmate, Unit> function12) {
                Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
                Intrinsics.checkParameterIsNotNull(function1, "onMessageClickListener");
                Intrinsics.checkParameterIsNotNull(function12, "onClickListener");
                View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_inmate, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate, "itemView");
                return new InmateViewHolder(inflate, function1, function12);
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InmateViewHolder(View view, Function1<? super Inmate, Unit> function1, Function1<? super Inmate, Unit> function12) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            Intrinsics.checkParameterIsNotNull(function1, "onMessageClickListener");
            Intrinsics.checkParameterIsNotNull(function12, "onClickListener");
            this.onMessageClickListener = function1;
            this.onClickListener = function12;
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x0099  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x011d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bind(com.forasoft.homewavvisitor.model.data.Inmate r12) {
            /*
                r11 = this;
                java.lang.String r0 = "inmate"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
                android.view.View r0 = r11.itemView
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = r12.getFirst_name()
                r3 = 0
                if (r2 == 0) goto L_0x001e
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                char r2 = kotlin.text.StringsKt.first(r2)
                java.lang.Character r2 = java.lang.Character.valueOf(r2)
                goto L_0x001f
            L_0x001e:
                r2 = r3
            L_0x001f:
                r1.append(r2)
                java.lang.String r2 = r12.getLast_name()
                if (r2 == 0) goto L_0x0032
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                char r2 = kotlin.text.StringsKt.first(r2)
                java.lang.Character r3 = java.lang.Character.valueOf(r2)
            L_0x0032:
                r1.append(r3)
                java.lang.String r5 = r1.toString()
                java.lang.String r1 = r12.getStatus()
                r2 = 1
                r3 = 0
                if (r1 != 0) goto L_0x0042
                goto L_0x0065
            L_0x0042:
                int r4 = r1.hashCode()
                r6 = 112785(0x1b891, float:1.58045E-40)
                if (r4 == r6) goto L_0x005b
                r6 = 98619139(0x5e0cf03, float:2.1140903E-35)
                if (r4 == r6) goto L_0x0051
                goto L_0x0065
            L_0x0051:
                java.lang.String r4 = "green"
                boolean r1 = r1.equals(r4)
                if (r1 == 0) goto L_0x0065
                r1 = 2
                goto L_0x0066
            L_0x005b:
                java.lang.String r4 = "red"
                boolean r1 = r1.equals(r4)
                if (r1 == 0) goto L_0x0065
                r1 = 1
                goto L_0x0066
            L_0x0065:
                r1 = 0
            L_0x0066:
                android.content.Context r4 = r0.getContext()
                java.lang.String r6 = "context"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r6)
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 14
                r10 = 0
                com.amulyakhare.textdrawable.TextDrawable r4 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r4, r5, r6, r7, r8, r9, r10)
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
                if (r4 == 0) goto L_0x011d
                android.graphics.drawable.LevelListDrawable r4 = (android.graphics.drawable.LevelListDrawable) r4
                r4.setLevel(r1)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_full_name
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r4 = "tv_full_name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
                java.lang.String r4 = r12.getFull_name()
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
                java.lang.String r6 = r12.getCredit_balance()
                if (r6 == 0) goto L_0x00dd
                double r6 = java.lang.Double.parseDouble(r6)
                java.lang.Double r6 = java.lang.Double.valueOf(r6)
                goto L_0x00e2
            L_0x00dd:
                r6 = 0
                java.lang.Float r6 = java.lang.Float.valueOf(r6)
            L_0x00e2:
                r5[r3] = r6
                java.lang.Object[] r2 = java.util.Arrays.copyOf(r5, r2)
                java.lang.String r3 = "%.2f"
                java.lang.String r2 = java.lang.String.format(r4, r3, r2)
                java.lang.String r3 = "java.lang.String.format(locale, format, *args)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_link
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter$InmateViewHolder$bind$$inlined$with$lambda$1 r2 = new com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter$InmateViewHolder$bind$$inlined$with$lambda$1
                r2.<init>(r11, r12)
                android.view.View$OnClickListener r2 = (android.view.View.OnClickListener) r2
                r1.setOnClickListener(r2)
                int r1 = com.forasoft.homewavvisitor.R.id.root
                android.view.View r0 = r0.findViewById(r1)
                androidx.constraintlayout.widget.ConstraintLayout r0 = (androidx.constraintlayout.widget.ConstraintLayout) r0
                com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter$InmateViewHolder$bind$$inlined$with$lambda$2 r1 = new com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter$InmateViewHolder$bind$$inlined$with$lambda$2
                r1.<init>(r11, r12)
                android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
                r0.setOnClickListener(r1)
                return
            L_0x011d:
                kotlin.TypeCastException r12 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
                r12.<init>(r0)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter.InmateViewHolder.bind(com.forasoft.homewavvisitor.model.data.Inmate):void");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/InmatesAdapter$INMATE_COMPARATOR;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: InmatesAdapter.kt */
    public static final class INMATE_COMPARATOR extends DiffUtil.ItemCallback<Inmate> {
        public static final INMATE_COMPARATOR INSTANCE = new INMATE_COMPARATOR();

        private INMATE_COMPARATOR() {
        }

        public boolean areItemsTheSame(Inmate inmate, Inmate inmate2) {
            Intrinsics.checkParameterIsNotNull(inmate, "oldItem");
            Intrinsics.checkParameterIsNotNull(inmate2, "newItem");
            return Intrinsics.areEqual((Object) inmate.getId(), (Object) inmate2.getId());
        }

        public boolean areContentsTheSame(Inmate inmate, Inmate inmate2) {
            Intrinsics.checkParameterIsNotNull(inmate, "oldItem");
            Intrinsics.checkParameterIsNotNull(inmate2, "newItem");
            return Intrinsics.areEqual((Object) inmate, (Object) inmate2);
        }
    }
}
