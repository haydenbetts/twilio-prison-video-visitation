package com.forasoft.homewavvisitor.ui.fragment.conversations;

import com.forasoft.homewavvisitor.model.data.Chat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "invoke", "com/forasoft/homewavvisitor/ui/fragment/conversations/ConversationListFragment$setupList$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationListFragment.kt */
final class ConversationListFragment$setupList$$inlined$apply$lambda$1 extends Lambda implements Function1<Chat, Unit> {
    final /* synthetic */ ConversationListFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ConversationListFragment$setupList$$inlined$apply$lambda$1(ConversationListFragment conversationListFragment) {
        super(1);
        this.this$0 = conversationListFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Chat) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Chat chat) {
        Intrinsics.checkParameterIsNotNull(chat, "it");
        this.this$0.getPresenter().onConversationClick(chat);
    }
}
