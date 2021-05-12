package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.Chat;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationsView;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "kotlin.jvm.PlatformType", "accept", "com/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationsPresenter$refreshChats$1$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsPresenter.kt */
final class ConversationsPresenter$refreshChats$1$$special$$inlined$forEach$lambda$1<T> implements Consumer<Chat> {
    final /* synthetic */ ConversationsPresenter$refreshChats$1 this$0;

    ConversationsPresenter$refreshChats$1$$special$$inlined$forEach$lambda$1(ConversationsPresenter$refreshChats$1 conversationsPresenter$refreshChats$1) {
        this.this$0 = conversationsPresenter$refreshChats$1;
    }

    public final void accept(Chat chat) {
        Object clone = ((ArrayList) this.this$0.this$0.subject.getValue()).clone();
        if (clone != null) {
            ArrayList arrayList = (ArrayList) clone;
            boolean z = false;
            int i = 0;
            for (Object next : arrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (Intrinsics.areEqual((Object) ((Chat) next).getInmate().getId(), (Object) chat.getInmate().getId())) {
                    arrayList.set(i, chat);
                    z = true;
                }
                i = i2;
            }
            if (!z) {
                arrayList.add(chat);
            }
            this.this$0.this$0.subject.onNext(arrayList);
            ((ConversationsView) this.this$0.this$0.getViewState()).displayConversations(arrayList);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.forasoft.homewavvisitor.model.data.Chat> /* = java.util.ArrayList<com.forasoft.homewavvisitor.model.data.Chat> */");
    }
}
