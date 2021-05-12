package com.forasoft.homewavvisitor.presentation.adapter.visits;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/visits/VisitVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "onClickListener", "Lkotlin/Function1;", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "bindVisit", "visit", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitVH.kt */
public final class VisitVH extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public final Function1<ScheduledVisit, Unit> onClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VisitVH(View view, Function1<? super ScheduledVisit, Unit> function1) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(function1, "onClickListener");
        this.onClickListener = function1;
    }

    public final void bindVisit(ScheduledVisit scheduledVisit) {
        Intrinsics.checkParameterIsNotNull(scheduledVisit, "visit");
        View view = this.itemView;
        String asInitials = StringExtensionsKt.getAsInitials(scheduledVisit.getInmate());
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        ((ImageView) view.findViewById(R.id.iv_avatar)).setImageDrawable(ContextExtensionsKt.createTextDrawable$default(context, asInitials, air.HomeWAV.R.dimen.initials_size_small, 0, 0, 12, (Object) null));
        TextView textView = (TextView) view.findViewById(R.id.tv_full_name);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_full_name");
        textView.setText(scheduledVisit.getInmate());
        TextView textView2 = (TextView) view.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_date");
        textView2.setText(DateExtensionsKt.getAsDetailedDateTime(new Date(scheduledVisit.getTimestamp() * ((long) 1000))));
        view.setOnClickListener(new VisitVH$bindVisit$$inlined$with$lambda$1(this, scheduledVisit));
    }
}
