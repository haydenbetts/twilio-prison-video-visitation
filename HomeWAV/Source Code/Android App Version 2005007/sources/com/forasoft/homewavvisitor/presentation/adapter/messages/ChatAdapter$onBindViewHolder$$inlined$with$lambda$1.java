package com.forasoft.homewavvisitor.presentation.adapter.messages;

import air.HomeWAV.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke", "com/forasoft/homewavvisitor/presentation/adapter/messages/ChatAdapter$onBindViewHolder$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChatAdapter.kt */
final class ChatAdapter$onBindViewHolder$$inlined$with$lambda$1 extends Lambda implements Function1<View, Boolean> {
    final /* synthetic */ String $messageId;
    final /* synthetic */ int $position$inlined;
    final /* synthetic */ View $this_with;
    final /* synthetic */ ChatAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChatAdapter$onBindViewHolder$$inlined$with$lambda$1(View view, String str, ChatAdapter chatAdapter, int i) {
        super(1);
        this.$this_with = view;
        this.$messageId = str;
        this.this$0 = chatAdapter;
        this.$position$inlined = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((View) obj));
    }

    public final boolean invoke(View view) {
        Drawable drawable;
        View view2 = this.$this_with;
        if (this.this$0.selectedItems.contains(this.$messageId)) {
            this.this$0.selectedItems.remove(this.$messageId);
            Context context = this.$this_with.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            drawable = ContextExtensionsKt.getDrawableResource(context, R.drawable.bg_white);
        } else {
            this.this$0.selectedItems.add(this.$messageId);
            Context context2 = this.$this_with.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            drawable = ContextExtensionsKt.getDrawableResource(context2, R.drawable.bg_light_green);
        }
        view2.setBackground(drawable);
        this.this$0.onMessageLongClickListener.invoke(Integer.valueOf(this.this$0.selectedItems.size()));
        return true;
    }
}
