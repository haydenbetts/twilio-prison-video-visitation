package com.forasoft.homewavvisitor.presentation.adapter.messages;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.model.data.ChatNotification;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/ChatNotificationVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "item", "Lcom/forasoft/homewavvisitor/model/data/ChatNotification;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ChatNotificationVH.kt */
public final class ChatNotificationVH extends RecyclerView.ViewHolder {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ChatNotificationVH(View view) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
    }

    public final void bind(ChatNotification chatNotification) {
        Intrinsics.checkParameterIsNotNull(chatNotification, "item");
        TextView textView = (TextView) this.itemView.findViewById(R.id.tv_label);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_label");
        textView.setText(chatNotification.getMessage());
    }
}
