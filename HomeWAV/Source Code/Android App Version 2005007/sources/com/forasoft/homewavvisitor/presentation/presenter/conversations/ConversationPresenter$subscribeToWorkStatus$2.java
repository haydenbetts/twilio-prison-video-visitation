package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$subscribeToWorkStatus$2<T> implements Consumer<Throwable> {
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$subscribeToWorkStatus$2(ConversationPresenter conversationPresenter) {
        this.this$0 = conversationPresenter;
    }

    public final void accept(Throwable th) {
        String message = th.getMessage();
        if (message != null) {
            ((ConversationView) this.this$0.getViewState()).showMessage(message);
        } else {
            ((ConversationView) this.this$0.getViewState()).showMessage((int) R.string.label_message_not_send);
        }
        this.this$0.isSendingMessage = false;
    }
}
