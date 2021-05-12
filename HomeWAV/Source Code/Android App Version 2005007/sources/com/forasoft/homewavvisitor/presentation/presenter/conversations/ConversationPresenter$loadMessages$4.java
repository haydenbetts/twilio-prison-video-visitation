package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.server.response.Response;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "", "Lcom/forasoft/homewavvisitor/model/data/Message;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$loadMessages$4<T> implements Consumer<Response<? extends List<? extends Message>>> {
    final /* synthetic */ boolean $isFirstTime;
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$loadMessages$4(ConversationPresenter conversationPresenter, boolean z) {
        this.this$0 = conversationPresenter;
        this.$isFirstTime = z;
    }

    public final void accept(Response<? extends List<Message>> response) {
        ConversationPresenter conversationPresenter = this.this$0;
        Integer access$getCurrentPage$p = conversationPresenter.currentPage;
        conversationPresenter.currentPage = Integer.valueOf((access$getCurrentPage$p != null ? access$getCurrentPage$p.intValue() : 1) + 1);
        if (response.getOk()) {
            List list = (List) response.getBody();
            if (list == null || list.isEmpty()) {
                this.this$0.isLastPage = true;
            }
            ConversationPresenter conversationPresenter2 = this.this$0;
            Object body = response.getBody();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            conversationPresenter2.readMessages((List) body);
            this.this$0.messageDao.saveMessages((List) response.getBody());
            if (this.$isFirstTime) {
                this.this$0.showMessages();
                return;
            }
            return;
        }
        this.this$0.handleError(new Throwable(response.getError()));
    }
}
