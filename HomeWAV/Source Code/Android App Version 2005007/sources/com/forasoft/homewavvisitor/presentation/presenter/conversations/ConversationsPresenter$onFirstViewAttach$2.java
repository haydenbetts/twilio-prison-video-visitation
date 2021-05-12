package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.Chat;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationsView;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004 \u0005*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "statusByIds", "", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsPresenter.kt */
final class ConversationsPresenter$onFirstViewAttach$2<T> implements Consumer<Map<String, ? extends String>> {
    final /* synthetic */ ConversationsPresenter this$0;

    ConversationsPresenter$onFirstViewAttach$2(ConversationsPresenter conversationsPresenter) {
        this.this$0 = conversationsPresenter;
    }

    public final void accept(Map<String, String> map) {
        Object value = this.this$0.subject.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "subject.value");
        Iterable<Chat> iterable = (Iterable) value;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Chat chat : iterable) {
            arrayList.add(Chat.copy$default(chat, Inmate.copy$default(chat.getInmate(), (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, map.get(chat.getInmate().getId()), (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -131073, -1, 63, (Object) null), (Message) null, 2, (Object) null));
        }
        List list = (List) arrayList;
        if (!list.isEmpty()) {
            ((ConversationsView) this.this$0.getViewState()).displayConversations(list);
        }
    }
}
