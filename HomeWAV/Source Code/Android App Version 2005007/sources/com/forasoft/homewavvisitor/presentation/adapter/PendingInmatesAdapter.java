package com.forasoft.homewavvisitor.presentation.adapter;

import air.HomeWAV.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0011B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\u00062\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/PendingInmatesAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "Lcom/forasoft/homewavvisitor/presentation/adapter/PendingInmatesAdapter$InmateVH;", "onInmateClickListener", "Lkotlin/Function1;", "", "onAddFundsClickListener", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "InmateVH", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PendingInmatesAdapter.kt */
public final class PendingInmatesAdapter extends ListAdapter<Inmate, InmateVH> {
    /* access modifiers changed from: private */
    public final Function1<Inmate, Unit> onAddFundsClickListener;
    /* access modifiers changed from: private */
    public final Function1<Inmate, Unit> onInmateClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PendingInmatesAdapter(Function1<? super Inmate, Unit> function1, Function1<? super Inmate, Unit> function12) {
        super(InmatesAdapter.INMATE_COMPARATOR.INSTANCE);
        Intrinsics.checkParameterIsNotNull(function1, "onInmateClickListener");
        Intrinsics.checkParameterIsNotNull(function12, "onAddFundsClickListener");
        this.onInmateClickListener = function1;
        this.onAddFundsClickListener = function12;
    }

    public InmateVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pending_connection, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "itemView");
        return new InmateVH(this, inflate);
    }

    public void onBindViewHolder(InmateVH inmateVH, int i) {
        Intrinsics.checkParameterIsNotNull(inmateVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        inmateVH.bind((Inmate) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/PendingInmatesAdapter$InmateVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/PendingInmatesAdapter;Landroid/view/View;)V", "bind", "", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: PendingInmatesAdapter.kt */
    public final class InmateVH extends RecyclerView.ViewHolder {
        final /* synthetic */ PendingInmatesAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InmateVH(PendingInmatesAdapter pendingInmatesAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = pendingInmatesAdapter;
        }

        public final void bind(Inmate inmate) {
            Intrinsics.checkParameterIsNotNull(inmate, "inmate");
            View view = this.itemView;
            String full_name = inmate.getFull_name();
            if (full_name == null) {
                Intrinsics.throwNpe();
            }
            String asInitials = StringExtensionsKt.getAsInitials(full_name);
            Context context = view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            ((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_avatar)).setImageDrawable(ContextExtensionsKt.createTextDrawable$default(context, asInitials, 0, 0, 0, 14, (Object) null));
            TextView textView = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_full_name);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_full_name");
            textView.setText(inmate.getFull_name());
            TextView textView2 = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_balance);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_balance");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ENGLISH");
            Object[] objArr = new Object[1];
            String credit_balance = inmate.getCredit_balance();
            if (credit_balance == null) {
                Intrinsics.throwNpe();
            }
            objArr[0] = Double.valueOf(Double.parseDouble(credit_balance));
            String format = String.format(locale, "%.2f", Arrays.copyOf(objArr, 1));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
            textView2.setText(format);
            TextView textView3 = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_add_funds);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tv_add_funds");
            textView3.setOnClickListener(new PendingInmatesAdapter$InmateVH$inlined$sam$i$android_view_View_OnClickListener$0(new PendingInmatesAdapter$InmateVH$bind$$inlined$with$lambda$1(this, inmate)));
            ImageView imageView = (ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_avatar);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "iv_avatar");
            imageView.setOnClickListener(new PendingInmatesAdapter$InmateVH$inlined$sam$i$android_view_View_OnClickListener$0(new PendingInmatesAdapter$InmateVH$bind$$inlined$with$lambda$2(this, inmate)));
        }
    }
}
