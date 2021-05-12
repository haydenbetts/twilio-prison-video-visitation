package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.pusher.PusherObserver;
import com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationPresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationPresenter$pusherListener$1", "Lcom/forasoft/homewavvisitor/model/pusher/PusherObserver;", "onEvent", "", "event", "Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
public final class ConversationPresenter$pusherListener$1 implements PusherObserver {
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$pusherListener$1(ConversationPresenter conversationPresenter) {
        this.this$0 = conversationPresenter;
    }

    public void onEvent(PusherEvent pusherEvent) {
        Intrinsics.checkParameterIsNotNull(pusherEvent, "event");
        if (pusherEvent.getType() == PusherEvent.Type.new_message) {
            MessageDao access$getMessageDao$p = this.this$0.messageDao;
            Object value = pusherEvent.getValue();
            if (value != null) {
                access$getMessageDao$p.saveMessage((Message) value);
                this.this$0.showMessages();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.model.data.Message");
        } else if (Intrinsics.areEqual(pusherEvent.getValue(), (Object) this.this$0.inmateId)) {
            int i = ConversationPresenter.WhenMappings.$EnumSwitchMapping$0[pusherEvent.getType().ordinal()];
            if (i == 1) {
                ((ConversationView) this.this$0.getViewState()).showWarningMessage();
            } else if (i == 2) {
                ((ConversationView) this.this$0.getViewState()).hideWarningMessage();
            }
        }
    }
}
