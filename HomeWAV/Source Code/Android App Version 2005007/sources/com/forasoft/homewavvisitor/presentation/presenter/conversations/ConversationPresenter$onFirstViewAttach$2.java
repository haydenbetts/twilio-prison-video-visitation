package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.State;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/State;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$onFirstViewAttach$2<T> implements Consumer<State> {
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$onFirstViewAttach$2(ConversationPresenter conversationPresenter) {
        this.this$0 = conversationPresenter;
    }

    public final void accept(State state) {
        if (state != null) {
            if (this.this$0.lastMessagesUnreadCount != Integer.parseInt(state.getUnreadMessages())) {
                this.this$0.updateMessages();
                this.this$0.lastMessagesUnreadCount = Integer.parseInt(state.getUnreadMessages());
            }
            if (this.this$0.inmate.hasValue()) {
                String credit_balance = ((Inmate) this.this$0.inmate.getValue()).getCredit_balance();
                String str = state.getBalances().get(this.this$0.inmateId);
                if (!Intrinsics.areEqual((Object) str, (Object) credit_balance)) {
                    this.this$0.inmate.onNext(Inmate.copy$default((Inmate) this.this$0.inmate.getValue(), (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, str, (String) null, (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -65537, -1, 63, (Object) null));
                }
            }
        }
    }
}
