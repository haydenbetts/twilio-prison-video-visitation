package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$onDeleteClicked$1<T> implements Consumer<ApiResponse<? extends List<? extends String>>> {
    final /* synthetic */ List $messages;
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$onDeleteClicked$1(ConversationPresenter conversationPresenter, List list) {
        this.this$0 = conversationPresenter;
        this.$messages = list;
    }

    public final void accept(ApiResponse<? extends List<String>> apiResponse) {
        ((ConversationView) this.this$0.getViewState()).showMessage((int) R.string.messages_deleted);
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new Function0<Unit>(this) {
            final /* synthetic */ ConversationPresenter$onDeleteClicked$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                this.this$0.this$0.messageDao.deleteMessages(this.this$0.$messages);
            }
        }, 31, (Object) null);
    }
}
