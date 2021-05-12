package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationsView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "response", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsPresenter.kt */
final class ConversationsPresenter$onDeleteChatClicked$1<T> implements Consumer<ApiResponse<? extends List<? extends String>>> {
    final /* synthetic */ ConversationsPresenter this$0;

    ConversationsPresenter$onDeleteChatClicked$1(ConversationsPresenter conversationsPresenter) {
        this.this$0 = conversationsPresenter;
    }

    public final void accept(ApiResponse<? extends List<String>> apiResponse) {
        List list = (List) apiResponse.getBody();
        if (list != null) {
            this.this$0.deletedMessages.addAll(list);
        }
        ((ConversationsView) this.this$0.getViewState()).showSuccessfulDeletionMessage();
    }
}
