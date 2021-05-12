package com.forasoft.homewavvisitor.presentation.adapter;

import android.view.View;
import com.forasoft.homewavvisitor.model.data.Chat;
import com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke", "com/forasoft/homewavvisitor/presentation/adapter/ConversationsAdapter$ConversationVH$bind$1$2"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsAdapter.kt */
final class ConversationsAdapter$ConversationVH$bind$$inlined$with$lambda$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ Chat $chat$inlined;
    final /* synthetic */ ConversationsAdapter.ConversationVH this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ConversationsAdapter$ConversationVH$bind$$inlined$with$lambda$1(ConversationsAdapter.ConversationVH conversationVH, Chat chat) {
        super(1);
        this.this$0 = conversationVH;
        this.$chat$inlined = chat;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        this.this$0.this$0.onItemClickListener.invoke(this.$chat$inlined);
    }
}
