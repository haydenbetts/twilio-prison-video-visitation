package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import air.HomeWAV.R;
import androidx.work.WorkInfo;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/work/WorkInfo;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$subscribeToWorkStatus$1<T> implements Consumer<WorkInfo> {
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$subscribeToWorkStatus$1(ConversationPresenter conversationPresenter) {
        this.this$0 = conversationPresenter;
    }

    public final void accept(WorkInfo workInfo) {
        ((ConversationView) this.this$0.getViewState()).showMessage((int) R.string.label_message_send);
        ((ConversationView) this.this$0.getViewState()).clearInputs();
        this.this$0.onCloseClicked();
        this.this$0.sendEvent();
        this.this$0.updateMessages();
        this.this$0.isSendingMessage = false;
    }
}
