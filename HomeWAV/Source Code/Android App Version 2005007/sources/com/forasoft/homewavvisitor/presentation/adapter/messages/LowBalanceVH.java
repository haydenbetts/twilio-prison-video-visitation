package com.forasoft.homewavvisitor.presentation.adapter.messages;

import air.HomeWAV.R;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.Notification;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/LowBalanceVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "onClickListener", "Lkotlin/Function0;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function0;)V", "bind", "notification", "Lcom/forasoft/homewavvisitor/model/data/Notification;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: LowBalanceVH.kt */
public final class LowBalanceVH extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public final Function0<Unit> onClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LowBalanceVH(View view, Function0<Unit> function0) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(function0, "onClickListener");
        this.onClickListener = function0;
    }

    public final void bind(Notification notification) {
        Intrinsics.checkParameterIsNotNull(notification, "notification");
        View view = this.itemView;
        JSONObject jSONObject = new JSONObject(notification.getBody());
        String string = jSONObject.getString("type");
        String string2 = jSONObject.getString("inmateName");
        int i = Intrinsics.areEqual((Object) string, (Object) "below_two") ? R.string.notification_balance_below_two : R.string.notification_balance_zero;
        TextView textView = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_label);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_label");
        textView.setText(view.getResources().getString(i, new Object[]{string2}));
        ((TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_add_funds)).setOnClickListener(new LowBalanceVH$bind$$inlined$with$lambda$1(this, notification));
    }
}
