package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.Message;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "com/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationPresenter$onDestroy$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$onDestroy$$inlined$let$lambda$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $visitorId;
    final /* synthetic */ ConversationPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ConversationPresenter$onDestroy$$inlined$let$lambda$1(String str, ConversationPresenter conversationPresenter) {
        super(0);
        this.$visitorId = str;
        this.this$0 = conversationPresenter;
    }

    public final void invoke() {
        this.this$0.messageDao.getAllUnread(this.this$0.inmateId, this.$visitorId).subscribe(new Consumer<List<? extends Message>>(this) {
            final /* synthetic */ ConversationPresenter$onDestroy$$inlined$let$lambda$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(List<Message> list) {
                Intrinsics.checkExpressionValueIsNotNull(list, "it");
                Iterable<Message> iterable = list;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Message id : iterable) {
                    arrayList.add(id.getId());
                }
                List list2 = (List) arrayList;
                if (!list2.isEmpty()) {
                    this.this$0.this$0.api.readMessages(list2).subscribe(ConversationPresenter$onDestroy$1$1$1$1.INSTANCE, ConversationPresenter$onDestroy$1$1$1$2.INSTANCE);
                }
                this.this$0.this$0.messageDao.deleteAll();
            }
        }, ConversationPresenter$onDestroy$1$1$2.INSTANCE);
    }
}
